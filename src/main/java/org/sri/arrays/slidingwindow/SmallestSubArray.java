package org.sri.arrays.slidingwindow;

public class SmallestSubArray {

    public int smallestSubArray(int nums[],int value){
        int n = nums.length;
        int left = 0;  // Left pointer for the sliding window
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right =0;right<n;right++){
            currentSum+=nums[right];

            while (currentSum >=value){
                minLength = Math.min(minLength,right-left+1);
                currentSum = currentSum - nums[left];
                left++;
            }
        }


        return minLength == Integer.MAX_VALUE ? 0 : minLength;

    }

    public static void main(String[] args) {
        SmallestSubArray smallestSubArray = new SmallestSubArray();
        int[] nums = {2, 3, 1, 2, 4, 3};
        int s = 7;
        int smallestSubArray1 = smallestSubArray.smallestSubArray(nums, s);
        System.out.println("result:"+ smallestSubArray1);
    }
}
