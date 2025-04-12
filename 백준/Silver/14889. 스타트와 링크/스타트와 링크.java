import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N, min = Integer.MAX_VALUE;
	static int[][] strength;
	static boolean[] matched;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		strength = new int[N][N];
		matched = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				strength[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			findFairTeam(1, i);
		}
		System.out.println(min);
	}

	static void findFairTeam(int numMatched, int p) {
		if (numMatched == N / 2) {
			min = Math.min(min, evaluate());
			return;
		}

		matched[p] = true;
		for (int i = p + 1; i < N; i++) {
			matched[i] = true;
			findFairTeam(numMatched + 1, i);
			matched[i] = false;
		}
		matched[p] = false;
	}

	static int evaluate() {
		int marked = 0;
		int notMarked = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (matched[i] && matched[j]) {
					marked += strength[i][j] + strength[j][i];
				}
				else if (!matched[i] && !matched[j]) {
					notMarked += strength[i][j] + strength[j][i];
				}
			}
		}
		return Math.abs(marked - notMarked);
	}
}