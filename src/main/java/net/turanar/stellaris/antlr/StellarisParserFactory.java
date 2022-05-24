package net.turanar.stellaris.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Component
public class StellarisParserFactory {
    public static StellarisParser getParser(Path p) {
        try {
            String content = Files.readAllLines(p).stream().collect(Collectors.joining("\n"));
            CharStream stream = CharStreams.fromPath(p);
            StellarisLexer lex = new StellarisLexer(stream);
            StellarisParser parser = new StellarisParser(new CommonTokenStream(lex));
            return parser;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static StellarisParser getParser(InputStream is) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            IOUtils.copy(is, bos);
            String result = new String(bos.toByteArray());
            result = result.replaceAll("optimize_memory","");
            CharStream stream = CharStreams.fromString(result);
            StellarisLexer lex = new StellarisLexer(stream);
            StellarisParser parser = new StellarisParser(new CommonTokenStream(lex));
            parser.addErrorListener(new ThrowingErrorListener());
            return parser;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
