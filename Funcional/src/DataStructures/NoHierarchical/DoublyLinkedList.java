package DataStructures.NoHierarchical;

/**
 * Represents a doubly linked list.
 *
 * This class provides functionality to work with a doubly linked list, including insertion, traversal, and removal operations.
 *
 * @param <T> The type of data to be stored in the list.
 * @author José Barquero
 */
public class DoublyLinkedList<T> {
    public Node<T> head;
    public Node<T> last;
    public Node<T> current;

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.last = null;
        this.current = null;
    }

    /**
     * Checks if the doubly linked list is empty.
     *
     * @return True if the doubly linked list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null && last == null;
    }

    /**
     * Inserts a new node with the given data at the end of the doubly linked list.
     *
     * @param data The data to be inserted.
     */
    public void insertAtEnd(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            this.head = newNode;
            this.last = newNode;
            this.current = this.head;
        } else {
            Node<T> lastNode = this.last;
            lastNode.setNext(newNode);
            newNode.setPrev(lastNode);
            this.last = newNode;
        }
    }

    /**
     * Displays the data of the current node.
     */
    public void displayCurrent() {
        if (current != null) {
            System.out.println("Valor del nodo actual (current): " + current.getData());
        } else {
            System.out.println("No hay nodo actual (current es null).");
        }
    }

    /**
     * Moves the current node forward.
     * If the current node is the last node, it remains unchanged.
     */
    public void moveCurrentForward() {
        if (current != null && current.getNext() != null) {
            current = current.getNext();
            System.out.println("Current se ha movido hacia adelante.");
        } else {
            System.out.println("Current ya está en el último nodo.");
        }
    }

    /**
     * Moves the current node backward.
     * If the current node is the first node, it remains unchanged.
     */
    public void moveCurrentBackward() {
        if (current != null && current.getPrev() != null) {
            current = current.getPrev();
            System.out.println("Current se ha movido hacia atrás.");
        } else {
            System.out.println("Current ya está en el primer nodo.");
        }
    }

    /**
     * Removes and returns the data from the front of the doubly linked list.
     *
     * @return The data from the front of the list.
     * @throws RuntimeException if the list is empty.
     */
    /**
     * Removes and returns the data from the front of the doubly linked list.
     *
     * @return The data from the front of the list.
     * @throws RuntimeException if the list is empty.
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new RuntimeException("The list is empty.");
        }

        T data = head.getData();
        head = head.getNext();

        if (head != null) {
            head.setPrev(null);
        } else {
            last = null;
        }

        return data;
    }

    /**
     * Returns the size of the doubly linked list.
     *
     * @return The number of nodes in the list.
     */
    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    /**
     * Returns the data of the last node in the doubly linked list.
     *
     * @return The data of the last node, or null if the list is empty.
     */
    public T getLast() {
        if (last == null) {
            return null;
        }
        return last.getData();
    }

    /**
     * Removes and returns the data from the end of the doubly linked list.
     *
     * @return The data from the end of the list.
     * @throws RuntimeException if the list is empty.
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("The list is empty.");
        }

        T data = last.getData();
        last = last.getPrev();

        if (last != null) {
            last.setNext(null);
        } else {
            head = null;
        }

        return data;
    }
}