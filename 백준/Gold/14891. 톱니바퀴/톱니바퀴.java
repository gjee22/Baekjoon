import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static final int N = 4, W = 8;
	static String[] wheels;
	static int[] left, right;

	static class Turn {
		int w;
		int d;
		boolean doLeft;
		boolean doRight;

		public Turn(int w, int d, boolean doLeft, boolean doRight) {
			this.w = w;
			this.d = d;
			this.doLeft = doLeft;
			this.doRight = doRight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wheels = new String[N];
		left = new int[] { 6, 6, 6, 6 };
		right = new int[] { 2, 2, 2, 2 };
		for (int i = 0; i < N; i++) {
			wheels[i] = br.readLine();
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			turn(new Turn(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), true, true));
		}
		int score = wheels[0].charAt((right[0] - 2 + W) % W) == '0' ? 0 : 1;
		score += wheels[1].charAt((right[1] - 2 + W) % W) == '0' ? 0 : 2;
		score += wheels[2].charAt((right[2] - 2 + W) % W) == '0' ? 0 : 4;
		score += wheels[3].charAt((right[3] - 2 + W) % W) == '0' ? 0 : 8;
		System.out.println(score);
	}

	static void turn(Turn t) {
		Queue<Turn> queue = new LinkedList<>();
		queue.offer(t);
		while (!queue.isEmpty()) {
			Turn cur = queue.poll();
			int l = cur.w - 1;
			if (cur.doLeft && l >= 0 && wheels[cur.w].charAt(left[cur.w]) != wheels[l].charAt(right[l])) {
				queue.offer(new Turn(l, -1 * cur.d, true, false));
			}
			left[cur.w] = (left[cur.w] - cur.d + W) % W;

			int r = cur.w + 1;
			if (cur.doRight && r < N && wheels[cur.w].charAt(right[cur.w]) != wheels[r].charAt(left[r])) {
				queue.offer(new Turn(r, -1 * cur.d, false, true));
			}
			right[cur.w] = (right[cur.w] - cur.d + W) % W;
		}
	}
}