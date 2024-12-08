import java.io.IOException;

class Main {
	static int[] sorted;
	
	public static void main(String[] args) throws IOException {
		int N = read();
		int[] arr = new int[N];
		sorted = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = read();
		}
		
		mergeSort(arr, 0, N);
			
		for (int n = 0; n < N; n++) { 
			System.out.println(arr[n]);
		}
	}
	
	static void mergeSort(int[] arr, int start, int end) {
		if (end - start <= 1) return;
		int mid = start + ((end - start) / 2);
		mergeSort(arr, start, mid);
		mergeSort(arr, mid, end);
		merge(arr, start, mid, end);
	}
	
	static void merge(int[] arr, int start, int mid, int end) {
		int left = start;
		int right = mid;
		int i = start;
		while (left < mid && right < end) {
			if (arr[left] < arr[right]) {
				sorted[i++] = arr[left++];
			}
			else {
				sorted[i++] = arr[right++];
			}
		}
		while (left < mid) {
			sorted[i++] = arr[left++];
		}
		while (right < end) {
			sorted[i++] = arr[right++];
		}
		
		// 꼭 배열 복사를 해줘야 됨
		for (int j = start; j < end; j++) {
			arr[j] = sorted[j];
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
		
		return isNeg ? ~n + 1 : n;
	}
}