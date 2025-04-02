import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) throws IOException {
        int V = read(), E = read(), S = read();
        ArrayList<int[]>[] nodes = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            nodes[read()].add(new int[] { read(), read() });
        }

        // 다익스트라
        boolean[] visited = new boolean[V + 1];
        int[] dist = new int[V + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;
        pq.offer(new int[] { S, 0 });
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int c = cur[0], d = cur[1];
            visited[c] = true;
            for (int[] i : nodes[c]) {
                int newD = d + i[1];
                if (!visited[i[0]] && newD < dist[i[0]]) {
                    dist[i[0]] = newD;
                    pq.offer(new int[] { i[0], newD });
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            res.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
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