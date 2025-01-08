package org.sri.arrays.slidingwindow;

import java.util.HashMap;

public class LongestSubstringKDistinct {
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.isEmpty() || k == 0) {
            return 0;
        }

        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();
        int maxLength = 0;
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            charFrequencyMap.put(currentChar, charFrequencyMap.getOrDefault(currentChar, 0) + 1);

            // Shrink the window until we have at most k distinct characters
            while (charFrequencyMap.size() > k) {
                char charAtStart = s.charAt(start);
                charFrequencyMap.put(charAtStart, charFrequencyMap.get(charAtStart) - 1);

                if (charFrequencyMap.get(charAtStart) == 0) {
                    charFrequencyMap.remove(charAtStart);
                }
                start++; // Move the start pointer forward
            }

            // Update the maximum length
            maxLength = Math.max(maxLength, charFrequencyMap.values().stream().reduce(0,Integer::sum));
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2)); // Output: 3
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));   // Output: 2
        System.out.println(lengthOfLongestSubstringKDistinct("aaabbcc", 2));   // Output: 5
        System.out.println(lengthOfLongestSubstringKDistinct("aabbcc", 2));   // Output: 4
    }
}
