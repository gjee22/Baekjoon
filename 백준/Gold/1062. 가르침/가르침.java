import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Main {
	static int max = Integer.MIN_VALUE;
	static int n, k;
	static int[] words;
	static int visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		if (k < 5) {
			System.out.println(0);
			return;
		} else if (k == 26) {
			System.out.println(n);
			return;
		}
		
		visited |= 1 << 0;
		visited |= 1 << ('n' - 'a');
		visited |= 1 << ('t' - 'a');
		visited |= 1 << ('i' - 'a');
		visited |= 1 << ('c' - 'a');
		
		words = new int[n];
		for(int i = 0; i < n; i++) {
			String word = br.readLine();
			for (char c : word.toCharArray())
				words[i] |= 1 << (c - 'a');
		}
		dfs(0, 0);
		System.out.println(max);		
	}
	
	public static void dfs(int alpha, int len) {
		if (len + 5 == k) {
			int count = 0;
			for (int w : words) {
				if ((w & visited) == w) count++;	
			}
			max = Math.max(max, count);
			return;
		}
		
		for (int i = alpha; i < 26; i++) {
			if ((visited & (1 << i)) != 0) continue;
			visited |= (1 << i);
			dfs(i + 1, len + 1);
			visited &= ~(1 << i);		
		}
	}
}