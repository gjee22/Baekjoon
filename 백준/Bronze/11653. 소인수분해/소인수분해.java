import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N == 1) return;
		
		boolean[] notP = new boolean[N + 1];
		notP[0] = true; notP[1] = true;
		for (int i = 2; i * i <= N; i++) {
			if (notP[i]) continue;
			for (int j = i + i; j <= N; j += i) {
				notP[j] = true;
			}
		}
		
		StringBuilder res = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			if (notP[i]) continue;
			while (N % i == 0 && N > 1) {
				res.append(i).append("\n");
				N /= i;
			}
			if (N == 1) break;
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(res.toString());
		bw.flush();
	}
}