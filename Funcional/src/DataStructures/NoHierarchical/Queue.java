package DataStructures.NoHierarchical;

/**
 * Represents a queue implemented using a doubly linked list.
 *
 * This class provides functionality to work with a queue, including enqueue and dequeue operations.
 *
 * @author José Barquero
 */
public class Queue {
    private DoublyLinkedList doublyLinkedList; // Doubly linked list used to implement the queue.

    /**
     * Constructs an empty queue.
     */
    public Queue() {
        doublyLinkedList = new DoublyLinkedList();
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param data The data to be added to the rear of the queue.
     */
    public void enqueue(String data) {
        doublyLinkedList.insertAtEnd(data);
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return The element at the front of the queue.
     * @throws RuntimeException if the queue is empty.
     */
    public String dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty.");
        }
        return doublyLinkedList.removeFromFront();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    /**
     * Returns the size of the queue.
     *
     * @return The number of nodes in the queue.
     */
    public int size() {
        return doublyLinkedList.size();
    }
}