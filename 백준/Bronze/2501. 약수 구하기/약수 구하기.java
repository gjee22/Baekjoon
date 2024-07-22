import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]), k = Integer.parseInt(input[1]);
		
		int res = findNthFactor(n, k);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(res));
		bw.flush();
		bw.close();
	}
	
	static int findNthFactor(int n, int k) {
		int count = 0;
		for (int i = 1; i <= n/2 + 1; i++) {
			if (n % i == 0) {
				count++;
				if (count == k) return i;
			}
		}
		if (count == k - 1) return n;
		return 0;
	}
}