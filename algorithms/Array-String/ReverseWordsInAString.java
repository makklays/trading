/**
 * Given an input string s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * Return a string of the words in reverse order concatenated by a single space.
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public String reverseWords(String s) {
        s = s.replaceAll(" +", " ").trim();
        String[] arr = s.split(" ");
        String[] words = new String[arr.length];
        int j = 0;
        for(int i = (arr.length - 1); i >= 0; i--) {
            words[j] = arr[i];
            j++;
        }

        return String.join(" ", words);
    }
}

