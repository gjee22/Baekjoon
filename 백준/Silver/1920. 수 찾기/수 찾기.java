import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.HashMap;

class Main {

    public static void main(String[] args) throws IOException {
        int N = read();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) { map.put(read(), 0); }

        int M = read();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            res.append(map.containsKey(read()) ? 1 : 0).append("\n");
        }
        bw.write(res.toString());
        bw.close();
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do n = (n << 3) + (n << 1) + (b & 15); while ((b = System.in.read()) > 47 && b < 58);
        return n;
    }
}