package DataStructures.NoHierarchical;

public class Stack {
    private Deque deque;

    public Stack() {
        deque = new Deque();
    }

    public void push(String data) {
        deque.addFront(data);
    }

    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        return deque.removeFront();
    }

    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        return deque.peek();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public int size() {
        return deque.size();
    }
}