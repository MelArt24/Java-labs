package lab9;

import java.util.Random;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        RingBuffer<String> buffer1 = new RingBuffer<>(10);
        RingBuffer<String> buffer2 = new RingBuffer<>(10);
        Random random = new Random();

        for (int i = 1; i <= 5; i++) {      // 5 потоків-виробників
            int id = i;
            Thread producer = new Thread(() -> {
                try {
                    int messageNum = 1;
                    while (true) {
                        String msg = "Потік №" + id + " згенерував повідомлення " + messageNum++;
                        buffer1.put(msg);
                        Thread.sleep(random.nextInt(200));
                    }
                } catch (InterruptedException ignored) {}
            });
            producer.setDaemon(true);   // робимо потік демоном, щоб програма могла завершитися
            producer.start();           // запускаємо потік
        }

        for (int i = 1; i <= 2; i++) {       // 2 потоки-посередники
            int id = i;
            Thread translator = new Thread(() -> {
                try {
                    while (true) {
                        String msg = buffer1.take();
                        String newMsg = "Потік №" + id + " переклав повідомлення: [" + msg + "]";
                        buffer2.put(newMsg);
                        Thread.sleep(random.nextInt(300));
                    }
                } catch (InterruptedException ignored) {}
            });
            translator.setDaemon(true);
            translator.start();
        }

        for (int i = 0; i < 100; i++) {
            String msg = buffer2.take();
            System.out.println(msg);
        }

        System.out.println("Програма завершила роботу.");
    }
}
