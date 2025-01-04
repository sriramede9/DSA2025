package org.sri.arrays.TwoSum;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSumUnsorted {

    public static void main(String args[]){
        int a[]= twoSumUnsorted(new int[]{1,3,5,7},8);

        System.out.println(Arrays.toString(a)); // 1,2 as 3+5 is 8

    }

    private static int[] twoSumUnsorted(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();

        for (int i=0;i<nums.length;i++){
            int compliment = target - nums[i];

            if(hashMap.containsKey(compliment)){
                return new int[]{hashMap.get(compliment),i};
            }
            hashMap.put(nums[i],i);
        }

        return new int[]{};
    }
}

