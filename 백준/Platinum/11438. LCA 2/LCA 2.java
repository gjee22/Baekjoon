import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.HashSet;

class Main {
    static int K = 1;
    static Node[] nodes;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        int N = r.nextInt();

        // 트리 구조 파악 및 깊이 구하기
        nodes = new Node[N + 1];
        for(int i = 1;i <= N; i*=2) K++;
        dp = new int[K][N + 1];

        for (int i = 0; i < N - 1; i++) {
            int x = r.nextInt();
            int y = r.nextInt();
            if (nodes[x] == null) {
                nodes[x] = new Node(x);
            }
            if (nodes[y] == null) {
                nodes[y] = new Node(y);
            }
            nodes[x].n.add(y);
            nodes[y].n.add(x);
        }
        dfs(nodes[1], 1, 0);
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][dp[i - 1][j]];
            }
        }

        // LCA 알고리즘으로 조상 구하기
        int M = r.nextInt();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            res.append(lca(r.nextInt(), r.nextInt())).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static int lca(int x, int y) {
        if (nodes[x].d < nodes[y].d) {
            int temp = x;
            x = y;
            y = temp;
        }

        for (int i = K - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= nodes[x].d - nodes[y].d)
                x = dp[i][x];
        }
        if (x == y) return x;
        for (int i = K - 1; i >= 0; i--) {
            if (dp[i][x] != dp[i][y]) {
                x = dp[i][x];
                y = dp[i][y];
            }
        }
        return dp[0][x];
    }

    static void dfs(Node cur, int p, int d) {
        dp[0][cur.id] = p;
        cur.d = d;
        for (int i : cur.n) {
            nodes[i].n.remove(cur.id);
            dfs(nodes[i], cur.id, d + 1);
        }
    }

    static class Node {
        int id, d;
        HashSet<Integer> n;

        public Node(int id) {
            this.id = id;
            this.n = new HashSet<>();
        }
    }

    static class Reader {
        final int SIZE = 1 << 15;
        byte[] buffer = new byte[SIZE];
        int i, size;

        int nextInt() throws IOException {
            byte b;
            do {
                b = read();
            } while (b <= 32);
            int n = 0;
            do {
                n = (n << 3) + (n << 1) + (b & 15);
                b = read();
            } while (b > 47 && b < 58);
            return n;
        }

        byte read() throws IOException {
            if (i == size) {
                size = System.in.read(buffer, i = 0, SIZE);
                if (size < 0) {
                    buffer[0] = -1;
                }
            }
            return buffer[i++];
        }
    }
}