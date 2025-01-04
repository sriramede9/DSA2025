package org.sri.arrays.slidingwindow;

public class BaseSlidingWindow {

    public int findMaxSubArray(int[] nums, int windowSize){
        int currentsum=0;
        for(int i=0;i<windowSize;i++){
            currentsum = currentsum+nums[i];
        }

        int maxSum = currentsum;

        for(int i=windowSize;i<nums.length;i++){
            int newSum = currentsum + nums[i] - nums[i-windowSize];
            maxSum= Math.max(maxSum,newSum);
        }
        return maxSum;
    }
}
