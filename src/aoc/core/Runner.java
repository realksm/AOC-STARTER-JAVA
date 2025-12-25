package aoc.core;

public final class Runner {
    public static void run(int day) throws Exception {
        String clsName = "aoc.days.Day%02d".formatted(day);
        Day dayObj = (Day) Class.forName(clsName).getDeclaredConstructor().newInstance();

        Benchmark.total(() -> {
            Benchmark.part("Part 1", dayObj::part1);
            Benchmark.part("Part 2", dayObj::part2);
        });
    }
}
