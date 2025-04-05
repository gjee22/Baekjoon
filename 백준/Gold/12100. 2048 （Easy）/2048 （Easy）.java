import java.io.IOException;
import java.util.Arrays;

class Main {
	static final int LIMIT = 5, RIGHT = 0, LEFT = 1, UP = 2, DOWN = 3;
	static int N, max = 0;

	public static void main(String[] args) throws IOException {
		N = read();
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = read();
				max = Math.max(max, board[i][j]);
			}
		}
		search(board, 1, -1);
		System.out.println(max);
	}

	static void search(int[][] board, int numMove, int prev) {
		if (numMove > LIMIT) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int[][] newBoard = move(board, i);
			if (prev == i && isEqual(board, newBoard)) continue;
			search(newBoard, numMove + 1, i);
		}
	}

	static boolean isEqual(int[][] arr1, int[][] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			if (!Arrays.equals(arr1[i], arr2[i])) {
				return false;
			}
		}
		return true;
	}

	static int[][] move(int[][] board, int d) {
		if (d == RIGHT) {
			return combine(board, N - 1, -1, true);
		}
		else if (d == LEFT) {
			return combine(board, 0, N, true);
		}
		else if (d == DOWN) {
			return combine(board, N - 1, -1, false);
		}
		else if (d == UP) {
			return combine(board, 0, N, false);
		}
		return null;
	}

	static int[][] combine(int[][] board, int start, int end, boolean isHorizontal) {
		int[][] newBoard = new int[N][N];
		int inc = end == -1 ? -1 : 1;

		for (int i = 0; i < N; i++) {
			int s = start;
			int p = start;
			int block = -1;
			while (s != end) {
				if (isHorizontal) {
					if (board[i][s] == 0) {
						s += inc;
						continue;
					}
					if (block == -1 || newBoard[i][block] != board[i][s]) {
						newBoard[i][p] = board[i][s];
						block = p;
						p += inc;
					} else {
						newBoard[i][block] += board[i][s];
						max = Math.max(max, newBoard[i][block]);
						block = -1;
					}
				} else {
					if (board[s][i] == 0) {
						s += inc;
						continue;
					}
					if (block == -1 || newBoard[block][i] != board[s][i]) {
						newBoard[p][i] = board[s][i];
						block = p;
						p += inc;
					} else {
						newBoard[block][i] += board[s][i];
						max = Math.max(max, newBoard[block][i]);
						block = -1;
					}
				}
				s += inc;
			}
		}
		return newBoard;
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