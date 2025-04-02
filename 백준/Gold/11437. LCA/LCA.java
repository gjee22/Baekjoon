import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;
import java.lang.StringBuilder;

class Main {
    public static void main(String[] args) throws IOException {
        int N = read();
        Node[] nodes = new Node[N + 1];
        List<Integer>[] children = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
            children[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            int n1 = read(), n2 = read();
            children[n1].add(n2);
            children[n2].add(n1);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(1);
        while (!q.isEmpty()) {
            Node n = nodes[q.poll()];
            visited[n.id] = true;
            for (int i : children[n.id]) {
                if (visited[nodes[i].id]) {
                    continue;
                }
                nodes[i].parent = n.id;
                nodes[i].depth = n.depth + 1;
                q.offer(i);
            }
        }

        StringBuilder res = new StringBuilder();
        int M = read();
        for (int i = 0; i < M; i++) {
            Node n1 = nodes[read()], n2 = nodes[read()];
            while (n1.depth > n2.depth) {
              n1 = nodes[n1.parent];
            }
            while (n2.depth > n1.depth) {
	            n2 = nodes[n2.parent];
            }
            while (n1.id != n2.id) {
                n1 = nodes[n1.parent];
                n2 = nodes[n2.parent];
            }
            res.append(n1.id).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static class Node {
        int id;
        int parent;
        int depth;

        public Node(int id) {
            this.id = id;
            this.parent = -1;
            this.depth = 0;
        }
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