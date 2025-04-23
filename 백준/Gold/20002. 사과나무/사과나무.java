import java.io.IOException;

class Main {
	public static void main(String[] args) throws IOException {
		int N = read();
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + read() - dp[i - 1][j - 1];
			}
		}

		int max = -1;
		// 수확 크기
		for (int n = 1; n <= N; n++) {
			// 수확 시작 위치 (i + 1, j + 1)에서 수확 시작
			for (int i = 0; i <= N - n; i++) {
				int row = i + n;
				for (int j = 0; j <= N - n; j++) {
					int col = j + n;
					max = Math.max(max, dp[row][col] - dp[i][col] - dp[row][j] + dp[i][j]);
				}
			}
		}

		System.out.println(max);
	}

	static int read() throws IOException {
		int b;
		do b = System.in.read(); while (b <= 32);
		boolean isNeg = false;
		if (b == 45) {
			isNeg = true;
			b = System.in.read();
		}
		int n = 0;
		do {
			n = (n << 3) + (n << 1) + (b & 15);
			b = System.in.read();
		} while (b > 47 && b < 58);
		return isNeg ? -n : n;
	}
}