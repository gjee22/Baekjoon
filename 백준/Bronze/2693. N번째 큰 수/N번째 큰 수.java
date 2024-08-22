import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	private static int N = 10;
	private static int K = 7;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numCases = Integer.parseInt(st.nextToken()); 
		for (int i = 0; i < numCases; i++) {
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			findThirdLargest(arr);
		}
	}
	
	private static void findThirdLargest(int[] arr) {
		int start = 0;
		int end = N;
		int pivotLoc = start;

		while (pivotLoc != K) {
		  pivotLoc = start;
			int pivot = arr[pivotLoc];
			int i = start;
			int j = end - 1;
			
			while (i < j) {
			  while (i < j && arr[j] > pivot) j--;
				while (i < j && arr[i] <= pivot) i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
			
			int temp = arr[i];
			arr[i] = arr[pivotLoc];
			arr[pivotLoc] = temp;
			pivotLoc = i;
			
			if (pivotLoc < K) {
				start = pivotLoc + 1;
				end = end;
			} else {
				start = start;
				end = pivotLoc;
			}
		}
		System.out.println(arr[pivotLoc]);
	}
}