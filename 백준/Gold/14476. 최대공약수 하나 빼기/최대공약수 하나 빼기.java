import java.io.IOException;

class Main {
    static int[] nums;
    static int[] left;
    static int[] right;
    static int N;

    public static void main(String[] args) throws IOException {
        N = read();
        nums = new int[N];
        left = new int[N];
        right = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = read();
        }

        left[0] = nums[0];
        for (int i = 1; i < N; i++) {
            left[i] = gcd(Math.max(left[i - 1], nums[i]), Math.min(left[i - 1], nums[i]));
        }
        right[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            right[i] = gcd(Math.max(right[i + 1], nums[i]), Math.min(right[i + 1], nums[i]));
        }

        int max = -1;
        int n = -1;
        if (left[N - 2] >= right[1]) {
            max = left[N - 2];
            n = nums[N - 1];
        } else {
            max = right[1];
            n = nums[0];
        }
        for (int i = 1; i < N - 1; i++) {
            int g = Math.max(left[i - 1], right[i + 1]);
            int l = Math.min(left[i - 1], right[i + 1]);
            int cur = gcd(g, l);
            if (cur > max && i % cur != 0) {
                max = cur;
                n = nums[i];
            }
        }

        System.out.println(n % max == 0 ? -1 : max + " " + n);
    }

    static int gcd(int g, int l) {
        if (g % l == 0) return l;
        return gcd(l, g % l);
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