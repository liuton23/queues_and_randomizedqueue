/* *****************************************************************************
 *  Name: Tong Liu
 *  Date: 4/13/2021
 *  Description: This is the implementation of randomized queue. This queue
 *  dequeues randomly and has a random iterator
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // construct an empty randomized queue
    private int N = 0;
    private Item[] queue;
    private int length = 0;

    public RandomizedQueue(){
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return N;
    }

    private void resize(int size){
        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < N; i++){
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()){
            queue = (Item[]) new Object[1];
            queue[0] = item;
            length = 1;
        } else {
            if (N == length) {
                resize(2 * length);
                length = length*2;
            }
            queue[N] = item;
        }
        N++;
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty())
            throw new NoSuchElementException();
        if (N == 1) {
            Item item = queue[0];
            queue[0] = null;
            --N;
            return item;
        } else {
            int index = StdRandom.uniform(N);
            Item item = queue[index];
            queue[index] = queue[N - 1];
            queue[N - 1] = null;
            --N;
            if (N < length / 4 && length > 4) {
                resize(length / 4);
                length = length / 4;
            }
            return item;
        }
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty())
            throw new NoSuchElementException();
        if (N == 1) {
            return queue[0];
        } else {
            int index = StdRandom.uniform(N);
            return queue[index];
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>
    {
        private int i = N;
        private Item[] copy = (Item[]) new Object[N];

        public RandomIterator() {
            for (int j = 0; j < N; j++){
                copy[j] = queue[j];
            }
        }


        public boolean hasNext() {
            return i > 0;
        }
        public void remove() { /* not supported */
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int index = StdRandom.uniform(i);
            Item ans = copy[index];
            copy[index] = copy[i-1];
            copy[i-1] = null;
            i--;
            return ans;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }
}