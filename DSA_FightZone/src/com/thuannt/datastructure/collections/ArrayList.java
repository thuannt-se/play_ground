package com.thuannt.datastructure.collections;

public class ArrayList<E> implements List<E>{
    
    public static final int CAPACITY = 20;
    private E[] data;
    private int size; //current size of List
    
    public ArrayList() {
        this(CAPACITY);
    }
    
    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }
    

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void add(E e) {
        if(data != null && size == data.length) {
            E[] extendedData = (E[]) new Object[CAPACITY*2];
            for(int i = 0; i < size; i++) {
                extendedData[i] = data[i];
            }
            extendedData[size++] = e;
            data = extendedData;
        } else {
            data[size++] = e;
        }
        
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i);
        return data[i];
    }

    @Override
    public E set(E e, int i) throws IndexOutOfBoundsException {
        checkIndex(i);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i);
        E temp = data[i];
        for(int k = i; k < size-1; k++)
            data[k] = data[k++];
        data[size-1] = null;
        size--;
        return temp;
    }
    
    private void checkIndex(int i) {
        if (data.length <= i || i < 0)
            throw new IndexOutOfBoundsException("Index out of bound: " + i);
    }
    
}
