package com.thuannt.datastructure.collections.map;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    
    protected ArrayList<Entry<K, V>> heap = new ArrayList<Entry<K,V>>();
    
    public HeapPriorityQueue() {
        super();
    }
    
    public HeapPriorityQueue(Comparator<K> comp) {
        super (comp);
    }
    
    protected int parent(int j) {
        return (j-1)/2;
    }
    
    protected int left(int j) {
        return 2*j + 1;
    }
    
    protected int right(int j) {
        return 2*j + 2;
    }
    
    protected boolean hasLeft(int j) {
        return left(j) < size();
    }
    
    protected boolean hasRight(int j) {
        return right(j) < size();
    }
    
    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    protected void uphead(int j) {
        while(j > 0) {
            int p = parent(j);
            if(compare(heap.get(j), heap.get(p)) >= 0) break;
            swap(j, p);
            j = p;
        }
    }
    
    //Heap should create a tree starting form left to right
    //Meaning heap has 2 element will have 1 parent and 1 left
    protected void downheap(int j) {
        while(hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex; //considering left is smaller
            if(hasRight(j)) {
                int rightIndex = right(j);
                if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;
            }
            //if heap.get(j) is bigger than both left, right, stay there
            if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break;
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }
    
    
    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        uphead(heap.size() - 1);
        return newest;
    }

    @Override
    public Entry<K, V> top() {
       if(heap.isEmpty()) return null;
       return heap.get(0);
    }
    
    //remove the first (min) from list and move the latest added down heap
    //actually the pivot can be choose from anywhere
    @Override
    public Entry<K, V> remove() {
        if(heap.isEmpty()) return null;
        Entry<K, V> top = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return top;
    }
    

}
