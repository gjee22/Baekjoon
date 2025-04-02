import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
    static int SIZE = 1 << 20;
    static int[] tree = new int[SIZE << 1];

    public static void main(String[] args) throws IOException {
        int N = read();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int inst = read();
            if (inst == 2) {
                update(read(), read());
            } else {
                res.append(query(read())).append("\n");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static int query(int r) {
        int p = 1;
        while ((2*p) < (SIZE << 1)) {
            if (tree[2*p] >= r) {
                p *= 2;
            } else {
                p = 2*p + 1;
                r -= tree[p - 1];
            }
        }
        p -= SIZE;
        update(p, -1);
        return p;
    }

    static void update(int i, int c) {
        i += SIZE;
        tree[i] += c;
        while (i > 1) {
            tree[i / 2] += c;
            i /= 2;
        }
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0; boolean isNeg = false;
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