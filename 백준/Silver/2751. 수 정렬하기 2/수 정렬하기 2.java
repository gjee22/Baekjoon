import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

class Main {	
	static int[] arr;
	public static void main(String[] args) throws IOException {
		int N = read();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = read();
		}
		
		partition(0, N - 1);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			bw.write(arr[i] + "\n");
    }
    bw.flush();
    bw.close();
	}
	
	static void partition(int left, int right) {
		if (left >= right) return;
		int low = left;
		int high = right;
		int p = getRandomPivot(left, right); 	
		while (low <= high) {
			while (low <= high && arr[low] < p) low++;
			while (low <= high && arr[high] > p) high--;
			if (low <= high) {
				swap(low++, high--);
			}
		}
		partition(left, high);
		partition(low, right);
	}
	
	static int getRandomPivot(int start, int end) {
		return arr[(int) (Math.random() * (end - start + 1) + start)];
	}
	
	static int getMedianPivot(int left, int mid, int right) {
		if (arr[left] > arr[mid]) swap(left, mid);
		if (arr[mid] > arr[right]) swap(mid, right);
		if (arr[left] > arr[mid]) swap(left, mid);
		return arr[mid];
	}
	
	static void swap(int a, int b) {
		int temp = arr[a]; 
		arr[a] = arr[b]; 
		arr[b] = temp;
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