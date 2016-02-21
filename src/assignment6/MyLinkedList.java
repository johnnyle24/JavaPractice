package assignment6;
 
import java.util.NoSuchElementException;
 
/**
 * Class that creates LinkedLists that uses 0-based indexing.
 *
 * @author Johnny Le & Emily Dennis
 *
 * @param <E>
 *            - a generic type
 *
 */
 
public class MyLinkedList<E> implements List<E> {
 
        // Private Member variables
        private Node first;
        private Node last;
        private Node current;
        private int listTotal;
 
        /**
         * Class that represents a node, to be contained by a linkedList
         *
         * @author Emily Dennis, Johnny Le
         *
         */
        private class Node {
                E item;
                Node next;
                Node prev;
        }
 
        /**
         * Constructor for MyLinkedList
         */
        public MyLinkedList() {
                // Set most of the variables to null, since the list starts out as empty
                first = null;
                last = null;
                current = null;
                listTotal = 0;
        }
 
        /**
         * Inserts the specified element at the beginning of the list. O(1) for a
         * doubly-linked list.
         *
         * @param E
         *            -- element to insert
         */
        @Override
        public void addFirst(E element) {
                // The first time that an element is added to the first position
                if (first == null) {
                        first = new Node();
                        first.item = element;
                        first.prev = null;
                        last = first;
                        last.next = null;
                        listTotal++;
                }
 
                // The next time the element is added to the first position
                else {
                        current = new Node();
                        current.item = (E) element;
                        current.next = first;
                        current.prev = null;
                        first.prev = current;
                        first = current;
                        listTotal++;
                }
        }
 
        /**
         * Inserts the specified element at the end of the list. O(1) for a
         * doubly-linked list.
         *
         * @param -- element to insert
         */
        @Override
        public void addLast(E element) {
 
                // If the list is empty, this is the first element added to the list
                if (last == null) {
                        last = new Node();
                        last.item = (E) element;
                        last.next = null;
                        first = last;
                        first.prev = null;
                        listTotal++;
                }
 
                // Otherwise, add to the end of the list
                else {
                        current = new Node();
                        current.item = (E) element;
                        current.prev = last;
                        current.next = null;
                        last.next = current;
                        last = current;
                        listTotal++;
                }
        }
 
        /**
         * Inserts the specified element at the specified position in the list.
         * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
         * index > size()) O(N) for a doubly-linked list.
         *
         * @param int -- index at which to insert
         * @param E
         *            -- the element to insert at the specified index
         */
        @Override
        public void add(int index, E element) throws IndexOutOfBoundsException {
 
                if (index > size() || index < 0) {
                        throw new IndexOutOfBoundsException();
                }
 
                if (index == 0) {
                        addFirst(element);
                } else if (index == listTotal) {
                        addLast(element);
                } else {
 
                        int median = (listTotal / 2);
 
                        if (index >= median) {
 
                                current = last;
 
                                for (int i = listTotal; i > index; i--) {
                                        current = current.prev;
                                }
 
                                Node after = current.next;
                                Node nItem = new Node();
 
                                nItem.item = (E) element;
                                nItem.next = current.next;
                                nItem.prev = current;
 
                                current.next = nItem;
                                after.prev = nItem;
                                listTotal++;
 
                        }
 
                        else {
 
                                current = first;
 
                                for (int i = 0; i < index; i++) {
                                        current = current.next;
                                }
 
                                Node after = current.next;
                                Node nItem = new Node();
 
                                nItem.item = (E) element;
                                nItem.next = current.next;
                                nItem.prev = current;
 
                                current.next = nItem;
                                after.prev = nItem;
                                listTotal++;
 
                        }
                }
        }
 
        /**
         * Returns the first element in the list. Throws NoSuchElementException if
         * the list is empty. O(1) for a doubly-linked list.
         *
         * @return E -- the first element of the linked list
         */
        @Override
        public E getFirst() throws NoSuchElementException {
                if (isEmpty()) {
                        throw new NoSuchElementException();
                }
 
                return first.item;
        }
 
        /**
         * Returns the last element in the list. Throws NoSuchElementException if
         * the list is empty. O(1) for a doubly-linked list.
         *
         * @return E -- the last element of the linked list
         */
        @Override
        public E getLast() throws NoSuchElementException {
                if (isEmpty()) {
                        throw new NoSuchElementException();
                }
 
                return last.item;
        }
 
