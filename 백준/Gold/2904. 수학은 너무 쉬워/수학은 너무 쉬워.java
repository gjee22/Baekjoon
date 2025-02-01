import java.io.IOException;
import java.util.HashMap;

class Main {
    static HashMap<Integer, Integer> count = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int N = read();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = read();
            findFactors(nums[i]);
        }
        if (N == 1) {
            System.out.println(nums[0] + " 0");
            return;
        }

        int sum = 1;
        for (int k : count.keySet()) {
            count.put(k, count.get(k) / N);
            if (count.get(k) != 0) {
                sum *= Math.pow(k, count.get(k));
            }
        }

        int move = 0;
        for (int i = 0; i < N; i++) {
            for (int k : count.keySet()) {
                if (nums[i] % k == 0) {
                    int f = 0;
                    while (nums[i] % k == 0) {
                        f++;
                        nums[i] /= k;
                    }
                    move += (count.get(k) > f ? count.get(k) - f : 0);
                } else {
                    move += count.get(k);
                }
            }
        }

        System.out.println(sum + " " + move);
    }

    static void findFactors(int n) {
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                count.put(i, count.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }
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