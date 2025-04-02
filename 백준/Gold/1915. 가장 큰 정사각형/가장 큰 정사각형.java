import java.io.IOException;

class Main {
	public static void main(String[] args) throws IOException {
		int N = readInt(), M = readInt();
		// 0 = 정사각형 크기, 1 = 가로, 2 = 세로
		// 1과 2에는 현재 칸까지 지정된 방향으로 연속된 1의 개수가 저장된다.
		int[][] nums = new int[N + 1][M + 1];
		int max = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (readOne() == 1) {
					nums[i][j] = Math.min(nums[i - 1][j - 1], Math.min(nums[i - 1][j], nums[i][j - 1])) + 1;
					max = Math.max(max, nums[i][j]);
				}
			}
		}

		System.out.println(max * max);
	}

	static int readInt() throws IOException {
		int b;
		do b = System.in.read(); while (b <= 32);
		int n = 0;
		do {
			n = (n << 3) + (n << 1) + (b & 15);
			b = System.in.read();
		} while (b > 47 && b < 58);
		return n;
	}

	static int readOne() throws IOException {
		int b;
		do b = System.in.read(); while (b <= 32);
		return b & 15;
	}
}