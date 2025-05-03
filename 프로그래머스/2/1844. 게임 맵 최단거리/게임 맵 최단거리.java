import java.util.Queue;
import java.util.LinkedList;


class Solution {
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    
    public int solution(int[][] maps) {
        int N = maps.length - 1, M = maps[0].length - 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0, 1 });
        maps[0][0] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < dr.length; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr < 0 || nr > N || nc < 0 || nc > M || maps[nr][nc] == 0) 
                    continue;
                if (nr == N && nc == M) return cur[2] + 1;
                maps[nr][nc] = 0;
                queue.offer(new int[] { nr, nc, cur[2] + 1});
            }
        }
        return -1;
    }
}