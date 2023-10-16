package DataStructures;

/**
 * Represents a double-ended queue (Deque) implemented using a doubly linked list.
 *
 * This class provides functionality to work with a double-ended queue, allowing insertion and removal
 * of elements from both the front and rear of the queue.
 *
 * @param <T> The type of data to be stored in the deque.
 * @author José Barquero
 */
public class Deque<T> { //"Double-Ended Queue"
    private DoublyLinkedList<T> doublyLinkedList;

    /**
     * Constructs an empty deque.
     */
    public Deque() {
        doublyLinkedList = new DoublyLinkedList<>();
    }

    /**
     * Adds an element to the front of the deque.
     *
     * @param data The data to be added to the front of the deque.
     */
    public void addFront(T data) {
        doublyLinkedList.insertAtEnd(data);  // Insert at the end for a doubly linked list
    }

    /**
     * Adds an element to the rear of the deque.
     *
     * @param data The data to be added to the rear of the deque.
     */
    public void addRear(T data) {
        doublyLinkedList.insertAtEnd(data);  // Insert at the end for a doubly linked list
    }

    /**
     * Removes and returns the element at the front of the deque.
     *
     * @return The element at the front of the deque.
     */
    public T removeFront() {
        return doublyLinkedList.removeFromFront();
    }

    /**
     * Removes and returns the element at the rear of the deque.
     *
     * @return The element at the rear of the deque.
     */
    public T removeRear() {
        // Assuming this is a valid operation for the deque
        return doublyLinkedList.removeFromFront();  // Remove from the front for a doubly linked list
    }

    /**
     * Checks if the deque is empty.
     *
     * @return True if the deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    /**
     * Displays the data of the current node.
     */
    public void displayCurrent() {
        doublyLinkedList.displayCurrent();
    }

    /**
     * Moves the current node forward.
     */
    public void moveCurrentForward() {
        doublyLinkedList.moveCurrentForward();
    }

    /**
     * Moves the current node backward.
     */
    public void moveCurrentBackward() {
        doublyLinkedList.moveCurrentBackward();
    }

    /**
     * Returns the size of the deque.
     *
     * @return The number of nodes in the deque.
     */
    public int size() {
        return doublyLinkedList.size();
    }
}
