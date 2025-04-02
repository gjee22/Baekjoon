import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        System.out.println(factorial(1, N, K) / factorial(1, N - K, 1));
    }

    static int factorial(int acc, int s, int e) {
        if (s <= e) return acc;
        return factorial(acc * s, --s, e);
    }
}