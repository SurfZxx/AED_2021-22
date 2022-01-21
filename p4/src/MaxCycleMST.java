//package aed.graphs;

import java.util.*;

public class MaxCycleMST {

        private boolean[] visited;
        private boolean[] inCurrentPath;
        private UndirectedWeightedGraph graph;
        private boolean hasCycle;
        //private int count;
        private Stack<UndirectedEdge> ciclo;// = new Stack<UndirectedEdge>();
        //private Stack<Integer> ciclo = new Stack<>();
        //private int maxWeight;
        private int start;
        private UndirectedWeightedGraph g = null;
        private UndirectedWeightedGraph mst = null;

        public MaxCycleMST(UndirectedWeightedGraph g) {
            this.graph = g;
            this.g = g;
            this.visited = new boolean[g.vCount()];
            this.inCurrentPath = new boolean[g.vCount()];
            this.ciclo = new Stack<UndirectedEdge>();
            //this.ciclo = new Stack<>();
            //this.count = 0;

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
                    visit(i,i);
                }
                if(this.hasCycle) return;
            }
        }




        private void visit(int from, int v) {//int from, int v,
            this.inCurrentPath[v] = true;
            this.visited[v] = true;
            for (UndirectedEdge adj : graph.adj(v)) {
                //if a cycle was already detected we do not need to continue
                if (this.hasCycle) return;
                else if (!this.visited[adj.other(v)] && adj.other(v) != from) {
                    ciclo.push(adj);
                    visit(v, adj.other(v));
                } else if (adj.other(v) != from) {
                    start = adj.other(v);
                    ciclo.push(adj);
                    this.hasCycle = true;
                    return;
                }
            }
            if (this.hasCycle) {
                return;
            }
            if (!ciclo.empty()) {
                this.ciclo.pop();
            }
            this.inCurrentPath[v] = false;
//                if (!adj.equals(from)) {
//                    if (!this.visited[adj.v2()]) {
//                        //if a vertex was already visited, we need to check if that vertex already exists
//                        //in the current path (if so, we detected a cycle)
//                        ciclo.push(adj);
//                        this.count++;
//                        visit(adj, adj.v2());
//                    } else if (this.inCurrentPath[adj.v1()]) {
//                        this.hasCycle = true;
//                        return;
//                    }
//                }
//                this.inCurrentPath[v] = false;
//                for (int i = 0; i < this.count; i++) {
//                    ciclo.pop();
//                }
//                this.count = 0;
        }

        public boolean hasCycle(){
            return this.hasCycle;
        }

    public Stack<UndirectedEdge> getCycle(){
        Stack<UndirectedEdge> cycle = new Stack<UndirectedEdge>();
        boolean end = false;
        cycle.push(ciclo.pop());
        while(!end) {
            UndirectedEdge edge = ciclo.pop();
            if(edge.v1() == start|| edge.v2() == start) {
                end = true;
            }
            cycle.push(edge);
        }
        return cycle;
    }


    public UndirectedEdge determineMaxInCycle(UndirectedWeightedGraph g) {
            UndirectedEdge maxWeight = null;
            float f = 0;
            MaxCycleMST cycle = new MaxCycleMST(g);
            cycle.search();
            for (UndirectedEdge edge : cycle.getCycle()) {
                if (edge.weight() > f) {
                    f = edge.weight();
                    maxWeight = edge;
                }
            }
            return maxWeight;
    }

    public UndirectedWeightedGraph buildMST() {
        UndirectedWeightedGraph copy = g.shallowCopy();
        for(int i = 0; i < g.eCount() - g.vCount()+1; i++) {
            UndirectedEdge maxWeight = determineMaxInCycle(copy);
            copy.removeEdge(maxWeight);
        }
        this.mst = copy;
        return copy;
    }

    public UndirectedWeightedGraph getMST() {
        return mst;
    }



    public static void main(String[] args) {
        UndirectedWeightedGraph g = new  UndirectedWeightedGraph(13);
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
//        UndirectedWeightedGraph g = new  UndirectedWeightedGraph(8);
//
//        g.addEdge(new UndirectedEdge(0,1,3));
//        g.addEdge(new UndirectedEdge(0,2,1));
//        g.addEdge(new UndirectedEdge(0,3,4));
//        g.addEdge(new UndirectedEdge(1,3,2));
//        g.addEdge(new UndirectedEdge(2,3,6));
//        g.addEdge(new UndirectedEdge(2,4,1));
//        g.addEdge(new UndirectedEdge(2,5,3));
//        g.addEdge(new UndirectedEdge(3,6,1));
//        g.addEdge(new UndirectedEdge(3,4,2));
//        g.addEdge(new UndirectedEdge(4,5,3));
//        g.addEdge(new UndirectedEdge(6,5,1));
//        g.addEdge(new UndirectedEdge(6,7,7));
//        g.addEdge(new UndirectedEdge(5,7,10));
        MaxCycleMST mst = new MaxCycleMST(g);
        System.out.print(mst.buildMST().toString());
    }
}
