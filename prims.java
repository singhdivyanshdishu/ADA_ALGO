 import java.util.*;

public class prims {
    public static List<Edge> prim(Graph graph, int start) {
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.addAll(graph.getEdges(start));
        List<Edge> mst = new ArrayList<>();
        while (visited.size() < graph.size()) {
            Edge edge = pq.poll();
            int u = edge.getU();
            int v = edge.getV();
            if (!visited.contains(u) || !visited.contains(v)) {
                mst.add(edge);
                int newVertex = visited.contains(u) ? v : u;
                visited.add(newVertex);
                pq.addAll(graph.getEdges(newVertex));
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);
        List<Edge> mst = prim(graph, 0);
        for (Edge edge : mst) {
            System.out.println(edge.getU() + " - " + edge.getV() + " : " + edge.getWeight());
        }
    }
}

class Graph {
    private final int size;
    private final List<List<Edge>> adjList;

    public Graph(int size) {
        this.size = size;
        adjList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new Edge(u, v, weight));
        adjList.get(v).add(new Edge(v, u, weight));
    }

    public List<Edge> getEdges(int vertex) {
        return adjList.get(vertex);
    }

    public int size() {
        return size;
    }
}

class Edge implements Comparable<Edge> {
    private final int u;
    private final int v;
    private final int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(weight, other.weight);
    }
}
// 