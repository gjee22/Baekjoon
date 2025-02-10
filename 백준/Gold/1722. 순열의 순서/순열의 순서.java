import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] factorial;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        factorial = new long[N];
        used = new boolean[N];
        factorial[0] = 1;
        for (int i = 1; i < N; i++) {
            factorial[i] = i * factorial[i - 1];
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int inst = Integer.parseInt(st.nextToken());
        if (inst == 1) {
            long k = Long.parseLong(st.nextToken()) - 1;
            int[] result = searchList(k);

            StringBuilder res = new StringBuilder();
            for (int i : result) {
                res.append(i).append(" ");
            }
            System.out.println(res);

        } else {
            int[] perm = new int[N];
            for (int i = 0; i < N; i++) {
                perm[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println(searchOrder(perm));
        }
    }

    static int[] searchList(long n) {
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            long f = factorial[N - i - 1];
            long o = n / f;
            int count = -1, j = 0;

            while (j < N) {
                if (!used[j]) {
                    count++;
                    if (count == o) break;
                }
                j++;
            }

            used[j] = true;
            arr[i] = j + 1;
            n -= o * f;
        }
        return arr;
    }

    static long searchOrder(int[] ls) {
        long n = 0;
        for (int i = 0; i < N; i++) {
            used[ls[i] - 1] = true;
            for (int j = 0; j < ls[i]; j++) {
                if (!used[j]) {
                    n += factorial[N - i - 1];
                }
            }
        }
        return n + 1;
    }
}
