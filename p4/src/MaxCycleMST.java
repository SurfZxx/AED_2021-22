import java.util.*;

public class MaxCycleMST {


        private boolean[] visited;
        private boolean[] inCurrentPath;
        private UndirectedWeightedGraph graph;
        private boolean hasCycle;
        private int count;
        private Stack<UndirectedEdge> ciclo = new Stack<>();
        //private Stack<Integer> ciclo = new Stack<>();
        private int maxWeight;

        public MaxCycleMST(UndirectedWeightedGraph g) {
            this.graph = g;
            this.visited = new boolean[g.vCount()];
            this.inCurrentPath = new boolean[g.vCount()];
            this.hasCycle = false;
            this.ciclo = new Stack<UndirectedEdge>();
            //this.ciclo = new Stack<>();
            this.count = 0;
        }

        public void search() {
            int vertices = this.graph.vCount();
            this.hasCycle = false;
            //initialize array to false
            for(int i = 0; i < vertices; i++) {
                this.visited[i] = false;
                this.inCurrentPath[i] = false;
            }
            for(int i = 0; i < vertices; i++) {
                //start a new search for each vertex that has not been visited yet
                if(!this.visited[i]) {
                    visit(null,i);
                } else if(this.visited[i]){
                    hasCycle = true;
                    return;
                }
                if(this.hasCycle) return;
            }
        }




        private void visit(UndirectedEdge from, int v) {//int from, int v,
            this.inCurrentPath[v] = true;
            this.visited[v] = true;
            for(UndirectedEdge adj : graph.adj(v))	{
                //if a cycle was already detected we do not need to continue
                if(this.hasCycle) return;
                if (!adj.equals(from)) {
                    if(!this.visited[adj.v2()]) {
                        //if a vertex was already visited, we need to check if that vertex already exists
                        //in the current path (if so, we detected a cycle)
                        ciclo.push(adj);
                        this.count++;
                        visit(adj,adj.v2());
                    } else if(this.inCurrentPath[adj.v1()]) {
                        this.hasCycle = true;
                        return;
                    }
                }
                this.inCurrentPath[v] = false;
                for (int i = 0; i < this.count; i++) {
                    ciclo.pop();
                }
                this.count = 0;
            }

        public boolean hasCycle(){
            return this.hasCycle;
        }


    public UndirectedEdge determineMaxInCycle(UndirectedWeightedGraph g) {
        search();
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
