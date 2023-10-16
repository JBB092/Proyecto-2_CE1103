package DataStructures.NoHierarchical;

/**
 * Represents a node in a doubly linked list.
 *
 * @param <T> The type of data to be stored in the node.
 * 
 * @author José Barquero
 */
public class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> prev;

    /**
     * Constructs a node with the given data and initializes next and prev nodes.
     *
     * @param data The data to be stored in the node.
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Gets the data stored in the node.
     *
     * @return The data stored in the node.
     */
    public T getData() {
        return this.data;
    }

    /**
     * Gets the reference to the next node.
     *
     * @return The reference to the next node.
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * Gets the reference to the previous node.
     *
     * @return The reference to the previous node.
     */
    public Node<T> getPrev() {
        return this.prev;
    }

    /**
     * Sets the reference to the next node.
     *
     * @param nextNode The node to set as the next node.
     */
    public void setNext(Node<T> nextNode) {
        this.next = nextNode;
    }

    /**
     * Sets the reference to the previous node.
     *
     * @param prevNode The node to set as the previous node.
     */
    public void setPrev(Node<T> prevNode) {
        this.prev = prevNode;
    }
}