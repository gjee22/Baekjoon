import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        int M = read();
        int N = 0;
        int[] colors = new int[M];
        for (int i = 0; i < M; i++) {
            colors[i] = read();
            N += colors[i];
        }
        int K = read();

        double res = 0;
        for (int i : colors) {
            double p = 1.0;
            if (i >= K) {
                for (int j = 0; j < K; j++) {
                    p *= ((double) (i - j) / (N - j));
                }
                res += p;
            }
        }
        System.out.println(res);
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