package aoc.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Generator {
    private static final String TEMPLATE = """
            package aoc.days;
            import aoc.core.Day;
            import aoc.util.InputFetcher;
            public class Day%02d implements Day {
                private final String input;
            
                public Day%02d() throws Exception {
                    input = InputFetcher.fetch(%d);
                }
            
                public Object part1() {
                    return 0;
                }
            
                public Object part2() {
                    return 0;
                }
            }
            """;

    public static void create(int day) throws Exception {
        Path p = Paths.get("src/aoc/days/Day%02d.java".formatted(day));
        if(Files.exists(p)) return;
        Files.createDirectories(p.getParent());
        Files.writeString(p, TEMPLATE.formatted(day, day, day));
        System.out.printf("Created Day%02d%n", day);
    }
}
