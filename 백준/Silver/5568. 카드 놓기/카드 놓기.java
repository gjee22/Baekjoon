import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.lang.StringBuilder;

class Main {
    static int count = 0, N, K;
    static StringBuilder n = new StringBuilder();
    static HashSet<Integer> nums = new HashSet<>();
    static boolean[] used;
    static String[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        used = new boolean[N];
        cards = new String[N];
        for (int i = 0; i < N; i++) {
            cards[i] = br.readLine();
        }
        search(K);
        System.out.println(count);
    }

    static void search(int k) {
        if (k == 0) {
            int res = Integer.parseInt(n.toString());
            if (nums.add(res)) {
                count++;
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            n.append(cards[i]);
            search(k - 1);
            used[i] = false;
            n.delete(n.length() - cards[i].length(), n.length());
        }
    }
}