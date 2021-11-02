package com.thuannt.datastructure.collections.map;

import java.util.Comparator;

import com.thuannt.util.DefaultComparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {
    
    protected static class PQEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;
        
        public PQEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
        
        protected void setKey(K key) {
            this.key = key;
        }
        
        protected void setValue(V value) {
            this.value = value;
        }
        
    }
    
    private Comparator<K> comp;
    
    protected AbstractPriorityQueue(Comparator<K> comparator) { this.comp = comparator; }
     
    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }
    
    protected int compare(Entry<K, V> entry1, Entry<K, V> entry2) {
        return this.comp.compare(entry1.getKey(), entry2.getKey());
    }
    
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return this.comp.compare(key, key) == 0; // check if it compare to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompitable key");
        }
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
} 
