import java.io.IOException;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        int N = read(), M = read();
        int start = 0, end = 0, count = 0, sum = 0;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = read();
        }

        while (end < N && start < N) {
            while (end < N && sum <= M) {
                sum += arr[end++];
                if (sum == M) {
                    count++;
                }
            }
            while (start < end && sum >= M) {
                sum -= arr[start++];
                if (sum == M) count++;
            }
        }
        System.out.println(count);
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