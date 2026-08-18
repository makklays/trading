/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> mapS2T = new HashMap<>();
        HashMap<Character, Character> mapT2S = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Проверяем маппинг из S в T
            if (mapS2T.containsKey(charS)) {
                if (mapS2T.get(charS) != charT) return false;
            } else {
                mapS2T.put(charS, charT);
            }

            // Проверяем маппинг из T в S
            if (mapT2S.containsKey(charT)) {
                if (mapT2S.get(charT) != charS) return false;
            } else {
                mapT2S.put(charT, charS);
            }
        }

        return true;
    }
}

