import java.util.*;

public class MaxCycleMST {

    private int count; //apenas para contar por quantos vertices passou
    private int vCount; //vertices
    private int eCount; //edges ... arestas
    private Object[] adj; //vertices adjacentes
    private boolean[] visited; //se um vertice ja foi visitado ou nao

    private void visit(int v) {
        this.count++;
        this.visited[v] = true;
        for (int adj: graph.adj(v)) {
            if (!visited[adj]) {
                visit(adj);
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
