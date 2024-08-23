import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		int numCases = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int count = 0;
		for (int i = 0; i < numCases; i++) {
			int cur = Integer.parseInt(st.nextToken());
			boolean isPrime = true;
			for (int j = 2; j <= cur / 2; j++) {
				if (cur % j == 0) {
				 isPrime = false;
				 break;
				}
			}
			if (isPrime && cur != 1) count++;
		}
		System.out.println(count);
	}
}