package DataStructures.NoHierarchical;

/**
 * Represents a stack for TCP operations implemented using a deque.
 *
 * This class provides functionality to work with a stack, including push, pop, peek,
 * checking for emptiness, and getting the size.
 *
 * @author José Barquero
 */
public class StackTCP {
    private Deque deque;

    /**
     * Constructs an empty stack for TCP operations.
     */
    public StackTCP() {
        deque = new Deque();
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param data The data to be pushed onto the stack.
     */
    public void push(String data) {
        deque.addFront(data);
    }

    /**
     * Pops the element from the top of the stack and returns it.
     *
     * @return The element popped from the stack.
     * @throws RuntimeException if the stack is empty.
     */
    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        return deque.removeFront();
    }

    /**
     * Retrieves the element from the top of the stack without removing it.
     *
     * @return The element at the top of the stack.
     * @throws RuntimeException if the stack is empty.
     */
    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        return deque.peek();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    /**
     * Returns the size of the stack.
     *
     * @return The number of elements in the stack.
     */
    public int size() {
        return deque.size();
    }
}
