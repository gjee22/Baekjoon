import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    static class Node implements Comparable<Node> {
        int id;
        int cost;

        public Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    static int V, E, K;
    static ArrayList<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        V = read();
        E = read();
        K = read();
        nodes = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            nodes[read()].add(new Node(read(), read()));
        }
        int[] cost = dijkstra();
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            res.append(cost[i]).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static int[] dijkstra() {
        PriorityQueue<Integer>[] candidates = new PriorityQueue[V + 1];
        for (int i = 1; i <= V; i++) {
            candidates[i] = new PriorityQueue<>(Comparator.reverseOrder());
        }
        int[] cost = new int[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        candidates[1].offer(0);
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (Node next : nodes[cur.id]) {
                int nd = cur.cost + next.cost;
                if (candidates[next.id].size() == K && nd < candidates[next.id].peek()) {
                    pq.offer(new Node(next.id, nd));
                    candidates[next.id].poll();
                    candidates[next.id].offer(nd);
                }
                else if (candidates[next.id].size() < K) {
                    pq.offer(new Node(next.id, nd));
                    candidates[next.id].offer(nd);
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            cost[i] = candidates[i].size() == K ? candidates[i].poll() : -1;
        }

        return cost;
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
}