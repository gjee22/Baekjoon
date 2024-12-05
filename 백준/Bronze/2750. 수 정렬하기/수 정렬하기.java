import java.io.IOException;

class Main {
	public static void main(String[] args) throws IOException {
		int N = read();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = read();
		}
		
		boolean isSwap = false;
		for (int i = 0; i < N; i++) {
			isSwap = false;
			for (int j = 0; j < N - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j]; 
					arr[j] = arr[j + 1]; 
					arr[j + 1] = tmp;
					isSwap = true;
				}
			}
			if (!isSwap) { 				
				break;
			}
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