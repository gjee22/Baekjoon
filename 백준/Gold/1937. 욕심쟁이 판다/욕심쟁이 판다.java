import java.io.IOException;

class Main {
	static int N;
	static int[][] map;
	static int[][] dp;
	static final int[] dr = { 0, 0, 1, -1 };
	static final int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		N = read();
		map = new int[N][N];
		dp = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = read();
				dp[r][c] = -1;
			}
		}

		int max = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				max = Math.max(max, dfs(r, c));
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