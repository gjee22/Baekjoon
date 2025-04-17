import java.io.IOException;

class Main {
	static final int[] dr = { 0, 0, 1, -1 };
	static final int[] dc = { 1, -1, 0, 0 };

	static int N, M, max = -1;
	static int[][] board;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		N = read(); M = read();
		board = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = read();
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				search(1, 0, r, c);
			}
		}
		System.out.println(max);
	}

	static void search(int n, int acc, int r, int c) {
		if (n > 4) {
			max = Math.max(max, acc);
			return;
		}

		visited[r][c] = true;
		acc += board[r][c];
//		System.out.println("Current cell: " + cur.r + " " + cur.c + " with " + acc);
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (isInvalid(nr, nc) || visited[nr][nc]) continue;
			search(n + 1, acc, nr, nc);
		}
		if (n == 2) {
			int leftR = r + dr[0], leftC = c + dc[0];
			int rightR = r + dr[1], rightC = c + dc[1];
			int downR = r + dr[2], downC = c + dc[2];
			int upR = r + dr[3], upC = c + dc[3];
			if ((!isInvalid(leftR, leftC) && visited[leftR][leftC])
					|| (!isInvalid(rightR, rightC) && visited[rightR][rightC])) {
				if (!isInvalid(downR, downC) && !isInvalid(upR, upC))
					max = Math.max(max, acc + board[downR][downC] + board[upR][upC]);
			}
			else if ((!isInvalid(upR, upC) && visited[upR][upC])
					|| (!isInvalid(downR, downC) && visited[downR][downC])) {
				if (!isInvalid(leftR, leftC) && !isInvalid(rightR, rightC))
					max = Math.max(max, acc + board[leftR][leftC] + board[rightR][rightC]);
			}
		}
		visited[r][c] = false;
	}

	static boolean isInvalid(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}

	static int read() throws IOException {
		int b;
		do { b = System.in.read(); } while (b <= 32);
		int n = 0;
		do {
			n = (n << 3) + (n << 1) + (b & 15);
			b = System.in.read();
		} while (b > 47 && b < 58);
		return n;
	}
}