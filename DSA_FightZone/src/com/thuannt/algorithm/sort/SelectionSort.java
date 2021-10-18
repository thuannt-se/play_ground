package com.thuannt.algorithm.sort;

public class SelectionSort {
    
    public static void sort(int[] src) {
        for(int i = 0; i < src.length; i++) {
            int min = src[i];
            for(int j = i + 1; j < src.length; j++) {
                if(src[j] < min) {
                    src[i] = src[j];
                    src[j] = min;
                    min = src[i];
                }
            }
        }
    }

}
