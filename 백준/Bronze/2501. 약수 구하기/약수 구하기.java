import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]), k = Integer.parseInt(input[1]);
		
		int count = 0;
		for (int i = 1; i <= n/2 + 1; i++) {
		  count = (n % i == 0) ? count + 1 : count;
		  if (count == k) {
				 System.out.println(i);
				 return;
			}
		}
		if (count == k - 1) System.out.println(n);
		else System.out.println(0); 
	}
}