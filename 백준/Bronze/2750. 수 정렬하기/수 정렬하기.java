import java.io.IOException;

class Main {
	static int[] arr;
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = read();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = read();
		}
		
		int[] gaps = { 1, 4, 10, 23, 57, 132, 301, 701, 1750, 3937, 
		8858, 19930, 44842, 100894, 227011, 510774,
		1149241, 2585792, 5818032, 13090572, 29453787, 
		66271020, 149109795, 335497038, 754868335, 1698453753};
		int gapIndex = gaps.length - 1;
		while (gaps[gapIndex] > N) gapIndex--;
		
		while (gapIndex >= 0) { 
			for (int i = 0; i < gaps[gapIndex]; i++) {
				shellSort(i, gaps[gapIndex]); 
			}
			gapIndex--;
		}
		
		for (int n = 0; n < N; n++) { 
			System.out.println(arr[n]);
		}
	}
	
	static void shellSort(int start, int gap) {
		for (int i = start + gap; i < N; i += gap) {
			int j = i; 
			int target = arr[j];
			while (j > start && target < arr[j - gap]) {
				arr[j] = arr[j - gap]; 
				j -= gap;
			}
			arr[j] = target;
		}		
	}
	
	static int read() throws IOException {
		int b; 
		boolean isNeg = false; 
		int n = 0;
		while ((b = System.in.read()) <= 32);
		if (b == 45) {
			isNeg = true;
			b = System.in.read();
		}
		do {
			n = (n << 3) + (n << 1) + (b & 15);
			b = System.in.read();
		} while (b > 47 && b < 58);
		
		return isNeg ? -n : n;
	}
}