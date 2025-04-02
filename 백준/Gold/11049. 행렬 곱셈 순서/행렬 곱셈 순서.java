import java.io.IOException;

class Main {
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		int N = read();
		int[] data = new int[N + 2];
		for (int i = 1; i <= N; i++) {
			data[i] = read();
			data[i + 1] = read();
		}

		int[][] matmul = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N - i; j++) {
				matmul[j][i + j] = INF;
				for (int k = j; k <= i + j - 1; k++) {
					int v = matmul[j][k] + matmul[k + 1][i + j] + (data[j] * data[k + 1] * data[i + j + 1]);
					matmul[j][i + j] = Math.min(matmul[j][i + j], v);
				}
			}
		}
		System.out.println(matmul[1][N]);
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