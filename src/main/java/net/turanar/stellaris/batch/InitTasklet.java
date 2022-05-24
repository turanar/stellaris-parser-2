package net.turanar.stellaris.batch;

import net.turanar.stellaris.antlr.StellarisParser.FileContext;
import net.turanar.stellaris.antlr.StellarisParserFactory;
import net.turanar.stellaris.util.CustomYaml;
import net.turanar.stellaris.Global;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Map.*;

@Component("init.tasklet")
public class InitTasklet implements Tasklet {
    @Autowired
    private StellarisParserFactory factory;

    @Value("file:files/localisation/english/*.yml")
    private Resource[] f_localisation;
    @Value("file:files/common/{folder:(scripted_variables|technology)}/*.txt")
    private Resource[] f_variables;
    @Value("file:files/common/scripted_triggers/*.txt")
    private Resource[] f_triggers;

    private Set<Entry<String,String>> loadYml(Resource resource) {
        try {
            Map<String,Map<String,String>> results = new CustomYaml().load(resource.getInputStream());
            return results.get("l_english").entrySet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FileContext load(Resource resource) {
        try {
            return factory.getParser(resource.getInputStream()).file();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        Global.GLOBAL_STRINGS.putAll(
            Arrays.stream(f_localisation)
            .map(this::loadYml)
            .flatMap(Set::stream)
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue))
        );

        Global.GLOBAL_VARIABLES.putAll(
            Arrays.stream(f_variables)
            .map(this::load)
            .map(FileContext::var)
            .flatMap(List::stream)
            .collect(Collectors.toMap(v->v.VARIABLE().getText(), v->v.NUMBER().getText(), (a1,a2) -> a2))
        );

        Global.GLOBAL_TRIGGERS.putAll(
            Arrays.stream(f_triggers)
            .map(this::load)
            .map(FileContext::pair)
            .flatMap(List::stream)
            .collect(Collectors.toMap(p -> p.key(), p -> p, (a1,a2) -> a2))
        );

        return RepeatStatus.FINISHED;
    }
}
