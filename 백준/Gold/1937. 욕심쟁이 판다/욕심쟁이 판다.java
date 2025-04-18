import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int[][] map;
	static int[][] dp;
	static final int[] dr = { 0, 0, 1, -1 };
	static final int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				dp[r][c] = -1;
			}
		}

		int max = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				max = Math.max(max, dp[r][c] == -1 ? dfs(r, c) : dp[r][c]);
			}
		}
		System.out.println(max);
	}

	static int dfs(int r, int c) {
		if (dp[r][c] != -1) return dp[r][c];
		dp[r][c] = 1;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i], nc = c + dc[i];
			if (isValid(nr, nc) && map[r][c] < map[nr][nc]) {
				dp[r][c] = Math.max(dp[r][c], 1 + dfs(nr, nc));
			}
		}
		return dp[r][c];
	}


	static boolean isValid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}