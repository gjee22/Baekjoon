import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int max = -1;
    static int D;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String nStr = st.nextToken();
        int n = Integer.parseInt(nStr);
        int K = Integer.parseInt(st.nextToken());
        D = nStr.length();
        visited = new boolean[1000001][K + 1];
        dfs(n, K);
        System.out.println(max);
    }

    static void dfs(int n, int cnt) {
        if (cnt == 0) {
            max = Math.max(n, max);
            return;
        }

        for (int i = 1; i < D; i++) {
            for (int j = i + 1; j < D + 1; j++) {
                int d1 = (int) Math.pow(10, D - i);
                int d2 = (int) Math.pow(10, D - j);
                int iNum = (n / d1) % 10;
                int jNum = (n / d2) % 10;
                if (jNum == 0 && i == 1) continue;
                int res = n - ((iNum * d1) + (jNum * d2)) + ((iNum * d2) + (jNum * d1));
                if (visited[res][cnt - 1]) continue;
                visited[res][cnt - 1] = true;
                dfs(res, cnt - 1);
            }
        }
    }
}