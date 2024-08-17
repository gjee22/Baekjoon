import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

class Main {
	static int N = 9;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer[] numbers = new Integer[N];
		int remainder = 0;
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
			remainder += numbers[i];
		}
		Arrays.sort(numbers);
		remainder -= 100;
	
		int fake1 = -1;
		int fake2 = -1;
		int curSum = -1;
		
		for (int i = 0; i < N - 1; i++) {
			if (numbers[i] > remainder) continue;
			for (int j = i + 1; j < N; j++) {
				curSum = numbers[i] + numbers[j];
				if (curSum > remainder) break;
				if (curSum == remainder) {
					fake1 = i;
					fake2 = j;
					break;
				}
			}
			if (curSum == remainder) break;
		}
		
		for (int x = 0; x < N; x++) {
			if (x == fake1 || x == fake2) continue;
			System.out.println(numbers[x]);
		}
	}
}