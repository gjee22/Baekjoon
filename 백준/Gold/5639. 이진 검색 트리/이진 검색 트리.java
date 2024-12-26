import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
    static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int k = read();
        if (k == -1) return;
        Node root = new Node(k);
        Node p = null;
        while ((k = read()) != -1) {
            boolean isLeft = true;
            Node cur = root;
            while (cur != null) {
                isLeft = true;
                p = cur;
                if (cur.key > k) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                    isLeft = false;
                }
            }
            if (isLeft) {
                p.left = new Node(k);
            } else {
                p.right = new Node(k);
            }
        }

        iterate(root);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static void iterate(Node n) {
        if (n == null) return;
        iterate(n.left);
        iterate(n.right);
        res.append(n.key).append("\n");
    }

    static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    static int read() throws IOException {
        int b;
        do {
            b = System.in.read();
        } while (b <= 32 && b != -1);
        if (b == -1) return -1;
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return n;
    }
}