import java.io.IOException;
import java.util.ArrayList;

class Main {

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        int N = r.nextInt(), M = r.nextInt();
        boolean[][] nodes = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i][i] = true;
        }
        for (int i = 0; i < M; i++) {
            nodes[r.nextInt()][r.nextInt()] = true;
        }
        for (int k = 1; k <= N; k++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    nodes[s][e] = nodes[s][e] || (nodes[s][k] && nodes[k][e]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res++;
            for (int j = 1; j <= N; j++) {
                if (!nodes[i][j] && !nodes[j][i]) {
                    res--;
                    break;
                }
            }
        }
        System.out.println(res);
    }

    static class Reader {
        final int SIZE = 1 << 15;
        byte[] buffer = new byte[SIZE];
        int i, size;

        int nextInt() throws IOException {
            byte b;
            do b = read(); while (b <= 32);
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