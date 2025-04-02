import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.lang.StringBuilder;

class Main {
    static int order = 1;
    static int[] visited;
    static boolean[] isCutVertex;
    static ArrayList<Integer>[] nodes;
    static StringBuilder res;

    public static void main(String[] args) throws IOException {
        int V = read(), E = read();
        nodes = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int a = read(), b = read();
            nodes[a].add(b);
            nodes[b].add(a);
        }

        visited = new int[V + 1];
        isCutVertex = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            if (visited[i] == 0) {
                dfs(i, true);
            }
        }

        res = new StringBuilder();
        int count = 0;
        for (int i = 1; i <= V; i++) {
            if (isCutVertex[i]) {
                count++;
                res.append(i).append(" ");
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(count + "\n");
        bw.write(res.toString());
        bw.flush();
    }

    static int dfs(int n, boolean isRoot) {
        visited[n] = order++;
        int ret = visited[n];
        int child = 0;

        for (int i : nodes[n]) {
            if (visited[i] == 0) {
                child++;
                int prev = dfs(i, false);
                if (!isRoot && prev >= visited[n]) {
                    isCutVertex[n] = true;
                }
                ret = Math.min(ret, prev);
            } else {
                ret = Math.min(ret, visited[i]);
            }
        }

        if (isRoot && child >= 2) {
            isCutVertex[n] = true;
        }

        return ret;
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