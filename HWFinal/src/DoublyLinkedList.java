public class DoublyLinkedList<T> {
    public Node<T> head;
    public Node<T> tail;
    public int length;

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    public class Node<T> {
        public T value;
        public Node<T> next;
        public Node<T> prev;

        Node(T value) {
            this.value = value;
        }
    }

    public DoublyLinkedList() {
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
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }

    public Node<T> removeLast() {
        if (length == 0) return null;
        Node<T> temp = tail;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }


    public Node<T> removeFirst() {
        if (length == 0) return null;
        Node<T> temp = head;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        length--;
        return temp;
    }

    public T removeByIndex(int index) {
        Node<T> current = head;
        int currentIndex = 0;

        if (index == 0) {
           return removeFirst().value;
        }

        //to find the node at the specified index
        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        // if the specified index is found we remove it
        if (current != null) {
            if (current == tail) {
               return removeLast().value;
            } else {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                T removedValue = current.value;
                current.next = null;
                current.prev = null;
                length--;
                return removedValue;
            }
        }
        return null;

    }
}
