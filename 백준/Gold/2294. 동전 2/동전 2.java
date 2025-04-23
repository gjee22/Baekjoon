import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Main {
	static int N, K;
	static int[] coins;
	static boolean[] checked;

	public static void main(String[] args) throws IOException {
		N = read();
		K = read();
		coins = new int[N];
		checked = new boolean[K];
		for (int i = 0 ; i < N; i++) {
			coins[i] = read();
		}
		Arrays.sort(coins);
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		for (int i = N - 1; i >= 0; i--) {
			if (coins[i] > K) continue;
			if (coins[i] == K) return 1;
			queue.offer(new int[] { coins[i], 1 });
		}

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int total = cur[0];
			for (int i = N - 1; i >= 0; i--) {
				int newTotal = total + coins[i];
				if (newTotal == K) {
					return cur[1] + 1;
				}
				if ( newTotal < K && !checked[newTotal]) {
					checked[newTotal] = true;
					queue.offer(new int[] { newTotal, cur[1] + 1 });
				}
			}
		}

		return -1;
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