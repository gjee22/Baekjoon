import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) throws IOException {
        int N = nextInt(), R = nextInt();
        Frame[] frames = new Frame[101];
        PriorityQueue<Frame> sorted = new PriorityQueue<>();
        for (int i = 0; i < R; i++) {
            int r = nextInt();
            if (frames[r] != null) {
                frames[r].rec++;
                sorted.remove(frames[r]);
                sorted.offer(frames[r]);
            }
            else if (sorted.size() < N) {
                frames[r] = new Frame(r, i);
                sorted.offer(frames[r]);
            }
            else {
                int removed = sorted.poll().id;
                frames[removed] = null;
                frames[r] = new Frame(r, i);
                sorted.offer(frames[r]);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder res = new StringBuilder();
        for (Frame f : frames) {
            if (f != null) res.append(f.id).append(" ");
        }
        bw.write(res.toString());
        bw.close();
    }

    static int nextInt() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
        } while ((b = System.in.read()) > 47 && b < 58);
        return n;
    }

    static class Frame implements Comparable<Frame> {
        int id;
        int time;
        int rec;

        public Frame(int id, int time) {
            this.id = id;
            this.time = time;
            this.rec = 1;
        }

        @Override
        public int compareTo(Frame f) {
            if (this.rec == f.rec) return this.time - f.time;
            return this.rec - f.rec;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            return this.id == ((Frame) o).id;
        }
    }
}