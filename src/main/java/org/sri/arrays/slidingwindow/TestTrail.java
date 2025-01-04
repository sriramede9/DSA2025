package org.sri.arrays.slidingwindow;

import java.util.*;

public class TestTrail {
    public static void main(String[] args) {
        String s = "aaabbcc";
        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            charFrequencyMap.put(s.charAt(i), charFrequencyMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        List<Integer> list = new ArrayList<>(charFrequencyMap.values());
        int maxValue = 0;
        int k = 2;
        for (int i = 0; i < k; i++) {
            maxValue = list.get(i) + maxValue;
        }
        for (int i = k; i < list.size(); i++) {

            int newSum = maxValue + list.get(i) - list.get(i - k);
            maxValue = Math.max(maxValue, newSum);
        }

        System.out.println("Max value" + maxValue);
    }
}
