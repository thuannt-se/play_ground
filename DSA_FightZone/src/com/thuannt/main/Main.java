package com.thuannt.main;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.thuannt.algorithm.sort.BubbleSort;
import com.thuannt.algorithm.sort.InsertionSort;
import com.thuannt.algorithm.sort.QuickSort;
import com.thuannt.algorithm.sort.SelectionSort;
import com.thuannt.datastructure.collections.ArrayStack;
import com.thuannt.datastructure.collections.Stack;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("quick sort");

        Integer[] test1 = { 29, 5, 3, 20, 9, 10, 100, 20, 2, 4 };
        QuickSort.BasicImplemation basicImplemation = new QuickSort.BasicImplemation();
        List<Integer> result = basicImplemation.sort(Arrays.asList(test1), (a1, a2) -> a1.compareTo(a2));
        System.out.println(Arrays.toString(result.toArray()));
        
        QuickSort.InPlaceImplementation inPlace = new QuickSort.InPlaceImplementation();
        
        //In place implementation
        System.out.println("In-place quick sort");

        inPlace.sort(test1, (a1, a2) -> a1.compareTo(a2), 0, test1.length - 1);
        System.out.println(Arrays.toString(test1));
        
        System.out.println("Insertion Sort");

        int[] test2 = { 29, 5, 3, 20, 9, 10, 100, 20, 2, 4 };
        InsertionSort.sort(test2);
        System.out.println(Arrays.toString(test2));
        
        
        System.out.println("Selection Sort");
        int[] test3 = { 29, 5, 3, 20, 9, 10, 100, 20, 2, 4 };
        SelectionSort.sort(test3);
        System.out.println(Arrays.toString(test3));
        
        System.out.println("Bubble sort");
        int[] test4 = { 29, 5, 3, 20, 9, 10, 100, 20, 2, 4 };
        BubbleSort.sort(test4);
        System.out.println(Arrays.toString(test4));
        
        //Test Stack;
        Stack<Integer> stack = new ArrayStack<Integer>();
        stack.push(5);
        stack.push(20);
        stack.push(1);
        System.out.println(stack.toString());

    }
}
