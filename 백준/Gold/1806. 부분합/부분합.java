import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		int total = 0;
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			if (nums[i] >= S) {
				System.out.println(1);
				return;
			}
			total += nums[i];
		}
		if (total < S) {
			System.out.println(0);
			return;
		} else if (total == S) {
			System.out.println(N);
			return;
		}
		
		int min = N;
		int start = 0;
		int end = 0;
		int acc = 0;
		while (start < N && end < N + 1) {
			if (acc >= S && min > end - start) min = end - start;
			if (acc < S && end < N) acc += nums[end++];
			else acc -= nums[start++];
		}
		
		System.out.println(min);
	}
}