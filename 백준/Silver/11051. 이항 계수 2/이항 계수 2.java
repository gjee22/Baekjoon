import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int DIV = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (K == 0) {
            System.out.println(1);
            return;
        }
        arr = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            arr[i][1] = i;
        }

        // nCr = (n-1)C(r-1) + (n-1)Cr 공식을 사용
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j]) % DIV;
            }
        }

        System.out.println(arr[N][K]);
    }
}