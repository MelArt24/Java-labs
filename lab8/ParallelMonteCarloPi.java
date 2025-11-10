package lab8;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ParallelMonteCarloPi {

    public static double calculatePi(int threads, long iterations) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        AtomicLong insideCircle = new AtomicLong(0);
        long iterationsPerThread = iterations / threads;
        CountDownLatch latch = new CountDownLatch(threads); // синхронізатор, який дозволяє головному потоку чекати, поки інші потоки завершаться

        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {                         // створює і запускає новий потік
                ThreadLocalRandom random = ThreadLocalRandom.current();
                long inside = 0;
                for (long j = 0; j < iterationsPerThread; j++) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();
                    if (x * x + y * y <= 1.0)
                        inside++;
                }
                insideCircle.addAndGet(inside);
                latch.countDown();
            });
        }

        latch.await();                                      // цей рядок блокує головний потік, доки всі інші не виконають countDown()
        executor.shutdown();

        return 4.0 * insideCircle.get() / iterations;
    }
}
