package aed.tables;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Treap<Key extends Comparable<Key>,Value> {

    private Node root;
    private Random random;
    private Node selected;
    private int count;

    private class Node {
        private Key key;
        private int priority;
        private Value value;
        private Node left;
        private Node right;
        private int size;

        public Node(Key k, Value v, int size, int priority) {
            this.key = k;
            this.value = v;
            this.size = size;
            this.priority = priority;
        }

        public String toString() {
            return "[k:" + this.key + " v:" + this.value + " p:" + this.priority + " s:" + this.size + "]";
        }

    }


    public Treap()  {
        this.root = null;
        this.random = new Random();
    }

    public Treap(Node root) {
        this.root = root;
        this.random = new Random();
    }
    
    public Treap(Random r)  {
        this.root = null;
        this.random = r;
    }

    public int size() {
        return size(this.root);
    }

    public int size(Node n)   {
        int right = 0;
        int left = 0;
        if (n == null) {
            return 0;
        }
        if (n.left != null) {
            left = n.left.size;
        }
        if (n.right != null) {
            right = n.right.size;
        }
        return right + left +1;
    }

    public Value get(Key k)   {
        return get(this.root, k);
    }

    private Value get(Node n, Key k) {
        if (n == null) {
            return null;
        }
        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            return get(n.left, k);
        } else if (cmp > 0) {
            return get(n.right, k);
        } else {
            return n.value;
        }
    }

    public boolean containsKey(Key k)   {
        return containsKey(this.root, k);
    }

    private boolean containsKey(Node n, Key k) {
        if (n == null) {
            return false;
        }
        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            return containsKey(n.left, k);
        } else if (cmp > 0) {
            return containsKey(n.right, k);
        } else {
            return true;
        }
    }

    /*
          b                             d
        /   \                         /   \
       a     d    rotate left        b     e
            / \                    /   \
           c   e                  a     c
     */

    private Node rotateLeft(Node root) {
        Node right = root.right;
        Node target = root.right.left;
        right.left = root;
        root.right = target;
        root.size = size(root);
        right.size = size(right);
        return right;
    }

        /*
          d                             b
        /   \                         /   \
       b      e    rotate right      a     d
     /  \                                /   \
    a    c                              c     e
     */

    private Node rotateRight(Node root) {
        Node left = root.left;
        Node target = root.left.right;
        left.right = root;
        root.left = target;
        root.size = size(root);
        left.size = size(left);
        return left;
    }

    public void put(Key k, Value v) {
        this.root = put(this.root, k,v);
    }

    private Node put(Node n, Key k, Value v) {
        if (n == null) {
            return new Node(k,v,1, random.nextInt());
        }
        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(n.left, k, v);
            if (n.left.priority < n.right.priority) {
                n = rotateLeft(n);
                delete(n.left, k);
            }
        } else if (cmp > 0) {
            n.right = put(n.right, k, v);
            if (n.left.priority > n.right.priority) {
                n = rotateRight(n);
                n.right = delete(n.right, k);
            }
        } else {
            n.value = v;
        }

        //update the node size
        n.size = size(n);
        return n;
    }
    
    public void delete(Key k)   {
    	this.root = delete(this.root, k);
    }

    private Node delete(Node n, Key k) {
        if (n == null) {
            return null;
        }
        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = delete(n.left, k);
        } else if (cmp > 0) {
            n.right = delete(n.right, k);
        } else {
            n.priority = Integer.MIN_VALUE;
            if (n.right == null) {
                return n.left;
            }
            if (n.left == null) {
                return n.right;
            } else {
                if (n.left.priority < n.right.priority) {
                    n = rotateLeft(n);
                    delete(n.left, k);
                } else {
                    n = rotateRight(n);
                    n.right = delete(n.right, k);
                }
            }
//            comentario funcionava se nao fosse treap
//            Node temp = n;
//            n = deleteMin(temp.right);
//            n.right = deleteMin(temp.right);
//            n.left = temp.left;
        }
        n.size = size(n);
        return n;
    }

    @SuppressWarnings("rawtypes")
	public Treap[] split(Key k)  {
        Treap[] result = new Treap[2];
        Node head = split(this.root, k);
        result[0] = new Treap<>(head.left);
        result[1] = new Treap<>(head.right);
        return result;
    }

    private Node split(Node n, Key k) {
        if (n == null) {
            return new Node(k, null, 1, Integer.MAX_VALUE);
        }
        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = split(n.left, k);
            if (n.priority < n.left.priority) {
                return rotateRight(n);
            }
        } else if (cmp > 0) {
            n.right = split(n.right, k);
            if (n.priority < n.right.priority) {
                return rotateLeft(n);
            }
        } else {
            n.priority = Integer.MAX_VALUE;
        }
        n.size = size(n);
        return n;
    }

    public Key min()   {
        Node actual = root;
        while (actual.left != null) {
            actual = actual.left;
        }
        return actual.key;
    }

    public Key max()  {
        Node actual = root;
        while (actual.right != null) {
            actual = actual.right;
        }
        return actual.key;
    }
    
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node n) {
        if (n.left == null) {
            return n.right;
        }
        n.left = deleteMin(n.left);
        n.size = size(n.left) + size(n.right) +1;
        return n;
    }
    
    public void deleteMax()   {
        root = deleteMax(root);
    }

    private Node deleteMax(Node n) {
        if (n.right == null) {
            return n.left;
        }
        n.right = deleteMax(n.right);
        n.size = size(n.left) + size(n.right) +1;
        return n;
    }

    public int rank(Key k)  {
        return rank(this.root, k, 0);
    }

    private int rank(Node n, Key k, int count) {
        if (n == null) {
            return 0;
        }
        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            return rank(n.left, k, count);
        } else if (cmp > 0) {
            count = count + size(n.left);
            return rank(n.right, k, count);
        } else {
            count = count + size(n.left);
        }
        return count;
    }
    
    public int size(Key min, Key max)   {
        if (containsKey(max)) {
            return (rank(max) - rank(min)) +1;
        }
        return (rank(max) - rank(min));
    }
    
    public Key select(int n)   {
        count = -1;
        selected = kthSmallest(this.root, n);
        return selected.key;
    }

    public Node kthSmallest(Node root, int n) {
        if (root == null) {
            return null;
        }
        Node left = kthSmallest(root.left, n);
        if (left != null) {
            return left;
        }
        count++;
        if (count == n) {
            return root;
        }
        return kthSmallest(root.right, n);
    }
    
    public Iterable<Key> keys()   {
        Queue<Key> queue = new LinkedList<>();
        selected = this.root;
        for (int i = 0; selected != null; i++) {
            select(i);
            queue.add(selected.key);
        }
        return queue;
    }
    
    public Iterable<Value> values()   {
        Queue<Value> queue = new LinkedList<Value>();
        selected = this.root;
        for(int i = 0; selected != null; i++) {
            select(i);
            queue.add(selected.value);
        }
        return queue;
    }
    
    public Iterable<Integer> priorities()  {
        Queue<Integer> queue = new LinkedList<Integer>();
        selected = this.root;
        for(int i = 0; selected != null; i++) {
            select(i);
            queue.add(selected.priority);
        }
        return queue;
    }
    
    public Iterable<Key> keys(Key min, Key max)   {
        Queue<Key> queue = new LinkedList<Key>();
        selected = this.root;
        for(int i = rank(min); selected != null && i < rank(max); i++) {
            select(i);
            queue.add(selected.key);
        }
        return queue;
    }

    public Treap<Key,Value> shallowCopy() {
        //prof escreveu
        Treap<Key, Value> copy = new Treap<>();
        copy.root = shallowCopy(this.root, copy.root);
    }

    private Node shallowCopy(Node n, Node n2) {
        n2 = n;
        if (n.left != null) {
            n2.left = shallowCopy(n.left, n2.left);
        }
        if (n.right != null) {
            n2.right = shallowCopy(n.right, n2.right);
        }
        return n2;
    }

