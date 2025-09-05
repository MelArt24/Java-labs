/* Завдання (UA): Знайти ті слова, які містять тільки символи латинського алфавіту.
Серед них знайти ті слова, які містять рівну кількість голосних та приголосних.
На вхід поступає рядок із словами. На виході – масив String.

Task (Eng): Find words that contain only Latin alphabet characters.
Among them, find words that contain an equal number of vowels and consonants.
The input is a string with words. The output is a String array. */


import java.util.ArrayList;
import java.util.List;

public class Lab1 {

    public static void main(String[] args) {
        String input = "Hello world тест beb25 zoom queen та король and clash royal with lola";
        String[] result = findBalancedLatinWords(input);

        // V1: new word from new line
        System.out.println("Result:");
        for (String word : result) {
            System.out.println(word);
        }

        // V2: all words separated by commas
        // System.out.println("Result: " + String.join(", ", result));
    }

    // a function that searches for words that contain only Latin alphabet characters
    public static String[] findBalancedLatinWords(String input) {
        // split("\\W+") – splits a string into words, ignoring punctuation marks, spaces and non-alphabetic characters
        String[] words = input.split("\\W+");

        List<String> balancedWords = new ArrayList<>();

        for (String word : words) {
            if (word.matches("[a-zA-Z]+")) { // only latin letters
                int vowels = countVowels(word);
                int consonants = word.length() - vowels;

                if (vowels == consonants) {
                    balancedWords.add(word);
                }
            }
        }

        return balancedWords.toArray(new String[0]);
    }

    // this function counts vowels
    private static int countVowels(String word) {
        int count = 0;
        String lower = word.toLowerCase();
        for (char c : lower.toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }
}
