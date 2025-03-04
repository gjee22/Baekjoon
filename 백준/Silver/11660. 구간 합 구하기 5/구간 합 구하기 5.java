import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
    public static void main(String[] args) throws IOException {
        int N = read(), M = read();
        int[][] nums = new int[N + 1][N + 1];
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                nums[x][y] = nums[x - 1][y] + nums[x][y - 1] + read() - nums[x - 1][y - 1];
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int x1 = read(), y1 = read(), x2 = read(), y2 = read();
            int sum = nums[x2][y2] - (nums[x2][y1 - 1] + nums[x1 - 1][y2] - nums[x1 - 1][y1 - 1]);
            res.append(sum).append("\n");
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