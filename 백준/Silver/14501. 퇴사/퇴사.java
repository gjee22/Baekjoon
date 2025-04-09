import java.io.IOException;

class Main {
	static class Appointment {
		int start, end, price;

		public Appointment(int start, int end, int price) {
			this.start = start;
			this.end = end;
			this.price = price;
		}
	}

	public static void main(String[] args) throws IOException {
		int N = read();
		int[][] mem = new int[N + 1][N + 1];
		Appointment[] appointments = new Appointment[N + 1];
		for (int i = 1; i <= N; i++) {
			int e = i + read() - 1;
			appointments[i] = new Appointment(i, e, read());
			if (e <= N) {
				mem[e][i] = appointments[i].price;
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Appointment a = appointments[j];
				if (a.end > i) {
					mem[i][j] = mem[i][j - 1];
				} else {
					mem[i][j] = Math.max(mem[i][j - 1], mem[a.start - 1][j] + a.price);
				}
			}
		}

		System.out.println(mem[N][N]);
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