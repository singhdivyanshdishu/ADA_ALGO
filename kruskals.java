/*
 * INPUT FORMAT
 * Enter the number of nodes in the graph: 7
Enter the edges and their weights (one per line, in the format 'node1 node2 weight'), and enter 'done' when finished:
A B 28
A F 10
B C 16
B G 14
C D 12
D E 22
D G 18
E G 24
E F 25
done

 */



import java.util.*;

class Kruskals {
static class Edge implements Comparable<Edge> {
    char node1, node2;
    int weight;
    
    Edge(char node1, char node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }
    
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

static Map<Character, Character> parent = new HashMap<>();
static Map<Character, Integer> rank = new HashMap<>();

static void makeSet(char node) {
    parent.put(node, node);
    rank.put(node, 0);
}

static char find(char node) {
    if (parent.get(node) != node) {
        parent.put(node, find(parent.get(node)));
    }
    return parent.get(node);
}

static void union(char node1, char node2) {
    char root1 = find(node1);
    char root2 = find(node2);
    
    if (root1 != root2) {
        if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
        }
    }
}

static List<Edge> kruskal(Map<Character, List<Edge>> graph) {
    List<Edge> mst = new ArrayList<>();
    for (char node : graph.keySet()) {
        makeSet(node);
    }
    List<Edge> edges = new ArrayList<>();
    for (char node : graph.keySet()) {
        for (Edge edge : graph.get(node)) {
            edges.add(edge);
        }
    }
    Collections.sort(edges);
    for (Edge edge : edges) {
        if (find(edge.node1) != find(edge.node2)) {
            mst.add(edge);
            union(edge.node1, edge.node2);
        }
    }
    return mst;
}

public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Map<Character, List<Edge>> graph = new HashMap<>();
    System.out.print("Enter the number of nodes in the graph: ");
    int numNodes = input.nextInt();
    input.nextLine();
    for (int i = 0; i < numNodes; i++) {
        char node = (char) ('A' + i);
        graph.put(node, new ArrayList<>());
    }
    System.out.println("Enter the edges and their weights (one per line, in the format 'node1 node2 weight'), and enter 'done' when finished:");
    String line;
    while (!(line = input.nextLine().trim()).equals("done")) {
        String[] parts = line.split(" ");
        char node1 = parts[0].charAt(0);
        char node2 = parts[1].charAt(0);
        int weight = Integer.parseInt(parts[2]);
        graph.get(node1).add(new Edge(node1, node2, weight));
        graph.get(node2).add(new Edge(node2, node1, weight));
    }
    List<Edge> mst = kruskal(graph);
    System.out.println("Minimum Spanning Tree:");
    for (Edge edge : mst) {
        System.out.println(edge.node1 + " -- " + edge.node2 + " : " + edge.weight);
    }
}
}