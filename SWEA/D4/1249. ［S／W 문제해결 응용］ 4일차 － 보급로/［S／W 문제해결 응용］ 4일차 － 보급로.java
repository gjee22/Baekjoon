import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            
            boolean[][] visited = new boolean[N][N];
    		int[][] edges = new int[N][N];
			int[][] distances = new int[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    edges[i][j] = line.charAt(j) - '0';
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }

            distances[0][0] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
            pq.offer(new int[] { 0, 0, 0 });

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int r = cur[0];
                int c = cur[1];
                int cost = cur[2];
                if (visited[r][c]) continue;
                visited[r][c] = true;

                for (int i = 0; i < dr.length; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                    int nd = cost + edges[nr][nc];
                    if (nd < distances[nr][nc]) {
                        distances[nr][nc] = nd;
                        pq.offer(new int[] { nr, nc, nd });
                    }
                }
            }

            System.out.printf("#%d %d%n", test_case, distances[N - 1][N - 1]);
        }
    }
}
