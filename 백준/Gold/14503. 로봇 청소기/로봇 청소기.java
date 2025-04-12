import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N, M;
	static int[][] room;
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		room = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(clean(r, c, d, 0));
	}

	static int clean(int r, int c, int d, int count) {
		// 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
		if (room[r][c] == 0) {
			count++;
			room[r][c] = 2;
		}

		for (int i = 1; i <= 4; i++) {
			int nd = ((d - i) + 4) % 4;
			int nr = r + dr[nd];
			int nc = c + dc[nd];
			// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			if (room[nr][nc] == 0) {
				return clean(nr, nc, nd, count);
			}
		}

		// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
		int nd = ((d - 2) + 4) % 4;
		int nr = r + dr[nd];
		int nc = c + dc[nd];
		// 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
		if (room[nr][nc] != 1) {
			return clean(nr, nc, d, count);
		}
		// 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
		return count;
	}
}