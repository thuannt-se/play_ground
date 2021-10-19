package com.thuannt.datastructure.collections;


/**
 * @author thuannt
 *
 * @param <E>
 */
public interface Stack<E> {
    
    int size();
    
    boolean isEmpty();
    
    void push(E e);
    
    E top();
    
    E pop();
    
}
