
/*INPUT FORMAT 
Enter number of nodes: 7
Enter number of edges: 9
Enter source, destination, and weight
A B 28
A F 10
B C 16
B G 14
C D 12
D E 22
D G 18
E G 24
E F 25
Enter start node: A
Enter end node: F
Shortest path from A to F: A F 
Distance: 10





A B 1
A C 5
B C 4
B D 8
B E 7
C D 6
C F 2
D E 11
D F 9
E F 37
E G 10
E F 25
*/
import java.util.*;

public class dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of nodes and edges
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();

        // Initialize adjacency list, distances, and previous nodes
        Map<Character, List<Edge>> graph = new HashMap<>();
        Map<Character, Integer> distances = new HashMap<>();
        Map<Character, Character> previous = new HashMap<>();
        for (char i = 'A'; i < 'A' + n; i++) {
            graph.put(i, new ArrayList<>());
            distances.put(i, Integer.MAX_VALUE);
            previous.put(i, ' '); // default "no previous node" value
        }

        // Read edges and weights
        System.out.println("Enter source, destination, and weight");
        for (int i = 0; i < m; i++) {
            //System.out.print("Enter source, destination, and weight of edge " + (i+1) + ": ");
            char u = sc.next().charAt(0);
            char v = sc.next().charAt(0);
            int w = sc.nextInt();
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w)); // Remove this line if graph is directed
        }

        // Read start and end nodes
        System.out.print("Enter start node: ");
        char start = sc.next().charAt(0);
        System.out.print("Enter end node: ");
        char end = sc.next().charAt(0);

        // Dijkstra's algorithm
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        distances.put(start, 0);
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            char u = curr.dest;
            int dist = curr.weight;
            if (distances.get(u) < dist) continue;
            for (Edge e : graph.get(u)) {
                char v = e.dest;
                int w = e.weight;
                if (dist + w < distances.get(v)) {
                    distances.put(v, dist + w);
                    previous.put(v, u); // set previous node
                    pq.offer(new Edge(v, dist + w));
                }
            }
        }

        // Print shortest path and distance
        System.out.print("Shortest path from " + start + " to " + end + ": ");
        List<Character> path = new ArrayList<>();
        for (char u = end; u != ' '; u = previous.get(u)) {
            path.add(u);
        }
        Collections.reverse(path);
        for (char u : path) {
            System.out.print(u + " ");
        }
        System.out.println("\nDistance: " + distances.get(end));
    }

    static class Edge implements Comparable<Edge> {
        char dest;
        int weight;
        Edge(char dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
        public int compareTo(Edge other) {
            return Integer.compare(weight, other.weight);
        }
    }
}


// import java.util.*;

// public class DijkstraAlgorithm {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         // Read number of nodes and edges
//         System.out.print("Enter number of nodes: ");
//         int n = sc.nextInt();
//         System.out.print("Enter number of edges: ");
//         int m = sc.nextInt();

//         // Initialize adjacency list and distances
//         Map<Character, List<Edge>> graph = new HashMap<>();
//         Map<Character, Integer> distances = new HashMap<>();
//         for (char i = 'A'; i < 'A' + n; i++) {
//             graph.put(i, new ArrayList<>());
//             distances.put(i, Integer.MAX_VALUE);
//         }

//         // Read edges and weights
//         for (int i = 0; i < m; i++) {
//             System.out.print("Enter source, destination, and weight of edge " + (i+1) + ": ");
//             char u = sc.next().charAt(0);
//             char v = sc.next().charAt(0);
//             int w = sc.nextInt();
//             graph.get(u).add(new Edge(v, w));
//             graph.get(v).add(new Edge(u, w)); // Remove this line if graph is directed
//         }

//         // Read start node
//         System.out.print("Enter start node: ");
//         char start = sc.next().charAt(0);

//         // Dijkstra's algorithm
//         PriorityQueue<Edge> pq = new PriorityQueue<>();
//         pq.offer(new Edge(start, 0));
//         distances.put(start, 0);
//         while (!pq.isEmpty()) {
//             Edge curr = pq.poll();
//             char u = curr.dest;
//             int dist = curr.weight;
//             if (distances.get(u) < dist) continue;
//             for (Edge e : graph.get(u)) {
//                 char v = e.dest;
//                 int w = e.weight;
//                 if (dist + w < distances.get(v)) {
//                     distances.put(v, dist + w);
//                     pq.offer(new Edge(v, dist + w));
//                 }
//             }
//         }

//         // Print distances
//         System.out.println("Shortest distances from node " + start + ":");
//         for (char i = 'A'; i < 'A' + n; i++) {
//             System.out.println(i + ": " + distances.get(i));
//         }
//     }

//     static class Edge implements Comparable<Edge> {
//         char dest;
//         int weight;
//         Edge(char dest, int weight) {
//             this.dest = dest;
//             this.weight = weight;
//         }
//         public int compareTo(Edge other) {
//             return Integer.compare(weight, other.weight);
//         }
//     }
// }
