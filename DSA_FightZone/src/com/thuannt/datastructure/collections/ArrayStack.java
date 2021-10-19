package com.thuannt.datastructure.collections;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E>{
    
    public static final int CAPACITY = 1000;
    private E[] data; //global attribute to store the data reference
    private int index = -1;
    public ArrayStack() {
        this(CAPACITY);
    }
    
    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        if(data.length == 0) return true;
        else return false;
    }

    @Override
    public void push(E e) throws IllegalStateException {
        if(index == CAPACITY - 1) throw new IllegalStateException("Stack is full");
        data[++index] = e;
    }

    @Override
    public E top() {
        if(isEmpty()) return null;
        return data[index];
    }

    @Override
    public E pop() {
        if(isEmpty()) return null;
        E e = data[index];
        data[index--] = null;
        return e;
    }

    @Override
    public String toString() {
        return "ArrayStack [data=" + Arrays.toString(data) + ", current index=" + index + "]";
    }
    

}
