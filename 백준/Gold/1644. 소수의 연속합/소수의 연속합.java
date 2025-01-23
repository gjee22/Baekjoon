import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 1. 에라토스테네스의 체로 소수 찾기
        boolean[] notPrimes = new boolean[N + 1];
        for (int i = 2; i * i <= N; i++) {
            if (notPrimes[i]) continue;
            for (int j = i * i; j <= N; j += i) {
                notPrimes[j] = true;
            }
        }

        // 2. 투포인터로 경우의 수 구하기
        int sum = 0, count = 0, s = 2, e = 2;
        while (e <= N && s <= e) {
            while (e <= N && sum <= N) {
                if (notPrimes[e]) {
                    e++;
                    continue;
                }
                sum += e;
                if (sum == N) count++;
                e++;
            }
            while (s <= e && sum >= N) {
                if (notPrimes[s]) {
                    s++;
                    continue;
                }
                sum -= s;
                if (sum == N) count++;
                s++;
            }
        }

        System.out.println(count);
    }
}