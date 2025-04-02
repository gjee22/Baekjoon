import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        int L = read();
        int[][] triangle = new int[L][L];
        triangle[0][0] = read();
        for (int level = 1; level < L; level++) {
            for (int j = 0; j <= level; j++) {
                int l = Math.max(j - 1, 0);
                int r = Math.min(j, L - 1);
                triangle[level][j] = Math.max(triangle[level - 1][l], triangle[level - 1][r]) + read();
            }
        }
        int max = -1;
        for (int n : triangle[L - 1]) {
            max = Math.max(max, n);
        }
        System.out.println(max);
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