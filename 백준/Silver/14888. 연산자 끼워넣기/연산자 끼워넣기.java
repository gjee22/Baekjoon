import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] res = new int[2];
	
	public static void main(String[] args) throws IOException {
		int[] signs = new int[4];
		int N = Integer.parseInt(br.readLine());
	  int[] numbers = new int[N];
		StringTokenizer numbersLine = new StringTokenizer(br.readLine());
		StringTokenizer signsLine = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(numbersLine.nextToken());
		for (int i = 0; i < 4; i++) signs[i] = Integer.parseInt(signsLine.nextToken());
		res[0] = Integer.MIN_VALUE;
		res[1] = Integer.MAX_VALUE;
		
		calculate(1, numbers[0], signs, numbers);
		
		System.out.println(res[0]);
		System.out.println(res[1]);
	}
	
	private static void calculate(int count, int acc, int[] signs, int[] numbers) {
		if (count == numbers.length) {
			res[0] = Math.max(res[0], acc);
			res[1] = Math.min(res[1], acc);
			return;
		}
				
		if (signs[0] > 0) {
			signs[0]--;
			calculate(count + 1, acc + numbers[count], signs, numbers);
		  signs[0]++;
		}
		if (signs[1] > 0) {
			signs[1]--;
			calculate(count + 1, acc - numbers[count], signs, numbers);
		    signs[1]++;
		}
		if (signs[2] > 0) {
			signs[2]--;
			calculate(count + 1, acc * numbers[count], signs, numbers);
		    signs[2]++;
		}
		if (signs[3] > 0) {
			signs[3]--;
			calculate(count + 1, acc / numbers[count], signs, numbers);
		    signs[3]++;
		}
	}
}