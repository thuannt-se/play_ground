package com.thuannt.algorithm.sort;

import java.util.Arrays;

//to simplify the implementation, I only consider list with number (int)
public class MergeSort {
	
	static class TopDownMergeSort {
		private static void merge(int[] x, int[] y , int[] output) {
			int i = 0, j = 0;
			while(i + j < output.length) {
				if(j == y.length || (i < x.length && x[i] < y [j])) output[i+j] = x[i++];
				else output[i+j] = y[j++];
			}
		}
		public static void sort(int[] output) {
			if(output.length < 2) {
				return ;
			}
			int mid = output.length/2;
			int[] x = Arrays.copyOfRange(output, 0, mid);
			int[] y = Arrays.copyOfRange(output, mid, output.length);
			//Recursive sort the two half
			sort(x);
			sort(y);
			
			//merge every two half created;
			merge(x, y, output);
		}
	}
	//This code is copied from DSA book of Wiley
	//It has a bug: cannot sort array list with odd length
	static class BottomUpMergeSort {
		
		private static void merge(int[] src, int[] dest, int start, int inc) {
			int end1 = Math.min(start + inc, src.length);
			int end2 = Math.min(start + 2*inc, src.length);
			int x = start;
			int y = start + inc;
			int z = start;
			while(x < end1 && y < end2) {
				if(src[x] < src[y]) 
					dest[z++] = src[x++];
				else
					dest[z++] = src[y++];
			if(x < end1) 
				System.arraycopy(src, x, dest, z, end1 - x); // copy from src -> dest, index x to z, with length end1-x
			else if(y < end2) 
				System.arraycopy(src, y, dest, z, end2 - y);
			}
		}
		
		public static void sort(int[] output) {
			int n = output.length;
			int[] src = output;
			int[] dest = new int[n];
			int[] temp;
			for(int i = 1; i < n; i *= 2) {
				for(int j = 0; j < n; j += 2*i)
					merge(src, dest, j, i);
				temp = src;
				src = dest;
				dest = temp;
			}
			if(output != src)
				System.arraycopy(src, 0, output, 0, n);
		}
	}
	
	public static void main(String[] args) {
		int[] test1 = {1, 5, 3, 20, 9, 10, 100, 20, 2, 4};
		TopDownMergeSort.sort(test1);
		System.out.println(Arrays.toString(test1));
		int[] test2 = {1, 5, 3};
		//Bottom up
		BottomUpMergeSort.sort(test2);
		System.out.println(Arrays.toString(test2));

	}
	
}
