/** Array based list.
 *  @author Josh Hug
 */

public class ArrayList{

    private int size;
    private int[] items;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty list. */
    public ArrayList() {
        size = 0;
        items =  new int[100];
        nextFirst = 5;
        nextLast = 6;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        items[nextLast] = x;
        nextLast ++;
        size ++;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return items[nextLast-1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return items[i-1];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {
        int res = getLast();
        items[nextLast-1] = 0; // this sentence is optional
        nextLast --;
        return res;
    }

    /** Add a new item to the first position. */
    public void addFirst(int x) {

    }

    public static void main(String[] args) {

    }
} 