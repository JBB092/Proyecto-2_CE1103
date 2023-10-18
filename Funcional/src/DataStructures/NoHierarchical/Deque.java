package DataStructures.NoHierarchical;

/**
 * Represents a double-ended queue (deque) implemented using a doubly linked list.
 *
 * This class provides functionality to work with a deque, including adding to the front,
 * removing from the front, peeking at the front, checking for emptiness, and getting the size.
 *
 * @author José Barquero
 */
public class Deque {
    private DoublyLinkedList doublyLinkedList;

    /**
     * Constructs an empty deque.
     */
    public Deque() {
        doublyLinkedList = new DoublyLinkedList();
    }

    /**
     * Adds an element to the front of the deque.
     *
     * @param data The data to be added to the front of the deque.
     */
    public void addFront(String data) {
        doublyLinkedList.insertAtFront(data);
    }

    /**
     * Removes and returns the element from the front of the deque.
     *
     * @return The element removed from the front of the deque.
     * @throws RuntimeException if the deque is empty.
     */
    public String removeFront() {
        if (isEmpty()) {
            throw new RuntimeException("The deque is empty.");
        }
        return doublyLinkedList.removeFromFront();
    }

    /**
     * Retrieves the element at the front of the deque without removing it.
     *
     * @return The element at the front of the deque.
     * @throws RuntimeException if the deque is empty.
     */
    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("The deque is empty.");
        }
        return doublyLinkedList.head.getData();
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
     * Returns the size of the deque.
     *
     * @return The number of elements in the deque.
     */
    public int size() {
        return doublyLinkedList.size();
    }
}