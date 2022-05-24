package net.turanar.stellaris.parser;

import net.turanar.stellaris.Global;
import net.turanar.stellaris.antlr.StellarisParser;
import net.turanar.stellaris.domain.Area;
import net.turanar.stellaris.domain.GameObject;
import net.turanar.stellaris.domain.Technology;
import net.turanar.stellaris.visitor.TechnologyVisitor;
import net.turanar.stellaris.visitor.UnlockVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class VanillaConfigParser extends AbstractConfigParser {
    @Autowired
    TechnologyVisitor techVisitor;
    @Autowired
    UnlockVisitor unlockVisitor;
    @Autowired
    Map<String, Technology> technologies;
    @Autowired
    ResourceLoader resourceLoader;

    ArrayList<Technology> anomalies = new ArrayList<>();

    @Value("file:${stellaris.path}/common/technology/*.txt")
    private Resource[] f_technologies;
    @Value("file:${stellaris.path}")
    private String path;

    private void parseTechnologies() {
        technologies.putAll(
            Arrays.stream(f_technologies)
            .map(Global::load)
            .map(StellarisParser.FileContext::pair)
            .flatMap(List::stream)
            .map(techVisitor::visitPair)
            .collect(Collectors.toMap(t->t.key, t->t))
        );
    }

    public void parseGamesObjects() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(resourceLoader);
        for(GameObject type : GameObject.values()) {
            try {
                Resource[] resources = resolver.getResources(this.path + "/" + type.folder + "/*.txt");
                Arrays.stream(resources).filter(r -> !r.getFilename().startsWith("README"))
                .map(Global::load).forEach(f -> unlockVisitor.visitFile(type,f));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void anomalies() throws IOException {
        technologies.values().stream().filter(t -> t.is_event).forEach(t -> {
            anomalies.add(t);
            if(t.children.size() > 0) t.children.forEach(c -> {c.is_event = true; anomalies.add(c);});
        });

        anomalies.sort((o1, o2) -> {
            if(o1 == o2) return 0;
            if(o1.equals(o2)) return 0;
            return o1.key.compareTo(o2.key);
        });
    }

    public Technology areaTech(Area area) {
        Technology t = new Technology();
        t.tier = 0; t.name = area.name(); t.area = area;
        return t;
    }

    public Technology gather(Area area) {
        Technology retval = areaTech(area);
        retval.children.addAll(
                technologies.values().stream()
                        .filter(t -> t.area.equals(area) && t.prerequisites.size() < 1 && !t.is_event)
                        .collect(Collectors.toList())
        );
        return retval;
    }

    @Override
    public void parse() throws IOException {
        parseTechnologies();
        parseGamesObjects();
        prepare(technologies);
        anomalies();

        FileOutputStream fos = new FileOutputStream("output/anomalies.json");
        fos.write(gson.toJson(anomalies).getBytes());
        fos.close();
        Technology rootP = new Technology(gather(Area.physics));
        writeJson("physics.json", rootP);
        Technology rootS = new Technology(gather(Area.society));
        writeJson("society.json", rootS);
        Technology rootE = new Technology(gather(Area.engineering));
        writeJson("engineering.json", rootE);
    }
}
