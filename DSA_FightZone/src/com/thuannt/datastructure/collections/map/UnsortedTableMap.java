package com.thuannt.datastructure.collections.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V>{
    
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();
    
    private int findIndex(K key) {
        int n = table.size();
        for (int i = 0; i < n; i++) {
            if(table.get(i).getKey() == key)
                return i;
        }
        return -1;
    }
    
    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        int i = findIndex(key);
        if(i == -1) {
            return null;
        }
        return table.get(i).getValue();
    }

    @Override
    public V put(K key, V value) {
        int i = findIndex(key);
        if(i == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        } else return table.get(i).getValue();
    }

    @Override
    public V remove(K key) {
        int i = findIndex(key);
        if(i == -1) return null;
        V answer = table.get(i).getValue();
        int s = size();
        if(i != s-1)
            table.set(i, table.get(s-1));
        table.remove(s-1);
        return answer;
    }
    
    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int j = 0;
        
        @Override
        public boolean hasNext() {
            return j < size();
        }

        @Override
        public Entry<K, V> next() {
            if(j >= size()) {
                throw new NoSuchElementException();
            }
            return table.get(j);
        }
        
    }
    
    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

}
