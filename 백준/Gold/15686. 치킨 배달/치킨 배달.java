import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

class Main {
    static int S, M, H = 0, min = Integer.MAX_VALUE;
    static List<int[]> stores = new ArrayList<>();
    static int[][] houses;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        int N = read();
        M = read();
        houses = new int[2 * N][2];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int loc = read();
                if (loc == 1) {
                    houses[H][0] = r;
                    houses[H][1] = c;
                    H++;
                }
                else if (loc == 2) {
                    stores.add(new int[] { r, c });
                    S++;
                }
            }
        }
        used = new boolean[S];
        getSolution(0, 0);
        System.out.println(min);
    }

    static void getSolution(int cnt, int cur) {
        if (cnt == M) {
            min = Math.min(min, findDistance());
            return;
        }

        for (int s = cur; s < S; s++) {
            if (!used[s]) {
                used[s] = true;
                getSolution(cnt + 1, s + 1);
                used[s] = false;
            }
        }
    }

    static int findDistance() {
        int dist = 0;
        for (int h = 0; h < H; h++) {
            int cur = 101;
            for (int s = 0; s < S; s++) {
                if (used[s]) {
                    cur = Math.min(cur, Math.abs(stores.get(s)[0] - houses[h][0]) + Math.abs(stores.get(s)[1] - houses[h][1]));
                }
            }
            dist += cur;
        }
        return dist;
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