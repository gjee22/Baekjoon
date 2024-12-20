import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.Arrays;

class Main {
    static int[] arr;
    static int N, M;

    public static void main(String[] args) throws IOException {
        N = read();
        arr = new int[N];
        for (int i = 0; i < N; i++) { arr[i] = read(); }
        Arrays.sort(arr);

        M = read();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            res.append(binarySearch(read())).append("\n");
        }
        bw.write(res.toString());
        bw.close();
    }

    static int binarySearch(int n) {
        int start = 0;
        int end = N;
        int mid = (start + end) / 2;
        while (start < end) {
            if (n == arr[mid]) {
                return 1;
            }
            else if (n < arr[mid]) {
                end = mid;
                mid = (start + end) / 2;
            }
            else {
                start = mid + 1;
                mid = (start + end) / 2;
            }
        }
        return 0;
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do n = (n << 3) + (n << 1) + (b & 15); while ((b = System.in.read()) > 47 && b < 58);
        return n;
    }
}