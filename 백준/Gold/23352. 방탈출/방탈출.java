import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

class Main {
	static int N, M, V;
	static int[][] map, visited;
	static final int[] dr = { 0, 0, 1, -1 };
	static final int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		N = read(); M = read();
		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = read();
			}
		}

		V = 1;
		int max = 0;
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) continue;
				int[] res = bfs(new int[] { i, j, 1 });
				if (count == res[1]) {
					max = Math.max(max, res[0]);
				}
				if (count < res[1]) {
					max = res[0];
					count = res[1];
				}
				V++;
			}
		}

		System.out.println(max);
	}

	private static int[] bfs(int[] start) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(start);
		int max = map[start[0]][start[1]];
		visited[start[0]][start[1]] = V;
		int count = 0;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < dr.length; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (isValid(nr, nc) && visited[nr][nc] != V && map[nr][nc] != 0) {
					visited[nr][nc] = V;
					queue.offer(new int[] { nr, nc, cur[2] + 1 });
					int pass = map[start[0]][start[1]] + map[nr][nc];
					if (count == cur[2] && max < pass) {
						max = pass;
					}
					else if (count < cur[2]) {
						count = cur[2];
						max = pass;
					}
				}
			}
		}

		return new int[] { max, count };
	}

	private static boolean isValid(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

	private static int read() throws IOException {
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