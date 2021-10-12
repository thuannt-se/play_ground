package com.thuannt.algorithm.sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class QuickSort {
	
	//For quicksort case, it would be easier if we use Java collection instead of array of int
	//Since array of int require us to copy array, handle case array with 0 value, or empty array, etc...
	static class BasicImplemation {
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
	public static void main(String[] args) {
		Integer[] test1 = { 29, 5, 3, 20, 9, 10, 100, 20, 2, 4 };
		LocalDateTime start1 = LocalDateTime.now();
		List<Integer> result = BasicImplemation.sort(Arrays.asList(test1), (a1, a2) -> a1.compareTo(a2));
		LocalDateTime end1 = LocalDateTime.now();
		System.out.println(Duration.between(start1, end1).toNanos());
		System.out.println(Arrays.toString(result.toArray()));
	}
}
