package org.sri.arrays.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LongestSubstringWithoutRepeatingCharacters {
    public static int longestSubstringWithoutRepeatingCharacters(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();
        int maxLength = 0;
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            // If the character was already in the window, move the start pointer
            if (charFrequencyMap.containsKey(currentChar) && charFrequencyMap.get(currentChar) >= start) {
                // Move start to the position after the last occurrence of currentChar
                start = charFrequencyMap.get(currentChar) + 1;
            }
            charFrequencyMap.put(currentChar,end);
            // Update the maximum length

            maxLength = Math.max(maxLength, end-start+1);

        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstringWithoutRepeatingCharacters("abcabcd")); // Output: 4
        System.out.println(longestSubstringWithoutRepeatingCharacters("aa"));   // Output: 1
        System.out.println(longestSubstringWithoutRepeatingCharacters("aaabbcc"));   // Output: 2
        System.out.println(longestSubstringWithoutRepeatingCharacters("aabbcc"));   // Output: 2
    }
}
