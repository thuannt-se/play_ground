package com.thuannt.datastructure.collections;


/**Re-implement Java Collections Library List<E> for learning puprpose
 * With a little improvement that allow user can set get at any index
 * @author thuannt
 *
 * @param <E>
 */
public interface List<E> {
    
    int size();
    
    boolean isEmpty();
    
    /**Dynamic array is automatically extends its capacity if the array is full
     * @param e
     */
    void add(E e);
    
    E get(int i) throws IndexOutOfBoundsException;
    
    E set(E e, int i) throws IndexOutOfBoundsException;
    
    E remove(int i) throws IndexOutOfBoundsException;
}
