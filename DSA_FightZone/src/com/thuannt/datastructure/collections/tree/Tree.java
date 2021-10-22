package com.thuannt.datastructure.collections.tree;

import java.util.Iterator;

import com.thuannt.datastructure.collections.Position;

public interface Tree<E> extends Iterable<E> {
    
    /**
     * @return the position of the root of the tree (or null of empty)
     */
    Position<E> root();
    
    /**
     * @param Position p
     * @return return the parent of position p
     * @throws IllegalArgumentException
     */
    Position<E> parent(Position<E> p) throws IllegalArgumentException;
    
    /**
     * @param Position p
     * @return the iterable collection of children of postion p
     * @throws IllegalArgumentException
     */
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;
    
    int numChildren(Position<E> p) throws IllegalArgumentException;
    
    
    /**
     * @param Position p
     * @return true if the position have any children
     * @throws IllegalArgumentException
     */
    boolean isInternal(Position<E> p) throws IllegalArgumentException;

    /**
     * @param Postion p
     * @return true if the position does not have any children
     * @throws IllegalArgumentException
     */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;
    
    /**
     * @param Position p
     * @return true if the postion is root
     * @throws IllegalArgumentException
     */
    boolean isRoot(Position<E> p) throws IllegalArgumentException;
    
    int size();
    
    boolean isEmpty();
    
    Iterator<E> iterator();
    
    /**
     * @return iterable collection of all positions of the tree
     */
    Iterable<Position<E>> positions();
    
}
