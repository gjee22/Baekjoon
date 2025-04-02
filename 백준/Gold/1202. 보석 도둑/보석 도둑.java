import java.io.IOException;
import java.lang.Comparable;
import java.util.LinkedList;
import java.util.Arrays;

class Main {
    static Jewel[] jewels;

    public static void main(String[] args) throws IOException {
        int N = read(), K = read();
        int size = 1;
        while ((size <<= 1) < N);
        jewels = new Jewel[size << 1];
        // Dummy
        jewels[0] = new Jewel(0, -1);
        int[] caps = new int[K];
        for (int i = size; i < size + N; i++) {
            jewels[i] = new Jewel(read(), read());
        }
        for (int i = 0; i < K; i++) {
            caps[i] = read();
        }
        Arrays.sort(jewels, size, size + N);
        Arrays.sort(caps);
        for (int i = size + N - 1; i > 1; i--) {
            int p = i / 2;
            if (i >= size) {
                jewels[i].i = i;
            }
            jewels[i] = jewels[i] == null ? new Jewel(0, -1) : jewels[i];
            jewels[p] = jewels[p] == null ? new Jewel(0, -1) : jewels[p];
            jewels[p] = jewels[p].p < jewels[i].p ? jewels[i] : jewels[p];
        }

        int e = size;
        long total = 0;
        for (int c : caps) {
            while (e < size + N && c >= jewels[e].w) {
                e++;
            }
            e--;
            int i = query(size, e);
            total += jewels[i].p == -1 ? 0 : jewels[i].p;
            update(i);
        }

        System.out.println(total);
    }

    static int query(int s, int e) {
        int i = 0;
        while (s <= e) {
            if (s % 2 == 1) {
                if (jewels[s].p > jewels[i].p) {
                    i = s;
                }
            }
            if (e % 2 == 0) {
                if (jewels[e].p > jewels[i].p) {
                    i = e;
                }
            }
            s = (s + 1) / 2;
            e = (e - 1) / 2;
        }
        return jewels[i].i;
    }

    static void update(int i) {
        jewels[i].p = -1;
        int p = i;
        while (p > 1) {
            p /= 2;
            jewels[p] = jewels[2*p].p > (jewels[2*p + 1] == null ? -1 : jewels[2*p + 1].p) ? jewels[2*p] : jewels[2*p + 1];
        }
    }

    static class Jewel implements Comparable<Jewel> {
        int i, w, p;

        public Jewel(int w, int p) {
            this.w = w;
            this.p = p;
        }

        @Override
        public int compareTo(Jewel j) {
            return this.w - j.w;
        }
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