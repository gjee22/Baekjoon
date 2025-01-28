import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
    public static void main(String[] args) throws IOException {
        int C = read();
        int MAX = 1_000;
        int[] nums = new int[MAX + 1];
        for (int i = 1; i <= MAX; i++) {
            nums[i] = i;
        }
        for (int i = 2; i <= MAX; i++) {
            if (nums[i] != i) continue;
            for (int j = i; j <= MAX; j += i) {
                nums[j] -= (nums[j] / i);
            }
        }
        for (int i = 1; i <= MAX; i++) {
            nums[i] += nums[i - 1];
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < C; i++) {
            int n = read();
            res.append(2*nums[n] + 1).append("\n");
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