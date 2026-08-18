/**
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:
 *
 *   Each letter in pattern maps to exactly one unique word in s.
 *   Each unique word in s maps to exactly one letter in pattern.
 *   No two letters map to the same word, and no two words map to the same letter.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
    
        // Проверка длины
        if (words.length != pattern.length()) {
            return false;
        }

        HashMap<Character, String> charToWord = new HashMap<>();
        HashMap<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            // Проверяем связь символ -> слово
            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(word)) return false;
            } else {
                charToWord.put(c, word);
            }

            // Проверяем связь слово -> символ
            if (wordToChar.containsKey(word)) {
                if (wordToChar.get(word) != c) return false;
            } else {
                wordToChar.put(word, c);
            }
        }

        return true;
    }
}

