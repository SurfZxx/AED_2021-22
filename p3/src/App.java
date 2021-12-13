public class App {


    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
