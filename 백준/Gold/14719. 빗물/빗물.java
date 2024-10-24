import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[w];
		int maxIdx = 0;
		for (int i = 0; i < w; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] > arr[maxIdx]) maxIdx = i;
		} 
		System.out.println(findTallestPair(0, maxIdx, maxIdx, arr) + findTallestPair(maxIdx, w, maxIdx, arr));
	}
	
	public static int findTallestPair(int start, int end, int maxIdx, int[] arr) {
		if (end - start < 2) return 0;
		int curMax = -1;
		int curIdx = -1;
		
		if (maxIdx == start) start += 1;
		for (int i = start; i < end; i++) {
			if (arr[i] > curMax) {
				curMax = arr[i];
				curIdx = i;
			}
		}
		
		int acc = 0;
		int finalMax = Math.min(curMax, arr[maxIdx]);
		if (curIdx < maxIdx) {
			if (curIdx == 0 && curMax == 0) return 0;
			for (int i = curIdx + 1; i < maxIdx; i++) {
				acc += finalMax - arr[i];
			}	
			return acc + findTallestPair(start, curIdx, curIdx, arr);
		}
		
		if (curIdx == 0 && curMax == 0) return 0;
		for (int i = maxIdx + 1; i < curIdx; i++) {
				acc += finalMax - arr[i];
		}
		return acc + findTallestPair(curIdx, end, curIdx, arr);
	}
}