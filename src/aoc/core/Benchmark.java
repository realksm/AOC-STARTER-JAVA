package aoc.core;

public class Benchmark {
    public static <T> T part(String name, Supplier<T> task) {
        long start = System.nanoTime();
        T result = task.get();
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.println(name + " : " + result + " (" + ms + " ms)");
        return result;
    }

    public static void total(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Total : " + ms + " ms");
    }

    public interface Supplier<T> { T get(); }
}
