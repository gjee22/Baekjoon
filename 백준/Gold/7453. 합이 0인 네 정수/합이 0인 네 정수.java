import java.io.IOException;
import java.util.Arrays;

class Main {
    static int[] AB, CD;

    public static void main(String[] args) throws IOException {
        int N = read();
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = read();
            B[i] = read();
            C[i] = read();
            D[i] = read();
        }

        AB = new int[N * N];
        CD = new int[N * N];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx++] = C[i] + D[j];
            }
        }

        Arrays.sort(CD); 
        long count = 0;

        for (int sum : AB) {
            int lower = lowerBound(CD, -sum);
            int upper = upperBound(CD, -sum);
            count += upper - lower; 
        }

        System.out.println(count);
    }

    static int lowerBound(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }

    static int upperBound(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0; boolean isNeg = false;
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
