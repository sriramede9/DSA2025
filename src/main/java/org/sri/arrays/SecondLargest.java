package org.sri.arrays;

public class SecondLargest {
    int[] arr = new int[]{1, 6, 3, 5, 7, 0, 9};

    void findSecondLargest() {
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {

            if(arr[i]>firstMax){
               firstMax=arr[i];
                //if first max is greater than
            }
        }


    }
}
