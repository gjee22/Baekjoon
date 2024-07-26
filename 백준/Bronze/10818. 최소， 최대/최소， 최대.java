import java.util.*;
import java.io.*;


class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] numStrings = br.readLine().split(" ");
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int cur = Integer.parseInt(numStrings[i]);
			max = max < cur ? cur : max;
			min = min > cur ? cur : min;
		}
		System.out.println(min + " " + max);
	}
}