        /**
         * Returns the element at the specified position in the list. Throws
         * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
         * size()) O(N) for a doubly-linked list.
         *
         * @param int -- index of the element we want to get
         * @return E -- element we wanted to get
         */
        @Override
        public E get(int index) throws IndexOutOfBoundsException {
 
                if (index >= listTotal || index < 0) {
                        throw new IndexOutOfBoundsException();
                }
 
                if (index == 0) {
                        return getFirst();
                }
 
                int median = (listTotal / 2);
 
                if (index >= median) {
 
                        current = last;
 
                        for (int i = listTotal - 1; i > index; i--) {
                                current = current.prev;
                        }
                        return current.item;
                }
 
                else {
 
                        current = first;
 
                        for (int i = 0; i < index; i++) {
                                current = current.next;
                        }
                        return current.item;
                }
        }
 
        /**
         * Removes and returns the first element from the list. Throws
         * NoSuchElementException if the list is empty. O(1) for a doubly-linked
         * list.
         *
         * @return E -- Element removed
         */
        @Override
        public E removeFirst() throws NoSuchElementException {
                if (isEmpty()) {
                        throw new NoSuchElementException();
                }
 
                current = first;
                first = current.next;
                listTotal--;
 
                if (listTotal == 0) {
                        last = null;
                }
                return current.item;
        }
 
        /**
         * Removes and returns the last element from the list. Throws
         * NoSuchElementException if the list is empty. O(1) for a doubly-linked
         * list.
         *
         * @return E -- Element removed
         */
        @Override
        public E removeLast() throws NoSuchElementException {// Seems kind of messy
                                                                                                                        // but it works
                if (isEmpty()) {
                        throw new NoSuchElementException();
                }
 
                current = last;
 
                if (current.prev != null) {
                        current.prev.next = null;
                }
 
                last = current.prev;
                listTotal--;
 
                if (listTotal == 0) {
                        first = null;
                }
 
                return current.item;
        }
 
        /**
         * Removes and returns the element at the specified position in the list.
         * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
         * index >= size()) O(N) for a doubly-linked list.
         *
         * @param int -- index to remove
         * @return E -- element removed
         */
        @Override
        public E remove(int index) throws IndexOutOfBoundsException {
 
                if (index >= listTotal || index < 0) {
                        throw new IndexOutOfBoundsException();
                }
 
                if (index == 0) {
                        return removeFirst();
                }
 
                if (index == listTotal - 1) {
                        return removeLast();
                }
 
                int median = (listTotal / 2);
 
                if (index >= median) {
 
                        current = last;
 
                        for (int i = listTotal - 1; i > index; i--) {
                                current = current.prev;
                        }
 
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
 
                }
 
                else {
 
                        current = first;
 
                        for (int i = 0; i < index; i++) {
                                current = current.next;
                        }
 
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
 
                }
 
                listTotal--;
                return current.item;
        }
 
        /**
         * Returns the index of the first occurrence of the specified element in the
         * list, or -1 if this list does not contain the element. O(N) for a
         * doubly-linked list.
         *
         * @param E
         *            -- Element to search for
         * @return int -- index where the element is stored
         */
        @Override
        public int indexOf(E element) {
                int tally = 0;
                for (Node x = first; x != null; x = x.next) {
                        if (x.item == element)
                                return tally;
                        else
                                tally++;
                }
                return -1;
        }
 
        /**
         * Returns the index of the last occurrence of the specified element in this
         * list, or -1 if this list does not contain the element. O(N) for a
         * doubly-linked list.
         *
         * @param E
         *            -- Element to search for
         * @return int -- index where the element is stored
         */
        @Override
        public int lastIndexOf(E element) {
                int tally = listTotal - 1;
                for (Node x = last; x != null; x = x.prev) {
                        if (x.item == element)
                                return tally;
                        else
                                tally--;
                }
                return -1;
        }
 
        /**
         * Returns the number of elements in this list. O(1) for a doubly-linked
         * list.
         *
         * @return int -- size of the LinkedList
         */
        @Override
        public int size() {
                return listTotal;
        }
 
        /**
         * Returns true if this collection contains no elements. O(1) for a
         * doubly-linked list.
         *
         * @return boolean -- if the list is empty
         */
        @Override
        public boolean isEmpty() {
                if (listTotal == 0 && first == null && last == null) {
                        return true;
                }
                return false;
        }
 
        /**
         * Removes all of the elements from this list. O(1) for a doubly-linked
         * list.
         */
        @Override
        public void clear() {
                first = null;
                last = null;
                listTotal = 0;
        }
 
        /**
         * Returns an array containing all of the elements in this list in proper
         * sequence (from first to last element). O(N) for a doubly-linked list.
         *
         * @return E[] -- array of the collection
         */
        @Override
        public E[] toArray() {
                E[] myList = (E[]) new Object[listTotal];
                int i = 0;
 
                for (Node x = first; x != null; x = x.next) {
                        myList[i] = (E) x.item;
                        i++;
                }
                return myList;
        }
 
}