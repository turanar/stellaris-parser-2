package net.turanar.stellaris;

import net.turanar.stellaris.antlr.StellarisParser;
import net.turanar.stellaris.antlr.StellarisParserFactory;
import net.turanar.stellaris.domain.Technology;
import net.turanar.stellaris.util.CustomYaml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class Config {
    @Autowired
    private StellarisParserFactory factory;

    @Value("file:${stellaris.path}/localisation/${stellaris.lang}/*.yml")
    private Resource[] f_localisation;
    @Value("file:${stellaris.path}/common/{folder:(scripted_variables|technology)}/*.txt")
    private Resource[] f_variables;
    @Value("file:${stellaris.path}/common/scripted_triggers/*.txt")
    private Resource[] f_triggers;
    @Value("l_${stellaris.lang}")
    private String lang;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Global.GLOBAL_STRINGS.putAll(
            Arrays.stream(f_localisation)
            .map(f -> Global.loadYml(f, this.lang))
            .flatMap(Set::stream)
            .collect(Collectors.toMap(e->String.valueOf(e.getKey()).toLowerCase(), Map.Entry::getValue, (a1,a2) -> a2))
        );

        Global.GLOBAL_VARIABLES.putAll(
            Arrays.stream(f_variables)
            .map(Global::load)
            .map(StellarisParser.FileContext::var)
            .flatMap(List::stream)
            .collect(Collectors.toMap(v->v.VARIABLE().getText(), v->v.NUMBER().getText(), (a1,a2) -> a2))
        );

        Global.GLOBAL_TRIGGERS.putAll(
            Arrays.stream(f_triggers)
            .map(Global::load)
            .map(StellarisParser.FileContext::pair)
            .flatMap(List::stream)
            .collect(Collectors.toMap(p -> p.key(), p -> p, (a1,a2) -> a2))
        );
    }

    @Bean("technologies")
    public Map<String, Technology> technologies() {
        return new HashMap<>();
    }
}
