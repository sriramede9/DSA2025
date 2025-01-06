package org.sri.arrays;

public class SecondLargest {
    public static int findSecondLargest(int[] arr) {
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int j : arr) {
            if (j > firstMax) {
                secondMax = firstMax; // if the horse is running faster than firstMax, this is my new max and current first is my secondMax
                firstMax = j;
            } else if (j < firstMax && j > secondMax) { // if the horse is running slower than first and faster than second, this is my secondMax
                secondMax = j;
            }
        }
        return secondMax;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,8,4,3,7,5};
        System.out.println(findSecondLargest(nums));
    }
}
