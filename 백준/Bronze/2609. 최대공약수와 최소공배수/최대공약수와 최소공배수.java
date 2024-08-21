import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());
		
		findGCFandLCM(1, n1, n2);
	}
	
	private static void findGCFandLCM(int acc, int n1, int n2) {
		int limit = Math.min(n1, n2);
		int cur = -1;
		for (int i = 2; i <= limit; i++) {
			if (n1 % i == 0 && n2 % i == 0) {
				cur = i;
				break;
			}
		}
		if (cur == -1) {
			System.out.println(acc);
			System.out.println(acc * n1 * n2);
			return;
		}
		findGCFandLCM(acc * cur, n1 / cur, n2 /cur);
	}
}