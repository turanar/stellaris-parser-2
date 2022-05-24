package net.turanar.stellaris;

import net.turanar.stellaris.antlr.StellarisParser;
import net.turanar.stellaris.antlr.StellarisParserFactory;
import net.turanar.stellaris.util.CustomYaml;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Global {
    public static Map<String,String> GLOBAL_VARIABLES = new HashMap<>();
    public static Map<String,String> GLOBAL_STRINGS = new HashMap<>();
    public static Map<String, StellarisParser.PairContext> GLOBAL_TRIGGERS = new HashMap<>();
    public static String LS = "    •   ";

    public static Set<Map.Entry<String,String>> loadYml(Resource resource, String lang) {
        try {
            Map<String,Map<String,String>> results = new CustomYaml().load(resource.getInputStream());
            if(results.get(lang) == null) return new HashSet<>();
            return results.get(lang).entrySet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static StellarisParser.FileContext load(Resource resource) {
        try {
            return StellarisParserFactory.getParser(resource.getInputStream()).file();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Path> parse(String path, String filetype) throws IOException {
        PathMatcher m = FileSystems.getDefault().getPathMatcher("glob:**." + filetype);
        return Files.list(Paths.get(path)).filter(m::matches).collect(Collectors.toList());
    }

    public static String i18n(String key) {
        String retval = GLOBAL_STRINGS.get(key.toLowerCase().replaceAll("\"",""));
        if(retval == null) return key;
        if(retval.contains("$")) {
            retval = applyTemplate(retval);
        }
        return retval;
    }

    public static String i18n(StellarisParser.PairContext pair) {
        return i18n(gs(pair));
    }

    public static Boolean gbool(StellarisParser.PairContext pair) {
        return gs(pair).equals("yes");
    }

    public static String op(StellarisParser.PairContext p) {
        String operator = p.SPECIFIER().getText();
        if(">".equals(operator)) return "greater than";
        if("<".equals(operator)) return "lower than";
        if(">=".equals(operator)) return "greater than or equal to";
        if("<=".equals(operator)) return "less than or equal to";
        return "equal to";
    }

    public static String gs(StellarisParser.PairContext pair) {
        return gs(pair.value());
    }

    public static String gs(StellarisParser.ValueContext value) {
        if(value.BAREWORD() != null)
            return value.BAREWORD().getText().replaceAll("^\"|\"$", "");
        if(value.STRING() != null)
            return value.STRING().getText().replaceAll("\"","");
        if(value.VARIABLE() !=null)
            return GLOBAL_VARIABLES.get(value.VARIABLE().getText());
        if(value.BOOLEAN() != null)
            return value.BOOLEAN().getText();
        if(value.NUMBER() != null)
            return value.NUMBER().getText();
        return null;
    }

    public static String variable(String key) {
        return GLOBAL_VARIABLES.get(key);
    }

    public static String applyTemplate(String retval) {
        Pattern p = Pattern.compile("\\$([a-zA-z0-9_]+)\\$");

        int i = 0;
        while(retval.contains("$") && i < 2) {
            Matcher m2 = p.matcher(retval);
            if(m2.find()) {
                retval = m2.replaceFirst(i18n(m2.group(1)));
                m2.reset();
            }
            i++;
        }
        return retval;
    }

    public static String f(String f, String... objects) {
        return String.format(f, objects);
    }

}
