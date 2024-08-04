import java.util.*;
import java.io.*;

class Main {
	private static String SPACE = " ";
	private static int STATION = 10;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = 0, curCount = 0;
		for (int i = 0; i < STATION; i++) {
			StringTokenizer curString = new StringTokenizer(br.readLine(), SPACE);
			curCount += (-Integer.parseInt(curString.nextToken()) + Integer.parseInt(curString.nextToken()));
			max = Math.max(max, curCount); 
		}
		System.out.println(max);
	}
}