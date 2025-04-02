import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
    public static void main(String[] args) throws IOException {
        int N = read(), M = read();
        UnionFind uf = new UnionFind(N + 1);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (read() == 0) {
                uf.union(read(), read());
            } else {
                if (uf.find(read()) == uf.find(read())) {
                    res.append("YES");
                } else {
                    res.append("NO");
                }
                res.append("\n");
            }
        }
        bw.write(res.toString());
        bw.flush();
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b < 58 && b > 47);
        return n;
    }

    static class UnionFind {
        int[] nodes;

        public UnionFind(int n) {
            this.nodes = new int[n];
            for (int i = 0; i < n; i++) this.nodes[i] = i;
        }

        int find(int n) {
            if (nodes[n] != n) {
                nodes[n] = find(nodes[n]);
            }
            return nodes[n];
        }

        boolean union(int x, int y) {
            if (find(x) == find(y)) return false;
            if (find(x) < find(y)) {
                nodes[find(y)] = find(x);
            } else {
                nodes[find(x)] = find(y);
            }
            return true;
        }
    }
}