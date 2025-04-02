import java.io.IOException;
import java.util.Stack;

class Main {
    static int[][] board;
    static int[][] cnt;
    static boolean[][] visited;
    static int R, C;
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };
    static int max = Integer.MIN_VALUE;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        R = read();
        C = read();
        board = new int[R][C];
        cnt = new int[R][C];
        visited = new boolean[R][C];
        visited[0][0] = true;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int cur;
                do cur = System.in.read(); while(cur <= 32);
                if ((char) cur == 'H') {
                    // Hole
                    board[r][c] = 0;
                } else {
                    board[r][c] = cur & 15;
                }
            }
        }
        dfs(0, 0, 1);
        if (flag) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
    }

    static void dfs(int r, int c, int cur) {
        if (cur > max) {
            max = cur;
        }
        cnt[r][c] = cur;
        for (int d = 0; d < dr.length; d++) {
            int nr = r + dr[d] * board[r][c];
            int nc = c + dc[d] * board[r][c];
            if (nr >= 0 && nr < R && nc >= 0 && nc < C && board[nr][nc] != 0) {
                if (visited[nr][nc]) {
                    flag = true;
                    return;
                }
                if (cnt[nr][nc] > cur) continue;
                visited[nr][nc] = true;
                dfs(nr, nc, cur + 1);
                visited[nr][nc] = false;
            }
        }
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do n = (n << 3) + (n << 1) + (b & 15); while ((b = System.in.read()) > 47 && b < 58);
        return n;
    }
}