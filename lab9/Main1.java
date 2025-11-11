package lab9;

import java.util.*;
import java.util.concurrent.*;

public class Main1 {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        int accountCount = 100;
        int threads = 2000;
        Random random = new Random();

        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < accountCount; i++) {
            accounts.add(new Account(i, random.nextInt(1000) + 100)); // 100-1100
        }

        int initialTotal = accounts.stream()
                .mapToInt(Account::getBalance)  // перетворює список у потік балансів
                .sum();
        System.out.println("Initial total: " + initialTotal);

        ExecutorService executor = Executors.newFixedThreadPool(20);


        /*
        Створюється 20 реальних потоків;
        Коли подається 2000 завдань через executor.submit(), вони потрапляють у чергу;
        Як тільки якийсь потік закінчує попереднє завдання — береться наступне;
        Це продовжується, доки всі 2000 завдань не виконано */

        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                Account from = accounts.get(random.nextInt(accountCount));
                Account to = accounts.get(random.nextInt(accountCount));
                int amount = random.nextInt(100);

                try {
                    bank.transfer(from, to, amount);
                } catch (IllegalArgumentException ignored) {}
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES); // чекаємо до 1 хвилини, поки всі потоки закінчать

        int finalTotal = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Final total: " + finalTotal);

        System.out.println((initialTotal == finalTotal ?
                "Total is consistent!" : "Mismatch detected!"));
    }
}
