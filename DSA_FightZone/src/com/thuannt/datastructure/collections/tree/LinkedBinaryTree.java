package com.thuannt.datastructure.collections.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }
        
        public Node<E> getParent() {
            return parent;
        }
        
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
        
        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public void setElement(E element) {
            this.element = element;
        }

        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }
        
    }
    
    protected Node<E> createNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
        return new Node<E>(e, parent, leftChild, rightChild);
    }
    
    //instance variables
    protected Node<E> root = null;
    private int size = 0;

    public LinkedBinaryTree() { }

    /** If node parent is itself, we see it as already removed from tree and does not allowed to add it again
     * @param p
     * @return  Passed validation Node
     * @throws IllegalArgumentException
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if(!(p instanceof Node))
            throw new IllegalArgumentException("Invalid position");
        Node<E> node = (Node<E>) p;
        if(node.getParent() == node) throw new IllegalArgumentException("node was removed");
        return node;
    }
    
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public int size() {
        return this.size;
    }
    
    public Position<E> addRoot(E e) throws IllegalArgumentException {
        if(!isEmpty()) throw new IllegalArgumentException("The tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }
    
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if(parent.getLeft() != null)
            throw new IllegalArgumentException("position already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }
    
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if(parent.getRight() != null)
            throw new IllegalArgumentException("position already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }
    
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E tmp = node.getElement();
        node.setElement(e);
        return tmp;
    }
    
    /** Attach left tree and right tree to the position p
     * @param p postion p
     * @param t1 left tree to be attached
     * @param t2 right tree to ba attached
     * @throws IllegalArgumentException
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if(isInternal(p)) throw new IllegalArgumentException("p must be a left");
        size += t1.size() + t2.size();
        if(!t1.isEmpty()) {
           t1.root.setParent(node);
           node.setLeft(t1.root);
           t1.root = null;
           t1.size = 0;
        }
        
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setLeft(t2.root);
            t1.root = null;
            t1.size = 0;
        }
    }
    
    /** Remove the node from position p and replace it with the child
     * @param p
     * @return Removed element
     * @throws IllegalArgumentException
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E tmp = node.getElement();
        Node<E> parent = node.getParent();
        if(numChildren(p) == 2) throw new IllegalArgumentException("p has two children");
        Node<E> child = node.getLeft() == null ? node.getRight() : node.getLeft();
        if(child != null && isInternal(node)) {
            child.setParent(parent);
        }
        if(node == root) {
            root = child;
        }
        else {
            if(node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return tmp;
    }
   
    private void preorderSubstree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for(Position<E> child : children(p)) {
            preorderSubstree(p, snapshot);
        }
    }
    
    /**
     * @return iterable collection of position using preorder
     */
    private Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<Position<E>>();
        if(!isEmpty()) {
            preorderSubstree(root(), snapshot);
        }
        return snapshot;
    }
    
    private void postorderSubTree(Position<E> p, List<Position<E>> snapshot) {
        for(Position<E> child : children(p)) {
            postorderSubTree(child, snapshot);
        }
        snapshot.add(p);
    }
    
    private Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<Position<E>>();
        if(!isEmpty()) {
            postorderSubTree(root(), snapshot);
        }
        return snapshot;
    }
    
    public Iterable<Position<E>> breadfirst() {
        List<Position<E>> snapshot = new ArrayList<Position<E>>();
        if(!isEmpty()) {
            Queue<Position<E>> fringe = new ConcurrentLinkedDeque<Position<E>>();
            fringe.add(root());
            while(!fringe.isEmpty()) {
                Position<E> p = fringe.poll();
                snapshot.add(p);
                for(Position<E> c : children(p)) {
                    fringe.add(c);
                }
            }
        }
        return snapshot;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Position<E>> positions() {
        return preorder();
    }
    

}
