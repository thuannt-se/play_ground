package com.thuannt.algorithm.sort;

public class InsertionSort {
    
    public static void sort(int[] src) {
        if(src.length < 2) return ;
        for(int i = 1; i < src.length; i++) {
            int cur = src[i];
            int tmp = 0;
            int j = i;
            while(j > 0 && src[j-1] > cur) {
                tmp = src[j];
                src[j] = src[j-1];
                src[j-1] =  tmp;
                j--;
             }
        }
    }

}
