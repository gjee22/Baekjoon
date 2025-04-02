import java.io.*;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String[] nums = br.readLine().split("\\.|\\(|\\)");
            long g = 1, x = 0, y = 0;
            if (nums.length == 2) {
                x = Integer.parseInt(nums[1]);
                y = (long) Math.pow(10, nums[1].length());
                g = gcd(x, y);
            } else {
                long a = (long) Math.pow(10, nums[1].length());
                long b = (long) Math.pow(10, nums[2].length());
                y = a * (b - 1);
                if (nums[1].length() != 0) {
                    x = Long.parseLong(nums[1] + nums[2]) - Integer.parseInt(nums[1]);
                } else {
                    x = Integer.parseInt(nums[2]);
                }
                g = gcd(x, y);
            }

            res.append(x / g).append("/").append(y / g).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static long gcd(long g, long l) {
        if (g % l == 0) return l;
        return gcd(l, g % l);
    }
}