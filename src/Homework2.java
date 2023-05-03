import java.util.NoSuchElementException;

public class Homework2 {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.addLast(i);
        }

        list.printList();
        list.reverse();
        list.printList();

    }
}

class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        Node(T value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T removedValue = head.value;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return removedValue;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T removedValue = tail.value;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return removedValue;
    }

    public void insertAfter(T existingValue, T newValue) {
        Node<T> currentNode = head;
        while (currentNode != null && !currentNode.value.equals(existingValue)) {
            currentNode = currentNode.next;
        }
        if (currentNode == null) {
            throw new NoSuchElementException();
        }
        Node<T> newNode = new Node<>(newValue);
        newNode.prev = currentNode;
        newNode.next = currentNode.next;
        if (currentNode.next != null) {
            currentNode.next.prev = newNode;
        } else {
            tail = newNode;
        }
        currentNode.next = newNode;
        size++;
    }

    public void insertBefore(T existingValue, T newValue) {
        Node<T> currentNode = tail;
        while (currentNode != null && !currentNode.value.equals(existingValue)) {
            currentNode = currentNode.prev;
        }
        if (currentNode == null) {
            throw new NoSuchElementException();
        }
        Node<T> newNode = new Node<>(newValue);
        newNode.next = currentNode;
        newNode.prev = currentNode.prev;
        if (currentNode.prev != null) {
            currentNode.prev.next = newNode;
        } else {
            head = newNode;
        }
        currentNode.prev = newNode;
        size++;
    }

    public boolean contains(T value) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public void reverse() {
        Node<T> temp = null;
        Node<T> current = head;

    /* swap next and prev for all nodes of
       doubly linked list */
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }

    /* Before changing head, check for the cases like empty
       list and list with only one node */
        if (temp != null) {
            head = temp.prev;
        }
    }

    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}

