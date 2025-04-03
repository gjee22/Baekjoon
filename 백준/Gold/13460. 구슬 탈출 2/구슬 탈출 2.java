import java.io.IOException;

class Main {

	static class Position {
		int r;
		int c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public boolean equals(Object o) {
			Position p = (Position) o;
			return this.r == p.r && this.c == p.c;
		}
	}

	static int N, M, min;
	static char[][] map;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };
	static Position dest;

	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		min = Integer.MAX_VALUE;
		map = new char[N][M];
		Position red = null;
		Position blue = null;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = readChar();
				if (map[i][j] == 'R') {
					red = new Position(i, j);
				}
				if (map[i][j] == 'B') {
					blue = new Position(i, j);
				}
				if (map[i][j] == 'O') {
					dest = new Position(i, j);
				}
			}
		}
		search(red, blue, -1, 1);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void search(Position red, Position blue, int prev, int move) {
		if (move > 10) {
			return;
		}

		int from = (prev / 2) * 2 + ((prev + 1) % 2);
		for (int i = 0; i < dr.length; i++) {
			if (prev != -1 && (prev == i || from == i)) continue;
			Position[] next = move(dr[i], dc[i], red, blue);

			if (next[1].equals(dest)) {
				if (next[0].equals(dest)) {
					map[red.r][red.c] = 'R';
				} else {
					map[next[0].r][next[0].c] = '.';
					map[red.r][red.c] = 'R';
				}
				map[blue.r][blue.c] = 'B';
				continue;
			}
			if (next[0].equals(dest)) {
				map[next[1].r][next[1].c] = '.';
				map[blue.r][blue.c] = 'B';
				map[red.r][red.c] = 'R';
				min = Math.min(min, move);
				continue;
			}
			if (next[0].equals(red) && next[1].equals(blue)) {
				continue;
			}

			search(next[0], next[1], i, move + 1);

			map[next[0].r][next[0].c] = '.';
			map[red.r][red.c] = 'R';
			map[next[1].r][next[1].c] = '.';
			map[blue.r][blue.c] = 'B';
		}
	}

	static Position[] move(int dr, int dc, Position red, Position blue) {
		Position nr;
		Position nb;

		if (checkBeadPosition(dr, dc, red, blue)) {
			nr = moveBead(dr, dc, red, 'R');
			nb = moveBead(dr, dc, blue, 'B');
		}
		else {
			nb = moveBead(dr, dc, blue, 'B');
			nr = moveBead(dr, dc, red, 'R');
		}

		return new Position[] { nr, nb };
	}

	static Position moveBead(int dr, int dc, Position p, char bead) {
		int i = 1;
		int nr = p.r + dr * i;
		int nc = p.c + dc * i;
		while (map[nr][nc] != '#' && map[nr][nc] != 'R' && map[nr][nc] != 'B') {
			if (map[nr][nc] == 'O') {
				map[p.r][p.c] = '.';
				return new Position(nr, nc);
			}
			i++;
			nr = p.r + dr * i;
			nc = p.c + dc * i;
		}
		Position moved = new Position(nr - dr, nc - dc);
		if (!moved.equals(p)) {
			map[moved.r][moved.c] = bead;
			map[p.r][p.c] = '.';
		}
		return moved;
	}

	static boolean checkBeadPosition(int dr, int dc, Position red, Position blue) {
		if (dc == 1 && red.r == blue.r && red.c > blue.c) return true;
		if (dc == -1 && red.r == blue.r && red.c < blue.c) return true;
		if (dr == 1 && red.c == blue.c && red.r > blue.r) return true;
		if (dr == -1 && red.c == blue.c && red.r < blue.r) return true;
		return false;
	}

	static int readInt() throws IOException {
		int b;
		do b = System.in.read(); while (b <= 32);
		int n = 0;
		do {
			n = (n << 3) + (n << 1) + (b & 15);
			b = System.in.read();
		} while (b > 47 && b < 58);
		return n;
	}

	static char readChar() throws IOException {
		int b;
		do b = System.in.read(); while (b <= 32);
		return (char) b;
	}
}