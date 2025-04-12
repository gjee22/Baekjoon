import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Main {
	static int N, M, max = -1;
	static int[][] lab;
	static final int[] dx = { 0, 0, 1, -1 };
	static final int[] dy = { 1, -1, 0, 0 };
	static LinkedList<int[]> viruses;

	public static void main(String[] args) throws IOException {
		N = read(); M = read();
		lab = new int[N][M];
		viruses = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				lab[i][j] = read();
				if (lab[i][j] == 2) {
					viruses.add(new int[] { i, j });
				}
			}
		}
		makeWalls(0, 0);
		System.out.println(max);
	}

	static void makeWalls(int start, int numWalls) {
		if (numWalls == 3) {
			int[][] res = spreadVirus();
			max = Math.max(max, countSafetyZone(res));
			return;
		}

		for (int idx = start; idx < N * M; idx++) {
			int i = idx / M;
			int j = idx % M;
			if (lab[i][j] == 0) {
				lab[i][j] = 1;
				makeWalls(idx + 1, numWalls + 1);
				lab[i][j] = 0;
			}
		}
	}

	static int[][] spreadVirus() {
		int[][] labCopy = makeLabCopy();
		Queue<int[]> queue = new LinkedList<>();
		for (int[] virus : viruses) {
			queue.offer(virus);
		}
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (isValidIndex(nx, ny) && labCopy[nx][ny] == 0) {
					labCopy[nx][ny] = 2;
					queue.offer(new int[]{nx, ny});
				}
			}
		}
		return labCopy;
	}


	static boolean isValidIndex(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	static int countSafetyZone(int[][] lab) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				count += lab[i][j] == 0 ? 1 : 0;
			}
		}
		return count;
	}

	static int[][] makeLabCopy() {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(lab[i], M);
		}
		return copy;
	}

	static int read() throws IOException {
		int b;
		do {
			b = System.in.read();
		} while (b <= 32);
		return b & 15;
	}
}