import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        boolean[] arr = new boolean[N + 1];
        arr[1] = true;
        for (int i = 2; i <= (int) Math.sqrt(N); i++) {
            int mul = 2;
            if (arr[i] == true) continue;
            while (i * mul <= N) {
                arr[i * mul++] = true;
            }
        }

        StringBuilder res = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = M; i <= N; i++) {
            if (!arr[i]) res.append(i).append("\n");
        }
        bw.write(res.toString());
        bw.close();
    }
}