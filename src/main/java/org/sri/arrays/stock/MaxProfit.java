package org.sri.arrays.stock;

public class MaxProfit {
    public static void main(String[] args) {
        int arr[] = {100, 180, 260, 310, 40, 695};
        int maxProfit = maxProfit2(arr);
        System.out.println(maxProfit);
    }

    public static int maxProfit2(int[] transactions) {
        int currentProfit = 0;
        if (null == transactions || transactions.length < 2) {
            return currentProfit;
        }
        int minPrice = transactions[0];
        for (int i = 1; i < transactions.length ; i++) {
            if(transactions[i]<minPrice){
                minPrice=transactions[i];
            }
            currentProfit = Math.max(currentProfit, transactions[i] - minPrice);
        }
        return currentProfit;
    }


    public static int maxProfit(int[] transactions) {
        int currentProfit = 0;
        for (int i = 0; i < transactions.length; i++) {
            for (int j = i; j < transactions.length; j++) {
                currentProfit = Math.max(currentProfit, transactions[j] - transactions[i]);
            }
        }
        return currentProfit;
    }

}
