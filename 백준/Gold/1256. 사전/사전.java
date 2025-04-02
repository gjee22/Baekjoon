import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuilder;
import java.util.StringTokenizer;

class Main {
    static int N, M, K, MAX = 1_000_000_000;
    static StringBuilder res = new StringBuilder();
    static int[][] comb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        comb = new int[N + M + 1][N + 1];
        for (int i = 0; i <= N + M; i++) {
            comb[i][0] = 1;
        }
        for (int i = 1; i <= N + M; i++) {
            for (int j = 1; j <= N; j++) {
                int cur = comb[i - 1][j - 1] + comb[i - 1][j];
                if (cur > MAX) {
                    cur = MAX + 1;
                }
                comb[i][j] = cur;
            }
        }
        search();
        System.out.println(res.toString());
    }

    static void search() {
        if (N == 0 || M == 0) {
            while (N-- > 0) {
                res.append("a");
            }
            while (M-- > 0) {
                res.append("z");
            }
            return;
        }

        int n = M;
        int r = 0;
        while (r <= N && comb[n][r] < K) {
            n++; r++;
        }
        if (n > N + M && comb[n - 1][r - 1] < K) {
            System.out.println("-1");
            System.exit(0);
        }

        for (int i = 0; i < N - r; i++) {
            res.append("a");
        }
        if (M-- > 0) {
            res.append("z");
        }
        N = r;

        if (n > 0 && r > 0) {
            K -= comb[n - 1][r - 1];
        }

        search();
    }
}