import java.io.IOException;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws IOException {
        long N = System.in.read() & 15;
        int b;
        while ((b = System.in.read()) > 47 && b < 58) {
            N = (N << 3) + (N << 1) + (b & 15);
        }
        long euler = N;
        for (long i = 2; i * i <= N; i++) {
            if (N % i == 0) euler -= (euler / i);
            while (N % i == 0) N /= i;
        }
        if (N > 1) {
            euler -= euler / N;
        }
        System.out.println(euler);
    }
}