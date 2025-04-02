import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int sum = 0, count = 1;
		for (int i = 1; i <= B; i++) {
			for (int j = 0; j < i; j++) {
				if (count >= A) sum += i;
				if (count >= B) {
					System.out.println(sum);
					return;
				}
				count++;
			}
		}
 	}
}