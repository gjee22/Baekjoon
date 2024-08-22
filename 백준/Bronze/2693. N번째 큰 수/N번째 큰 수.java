import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.lang.StringBuilder;

class Main {
	private static int K = 7;
	private static int N = 10;
	private static int[] arr = new int[N];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder res = new StringBuilder();
		int numCases = Integer.parseInt(st.nextToken()); 
		for (int i = 0; i < numCases; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			res.append(arr[K]).append("\n");
		}
		System.out.print(res.toString());
	}
}