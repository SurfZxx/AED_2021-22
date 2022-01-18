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
        scanner.close();
    }
}
