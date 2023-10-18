package DataStructures.NoHierarchical;

/**
 * Represents a node in a doubly linked list.
 *
 * @param <String> The type of data to be stored in the node.
 * 
 * @author José Barquero
 */
public class Node {
    public String data;  // The data stored in the node.
    public Node next;    // The reference to the next node.
    public Node prev;    // The reference to the previous node.

    /**
     * Constructs a node with the given data and initializes next and prev nodes.
     *
     * @param data The data to be stored in the node.
     */
    public Node(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Gets the data stored in the node.
     *
     * @return The data stored in the node.
     */
    public String getData() {
        return this.data;
    }

    /**
     * Gets the reference to the next node.
     *
     * @return The reference to the next node.
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * Gets the reference to the previous node.
     *
     * @return The reference to the previous node.
     */
    public Node getPrev() {
        return this.prev;
    }

    /**
     * Sets the reference to the next node.
     *
     * @param nextNode The node to set as the next node.
     */
    public void setNext(Node nextNode) {
        this.next = nextNode;
    }

    /**
     * Sets the reference to the previous node.
     *
     * @param prevNode The node to set as the previous node.
     */
    public void setPrev(Node prevNode) {
        this.prev = prevNode;
    }
}
