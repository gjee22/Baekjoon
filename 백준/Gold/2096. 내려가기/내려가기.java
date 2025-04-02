import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        int N = read();
        int[][] max = new int[N][3];
        int[][] min = new int[N][3];

        max[0][0] = read();
        max[0][1] = read();
        max[0][2] = read();

        min[0][0] = max[0][0];
        min[0][1] = max[0][1];
        min[0][2] = max[0][2];

        for (int i = 1; i < N; i++) {
            int l = read(), m = read(), r = read();

            max[i][0] = Math.max(max[i - 1][0] + l, max[i - 1][1] + l);
            max[i][1] = Math.max(Math.max(max[i - 1][0] + m, max[i - 1][1] + m), max[i - 1][2] + m);
            max[i][2] = Math.max(max[i - 1][1] + r, max[i - 1][2] + r);

            min[i][0] = Math.min(min[i - 1][0] + l, min[i - 1][1] + l);
            min[i][1] = Math.min(Math.min(min[i - 1][0] + m, min[i - 1][1] + m), min[i - 1][2] + m);
            min[i][2] = Math.min(min[i - 1][1] + r, min[i - 1][2] + r);
        }

        int maxR = Math.max(Math.max(max[N - 1][0], max[N - 1][1]), max[N - 1][2]);
        int minR = Math.min(Math.min(min[N - 1][0], min[N - 1][1]), min[N - 1][2]);

        System.out.println(maxR + " " + minR);
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
