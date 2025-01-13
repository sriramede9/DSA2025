package org.sri.arrays.slidingwindow;

import java.util.Arrays;

public class LongestSubArray {

    public static int longestSubArray(int nums[], int k) {
        int maxSize = 0;
        int left = 0;
        int currentSum = 0;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            while (currentSum > k) {
                //remove left element
                currentSum -= nums[left];
                left++;
            }
            if (currentSum == k) {
                maxSize = Math.max(maxSize, right - left + 1);
            }
        }
        return maxSize;
    }

    public static void main(String[] args) {
        int ans = longestSubArray(new int[]{1, 2, 3, 1, 1, 1, 2, 1}, 6);
        System.out.println(ans);
    }
}
