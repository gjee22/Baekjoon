import java.io.IOException;
import java.lang.StringBuilder;

class Main {
    static int[] digits = new int[10];

    public static void main(String[] args) throws IOException {
        int n;
        while ((n = read()) != -1) {
            digits[n]++;
        }
        if (digits[0] == 0) {
            System.out.print("-1");
            return;
        }

        StringBuilder res = new StringBuilder();
        int sum = 0;
        for (int i = 9; i >= 0; i--) {
            sum += i * digits[i];
            while (digits[i] != 0) {
                res.append(i);
                digits[i]--;
            }
        }

        System.out.print(sum % 3 == 0 ? res.toString() : -1);
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32 && b != -1);
        return b == -1 ? -1 : (b & 15);
    }
}