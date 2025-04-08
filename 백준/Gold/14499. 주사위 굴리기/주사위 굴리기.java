import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
	static final int[] dr = { 0, 0, 0, -1, 1 };
	static final int[] dc = { 0, 1, -1, 0, 0 };
	static final int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4;

	static int N, M, K, r, c;
	static int[][] board;
	static int[] dice;

	public static void main(String[] args) throws IOException {
		N = read();
		M = read();
		board = new int[N][M];
		dice = new int[6];
		r = read();
		c = read();
		K = read();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = read();
			}
		}

		StringBuilder res = new StringBuilder();
		for (int i = 0; i < K; i++) {
			int d = read();
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) {
				continue;
			}
			r = nr;
			c = nc;
			res.append(move(d)).append("\n");
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(res.toString());
		bw.close();
	}

	static int move(int direction) {
		int tmp = dice[2];
		if (direction == EAST) {
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[3];
			dice[3] = tmp;
		}
		else if (direction == WEST) {
			dice[2] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[1];
			dice[1] = tmp;
		}
		else if (direction == NORTH) {
			dice[2] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[0];
			dice[0] = tmp;
		}
		else if (direction == SOUTH) {
			dice[2] = dice[0];
			dice[0] = dice[5];
			dice[5] = dice[4];
			dice[4] = tmp;
		}
		if (board[r][c] == 0) {
			board[r][c] = dice[5];
		} else {
			dice[5] = board[r][c];
			board[r][c] = 0;
		}
		return dice[2];
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