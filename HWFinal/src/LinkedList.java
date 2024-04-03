public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length = 0;

    class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        head = null;
        tail = null;
        length = 0;
    }


    public void append(T value) {
        Node<T> newNode = new Node<>(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public Node<T> removeLast() {
        if (length == 0)
            return null;
        Node<T> temp = head;
        Node<T> pre = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    public Node<T> removeFirst() {
        if (length == 0)
            return null;
        Node<T> temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) {
            tail = null;
        }
        return temp;
    }

    public Node<T> get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }


    public Node<T> remove(int index) {
        if (index < 0 || index >= length)
            return null;
        if (index == 0)
            return removeFirst();
        if (index == length - 1)
            return removeLast();

        Node<T> prev = get(index - 1);
        Node<T> temp = prev.next;

        prev.next = temp.next;
        temp.next = null;
        length--;
        return temp;
    }
}
