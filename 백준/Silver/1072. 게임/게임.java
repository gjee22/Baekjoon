import java.io.IOException;

class Main {
    static long X, Y, old;

    public static void main(String[] args) throws IOException {
        X = read(); Y = read();
        Y = (Y << 6) + (Y << 5) + (Y << 2);
        old = Y / X;
        if (old >= 99) {
            System.out.println(-1);
            return;
        }
        System.out.println(binarySearch(1, 1_000_000_000));
    }

    static long binarySearch(long lower, long upper) {
        if (upper - lower <= 1) {
            long temp = (Y + (lower << 6) + (lower << 5) + (lower << 2)) / (X + lower);
            return temp == old ? upper : lower;
        }
        long mid = (lower + upper) / 2;
        long temp = (Y + (mid << 6) + (mid << 5) + (mid << 2)) / (X + mid);
//        System.out.println(mid + " " + ((mid << 6) + (mid << 5) + (mid << 2)) + " " + (Y + (mid << 6) + (mid << 5) + (mid << 2)));
        if (temp == old) return binarySearch(mid, upper);
        return binarySearch(lower, mid);
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