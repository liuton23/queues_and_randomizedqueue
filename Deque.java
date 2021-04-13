/* *****************************************************************************
 *  Name: Tong Liu
 *  Date: 4/13/2021
 *  Description: This is the implementation of deque which is a queue that can
 *  enqueue or dequeue at the front or the end.
 **************************************************************************** */


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first = null;
    private Node<Item> last = null;
    private int size = 0;

    private class Node<Item> {
        private Item item = null;
        private Node<Item> next = null;
        private Node<Item> pre = null;

        public Node(Item i) {
            item = i;
        }
    }

    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            Node<Item> newNode = new Node<>(item);
            first = newNode;
            last = newNode;
        }
        else {
            Node<Item> newfirst = new Node<>(item);
            Node<Item> oldfirst = first;
            first = newfirst;
            newfirst.next = oldfirst;
            oldfirst.pre = newfirst;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            Node<Item> newNode = new Node<>(item);
            first = newNode;
            last = newNode;
        }
        else {
            Node<Item> newlast = new Node<>(item);
            Node<Item> oldlast = last;
            last = newlast;
            newlast.pre = oldlast;
            oldlast.next = newlast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size == 1) {
            Item oldItem = first.item;
            first = null;
            --size;
            return oldItem;
        }
        else {
            Item oldItem = first.item;
            first = first.next;
            first.pre = null;
            --size;
            return oldItem;
        }

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size == 1) {
            Item oldItem = last.item;
            last = null;
            --size;
            return oldItem;
        }
        else {
            Item oldItem = last.item;
            last = last.pre;
            last.next = null;
            --size;
            return oldItem;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            else {
                Item next = current.item;
                current = current.next;
                return next;
            }
        }
    }


    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        dq.addFirst(1);
        dq.addFirst(2);
        dq.addLast(3);
        dq.addFirst(30);
        dq.addLast(50);
        dq.addLast(4);
        for (int i : dq) {
            System.out.println(i);
        }
    }
}
