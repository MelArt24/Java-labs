package Lab5.task4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagCounter {

    public static void countTags(String urlString) {
        Map<String, Integer> tagFrequency = new HashMap<>();

        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            Pattern tagPattern = Pattern.compile("<\\s*(\\w+)[^>]*>");
            while ((line = reader.readLine()) != null) {
                Matcher matcher = tagPattern.matcher(line);
                while (matcher.find()) {
                    String tag = matcher.group(1).toLowerCase();
                    // matcher.group(1) -> отримує назву тега з HTML.
                    // group(1) – це перша група в дужках ( … )
                    tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading URL: " + e.getMessage());
            return;
        }

        // a) Лексикографічний порядок
        System.out.println("\nTags in lexicographic order:");
        tagFrequency.keySet().stream().sorted()
                .forEach(tag -> System.out.println(tag + ": " + tagFrequency.get(tag)));

        // b) За частотою
        System.out.println("\nTags sorted by frequency:");
        tagFrequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
