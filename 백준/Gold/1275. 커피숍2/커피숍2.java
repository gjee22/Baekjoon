import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
	static int SIZE = 1;
	static long[] st;
	
	public static void main(String[] args) throws IOException {
		int N = read(), Q = read();
		while ((SIZE <<= 1) < N);
		st = new long[SIZE << 1];
		for (int i = 0; i < N; i++) {
			st[SIZE + i] = read();
		}
		for (int i = SIZE + N - 1; i > 1; i--) {
			st[i / 2] += st[i];
		}
		
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int s = read();
			int e = read();
			if (s > e) {
				int temp = s;
				s = e;
				e = temp;
			}
			res.append(query(s, e)).append("\n");
			update(read(), read());
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(res.toString());
		bw.flush();
	}
	
	static long query(int s, int e) {
		s += SIZE - 1;
		e += SIZE - 1;
		long sum = 0;
		while (s <= e) {
			if (s % 2 == 1) {
				sum += st[s];
			}
			if (e % 2 == 0) {
				sum += st[e];
			}
			s = (s + 1) / 2;
			e = (e - 1) / 2;
		}
		return sum;
	}
	
	static void update(int i, int n) {
		i += SIZE - 1;
		st[i] = n;
		while (i > 1) {
			i /= 2;
			st[i] = st[2*i] + st[2*i + 1];
		}
	}
	
	static int read() throws IOException {
		int b;
		do b = System.in.read(); while (b <= 32);
		int n = 0; boolean isNeg = false;
		if (b == 45) {
			isNeg = true;
			b = System.in.read();
		}
		do {
			n = (n << 3) + (n << 1) + (b & 15);
			b = System.in.read();
		} while (b > 47 && b < 58);
		return isNeg ? -n : n;
	}
}