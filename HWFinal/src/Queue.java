public class Queue<T> {
    private Node<T> first;
    private Node<T> last;
    private int length;

    public boolean isEmpty() {
        return length == 0;
    }

    class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    public Queue() {
        first = null;
        last = null;
        length = 0;
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);
        if (length == 0) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        length++;
    }

    public T dequeue() {
        if (length == 0)
            return null;
        Node<T> temp = first;
        if (length == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            temp.next = null;
        }
        length--;
        return temp.value;
    }
}
