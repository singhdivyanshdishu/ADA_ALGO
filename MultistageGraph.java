import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class MultistageGraph {
    private int numVertices;
    private int numStages;
    private List<List<Edge>> adjacencyList;

    private static class Edge {
        private final int to;
        private final int weight;

        private Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public MultistageGraph(int numVertices, int numStages) {
        this.numVertices = numVertices;
        this.numStages = numStages;
        adjacencyList = new ArrayList<>(numStages);

        for (int i = 0; i < numStages; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int stage, int from, int to, int weight) {
        adjacencyList.get(stage).add(new Edge(to, weight));
    }

    public int findShortestPath(int source, int sink) {
        int[] dist = new int[numVertices];
        int[] next = new int[numVertices];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < numStages - 1; i++) {
            for (Edge e : adjacencyList.get(i)) {
                int u = e.to;
                int v = source;
                int w = e.weight;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    next[v] = u;
                }
            }
        }

        for (int i = numStages - 2; i >= 0; i--) {
            for (Edge e : adjacencyList.get(i)) {
                int u = e.to;
                int v = sink;
                int w = e.weight;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    next[v] = u;
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int u = source;
        path.add(u);

        while (u != sink) {
            u = next[u];
            path.add(u);
        }

        System.out.println("Shortest path from " + source + " to " + sink + " has length " + dist[sink] + ":");
        System.out.println(path);

        return dist[sink];
    }

    public static void main(String[] args) {
       // Scanner sc=new Scanner(System.in);
        MultistageGraph m = new MultistageGraph(5, 4);
        m.addEdge(0, 0, 1, 3);
        m.addEdge(0, 0, 2, 6);
        m.addEdge(1, 1, 3, 2);
        m.addEdge(1, 1, 4, 1);
        m.addEdge(2, 2, 3, 1);
        m.addEdge(2, 2, 4, 4);
        m.addEdge(3, 3, 4, 2);

        int source = 0;
        int sink = 4;
        m.findShortestPath(source, sink);
    }
}
