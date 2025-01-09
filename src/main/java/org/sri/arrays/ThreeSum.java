package org.sri.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = new int[]{-4, -1, -1, 0, 1, 2};
        List<List<Integer>> list = threeSum(nums, 0);
        System.out.println(list.get(0));
    }

    private static List<List<Integer>> threeSum(int[] nums, int target) {

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            int current = nums[i];

            int left = i + 1;
            int right = nums.length - 1;

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            while (left < right) {
                int sum = current + nums[left] + nums[right];
                if (sum == target) {
                    result.add(List.of(current, nums[left], nums[right]));
                    // After finding a valid triplet
                    while (left < right && nums[left] == nums[left + 1]) left++; // Skip duplicate `left`
                    while (left < right && nums[right] == nums[right - 1]) right--; // Skip duplicate `right`
                    left++;
                    right--;

                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
