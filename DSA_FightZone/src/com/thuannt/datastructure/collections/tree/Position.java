package com.thuannt.datastructure.collections.tree;

public interface Position<E> {
    
    /**
     * @return element store at this position
     * @throws IllegalStateException
     */
    E getElement() throws IllegalStateException;

}
