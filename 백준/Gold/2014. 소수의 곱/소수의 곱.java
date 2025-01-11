import java.io.IOException;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) throws IOException {
        int K = read(), N = read();
        int[] primes = new int[K];
        TreeSet<Integer> multiples = new TreeSet<>();
        primes[0] = read();
        multiples.add(primes[0]);

        if (primes[0] == 1) {
            System.out.println(1);
            return;
        }

        for (int i = 1; i < K; i++) {
            primes[i] = read();
            multiples.add(primes[i]);
        }

        int cur = -1;
        int count = K;
        for (int i = 0; i < N; i++) {
            cur = multiples.pollFirst();
            for (int p : primes) {
                long n = ((long) p) * cur;
                if (n > Integer.MAX_VALUE) {
                    continue;
                }
                if (count < N) {
                    count += multiples.add((int) n) ? 1 : 0;
                    continue;
                }
                if (multiples.isEmpty()) break;
                if (n < multiples.last()) {
                    multiples.add((int) n);
                } else {
                    break;
                }
            }
        }

        System.out.println(cur);
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