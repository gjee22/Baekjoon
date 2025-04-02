import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        int N = read(), K = read();
        boolean[] arr = new boolean[N + 1];
        int count = 0;
        int i = 2, j = 0;
        while (count < K && i < N + 1) {
            j = i;
            while (count < K && j < N + 1) {
                if (arr[j]) {
                    j += i;
                    continue;
                } else {
                    arr[j] = true;
                    if (++count == K) break;
                    j += i;
                }
            }
            i++;
        }
        System.out.println(j);
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return n;
    }
}