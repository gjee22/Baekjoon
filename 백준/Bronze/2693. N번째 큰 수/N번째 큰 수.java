import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
	private static int N = 10;
	private static int K = 7;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numCases = Integer.parseInt(st.nextToken()); 
		for (int i = 0; i < numCases; i++) {
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			System.out.println(arr[K]);
		}
	}
}