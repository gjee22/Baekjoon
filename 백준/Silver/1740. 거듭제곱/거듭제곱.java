import java.io.IOException;

class Main {
	public static void main(String[] args) throws IOException {
		long N = read();
		long res = 0;
		long three = 1;
		while (N > 0) {
			if ((N & 1) == 1) {
				res += three;
			}
			three *= 3;
			N >>= 1;
		}
		System.out.println(res);
	}

	private static long read() throws IOException {
		int b;
		do b = System.in.read(); while (b <= 32);
		long n = 0;
		do {
			n = (n << 3) + (n << 1) + (b & 15);
			b = System.in.read();
		} while (b > 47 && b < 58);
		return n;
	}
}