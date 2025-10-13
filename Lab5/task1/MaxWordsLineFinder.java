/*
* Серіалізація — це процес перетворення структури даних або об'єкта в послідовність байтів (або в інший формат)
* для збереження, передачі або відтворення.
* */

package Lab5.task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MaxWordsLineFinder {

    // Зчитування текстового файлу у список рядків
    public static List<String> readTextFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    // Серіалізація списку рядків
    public static void saveSerializedLines(String filename, List<String> lines) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(lines);
            System.out.println("Lines serialized successfully.");
        } catch (IOException e) {
            System.out.println("Error serializing lines: " + e.getMessage());
        }
    }

    // Десеріалізація списку рядків
    public static List<String> loadSerializedLines(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing lines: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Знаходження рядка з максимальною кількістю слів
    public static String findLineWithMaxWords(List<String> lines) {
        String maxLine = "";
        int maxWords = 0;
        for (String line : lines) {
            // split("\\s+") – ділить рядок на слова по будь-якій кількості пробілів/табуляцій
            int wordCount = line.trim().isEmpty() ? 0 : line.trim().split("\\s+").length;
            if (wordCount > maxWords) {
                maxWords = wordCount;
                maxLine = line;
            }
        }
        return maxLine;
    }

    public static void main(String[] args) {
        String textFile = "Lab5/task1/input.txt";
        String serializedFile = "Lab5/task1/lines.dat"; // бінарний файл, у який ми серіалізуємо список рядків із текстового файлу

        // 1. Читання текстового файлу
        List<String> lines = readTextFile(textFile);

        // 2. Серіалізація рядків
        saveSerializedLines(serializedFile, lines);

        // 3. Десеріалізація рядків
        List<String> loadedLines = loadSerializedLines(serializedFile);

        // 4. Пошук рядка з максимальною кількістю слів
        String maxLine = findLineWithMaxWords(loadedLines);
        System.out.println("Line with maximum words:\n" + maxLine);
    }
}
