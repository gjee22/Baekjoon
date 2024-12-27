import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
    static long[] arr;
    static int N, M, K, size;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        K = readInt();

        size = 1;
        while ((size <<= 1) < N);
        arr = new long[size << 1];

        for (int i = size; i < size + N; i++) {
            arr[i] = readLong();
        }
        for (int i = size + N - 1; i > 1; i--) {
            arr[i / 2] += arr[i];
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            int inst = readInt();
            if (inst == 1) {
                update(readInt(), readLong());
            }
            else {
                res.append(query(readInt(), readInt())).append("\n");
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static void update(int i, long n) {
        i = (i + size) - 1;
        long old = arr[i];
        arr[i] = n;
        while ((i /= 2) > 0) {
            arr[i] -= (old - n);
        }
    }

    static long query(int s, int e) {
        long sum = 0;
        s = s + size - 1;
        e = e + size - 1;
        while (e >= s) {
            if (s % 2 == 1) sum += arr[s];
            if (e % 2 == 0) sum += arr[e];
            s = (s + 1) / 2;
            e = (e - 1) / 2;
        }
        return sum;
    }

    static int readInt() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return n;
    }

    static long readLong() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        long n = 0;
        boolean isNeg = false;
        if (b == 45) {
            isNeg = true;
            b = System.in.read();
        }
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return isNeg ? -n : n;
    }
}