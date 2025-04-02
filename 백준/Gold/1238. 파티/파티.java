import java.io.IOException;
import java.util.*;

class Main {
    static int N, M, X;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        N = read(); M = read(); X = read();
        Node[] nodes = new Node[N + 1];
        Node[] reverse = new Node[N + 1];
        for (int i = 0; i < M; i++) {
            int src = read(), dest = read(), cost = read();
            if (nodes[src] == null) {
                nodes[src] = new Node(src);
            }
            if (reverse[dest] == null) {
                reverse[dest] = new Node(dest);
            }
            nodes[src].neighbors.add(new int[] { dest, cost });
            reverse[dest].neighbors.add(new int[] { src, cost });
        }
        int[] distances = dijkstra(nodes, X);
        int[] rDistances = dijkstra(reverse, X);
        int max = -1;
        for (int i = 1; i <= N; i++) {
            if (nodes[i] == null) continue;
            max = Math.max(max, distances[i] + rDistances[i]);
        }
        System.out.println(max);
    }

    static int[] dijkstra(Node[] nodes, int src) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;
        pq.offer(new int[] { src, distances[src] });
        boolean[] visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Node cur = nodes[pq.poll()[0]];
            visited[cur.id] = true;
            for (int[] n : cur.neighbors) {
                int nd = distances[cur.id] + n[1];
                if (nd < distances[n[0]]) {
                    distances[n[0]] = nd;
                    if (!visited[n[0]]) pq.offer(new int[] { n[0], nd });
                }
            }
        }

        return distances;
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return n;
    }

    static class Node {
        List<int[]> neighbors;
        int id;

        public Node (int id) {
            this.id = id;
            this.neighbors = new LinkedList<>();
        }
    }
}