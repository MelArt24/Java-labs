/* Трошки теорії

Lambda — це короткий спосіб написати анонімну функцію (функцію без назви),
яку можна передати як аргумент у метод.

Stream — це послідовність елементів, над якими можна виконувати операції як у конвеєрі.

*/

package lab7;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String input = "Hello world тест,,,,, beb25 боба zoom,,,, queen      та король and clash royal with lola!!!";
        String[] result = findBalancedLatinWords(input);

        System.out.println("Result:");
        Arrays.stream(result).forEach(System.out::println);
    }

    public static String[] findBalancedLatinWords(String input) {
        return Arrays.stream(input.split("\\W+"))           // Розбиваємо на слова
                .filter(word -> word.matches("[a-zA-Z]+"))  // Лише латинські літери
                .filter(word -> {                                 // Перевіряємо чи к-ть голосних = к-ть приголосних
                    long vowels = word.toLowerCase()
                            .chars()
                            .filter(c -> "aeiou".indexOf(c) >= 0)
                            .count();
                    return vowels * 2 == word.length();
                })
                .toArray(String[]::new);
    }
}
