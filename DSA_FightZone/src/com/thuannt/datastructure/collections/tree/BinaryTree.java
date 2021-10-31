package com.thuannt.datastructure.collections.tree;

public interface BinaryTree<E> extends Tree<E> {
    
    /**
     * @param p
     * @return left child
     * @throws IllegalArgumentException
     */
    Position<E> left(Position<E> p) throws IllegalArgumentException;
    
    /**
     * @param p
     * @return right child
     * @throws IllegalArgumentException
     */
    Position<E> right(Position<E> p) throws IllegalArgumentException;

    /**
     * @param Postion
     * @return a sibling (Node in same level) of position p
     * @throws IllegalArgumentException
     */
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;


}
