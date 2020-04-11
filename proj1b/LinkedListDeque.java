public class LinkedListDeque<T> implements Deque<T>{

    private class TNode {

        private T item;
        private TNode prev;
        private TNode next;

        private TNode(T itm, TNode p, TNode n) {
            item = itm;
            next = n;
            prev = p;
        }

    }

    private TNode sentinelF;
    private TNode sentinelB;
    private int size;

    public LinkedListDeque() {
        sentinelB = new TNode(null, null, null);
        sentinelF = new TNode(null, null, sentinelB);
        sentinelB.prev = sentinelF;
        size = 0;
    }

    @Override
    public void printDeque() {
        TNode start = sentinelF.next;
        while (start != sentinelB) {
            System.out.print(start.item);
            System.out.print(" ");
            start = start.next;
        }
        System.out.println();
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        TNode ptr = sentinelF.next;
        while (index > 0) {
            ptr = ptr.next;
            index = index - 1;
        }
        T res = (T) ptr.item;
        return res;
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinelF.next);
    }

    private T getRecursive(int index, TNode node) {
        if (size == 0 | index >= size) {
            return null;
        }
        if (index == 0) {
            return node.item;
        }
        return getRecursive(index - 1, node.next);
    }

    @Override
    public void addFirst(T item) {
        TNode originalFirst = sentinelF.next;
        TNode newNode = new TNode(item, sentinelF.next, originalFirst);
        sentinelF.next = newNode;
        originalFirst.prev = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        TNode originalLast = sentinelB.prev;
        TNode newNode = new TNode(item, originalLast, sentinelB);
        originalLast.next = newNode;
        sentinelB.prev = newNode;
        size++;
    }

    @Override
    public T removeFirst() {
        T res = (T) sentinelF.next.item;
        TNode originalFirst = sentinelF.next;
        sentinelF.next = originalFirst.next;
        originalFirst.next = sentinelF;
        size--;
        return res;
    }

    @Override
    public T removeLast() {
        T res = (T) sentinelB.prev.item;
        TNode originalLast = sentinelB.prev;
        sentinelB.prev = originalLast.prev;
        originalLast.prev = sentinelB;
        size--;
        return res;
    }

    /*
    public static void main(String[] args) {
        LinkedListDeque<Integer> l1 = new LinkedListDeque<>();
        l1.addFirst(1);
        l1.addFirst(2);
        l1.addFirst(3);
        l1.printDeque();
        Integer res = l1.get(1);
    }

     */
}
