import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        int N = read(), M = read();
        int[][] edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            edges[i][0] = read();
            edges[i][1] = read();
            edges[i][2] = read();
        }

        long[] distances = new long[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[1] = 0;
        boolean updated = false;
        for (int i = 0; i < N; i++) {
            updated = false;
            for (int[] e : edges) {
                int s = e[0], d = e[1], c = e[2];
                if (distances[s] == Integer.MAX_VALUE) continue;
                long nd = distances[s] + c;
                if (distances[d] > nd) {
                    distances[d] = nd;
                    updated = true;
                }
            }
        }

        if (updated) {
            System.out.println(-1);
            return;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder res = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            res.append(distances[i] == Integer.MAX_VALUE ? -1 : distances[i]).append("\n");
        }
        bw.write(res.toString());
        bw.flush();
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        boolean isNeg = false;
        if (b == 45) {
            isNeg = true;
            b = System.in.read();
        }
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return isNeg ? -n : n;
    }
}