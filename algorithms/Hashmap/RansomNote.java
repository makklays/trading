/**
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        
        int[] arrChars = new int[26];

        for (char c : magazine.toCharArray()) {
            arrChars[c - 'a']++; 
        }

        System.out.println(Arrays.toString(arrChars));

        for (char c : ransomNote.toCharArray()) {
            if (--arrChars[c - 'a'] < 0) return false;
        }

        /*if (magazine.indexOf(ransomNote) >= 0) {
            return true;
        } else {
            return false;
        }*/

        return true;        
    }
}

