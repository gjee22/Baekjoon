import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main {
    static int N, C = 0, max = Integer.MIN_VALUE;
    static List<Integer> charList = new ArrayList<>();
    static int[] nums = new int[26];
    static boolean[] used = new boolean[10];
    static String[] equation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[26];
        equation = new String[N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            equation[i] = line;
            for (char c : line.toCharArray()) {
                if (nums[c - 'A'] == 0) {
                    nums[c - 'A'] = -1;
                    charList.add(c - 'A');
                    C++;
                }
            }
        }
        findMax(0);
        System.out.println(max);
    }

    static void findMax(int cnt) {
        if (cnt == C) {
            max = Math.max(max, calc());
            return;
        }

        int currentChar = charList.get(cnt);
        for (int i = 0; i < 10; i++) {
            if (!used[i]) {
                nums[currentChar] = i;
                used[i] = true;
                findMax(cnt + 1);
                used[i] = false;
            }
        }
    }

    static int calc() {
        int sum = 0;
        for (String eq : equation) {
            int term = 0;
            for (char c : eq.toCharArray()) {
                term = term * 10 + nums[c - 'A'];
            }
            sum += term;
        }
        return sum;
    }
}
