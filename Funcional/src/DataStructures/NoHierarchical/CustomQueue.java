package DataStructures.NoHierarchical;

import java.util.LinkedList;

/**
 * CustomQueue is a simple implementation of a queue data structure using a LinkedList.
 * It allows you to enqueue and dequeue elements.
 *
 * @author José Barquero
 */
public class CustomQueue {

    private final LinkedList<Object> list;

    /**
     * Constructs an empty CustomQueue.
     */
    public CustomQueue() {
        this.list = new LinkedList<>();
    }

    /**
     * Enqueues an element at the end of the queue.
     *
     * @param element The element to be enqueued.
     */
    public void enqueue(Object element) {
        this.list.addLast(element);
    }

    /**
     * Dequeues and removes the element from the front of the queue.
     *
     * @return The element dequeued from the queue.
     */
    public Object dequeue(){
        return this.list.removeFirst();
    }

    /**
     * Gets the element from the front of the queue without removing it.
     *
     * @return The element at the front of the queue.
     */
    public Object getFirst(){
        return this.list.getFirst();
    }

    /**
     * Returns the underlying LinkedList representing the queue.
     *
     * @return The LinkedList representing the queue.
     */
    public LinkedList<Object> getList() {
        return list;
    }
}