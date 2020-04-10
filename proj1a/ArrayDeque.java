/** Array based list.
 *  @author Josh Hug
 */

/** Invariants:
 *  1. addFirst() always
 *
 */

public class ArrayDeque<T> {

    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    private static double refactor = 1.25;
    private static double usageFactor = 0.25;

    /* Helper Methods */
    /** Give the index before the 'index'. */
    private int minusOne(int index) {
        int i = index - 1;
        if (i < 0) {
            i = items.length - 1;
        }
        return i;
    }

    /** Give the index after the 'index'. */
    private int plusOne(int index) {
        int i = index + 1;
        if (i >= items.length) {
            i = 0;
        }
        return i;
    }

    /** Resize the items list if the capacity isn't enough. */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int copyBegin = plusOne(nextFirst);
        int copyEnd = minusOne(nextLast);
        if (copyBegin > copyEnd) {
            int sizeOfFirstHalf = items.length - copyBegin;
            int sizeOfSecondHalf = size() - sizeOfFirstHalf;
            System.arraycopy(items, copyBegin, newItems, 0, sizeOfFirstHalf);
            System.arraycopy(items, 0, newItems, sizeOfFirstHalf, sizeOfSecondHalf);
        } else {
            System.arraycopy(items, copyBegin, newItems, 0, size());
        }
        items = newItems;
        nextFirst = newItems.length - 1;
        nextLast = size;
    }

    /* Interface Starts From Here. */
    /** Creates an empty list. */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    /*
    public ArrayDeque(T x) {
        items = (T[]) new Object[8];
        items[0] = x;
        size = 1;
        nextFirst = 7;
        nextLast = 1;
    }

     */

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Check if the ArrayDeque is empty. */
    public boolean isEmpty() {
        return (size() == 0);
    }

    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /** Add a new item to the first position. */
    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** Gets the ith item in the list (0 is the front). */
    public T get(int i) {
        return items[i];
    }

    /** Returns the item from the back of the list. */
    public T getLast() {
        return get(minusOne(nextLast));
    }

    /** Returns the item from the start of the list. */
    public T getFirst() {
        return get(plusOne(nextFirst));
    }


    /** Delete the first item in the ArrayDeque. Returns the item deleted. */
    public T removeFirst() {
        T res = getFirst();
        nextFirst = plusOne(nextFirst);
        items[nextFirst] = null;
        size -= 1;
        return res;
    }

    /** Deletes item from back of the list and returns deleted item. */
    public T removeLast() {
        T res = getLast();
        nextLast = minusOne(nextLast);
        items[nextLast] = null; // this sentence is optional
        size -= 1;
        return res;
    }

    /** Print the deque. */
    public void printDeque() {
        int begin = plusOne(nextFirst);
        int i = 0;
        while (i < size()) {
            System.out.print(get(begin) + " ");
            begin = plusOne(begin);
            i += 1;
        }
        System.out.println();
    }


}
