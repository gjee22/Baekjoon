import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.Arrays;

class Main {
    static int N, M;
    static int[] nums;
    static boolean[] used;
    static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        N = read();
        M = read();
        nums = new int[N];
        used = new boolean[N];
        for (int i = 0; i < N; i++) {
            nums[i] = read();
        }
        Arrays.sort(nums);
        dfs(M, new String[M], 0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static void dfs(int m, String[] cur, int p) {
        if (m == 0) {
            for (String s : cur) {
                res.append(s).append(" ");
            }
            res.append("\n");
            return;
        }

        int prev = -1;
        for (int i = 0; i < N; i++) {
            if (!used[i] && prev != nums[i]) {
                prev = nums[i];
                used[i] = true;
                cur[p] = String.valueOf(nums[i]);
                dfs(m - 1, cur, p + 1);
                used[i] = false;
            }
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