import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.PriorityQueue;
import java.util.Comparator;

class Main {
    public static void main(String[] args) throws IOException {
        int N = read();
        StringBuilder res = new StringBuilder();
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            if (maxQ.size() <= minQ.size()) {
                maxQ.offer(read());
            } else {
                minQ.offer(read());
            }
            if (minQ.size() != 0 && maxQ.peek() >= minQ.peek()) {
                int min = minQ.poll();
                int max = maxQ.poll();
                minQ.offer(max);
                maxQ.offer(min);
            }
            res.append(maxQ.peek()).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0; boolean isNeg = false;
        if (b == 45) {
            isNeg = true;
            b = System.in.read();
        }
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return isNeg ? -n : n;
    }
}