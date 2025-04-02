import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
    static int[] tree;
    static int size;

    public static void main(String[] args) throws IOException {
        int N = read();
        int L = read();
        size = 1;
        while ((size <<= 1) < N);
        tree = new int[size << 1];
        for (int i = 0; i < size; i++) {
            tree[i] = 1_000_000_001;
        }
        for (int i = size; i < size + N; i++) {
            tree[i] = read();
        }
        for (int i = size + N - 1; i > 0; i--) {
            tree[i / 2] = Math.min(tree[i / 2], tree[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int s = Math.max(0, i - L + 1);
            sb.append(query(s, i)).append(" ");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    static int query(int i, int j) {
        i = size + i;
        j = size + j;

        int min = 1_000_000_001;
        while (i <= j) {
            if (i % 2 == 1) {
                min = Math.min(min, tree[i]);
            }
            if (j % 2 == 0) {
                min = Math.min(min, tree[j]);
            }
            i = (i + 1) / 2;
            j = (j - 1) / 2;
        }

        return min;
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