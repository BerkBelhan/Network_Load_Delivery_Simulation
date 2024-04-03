public class Stack<T> {
    private Node<T> top;
    private int height;

    public boolean isEmpty() {
        return height == 0;
    }

    class Node<t> {
        t value;
        Node<t> next;

        Node(t value) {
            this.value = value;
        }
    }

    public Stack() {
        top = null;
        height = 0;
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        if (height == 0) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        height++;
    }

    public T pop() {
        if (height == 0)
            return null;
        Node<T> temp = top;
        top = top.next;
        temp.next = null;
        height--;
        return temp.value;
    }
}
