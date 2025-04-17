import java.io.IOException;

class Main {
	public static void main(String[] args) throws IOException {
		int N = read();
		int[] nums = new int[N];
		int[] dp = new int[N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			nums[i] = read();
			dp[i] = 1;
			for (int j = 0; j < N; j++) {
				if (nums[i] > nums[j])
					dp[i] = Math.max(dp[i], dp[j] + 1);
				max = Math.max(max, dp[i]);
			}
		}
		System.out.println(max);
	}

	static int read() throws IOException {
		int b;
		do {
			b = System.in.read();
		} while (b <= 32);
		int n = 0;
		do {
			n = (n << 3) + (n << 1) + (b & 15);
			b = System.in.read();
		} while (b > 47 && b < 58);
		return n;
	}
}