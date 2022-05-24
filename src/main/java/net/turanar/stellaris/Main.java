package net.turanar.stellaris;

import net.turanar.stellaris.antlr.StellarisParser.FileContext;
import net.turanar.stellaris.parser.VanillaConfigParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = "net.turanar.stellaris", exclude = DataSourceAutoConfiguration.class)
@PropertySource("file:application.properties")
public class Main implements CommandLineRunner {
    @Autowired
    private VanillaConfigParser parser;

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        parser.parse();
    }
}
