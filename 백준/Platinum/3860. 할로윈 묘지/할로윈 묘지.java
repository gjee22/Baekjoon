import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.StringBuilder;
import java.util.Objects;

class Main {
    static final int[] dx = { 1, -1, 0, 0 };
    static final int[] dy = { 0, 0, -1, 1 };
    static int W, H, V;

    static class Edge {
        Node s;
        Node d;
        int c;

        public Edge(Node s, Node d, int c) {
            this.s = s;
            this.d = d;
            this.c = c;
        }
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Node n = (Node) o;
            return this.x == n.x && this.y == n.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws IOException {
        W = -1;
        H = -1;
        V = 0;
        StringBuilder res = new StringBuilder();
        while (W != 0) {
            W = read();
            H = read();
            if (W == 0) {
                break;
            }
            ArrayList<Edge> edges = getMaze();
            res.append(bellmanFord(edges)).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static String bellmanFord(ArrayList<Edge> edges) {
        boolean updated = false;
        int[][] dist = new int[W][H];
        for (int[] arr : dist) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        for (int i = 0; i < V; i++) {
            updated = false;
            for (Edge e : edges) {
                Node s = e.s;
                Node d = e.d;
                if (dist[s.x][s.y] == Integer.MAX_VALUE) {
                    continue;
                }
                int nd = dist[s.x][s.y] + e.c;
                if (nd < dist[d.x][d.y]) {
                    updated = true;
                    dist[d.x][d.y] = nd;
                }
            }
        }

        if (updated) {
            return "Never";
        }
        if (dist[W - 1][H - 1] == Integer.MAX_VALUE) {
            return "Impossible";
        }
        return String.valueOf(dist[W - 1][H - 1]);
    }

    static ArrayList<Edge> getMaze() throws IOException {
        ArrayList<Edge> edges = new ArrayList<>();
        HashSet<Node> tombs = new HashSet<>();
        HashSet<Node> holes = new HashSet<>();
        int G = read();
        for (int i = 0; i < G; i++) {
            tombs.add(new Node(read(), read()));
        }
        int T = read();
        for (int i = 0; i < T; i++) {
            Node s = new Node(read(), read());
            Node d = new Node(read(), read());
            holes.add(s);
            edges.add(new Edge(s, d, read()));
        }
        holes.add(new Node(W - 1, H - 1));

        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                Node from = new Node(x, y);
                if (holes.contains(from)) {
                    continue;
                }
                for (int i = 0; i < dx.length; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    Node to = new Node(nx, ny);
                    if (check(nx, ny) && !tombs.contains(to)) {
                        edges.add(new Edge(from, to, 1));
                    }
                }
            }
        }
        
        V = (W * H) - tombs.size() + 1;

        return edges;
    }

    static boolean check(int x, int y) {
        return x < W && x >= 0 && y < H && y >= 0;
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        boolean isNeg = false;
        if (b == 45) {
            isNeg = true;
            b = System.in.read();
        }
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return isNeg ? -n : n;
    }
}