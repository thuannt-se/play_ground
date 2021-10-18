package com.thuannt.algorithm.sort;

public class BubbleSort {
    
    public static void sort(int[] src) {
        for(int i = 0; i < src.length; i++) {
            int tmp = 0;
            for(int j = 1; j < src.length; j++) {
                if(src[j] < src [j-1]) {
                    tmp = src[j];
                    src[j] = src[j-1];
                    src[j-1] = tmp;
                }
            }
        }
    }

}
