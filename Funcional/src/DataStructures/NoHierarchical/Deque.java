package DataStructures.NoHierarchical;

public class Deque {
    private DoublyLinkedList doublyLinkedList;

    public Deque() {
        doublyLinkedList = new DoublyLinkedList();
    }

    public void addFront(String data) {
        doublyLinkedList.insertAtFront(data);
    }

    public String removeFront() {
        if (isEmpty()) {
            throw new RuntimeException("The deque is empty.");
        }
        return doublyLinkedList.removeFromFront();
    }

    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("The deque is empty.");
        }
        return doublyLinkedList.head.getData();
    }

    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    public int size() {
        return doublyLinkedList.size();
    }
}
