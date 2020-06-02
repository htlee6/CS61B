package lab9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class BSTMap<Key extends Comparable<Key>, Value> implements Map61B<Key, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node leftChild, rightChild;
        private int size; // number of nodes in subtree

        public Node(Key k, Value v, int size) {
            key = k;
            value = v;
            this.size = size;
        }
        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
        }
    }

    /* Member Variables of class BSTMap */
    private Node root;

    /* Constructor */
    public BSTMap() {

    }

    @Override
    public void clear() {
        root.size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(Key key) {
        return containsKey(root, key);
    }

    private boolean containsKey(Node n, Key key) {
        if (n == null) {
            return false;
        }
        if (key == null) {
            throw new UnsupportedOperationException("Can't contains a 'null' key. ");
        }
        int cmp = key.compareTo(n.key);
        if (cmp > 0) {
            return containsKey(n.rightChild, key);
        } else if (cmp < 0) {
            return containsKey(n.leftChild, key);
        } else {
            return true;
        }
    }

    @Override
    public Value get(Key key) {
        return get(key, root);
    }

    private Value get(Key key, Node n) {
        if (key == null) {
            throw new UnsupportedOperationException("Can't get() given a null key. ");
        }
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp > 0) { // Key to be found is bigger than current top's key
            return get(key, n.rightChild);
        } else if (cmp < 0) { // Key to be found is smaller than current top's key
            return get(key, n.leftChild);
        } else { // // Key to be found is exactly current top's key
            return n.value;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    /* HELPER METHOD */
    /**
     * Return the number of subtree of n.
     * @param n Root of the subtree.
     * @return number of subtree.
     */
    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    /* HELPER METHOD */
    private Node put(Node n, Key ikey, Value ival) {
        if (n == null) {
            return new Node(ikey, ival, 1);
        }
        int cmp = ikey.compareTo(n.key);
        if (cmp < 0) {
            n.leftChild = put(n.leftChild, ikey, ival);
            n.size += 1;
        } else if (cmp > 0) {
            n.rightChild = put(n.rightChild, ikey, ival);
            n.size += 1;
        }
        return n;
    }

    @Override
    public Set<Key> keySet() {
        return keys(minKey(), maxKey());
    }

    private Key minKey() {
        return minKey(root);
    }

    private Key minKey(Node node) {
        if (node.leftChild == null) {
            return node.key;
        }
        return minKey(node.leftChild);
    }

    private Key maxKey() {
        return maxKey(root);
    }

    private Key maxKey(Node node) {
        if (node.rightChild == null) {
            return node.key;
        }
        return maxKey(node.rightChild);
    }

    private Set<Key> keys(Key lo, Key hi) {
        Set<Key> keys = new HashSet<>();
        keys(root, keys, lo, hi);
        return keys;
    }

    private void keys(Node n, Set<Key> s, Key lo, Key hi) {
        if (n == null) {
            return;
        }
        int cmplo = lo.compareTo(n.key);
        int cmphi = hi.compareTo(n.key);
        if (cmplo < 0) {
            keys(n.leftChild, s, lo, hi);
        }
        // TODO ???
        if (cmplo <= 0 && cmphi >= 0) {
            s.add(n.key);
        }
        if (cmphi > 0) {
            keys(n.rightChild, s, lo, hi);
        }
    }

    @Override
    public Value remove(Key key) {
        // 3 cases
        // #1 No Child - Delete directly
        // #2 1 Child - Child takes its place, aka node.child = node.child.child
        // #3 2 Children - Find the node greater than all nodes in left subtree and
        // the other node smaller than all nodes in right subtree; then make them children of root (AKA Hibbard Algo)
        /*
        root = remove(root, key);
        return root.value;

         */
        throw new UnsupportedOperationException();
    }

    private Node remove(Node n, Key key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp > 0) {
            n.rightChild = remove(n.rightChild, key);
        } else if (cmp < 0) {
            n.leftChild = remove(n.leftChild, key);
        } else {
            if (n.rightChild == null) {
                return n.leftChild;
            }
            if (n.leftChild == null) {
                return n.rightChild;
            }
            Node temp = n;
            Key leftSubMaxKey = maxKey(n.leftChild), rightSubMinKey = minKey(n.rightChild);
        }
        n.size -= 1;
        return n;
    }

    @Override
    public Value remove(Key key, Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Key> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<Key> {
        private ArrayList<Key> keysInOrder = new ArrayList<>();
        private int cursor;

        public BSTMapIterator() {
            cursor = 0;
            inOrder();
        }

        private void inOrder() {
            inOrder(root);
        }

        private void inOrder(Node n) {
            if (n == null) {
                return;
            }
            inOrder(n.leftChild);
            keysInOrder.add(n.key);
            inOrder(n.rightChild);
        }

        @Override
        public boolean hasNext() {
            return cursor < keysInOrder.size() - 1;
        }

        @Override
        public Key next() {
            cursor += 1;
            return keysInOrder.get(cursor);
        }
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bm = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            bm.put("hi" + i, 1 + i);
        }
        int count = 0;
        for (String k : bm) {
            count += 1;
            System.out.println(k);
        }
        System.out.println(count);

    }
}
