/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public boolean isSubsequence(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;

        // use while for edge cases 
        while(sIndex < s.length() && tIndex < t.length()) {
            if (t.charAt(tIndex) == s.charAt(sIndex)) {
                sIndex++;
            }

            tIndex++;
        }

        /*for(int fastIndex = 0; fastIndex < t.length(); fastIndex++) {
            if (t.charAt(fastIndex) == s.charAt(slowIndex)) {
                slowIndex++;
            }

            if(slowIndex >= s.length()) {
                return true;
            }
        }*/

        return sIndex >= s.length();
    }
}

