package data_sturcs.linked_list;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    private static int size = 0;
    private Node <T> head = null; // current head value
    private Node <T> tail = null; //current tail value

    public static int getSize() {
        return size;
    }

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // checks if linkedlist is empty
    public boolean isEmpty() {
        return getSize() == 0;
    }

    // return true if value is in linkedlist
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    // checks the value of the first node
    public T getFirstElement() {
        if (isEmpty()) {
            throw new RuntimeException("Linked List is empty");
        }
        return head.data;
    }

    // checks the value of the last node
    public T getLastElement() {
        if (isEmpty()) {
            throw new RuntimeException("Linked List is empty");
        }
        return tail.data;
    }

    // clears linkedlist
    public void clear() {
        Node<T> traverse = head;
        while (traverse != null) {
            Node<T> next = traverse.next;
            traverse.prev = traverse.next = null; // clearing nodes in front and before
            traverse.data = null;
            traverse = next; // moving on to next node
        }
        head = tail = traverse = null; // clearing all variables
        size = 0; // reseting size
    }

    // add element to end of the linkedlist
    public void addLast(T elem) {
        if (isEmpty()) {
            tail = head = new Node<T>(elem, null, null);
        } else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    // add element to the front of the linkedlist
    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null); // only 1 element in linkedlist right now
        } else {
            head.prev = new Node<T>(elem, null, head); // storing param to pass into head
            head = head.prev;
        }
        size++;
    }

    // removes the first element in the linkedlist
    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Linked List is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        } else {
            head.prev = null;
        }
        return data;
    }

    // removes the last element in the linkedlist
    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Linked List is empty");
        }

        T data = tail.data;
        tail = tail.prev;
        size--;
        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }
        return data;
    }

    // removes any node from the linkedlist, passing the node
    private T remove(Node<T> node) {
        // exceptions
        if (isEmpty()) {
            throw new RuntimeException("Linked List is empty");
        }

        if (node.prev == null) {
            // removing first element
            removeFirst();
        } else if (node.next == null) {
            // removing last element
            removeLast();
        } else {
            // removing elements in the middle of the linked list
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        T data = node.data;
        // cleanning up memory
        node.data = null;
        node = node.prev = node.next = null;
        size--;

        return data;
    }

    // removes any node from the linkedlist, passing the value
    public boolean remove(Object obj) {
        Node<T> trav = head;

        // allows user to remove null values
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    // removes any node from the linkedlist, passing in the index number
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        int i;
        Node<T> trav = new Node<T>(null, null, null);

        // traverse from front of index if index < getSize()/2, else traverse from
        // behind
        if (index < getSize() / 2) {
            for (i = 0, trav = head; i < index; i++) {
                trav = trav.next;
            }
        } else if (index > getSize() / 2) {
            for (i = getSize(), trav = tail; i > index; i--) {
                trav = trav.prev;
            }
        }
        return remove(trav);
    }

    // returns index of any value, pass in value
    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;

        // allows user to remove null values
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        Node<T> trav = head;
        int index = 0;

        while (trav != null) {
            index++;
            if (index == getSize()) {
                sb.append(trav.data);
            } else {
                sb.append(trav.data + ", ");
            }
            trav = trav.next;
        }

        sb.append(" ]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            dll.addLast(i);
        }
        System.out.println(dll);
        dll.addFirst(6);
        System.out.println(dll);
        Object obj = new Integer(2); 
        dll.removeFirst(); 
        System.out.println(dll);
        dll.remove(obj);
        System.out.println(dll);
    }
}