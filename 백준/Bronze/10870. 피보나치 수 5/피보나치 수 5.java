import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Integer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		fibonacci(0, 1, n);
	}
	
	private static void fibonacci(int x, int y, int n) {
		if (n == 0) {
			System.out.println(x);
			return;
		}
		fibonacci(y, x + y, n - 1);
	}
}