import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Main {
	static int max = Integer.MIN_VALUE;
	static int n, k;
	static String[] words;
	static boolean[] visited;
	
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
		
		words = new String[n];
		for(int i = 0; i < n; i++) {
			words[i] = br.readLine();
			words[i] = words[i].substring(4, words[i].length() - 4);
		}
		
		visited = new boolean[26];
		visited['a' - 'a'] = true; 
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['c' - 'a'] = true;
		dfs(0, 0);
		System.out.println(max);		
	}
	
	public static void dfs(int alpha, int len) {
		if (len + 5 == k) {
			int count = 0;
			for (String w : words) {
				boolean readable = true;
				for (char c : w.toCharArray()) {
					if (!visited[c - 'a']) {
						readable = false;
						break;
					}
				}
				if (readable) count++;
			}
			max = Math.max(max, count);
			return;
		}
		
		for (int i = alpha; i < 26; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i + 1, len + 1);
				visited[i] = false;
			}
		}
	}
}