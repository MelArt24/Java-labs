/*
--add-opens java.base/java.lang=ALL-UNNAMED

| `java.base`    | модуль, у якому знаходиться клас `String`                                      |
| `/java.lang`   | пакет, де знаходяться базові класи (`String`, `Math`, `Object`, тощо)          |
| `=ALL-UNNAMED` | дозволяє всім неіменованим модулям (тобто звичайним програмам) отримати доступ |



Рефлексія (1) — це можливість програми дивитись на саму себе під час виконання.
Рефлексія (2) — це процес, під час якого програма може відстежувати та модифікувати власну
структуру та поведінку під час виконання.

Програма може:
- дізнатись, які класи, поля, методи, конструктори в неї є;
- отримати доступ до них навіть якщо вони приватні;
- створювати об’єкти, викликати методи, змінювати значення полів,
не знаючи про них заздалегідь у коді.                               */

package lab10;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String literalString = "Hello World";
        System.out.println("Літеральний рядок до зміни: " + literalString);

        changeStringValue(literalString, "Changed!");
        System.out.println("Літеральний рядок після зміни: " + literalString);

        System.out.print("\nВведіть рядок: ");
        String userInput = scanner.nextLine();
        System.out.println("Введений рядок до зміни: " + userInput);

        System.out.print("Введіть нове значення: ");
        String newValue = scanner.nextLine();

        changeStringValue(userInput, newValue);
        System.out.println("Введений рядок після зміни: " + userInput);
    }

    private static void changeStringValue(String target, String newValue) throws Exception {
        Field valueField = String.class.getDeclaredField("value"); // шукає приватне поле з іменем "value" у класі String
        valueField.setAccessible(true); // знімає обмеження доступу, щоб можна було читати й змінювати поле

        Object value = valueField.get(target); // З об’єкта target дістаємо значення його приватного поля value

        if (value instanceof char[] chars) {
            char[] newChars = newValue.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                chars[i] = (i < newChars.length) ? newChars[i] : ' ';
            }

        } else if (value instanceof byte[] bytes) {     // спрацює ось це
            byte[] newBytes = newValue.getBytes();

            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (i < newBytes.length) ? newBytes[i] : (byte) ' ';
            }
        } else {
            System.out.println("Невідомий тип поля value: " + value.getClass());
        }
    }
}
