import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.StringBuilder;

class Main {
    public static void main(String[] args) throws IOException {
        int N = read();
        int[] incoming = new int[N + 1];
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i < N + 1; i++) {
            nodes[i] = new Node(i);
        }
        int M = read();
        for (int i = 0; i < M; i++) {
            int src = read(), dest = read();
            nodes[src].neighbors.add(dest);
            incoming[dest]++;
        }

        Queue<Node> order = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (incoming[i] == 0) {
                order.offer(nodes[i]);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!order.isEmpty()) {
            Node cur = order.poll();
            res.append(cur.id).append(" ");
            for (Integer n : cur.neighbors) {
                if (--incoming[nodes[n].id] == 0) {
                    order.offer(nodes[n]);
                }
            }
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
        } while (b < 58 && b > 47);
        return n;
    }

    static class Node {
        List<Integer> neighbors = new LinkedList<>();
        int id;

        public Node(int id) {
            this.id = id;
        }
    }
}