package org.sri.arrays.TwoSum;

import java.util.Arrays;

public class TwoSumSorted {
    public static void main(String[] args) {
        int a[]= twoSumSorted(new int[]{1,3,5,7,9},8);

        System.out.println(Arrays.toString(a)); // 0,3 as 1+7 is 8
    }

    private static int[] twoSumSorted(int[] nums, int target) {

        int left = 0;
        int right = nums.length-1;

        while(left<right){
            int sum = nums[left]+nums[right];

            if (sum == target){
                return new int[]{left,right};
            } else if (sum < target) {
                left++;
            }else {
                right--;
            }
        }

        return new int[]{};
    }
}
