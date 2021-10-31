package com.thuannt.datastructure.collections.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E>{

    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if(parent == null) return null;
        if(p == left(parent)) 
            return right(parent);
        else 
            return left(parent);
        
    }
    
    public int numChildren(Position<E> p) {
        int count = 0;
        if(left(p) != null) count++;
        if(right(p) != null) count++;
        return count;
    }
    
    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<Position<E>>(2);
        if(left(p) != null) snapshot.add(p);
        if(right(p) != null) snapshot.add(p);
        return snapshot;
    }
    
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }
        
        @Override
        public E next() {
            return posIterator.next().getElement();
        }
        
        public void remove() { posIterator.remove(); }
        
    }
    
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
}
