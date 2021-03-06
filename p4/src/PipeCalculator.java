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
        UndirectedEdge maxWeight;
        private UndirectedWeightedGraph g = null;
        private UndirectedWeightedGraph mst = null;


        public PipeCalculator(int n, float[] well, float[][] costs) {
            this.casas = n;
            this.well = well;
            this.costs = costs;
            this.visited = new boolean[n + 1];
            this.hasCycle = false;
            this.ciclo = new Stack<UndirectedEdge>();
            this.maxWeight = null;
        }

        public class MaxCycleMST {

            private boolean[] visited;
            private boolean[] inCurrentPath;
            private UndirectedWeightedGraph graph;
            private boolean hasCycle;
            private Stack<UndirectedEdge> ciclo;
            private int start;
            private UndirectedWeightedGraph g = null;
            private UndirectedWeightedGraph mst = null;

            public MaxCycleMST(UndirectedWeightedGraph g) {
                this.graph = g;
                this.g = g;
                this.visited = new boolean[g.vCount()];
                this.inCurrentPath = new boolean[g.vCount()];
                this.ciclo = new Stack<UndirectedEdge>();

            }

            public void search() {
                int vertices = this.graph.vCount();
                this.hasCycle = false;
                //initialize array to false
                for (int i = 0; i < vertices; i++) {
                    this.visited[i] = false;
                    this.inCurrentPath[i] = false;
                }
                for (int i = 0; i < vertices; i++) {
                    //start a new search for each vertex that has not been visited yet
                    if (!this.visited[i]) {
                        visit(i, i);
                    }
                    if (this.hasCycle) return;
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
                while (!end) {
                    UndirectedEdge edge = ciclo.pop();
                    if (edge.v1() == start || edge.v2() == start) end = true;
                    cycle.push(edge);
                }
                return cycle;
            }


            public UndirectedEdge determineMaxInCycle(UndirectedWeightedGraph g) {
                UndirectedEdge maxWeight = null;
                float f = Float.NEGATIVE_INFINITY;
                MaxCycleMST cycle = new MaxCycleMST(g);
                cycle.search();
                if (cycle.hasCycle) {
                    for (UndirectedEdge edge : cycle.getCycle()) {
                        if (edge.weight() > f) {
                            f = edge.weight();
                            maxWeight = edge;
                        }
                    }
                }
                return maxWeight;
            }

            public UndirectedWeightedGraph buildMST() {
                UndirectedWeightedGraph copy = g.shallowCopy();
                for (int i = 0; i < g.eCount() - g.vCount() + 1; i++) {
                    UndirectedEdge maxWeight = determineMaxInCycle(copy);
                    copy.removeEdge(maxWeight);
                }
                this.mst = copy;
                return copy;
            }
        }

        public UndirectedWeightedGraph createGraph(int n, float[] well, float[][] costs) {
            this.casas = n;
            this.well = well;//pre??o construir po??o na casa
            this.costs = costs;//pre??o de canaliza??ao de uma casa para outra
            this.mst = new UndirectedWeightedGraph(n+1);
            this.visited = new boolean[n+1];
            this.hasCycle = false;
            this.ciclo = new Stack<UndirectedEdge>();
            this.maxWeight = null;

            for(int i = 0; i < n; i++) {
                mst.addEdge(new UndirectedEdge(i, n, well[i]));
                for(int j = i +1; j < n; j++) {
                    mst.addEdge(new UndirectedEdge(i, j, costs[i][j]));
                }
            }
            return mst;
        }

        public UndirectedWeightedGraph calculateSolution(UndirectedWeightedGraph g) {
            createGraph(casas, well, costs);
            mst = g;
            MaxCycleMST cycle = new MaxCycleMST(mst);
            cycle.search();
            while(cycle.hasCycle) {
                cycle.buildMST();
            }
            return mst;
        }

        public UndirectedWeightedGraph calculateSolution() {
            createGraph(casas, well, costs);
            MaxCycleMST cycle = new MaxCycleMST(mst);
            cycle.search();
            while(cycle.hasCycle) {
                cycle.buildMST();
            }
            return mst;
        }

        public UndirectedWeightedGraph getMST() {
            return mst;
        }


        public static void main(String[] args) {
            float well[] = {2, 5, 7, 9, 4};
            float costs[][] = new float[5][5];
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    costs[i][j] = 0;
                }
            }
            costs[0][1] = 10;
            costs[1][3] = 6;
            costs[1][2] = 4;
            costs[2][3] = 20;
            costs[3][4] = 12;
            PipeCalculator pc = new PipeCalculator(5, well, costs);
            pc.createGraph(5, well, costs);
            System.out.println(pc.calculateSolution());
        }
    }
