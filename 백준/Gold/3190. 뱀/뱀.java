import java.io.IOException;
import java.util.LinkedList;

class Main {
	static class Position {
		int r;
		int c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static final int SNAKE = 1, APPLE = 2, RIGHT = 1, LEFT = -1;
	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	static int N, K;
	static int[][] board;
	static LinkedList<Position> snake;

	public static void main(String[] args) throws IOException {
		N = readInt();
		K = readInt();
		board = new int[N + 1][N + 1];
		snake = new LinkedList<>();
		snake.add(new Position(1, 1));
		board[1][1] = SNAKE;
		for (int i = 0; i < K; i++) {
			board[readInt()][readInt()] = APPLE;
		}

		int L = readInt(), toChange = readInt(), count = 1, time = 0, direction = 0;
		while (true) {
			time++;

			/* 움직이기
				1. 자기 자신이나 벽에 도달 -> 게임 오버
				2. 사과를 먹음 -> 사이즈 증가
				3. 사과를 먹지 않음 -> 사이즈 증가 X
			*/
			Position cur = snake.getFirst();
			Position next = new Position(cur.r + dr[direction], cur.c + dc[direction]);
			if (isFinish(next)) {
				break;
			}
			snake.addFirst(next);
			if (board[next.r][next.c] != APPLE) {
				Position tail = snake.removeLast();
				board[tail.r][tail.c] = 0;
			}
			board[next.r][next.c] = 1;

			// printBoard();

			// 방향 전환
			if (toChange == time) {
				direction = (direction + (readChar() == 'D' ? RIGHT : LEFT) + 4) % 4;
				if (count++ < L) {
					toChange = readInt();
				}
			}
		}
		System.out.println(time);
	}

	static void printBoard() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static boolean isFinish(Position p) {
		return p.r <= 0 || p.r > N || p.c <= 0 || p.c > N || board[p.r][p.c] == SNAKE;
	}

	static int readInt() throws IOException {
		int b;
		do { b = System.in.read();	} while (b <= 32);
		int n = 0;
		do {
			n = (n << 3) + (n << 1) + (b & 15);
			b = System.in.read();
		} while (b > 47 && b < 58);
		return n;
	}

	static char readChar() throws IOException {
		int b;
		do { b = System.in.read();	} while (b <= 32);
		return (char) b;
	}
}
