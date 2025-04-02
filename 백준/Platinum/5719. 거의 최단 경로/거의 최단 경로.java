import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;
import java.lang.StringBuilder;

class Main {
    static class Node implements Comparable<Node> {
        int n;
        int c;

        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }

        @Override
        public int compareTo(Node e) {
            return this.c - e.c;
        }
    }

    static boolean[][] isUsed;
    static LinkedList<Node>[] nodes;
    static LinkedList<Integer>[] path;

    public static void main(String[] args) throws IOException {
        StringBuilder res = new StringBuilder();
        while (true) {
            int V = read(), E = read();
            if (V == 0) break;
            int s = read(), e = read();
            isUsed = new boolean[V][V];
            nodes = new LinkedList[V];
            path = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                nodes[i] = new LinkedList<>();
                path[i] = new LinkedList<>();
            }
            for (int i = 0; i < E; i++) {
                nodes[read()].add(new Node(read(), read()));
            }
            dijkstra(V, s, e);
            removeEdges(s, e);
            int r = dijkstra(V, s, e);
            res.append(r == Integer.MAX_VALUE ? -1 : r).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static void removeEdges(int s, int e) {
        if (s == e) return;
        for (int i : path[e]) {
            if (!isUsed[i][e]) {
                isUsed[i][e] = true;
                removeEdges(s, i);
            }
        }
    }

    static int dijkstra(int v, int s, int e) {
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (Node next : nodes[cur.n]) {
                if (isUsed[cur.n][next.n]) continue;
                int nd = dist[cur.n] + next.c;
                if (nd < dist[next.n]) {
                    path[next.n] = new LinkedList<>();
                    path[next.n].add(cur.n);
                    dist[next.n] = nd;
                    pq.offer(new Node(next.n, nd));
                }
                else if (nd == dist[next.n]) {
                    path[next.n].add(cur.n);
                }
            }
        }
        return dist[e];
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