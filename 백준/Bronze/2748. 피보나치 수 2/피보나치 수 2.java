import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 0) {
            System.out.println(0);
            return;
        }
        if (N == 1) {
            System.out.println(1);
            return;
        }

        long first = 0, sec = 1, n = 0;

        for (int i = 2; i <= N; i++) {
            n = first + sec;
            first = sec;
            sec = n;
        }

        System.out.println(n);
    }
}