package lab8;

public class Main {

    private static final long ITERATIONS = 1_000_000_000L;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <threads>");
            return;
        }

        int threads = Integer.parseInt(args[0]);

        try {
            long start = System.nanoTime();
            double pi = ParallelMonteCarloPi.calculatePi(threads, ITERATIONS);
            long end = System.nanoTime();

            double timeMs = (end - start) / 1_000_000.0;

            System.out.printf("PI is %.6f%n", pi);
            System.out.printf("THREADS %d%n", threads);
            System.out.printf("ITERATIONS %,d%n", ITERATIONS);
            System.out.printf("TIME %.2fms%n", timeMs);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
