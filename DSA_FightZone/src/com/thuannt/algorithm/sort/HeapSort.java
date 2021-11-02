package com.thuannt.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.thuannt.datastructure.collections.map.HeapPriorityQueue;

public class HeapSort {
    public static <E> List<E> sort(List<E> src) {
        HeapPriorityQueue<Integer, E> heap = new HeapPriorityQueue<>();
        List<E> result = new ArrayList<E>();
        for(int i = 1; i <= src.size(); i++) {
            heap.insert((Integer) src.get(i-1), src.get(i-1));
        }
        int size = heap.size();
        for(int j = 0; j < size; j++) {
            result.add(heap.remove().getValue());
        }
        return result;
    }
}
