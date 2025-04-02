import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
    public static void main(String[] args) throws IOException {
        int SIZE = 30;
        int[][] comb = new int[SIZE][SIZE];
        for (int i = 1; i < SIZE; i++) {
            comb[i][1] = i;
        }
        for (int i = 2; i < SIZE; i++) {
            for (int j = 2; j < SIZE; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }

        StringBuilder res = new StringBuilder();
        int N = read();
        for (int i = 0; i < N; i++) {
            int n = read(), m = read();
            res.append(comb[m][n]).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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