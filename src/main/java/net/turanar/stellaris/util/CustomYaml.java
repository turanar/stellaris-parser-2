package net.turanar.stellaris.util;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class CustomYaml extends Yaml {
    private String clean(String line) {
        return line.replaceFirst("^([^:]*):\\d+\\s","$1: ")
               .replaceAll("\\\"(.*)\\\"","{quote}$1{quote}")
               .replaceAll("\"","\\\\\"")
               .replaceAll("\\{quote\\}","\"");
    }

    public <T> T load(Path path) throws RuntimeException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(bos);

        try {
            Files.lines(path).map(this::clean).forEach(pw::println);
            pw.flush();
            return load(bos.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T load(InputStream is) throws RuntimeException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(bos);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        reader.lines().map(this::clean).forEach(pw::println);
        pw.flush();
        return load(bos.toString());
    }
}
