// package aed.tables;

import java.util.Random;

public class Treap<Key extends Comparable<Key>,Value> {

    private class Node {
        private Key key;
         private int priority;
        private Value value;
        private Node left;
        private Node right;
        private int size;

        public Node(Key key, Value value, int size, int priority) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.priority = priority;
        }

        public String toString() {
            return "[k:" + this.key + " v:" + this.value + " p:" + this.priority + " s:" + this.size + "]";
        }

    }

    public Treap<Key,Value> shallowCopy() {
        Treap<Key,Value> copy = new Treap<>();
        copy.root = copy(copy.root);

        Node left = null;
        Node right = null;
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

    public Treap()  {
        //TODO: implement
    }
    
    public Treap(Random r)  {
        //TODO: implement
    }

    public int size()   {
        //TODO: implement
        return 0;
    }

    public Value get(Key k)   {
        //TODO: implement
        return null;
    }

    public boolean containsKey(Key k)   {
    	//TODO: implement
        return false;
    }

    public void put(Key k, Value v) {
        //TODO: implement
    }
    
    public void delete(Key k)   {
    	//TODO: implement
    }

    @SuppressWarnings("rawtypes")
	public Treap[] split(Key k)  {
    	//TODO: implement
        return null;
    }

    public Key min()   {
        //TODO: implement
        return null;
    }

    public Key max()  {
        //TODO: implement
        return null;
    }
    
    public void deleteMin() {
        //TODO: implement
    }
    
    public void deleteMax()   {
        //TODO: implement
    }

    public int rank(Key k)  {
        //TODO: implement
        return 0;
    }
    
    public int size(Key min, Key max)   {
        //TODO: implement
        return 0;
    }
    
    public Key select(int n)   {
        //TODO: implement
        return null;
    }
    
    public Iterable<Key> keys()   {
        //TODO: implement
        return null;
    }
    
    public Iterable<Value> values()   {
        //TODO: implement
        return null;
    }
    
    public Iterable<Integer> priorities()  {
        //TODO: implement
        return null;
    }
    
    public Iterable<Key> keys(Key min, Key max)   {
        //TODO: implement
        return null;
    }

    //helper method that uses the treap to build an array with a heap structure
    private void fillHeapArray(Node n, Object[] a, int position)    {
        if(n == null) return;

        if(position < a.length)    {
            a[position] = n;
            fillHeapArray(n.left,a,2*position);
            fillHeapArray(n.right,a,2*position+1);
        }
    }

    //if you want to use a different organization that a set of nodes with pointers, you can do it, but you will have to change
    //this method to be consistent with your implementation
    private Object[] getHeapArray()  {
        //we only store the first 31 elements (position 0 is not used, so we need a size of 32), to print the first 5 rows of the treap
        Object[] a = new Object[32];
        fillHeapArray(this.root,a,1);
        return a;
    }

    private void printCentered(String line)   {
        //assuming 120 characters width for a line
        int padding = (120 - line.length())/2;
        if(padding < 0) padding = 0;
        String paddingString = "";
        for(int i = 0; i < padding; i++)   {
            paddingString +=" ";
        }

        System.out.println(paddingString + line);
    }

    //this is used by some of the automatic tests in Mooshak. It is used to print the first 5 levels of a Treap.
    //feel free to use it for your own tests
    public void printTreapBeginning() {
        Object[] heap = getHeapArray();
        int size = size(this.root);
        int printedNodes = 0;
        String nodeToPrint;
        int i = 1;
        int nodes;
        String line;

        //only prints the first five levels
        for (int depth = 0; depth < 5; depth++) {
            //number of nodes to print at a particular depth
            nodes = (int) Math.pow(2, depth);
            line = "";
            for (int j = 0; j < nodes && i < heap.length; j++) {
                if (heap[i] != null) {
                    nodeToPrint = heap[i].toString();
                    printedNodes++;
                } else {
                    nodeToPrint = "[null]";
                }
                line += " " + nodeToPrint;
                i++;
            }

            printCentered(line);
            if (i >= heap.length || printedNodes >= size) break;
        }
    }

    public static void main(String[] args) {
        System.out.println("Boas");
    }
    
}