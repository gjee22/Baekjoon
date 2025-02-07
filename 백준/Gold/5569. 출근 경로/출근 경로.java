import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int MOD = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        // 0, 1 = 좌표, 2 = 가는 방향 (0 = 오, 1 = 아래),
        // 3 = 현재 교차로에서 꺾음 여부 (0 = 꺾지 않음, 1 = 꺾음)
        int[][][][] dp = new int[W + 1][H + 1][2][2];
        for (int i = 1; i <= W; i++) {
            dp[i][1][0][0] = 1;
        }
        for (int i = 1; i <= H; i++) {
            dp[1][i][1][0] = 1;
        }

        for (int r = 2; r <= W; r++) {
            for (int c = 2; c <= H; c++) {
                dp[r][c][0][0] = (dp[r - 1][c][0][0] + dp[r - 1][c][0][1]) % MOD;
                dp[r][c][0][1] = dp[r - 1][c][1][0] % MOD;
                dp[r][c][1][0] = (dp[r][c - 1][1][0] + dp[r][c - 1][1][1]) % MOD;
                dp[r][c][1][1] = dp[r][c - 1][0][0] % MOD;
            }
        }

        System.out.println((dp[W][H][0][0] + dp[W][H][0][1] + dp[W][H][1][0] + dp[W][H][1][1]) % MOD);
    }
}