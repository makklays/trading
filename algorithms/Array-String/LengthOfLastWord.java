/**
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 * 
 * A word is a maximal substring consisting of non-space characters only.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        String lastWord = words[words.length - 1];
        int len = lastWord.trim().length();

        return len;
    }
}

