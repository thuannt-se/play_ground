package com.thuannt.algorithm.sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class QuickSort {
	
	//For quicksort case, it would be easier if we use Java collection instead of array of int
	//Since array of int require us to copy array, handle case array with 0 value, or empty array, etc...
	public static class BasicImplemation {
		public static <T> List<T> sort(List<T> src, Comparator<T> comparator) {
			int maxLength = src.size();

			if (maxLength < 2)
				return src;

			T pivot = src.get(0); //select first pivot as index
			// Create 3 array with length equal to src
			List<T> lesser = new ArrayList<T>();
			List<T> equal = new ArrayList<T>();
			List<T> greater = new ArrayList<T>();

			int i = 0;
			while (i < maxLength) {
				int compareOutCome = comparator.compare(src.get(i), pivot);
				if (compareOutCome < 0)
					lesser.add(src.get(i++));
				else if (compareOutCome == 0)
					equal.add(src.get(i++));
				else
					greater.add(src.get(i++));
			}
			
			lesser = sort(lesser, comparator);
			greater = sort(greater, comparator);
			
			//Begin to merge
			List<T> temp = new ArrayList<T>();
			temp.addAll(lesser);
			temp.addAll(equal);
			temp.addAll(greater);
			return temp;
			
		}
	}
	
	public static class InPlaceImplementation {
		public static <T> void sort(T[] src, Comparator<T> comparator, int firstIdx, int lastIdx) {
			if(firstIdx > lastIdx) return ;
			int left = firstIdx;
			T pivot = src[lastIdx];
			int right = lastIdx - 1;
			T tmp;
			while (left <= right) {
				while (left <= right && comparator.compare(src[left], pivot) < 0) left++;
				while (left <= right && comparator.compare(src[right], pivot) > 0) right--;
				
				if(left <= right) {
					tmp = src[left];
					src[left] = src[right];
					src[right] = tmp;
					left++;
				}
			}
			// move the pivot into the current middle position
			tmp = src[left];
			src[left] = src[lastIdx]; 
			src[lastIdx] = tmp;
			
			//recursive call with updated index;
			sort(src, comparator, firstIdx, left - 1);
			sort(src, comparator, left + 1, lastIdx);
			
		}
	}
}
