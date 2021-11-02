package com.thuannt.datastructure.collections.map;

public interface PriorityQueue<K, V> {
    int size();
    boolean isEmpty();
    
    Entry<K, V> insert(K key, V value) throws IllegalArgumentException;
    Entry<K, V> top();
    Entry<K, V> remove();
}
