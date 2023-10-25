package DataStructures.NoHierarchical;

import java.util.EmptyStackException;

/**
 * The `CustomStack` class represents a stack data structure that can store elements of a generic type.
 * It provides basic stack operations such as push, pop, peek, isEmpty, size, and clear.
 *
 * @param <E> The type of elements that the stack can store.
 * @author José Barquero
 */
public class CustomStack<E> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty stack with the default initial capacity of 10.
     */
    public CustomStack() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Pushes an element onto the stack.
     *
     * @param item The element to be pushed onto the stack.
     */
    public void push(E item) {
        ensureCapacity();
        elements[size] = item;
        size++;
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return The element removed from the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E item = peek();
        elements[size - 1] = null; // Allow for garbage collection
        size--;
        return item;
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return The top element of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) elements[size - 1];
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return The number of elements in the stack.
     */
    public int size() {
        return size;
    }

    /**
     * Removes all elements from the stack, making it empty.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Ensures that the stack has enough capacity to accommodate additional elements.
     * If the current capacity is reached, the stack's capacity is doubled.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = java.util.Arrays.copyOf(elements, newCapacity);
        }
    }
}