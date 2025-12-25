package aoc;

import aoc.core.Generator;
import aoc.core.Runner;
import aoc.util.InputFetcher;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length == 0) {
            System.out.println("Usage: ");
            System.out.println("  java Main desc <day>");
            System.out.println("  java Main gen <day>");
            System.out.println("  java Main run <day>");
        }

        String cmd = args[0];
        int day = Integer.parseInt(args[1]);

        switch (cmd) {
            case "gen" -> Generator.create(day);
            case "run" -> Runner.run(day);
            case "desc" -> {
                System.out.println("Opening description for Day " + day + "...");
                InputFetcher.openDescription(day);
            }
            default -> throw new IllegalArgumentException("Unknown cmd: " + cmd);
        }
    }
}
