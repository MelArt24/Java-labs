package lab6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();

        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");

        System.out.println("Бажаєте додати слова до словника? (Y / smth else)");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("Y")) {
            while (true) {
                System.out.print("Введіть англійське слово (або 's' для завершення): ");
                String eng = scanner.nextLine();

                if (eng.equalsIgnoreCase("S")) break;

                System.out.print("Введіть український переклад: ");
                String ukr = scanner.nextLine();

                translator.addWord(eng, ukr);
            }
        }

        System.out.println("Введіть фразу англійською для перекладу:");
        String phrase = scanner.nextLine();

        String translatedPhrase = translator.translatePhrase(phrase);
        System.out.println("Переклад: " + translatedPhrase);
    }
}