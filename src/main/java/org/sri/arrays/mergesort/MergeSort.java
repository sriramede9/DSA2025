package org.sri.arrays.mergesort;

import java.util.Arrays;

public class MergeSort {

    public static int[] mergeSort(int[] arr, int start, int end) {
        System.out.println("mergeSort called with start: " + start + ", end: " + end);

        // Base case: If the array has one or zero elements
        if (start >= end - 1) {
            System.out.println("Base case reached with element: " + arr[start]);
            return new int[]{arr[start]};
        }

        // Find the middle point
        int mid = (start + end) / 2;
        System.out.println("Splitting array: left (" + start + " to " + mid + "), right (" + mid + " to " + end + ")");

        // Recursively sort the left half
        int[] left = mergeSort(arr, start, mid);
        System.out.println("Left half result: " + Arrays.toString(left));

        // Recursively sort the right half
        int[] right = mergeSort(arr, mid, end);
        System.out.println("Right half result: " + Arrays.toString(right));

        // Returning dummy array for now
        return merge(left,right);
    }

    // Merge two sorted arrays
    public static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        // Merge the two arrays while comparing the elements
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        // If there are remaining elements in the left array
        while (i < left.length) {
            merged[k++] = left[i++];
        }

        // If there are remaining elements in the right array
        while (j < right.length) {
            merged[k++] = right[j++];
        }

        return merged;
    }


    public static void main(String[] args) {
        int[] array = new int[]{1, 7, 2, 3};
        System.out.println("before split" + Arrays.toString(array));
        int[] me = mergeSort(array, 0, array.length);
        System.out.println(Arrays.toString(me));
    }
}
