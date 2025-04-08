import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

class Main {
	static final int[] dr = { 0, 0, 1, -1 };
	static final int[] dc = { 1, -1, 0, 0 };

	static int N, M, max = -1;
	static int[][] board;

	static class Cell {
		int r, c;

		public Cell(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public boolean equals(Object o) {
			Cell c = (Cell) o;
			return this.r == c.r && this.c == c.c;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.r, this.c);
		}
	}

	public static void main(String[] args) throws IOException {
		N = read(); M = read();
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = read();
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				Cell cur = new Cell(r, c);
				dfs(1, 0, cur, new HashSet<>());
				bfs(cur);
			}
		}
		System.out.println(max);
	}

	static void dfs(int n, int acc, Cell cur, HashSet<Cell> visited) {
		if (n > 4) {
			max = Math.max(max, acc);
			return;
		}

		visited.add(cur);
//		System.out.println("Current cell: " + cur.r + " " + cur.c + " with " + acc);
		for (int i = 0; i < dr.length; i++) {
			Cell next = new Cell(cur.r + dr[i], cur.c + dc[i]);
			if (isInvalid(next) || visited.contains(next)) continue;
			dfs(n + 1, acc + board[cur.r][cur.c], next, visited);
		}
		visited.remove(cur);
	}

	static void bfs(Cell cur) {
		int sum = board[cur.r][cur.c];
		int count = 1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < dr.length; i++) {
			Cell next = new Cell(cur.r + dr[i], cur.c + dc[i]);
			if (isInvalid(next)) continue;
			count++;
			int toAdd = board[next.r][next.c];
			sum += toAdd;
			min = Math.min(min, toAdd);
		}
		if (count > 4) {
			sum -= min;
			count--;
		}
		if (count == 4) {
			max = Math.max(max, sum);
		}
	}

	static boolean isInvalid(Cell c) {
		return c.r < 0 || c.r >= N || c.c < 0 || c.c >= M;
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