//    feito na aula, ideia +/- mas mal feito
//        Node left = null;
//        Node right = null;
//        if (Node.left != null) {
//            left = this.left.shallowCopy();
//            // copy.put(this.key, this.value);
//        }
//        if (this.right != null) {
//            right = this.right.shallowCopy();
//            // copy.put(this.key, this.value);
//        }
//        // return Node(this.key, this.value, left, right);
//    }

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

    public void printTreap(Node root, int space) {
        final int height = 20;

        // Base case
        if (root == null) {
            return;
        }
        // increase distance between levels
        space += height;

        // print the right child first
        printTreap(root.right, space);
        System.lineSeparator();

        // print the current node after padding with spaces
        for (int i = height; i < space; i++) {
            System.out.print(' ');
        }

        System.out.println("(" + "k=" + root.key + " v=" + root.value + " s=" + root.size + " p=" + root.priority + ")" + " ");

        // print the left child
        System.lineSeparator();
        printTreap(root.left, space);
    }
    // como main aqui serve para testar, autoria de Rafael Reis a68039
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Treap<Integer, Integer> Tree = new Treap<>(new Random());
        // Tree.put(10, 5);
        // Tree.put(12, 5);
        // Tree.put(90, 5);
        // Tree.put(8, 5);
        // Tree.put(14, 5);
        // Tree.put(6, 5);
        // Tree.put(50, 5);
        Random random = new Random();
        for(int i = 0; i < 10; i++)
            Tree.put(random.nextInt(1000), 5);

        Tree.printTreap(Tree.root, 0);
        System.out.println("________________________");

        Treap<Integer, Integer> newTree = Tree.shallowCopy();
        Tree.printTreap(newTree.root, 0);
        System.out.println("________________________");

        // Treap[] split = Tree.split(500);

        // Tree.printTreap(split[0].root, 0);
        // System.out.println("*________________________");
        // System.out.println("------------------------*");


        // Tree.printTreap(split[1].root, 0);
        // System.out.println("________________________");

        // while(sc.hasNext()) {
        //     // System.out.println(Tree.size(sc.nextInt(), sc.nextInt()));
        //     // System.out.println(Tree.select(sc.nextInt()));
        //     // Tree.printTreap(Tree.root, 0);
        //     // System.out.println("________________________");
        // }

        scanner.close();
    }
    
}