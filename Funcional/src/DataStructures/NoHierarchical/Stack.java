package DataStructures.NoHierarchical;

import java.util.LinkedList;

/**
 * Represents a stack implemented using a linked list.
 *
 * This class provides functionality to work with a stack, including push, pop, peek,
 * checking for emptiness, and getting the size.
 *
 * @param <T> The type of data to be stored in the stack.
 * @author José Barquero
 */
public class Stack<T> {
    private LinkedList<T> stack;

    /**
     * Constructs an empty stack.
     */
    public Stack() {
        stack = new LinkedList<>();
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param data The data to be pushed onto the stack.
     */
    public void push(T data) {
        stack.addFirst(data);
    }

    /**
     * Pops the element from the top of the stack and returns it.
     *
     * @return The element popped from the stack.
     * @throws RuntimeException if the stack is empty.
     */
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        return stack.removeFirst();
    }

    /**
     * Retrieves the element from the top of the stack without removing it.
     *
     * @return The element at the top of the stack.
     * @throws RuntimeException if the stack is empty.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        return stack.getFirst();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Returns the size of the stack.
     *
     * @return The number of elements in the stack.
     */
    public int size() {
        return stack.size();
    }
}