import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.StringBuilder;

class Main {
    static int N;
    static int[] time;
    static Building[] buildings;
    static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        N = r.nextInt();
        time = new int[N + 1];
        buildings = new Building[N + 1];
        for (int i = 1; i <= N; i++) {
            buildings[i] = new Building();
        }
        for (int i = 1; i <= N; i++) {
            buildings[i].dur = r.nextInt();
            int b;
            while ((b = r.nextInt()) != -1) {
                buildings[i].in++;
                buildings[b].out.add(i);
            }
        }

        topological();

        for (int i = 1; i <= N; i++) {
            res.append(time[i]).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static void topological() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (buildings[i].in == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            time[cur] += buildings[cur].dur;
            for (int b : buildings[cur].out) {
                buildings[b].in--;
                time[b] = Math.max(time[b], time[cur]);
                if (buildings[b].in == 0) {
                    q.offer(b);
                }
            }
        }
    }

    static class Building {
        int dur, in;
        ArrayList<Integer> out;

        public Building() {
            this.dur = 0;
            this.in = 0;
            out = new ArrayList<>();
        }
    }

    static class Reader {
        int SIZE = 1 << 15;
        byte[] buf = new byte[SIZE];
        int i, size;

        int nextInt() throws IOException {
            byte b;
            boolean isNeg = false;
            do b = read(); while (b <= 32);
            if (b == 45) {
                isNeg = true;
                b = read();
            }
            int n = 0;
            do {
                n = (n << 3) + (n << 1) + (b & 15);
                b = read();
            } while (b > 47 && b < 58);
            return isNeg ? -n : n;
        }

        byte read() throws IOException {
            if (i == size) {
                size = System.in.read(buf, i = 0, SIZE);
                if (size < 0) {
                    buf[0] = -1;
                }
            }
            return buf[i++];
        }
    }
}