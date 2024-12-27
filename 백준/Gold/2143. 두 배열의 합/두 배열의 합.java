import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

class Main {
    public static void main(String[] args) throws IOException {
        int T = read();
        int N = read();
        int[] A = new int[N + 1];
        A[1] = read();
        for (int i = 2; i <= N; i++) {
            A[i] = A[i - 1] + read();
        }

        int M = read();
        int[] B = new int[M + 1];
        B[1] = read();
        for (int i = 2; i <= M; i++) {
            B[i] = B[i - 1] + read();
        }

        Map<Integer, Integer> combB = new HashMap<>();
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < i; j++) {
                int k = B[i] - B[j];
                if (combB.containsKey(k)) {
                    combB.replace(k, combB.get(k) + 1);
                } else {
                    combB.put(k, 1);
                }
            }
        }

        long count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                int sum = T - (A[i] - A[j]);
                if (combB.containsKey(sum)) count += combB.get(sum);
            }
        }
        System.out.println(count);
    }

    static int read() throws IOException {
        int b;
        boolean isNeg = false;
        do b = System.in.read(); while (b <= 32);
        if (b == 45) {
            isNeg = true;
            b = System.in.read();
        }
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return isNeg ? -n : n;
    }
}