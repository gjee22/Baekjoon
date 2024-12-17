import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static int R, C;
    static int[] src = new int[2];
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, -1, 0, 1 };
    static char[][] map;

    public static void main(String[] args) throws IOException {
        R = nextInt();
        C = nextInt();
        map = new char[R][C];
        Queue<int[]> waterQueue = new LinkedList<>();
        Queue<int[]> hedgehogQueue = new LinkedList<>();

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                char cur = nextChar();
                if (cur == 'S') {
                    src[0] = r;
                    src[1] = c;
                    hedgehogQueue.offer(new int[] { r, c });
                } else if (cur == '*') {
                    waterQueue.offer(new int[] { r, c });
                }
                map[r][c] = cur;
            }
        }

        int turn = 0;
        while (!hedgehogQueue.isEmpty()) {
            int waterSize = waterQueue.size();
            while (waterSize-- > 0) {
                int[] water = waterQueue.poll();
                for (int i = 0; i < 4; i++) {
                    int ny = water[0] + dy[i];
                    int nx = water[1] + dx[i];
                    if (ny >= 0 && ny < R && nx >= 0 && nx < C && map[ny][nx] == '.') {
                        map[ny][nx] = '*';
                        waterQueue.offer(new int[] { ny, nx });
                    }
                }
            }

            int hedgehogSize = hedgehogQueue.size();
            while (hedgehogSize-- > 0) {
                int[] hedgehog = hedgehogQueue.poll();
                for (int i = 0; i < 4; i++) {
                    int ny = hedgehog[0] + dy[i];
                    int nx = hedgehog[1] + dx[i];
                    if (ny >= 0 && ny < R && nx >= 0 && nx < C) {
                        if (map[ny][nx] == 'D') {
                            System.out.println(turn + 1);
                            return;
                        }
                        if (map[ny][nx] == '.') {
                            map[ny][nx] = 'S';
                            hedgehogQueue.offer(new int[] { ny, nx });
                        }
                    }
                }
            }
            turn++;
        }

        System.out.println("KAKTUS");
    }

    static int nextInt() throws IOException {
        int b;
        do {
            b = System.in.read();
        } while (b <= 32);
        int n = 0;
        do n = (n << 3) + (n << 1) + (b & 15); while ((b = System.in.read()) < 58 && b > 47);
        return n;
    }

    static char nextChar() throws IOException {
        int b;
        while ((b = System.in.read()) <= 32);
        return (char) b;
    }
}
