import java.util.*;

public class MaxCycleMST {

    public class Graph {

        private int count; //apenas para contar por quantos vertices passou
        private int vCount; //vertices
        private int eCount; //edges ... arestas
        private LinkedList<Integer>[] adj; //vertices adjacentes
        private boolean[] visited; //se um vertice ja foi visitado ou nao

        @SuppressWarnings("unchecked")
        public Graph(int vCount) {
            this.vCount = vCount;
            this.eCount = eCount;
            this.adj = (LinkedList<Integer>[])  new Object[vCount];

            for (int i = 0; i < vCount; i++) {
                this.adj[i] = new LinkedList<Integer>();
            }

        }



    }


    public UndirectedEdge determineMaxInCycle(UndirectedWeightedGraph g) {
        //to do
    }

    public UndirectedWeightedGraph buildMST() {
        //to do
    }

    public UndirectedWeightedGraph getMST() {
        //to do
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello there");
        g.addEdge(new UndirectedEdge(0, 1, 10));
        g.addEdge(new UndirectedEdge(1, 2, 4));
        g.addEdge(new UndirectedEdge(1, 3, 6));
        g.addEdge(new UndirectedEdge(2, 6, 8));
        g.addEdge(new UndirectedEdge(6, 8, 14));
        g.addEdge(new UndirectedEdge(8, 9, 12));
        g.addEdge(new UndirectedEdge(9, 11, 15));
        g.addEdge(new UndirectedEdge(11, 12, 15));
        g.addEdge(new UndirectedEdge(12, 7, 20));
        g.addEdge(new UndirectedEdge(7, 4, 10));
        g.addEdge(new UndirectedEdge(4, 3, 12));
        g.addEdge(new UndirectedEdge(3, 5, 30));
        g.addEdge(new UndirectedEdge(5, 10, 25));
        g.addEdge(new UndirectedEdge(2, 3, 20));
        scanner.close();
    }
}
