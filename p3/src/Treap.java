// package aed.tables;

import java.util.Random;

public class Treap<Key extends Comparable<Key>,Value> {

    private class Node {
        private Key key;
        // private int priority;
        private Value value;
        private Node left;
        private Node right;
        // private int size;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    public Treap<Key,Value> shallowCopy() {
        Treap<Key,Value> copy = new Treap<>();
        copy.root = copy(copy.root)

        Node left = null;
        Node right = null;
        // 
        if (this.left != null) {
            left = this.left.shallowCopy();
            // copy.put(this.key, this.value);
        }
        if (this.right != null) {
            right = this.right.shallowCopy();
            // copy.put(this.key, this.value);
        }
        // return Node(this.key, this.value, left, right);
    }

    /*
    public Treap()
    {
        //TODO: implement
    }
    
    public Treap(Random r)
    {
        //TODO: implement
    }

    public int size()
    {
        //TODO: implement
        return 0;
    }

    public Value get(Key k)
    {
        //TODO: implement
        return null;
    }

    public boolean containsKey(Key k)
    {
    	//TODO: implement
        return false;
    }

    public void put(Key k, Value v) {
        //TODO: implement
    }
    
    public void delete(Key k)
    {
    	//TODO: implement
    }

    @SuppressWarnings("rawtypes")
	public Treap[] split(Key k)
    {
    	//TODO: implement
        return null;
    }

    public Key min()
    {
        //TODO: implement
        return null;
    }

    public Key max()
    {
        //TODO: implement
        return null;
    }
    
    public void deleteMin() {
        //TODO: implement
    }
    
    public void deleteMax()
    {
        //TODO: implement
    }

    public int rank(Key k)
    {
        //TODO: implement
        return 0;
    }
    
    public int size(Key min, Key max)
    {
        //TODO: implement
        return 0;
    }
    
    public Key select(int n)
    {
        //TODO: implement
        return null;
    }
    
    public Iterable<Key> keys()
    {
        //TODO: implement
        return null;
    }
    
    public Iterable<Value> values()
    {
        //TODO: implement
        return null;
    }
    
    public Iterable<Integer> priorities()
    {
        //TODO: implement
        return null;
    }
    
    public Iterable<Key> keys(Key min, Key max)
    {
        //TODO: implement
        return null;
    }*/

    public static void main(String[] args) {
        System.out.println("Boas");
    }
    
}
