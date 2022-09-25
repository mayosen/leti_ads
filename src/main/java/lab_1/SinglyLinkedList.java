package lab_1;

public class SinglyLinkedList<E extends Comparable<E>> implements List<E> {
    private int size;
    private Node head;

    public SinglyLinkedList() {
        size = 0;
    }

    public SinglyLinkedList(E[] array) {
        for (E e : array) {
            add(e);
        }
        size = array.length;
    }

    private class Node {
        E data;
        Node next;

        public Node(E data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E e) {
        if (head == null) {
            head = new Node(e);
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = new Node(e);
        }
        size++;
    }

    @Override
    public boolean remove(Object e) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            if (current.data.equals(e)) {
                if (current == head) {
                    head = head.next;
                } else {
                    previous.next = current.next;
                }

                current.next = null;
                current.data = null;
                size--;
                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    @Override
    public boolean remove(int index) {
        checkIndex(index);
        Node previous = null;
        Node current = head;

        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }

        if (current == head) {
            head = head.next;
        } else {
            previous.next = current.next;
        }

        current.next = null;
        current.data = null;
        size--;

        return true;
    }

    @Override
    public E get(int index) {
        return getNode(index).data;
    }

    private Node getNode(int index) {
        checkIndex(index);
        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E set(int index, E e) {
        Node node = getNode(index);
        E previousData = node.data;
        node.data = e;

        return previousData;
    }

    @Override
    public boolean contains(Object e) {
        return indexOf(e) != -1;
    }

    @Override
    public int indexOf(Object e) {
        Node current = head;
        Node target = null;
        int index;

        for (index = 0; index < size; index++) {
            if (current.data.equals(e)) {
                target = current;
                break;
            }
            current = current.next;
        }

        return target != null ? index : -1;
    }

    @Override
    public void clear() {
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = null;
            current.data = null;
            current = next;
        }

        size = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof List<?> other)) {
            return false;
        }

        if (size != other.size()) {
            return false;
        }

        if (o.getClass() == SinglyLinkedList.class) {
            return equalsSinglyLinkedList((SinglyLinkedList<?>) other);
        } else {
            return equalsList(other);
        }
    }

    private boolean equalsSinglyLinkedList(SinglyLinkedList<?> other) {
        Node current = head;
        SinglyLinkedList<?>.Node otherCurrent = other.head;

        while (current != null) {
            if (!current.data.equals(otherCurrent.data)) {
                return false;
            }
            current = current.next;
            otherCurrent = otherCurrent.next;
        }

        return true;
    }

    private boolean equalsList(List<?> other) {
        Node current = head;

        for (int i = 0; i < size; i++) {
            if (!current.data.equals(other.get(i))) {
                return false;
            }
            current = current.next;
        }

        return true;
    }

    /**
     * Сортировка пузырьком.
     */
    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            Node secondPrevious = null;
            Node previous = head;
            Node current = head.next;
            boolean changed = false;

            while (current != null) {
                if (previous.data.compareTo(current.data) > 0) {
                    if (previous == head) {
                        previous.next = current.next;
                        head = current;
                        head.next = previous;
                        previous = head;
                        current = head.next;
                    } else {
                        secondPrevious.next = current;
                        previous.next = current.next;
                        current.next = previous;
                    }
                    changed = true;
                }

                secondPrevious = previous;
                previous = current;
                current = current.next;
            }

            if (!changed) {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "SinglyLinkedList{" +
                "size=" + size +
                '}';
    }
}
