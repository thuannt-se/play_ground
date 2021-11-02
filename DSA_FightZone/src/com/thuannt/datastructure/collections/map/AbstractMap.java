package com.thuannt.datastructure.collections.map;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    public boolean isEmpty() { return size() == 0; }
    
    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;
        
        public MapEntry(K key, V value) {
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
        
        protected V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }
    
    // Key set method should return an object that implement iterator
    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return entries.next().getKey();
        }
    }
    
    private class KeyIterable implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }
    
    public Iterable<K> keySet() {
        return new KeyIterable();
    }
    
    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K, V>> values = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return values.hasNext();
        }

        @Override
        public V next() {
            return values.next().getValue();
        }
    }
    
    private class ValueIterable implements Iterable<V> {

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }
    
    public Iterable<V> values() {
        return new ValueIterable();
    }

}
