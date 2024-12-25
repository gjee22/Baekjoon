import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.lang.StringBuilder;

class Main {
    final static long MAX = 100_000_000_001L;

    public static void main(String[] args) throws IOException {
        int N = read(), M = read();
        long[][] graph = new long[N + 1][N + 1];
        for (long[] g : graph) Arrays.fill(g, MAX);
        for (int i = 1; i <= N; i++) graph[i][i] = 0;
        for (int i = 0; i < M; i++) {
            int r = read(), c = read();
            graph[r][c] = Math.min(graph[r][c], read());
        }

        for (int k = 1; k <= N; k++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    graph[s][e] = Math.min(graph[s][e], graph[s][k] + graph[k][e]);
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                res.append(graph[i][j] == MAX ? 0 : graph[i][j]).append(" ");
            }
            res.append("\n");
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
        } while (b > 47 && b < 58);
        return n;
    }
}