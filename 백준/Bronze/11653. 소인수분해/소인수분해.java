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
		
		StringBuilder res = new StringBuilder();
		for (int i = 2; i <= N; i++) {
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