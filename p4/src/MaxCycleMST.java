import java.util.*;

public class MaxCycleMST<DiGraph> {

    public class CycleDetector {

        private boolean[] visited;
        private boolean[] inCurrentPath;
        private DiGraph graph;
        private boolean hasCycle;
        private int eCount; //quantos arcos ha no grafo
        private int vCount;
        private int count;
        private Stack<Integer> ciclo = new Stack<Integer>();

        public CycleDetector(DiGraph g) {
            this.graph = g;
            this.visited = new boolean[g.vCount()];
            this.inCurrentPath = new boolean[g.vCount()];
        }

        public void search() {
            int vertives = this.graph.vCount();
            this.hasCycle = false;
            //initialize array to false
            for(int i = 0; i < vertices; i++) {
                this.visited[i] = false;
                this.inCurrentPath[i] = false;
            }
            for(int i = 0; i < vertices; i++) {
                //start a new search for each vertex that has not been visited yet
                if(!this.visited[i]) {
                    visit(i-1,i);
                } else if(this.visited[i]){
                    hasCycle = true;
                    return;
                }
                if(this.hasCycle) return;
            }
        }




        private void visit(int from, int v) {//int from, int v,
            this.inCurrentPath[v] = true;
            this.visited[v] = true;
            for(UndirectedEdge adj : graph.adj(v))	{
                //if a cycle was already detected we do not need to continue
                if(this.hasCycle) return;
                else if(!this.visited[adj]) {
                    //if a vertex was already visited, we need to check if that vertex already exists
                    //in the current path (if so, we detected a cycle)
                    ciclo.push(v);
                    count++;
                    visit(v,adj);
                } else if(this.inCurrentPath[adj]) {
                    this.hasCycle = true;
                    return;
                }
            }
            this.inCurrentPath[v] = false;
            for (int i = 0; i < count; i++) {
                ciclo.pop();
            }
        }

        public boolean hasCycle(){
            return this.hasCycle;
        }
    }


        private int count; //apenas para contar por quantos vertices passou
        private int vCount; //vertices
        private int eCount; //edges ... arestas
        private LinkedList<Integer>[] adj; //vertices adjacentes
        private boolean[] visited; //se um vertice ja foi visitado ou nao
        private DiGraph graph;

        @SuppressWarnings("unchecked")
        public Graph(int vCount, int eCount) {
            this.vCount = vCount;
            this.eCount = eCount;
            this.adj = (LinkedList<Integer>[])  new Object[vCount];

            for (int i = 0; i < vCount; i++) {
                this.adj[i] = new LinkedList<Integer>();
            }
        }



    public void visit(int v) {
        this.count++;
        this.visited[v] = true;
        for (int adj : graph.adj(v)) {
            if (!visited[adj]) {
                visit(adj);
            }
        }

    }

    public void search(int startingV) {
            int vertices = this.graph.vCount();
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
