import java.io.IOException;

class Main {
	public static void main(String[] args) throws IOException {
		int N = read();
		int[] rooms = new int[N];
		for (int i = 0; i < N; i++) {
			rooms[i] = read();
		}
		long count = N, a = read();
		float b = read();
		for (int i = 0; i < N; i++) {
			rooms[i] -= a;
			if (rooms[i] > 0) {
				count += Math.ceil(rooms[i] / b);
			}
		}
		System.out.println(count);
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