package lab6;

import java.util.HashMap;
import java.util.Map;

class Translator {
    private final Map<String, String> dictionary;

    public Translator() {
        dictionary = new HashMap<>();
    }

    public void addWord(String english, String ukrainian) {
        dictionary.put(english.toLowerCase(), ukrainian.toLowerCase());
    }

    public String translatePhrase(String phrase) {
        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < phrase.length(); i++) {
            char ch = phrase.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                word.append(ch);
            } else {
                result.append(translateSingleWord(word.toString()));
                word.setLength(0);
                result.append(ch);
            }
        }

        result.append(translateSingleWord(word.toString()));

        return result.toString();
    }

    private String translateSingleWord(String word) {
        if (word.isEmpty()) return "";

        boolean isCapitalized = Character.isUpperCase(word.charAt(0));

        String lower = word.toLowerCase();
        String translated = dictionary.getOrDefault(lower, word);

        if (isCapitalized && !translated.isEmpty()) {
            translated = Character.toUpperCase(translated.charAt(0)) + translated.substring(1);
        }

        return translated;
    }
}
