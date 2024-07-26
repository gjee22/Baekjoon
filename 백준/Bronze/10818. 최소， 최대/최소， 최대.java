import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		Reader br = new Reader();
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int n = br.nextInt();
		for (int i = 0; i < n; i++) {
			int cur = br.nextInt();
			max = Math.max(cur, max);
			min = Math.min(cur, min);
		}
		System.out.println(min + " " + max);
	}
}

class Reader {
	final int SIZE = 1 << 13;
	byte[] buf = new byte[SIZE];
	int index, size;
	
	int nextInt() throws Exception {
		boolean isMinus = false;
		byte b;
		int n = 0;
		while ((b = read()) <= 32) { if (size == 0) return -1; }
		if (b == 45) { b = read(); isMinus = true; }
		do n = (n << 3) + (n << 1) + (b & 15);
		while (isNumber(b = read()));
		
		return isMinus ? ~n + 1 : n;
	}
	
	boolean isNumber(byte b) {
		return 47 < b && b < 58;
	}
	
	byte read() throws Exception {
		if (index == size) {
			size = System.in.read(buf, index = 0, SIZE);
			if (size < 0) buf[0] = -1;
		}
		
		return buf[index++];
	}
}