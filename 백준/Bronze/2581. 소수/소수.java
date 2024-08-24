import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuilder;

class Main {
	private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		int m = Integer.parseInt(bf.readLine());
		int n = Integer.parseInt(bf.readLine());
		boolean[] notPrime = new boolean[n + 1];
		notPrime[0] = true;
		notPrime[1] = true;
		int sum = 0, min = -1;
		for (int i = m; i <= n; i++) {
			if (notPrime[i]) continue;
			boolean isPrime = true;
			for (int j = 2; j * j <= i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				if (min == -1) min = i;
				sum += i;
				for (int j = 2; j * i <= n; j++) {
					notPrime[j * i] = true;
				}
			}
		}
		
		if (min == -1) { 
			System.out.println(-1);
			return;
		}
		System.out.println(sum);
		System.out.println(min);
	}
}