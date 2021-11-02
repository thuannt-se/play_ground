package com.thuannt.datastructure.collections.map;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V>{

    private static final int DEFAULT_PRIME = 109345121;
    private static final int DEFAULT_CAPACITY = 17;
    protected int n = 0;
    protected int capacity;
    private int prime;
    private long scale, shift;
    
    // abstract class cannot be initialize, however, it's a blue print for child
    public AbstractHashMap(int cap, int p) {
        prime = p;
        capacity = cap;
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }
    
    public AbstractHashMap(int cap) {
       this(cap, DEFAULT_PRIME); //default prime
    }
    
    public AbstractHashMap() {
        this(DEFAULT_CAPACITY); //default capacity
    }
    @Override
    public int size() {
        return n;
    }

    @Override
    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }
    

    @Override
    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if(n > capacity / 2) {
            resize(2*capacity - 1);
        }
        return answer;
    }

    @Override
    public V remove(K key) {
        // TODO Auto-generated method stub
        return null;
    }
    //Hash code MAD method
    private int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }
    
    private void resize(int newCap) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(n);
        
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }
    
    protected abstract void createTable();
    
    protected abstract V bucketGet(int h, K key);
    
    protected abstract V bucketPut(int h, K key, V value);
    
    protected abstract V bucketRemove(int h, K key);

}
