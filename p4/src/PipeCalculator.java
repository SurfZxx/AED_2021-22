//package aed.graphs;

import java.util.Stack;

    public class PipeCalculator {

        private int casas; // n = nr de casas
        private int rows;
        private int collums;
        private float[] well;
        private float[][] costs;


        private boolean[] visited;
        private boolean[] inCurrentPath;
        private UndirectedWeightedGraph graph;
        private boolean hasCycle;
        private Stack<UndirectedEdge> ciclo;
        private int start;
        private UndirectedWeightedGraph g = null;
        private UndirectedWeightedGraph mst = null;


        public PipeCalculator(int n, float[] well, float[][] costs) {
            this.casas = n;

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
        }

        public boolean hasCycle() {
            return this.hasCycle;
        }

        public Stack<UndirectedEdge> getCycle() {
            Stack<UndirectedEdge> cycle = new Stack<UndirectedEdge>();
            boolean end = false;
            cycle.push(ciclo.pop());
            while(!end) {
                UndirectedEdge edge = ciclo.pop();
                if(edge.v1() == start|| edge.v2() == start) end = true;
                cycle.push(edge);
            }
            return cycle;
        }

        public UndirectedWeightedGraph createGraph(int n, float[] well, float[][] costs) {

        }

        public UndirectedWeightedGraph calculateSolution(UndirectedWeightedGraph g) {

        }

        public UndirectedWeightedGraph calculateSolution() {

        }

        public UndirectedWeightedGraph getMST() {
            return mst;
        }


        public static void main(String[] args) {

        }
    }
