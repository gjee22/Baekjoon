import java.io.IOException;

class Main {
	public static void main(String[] args) throws IOException {
		int N = read();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = read();
		}
		
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++) {
				if (arr[j] < arr[min]) { 
					min = j;
				}
			}
			int tmp = arr[i]; 
			arr[i] = arr[min]; 
			arr[min] = tmp;
		}		
		for (int n = 0; n < N; n++) { 
			System.out.println(arr[n]);
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