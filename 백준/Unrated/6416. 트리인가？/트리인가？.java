import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.HashSet;
import java.util.Set;

class Main {
    public static void main(String[] args) throws IOException {
        int caseN = 0;
        int s = read(), e = read();
        Set<Integer> nodes;
        Set<Integer> in;
        StringBuilder res = new StringBuilder();

        while (s != -1) {
            nodes = new HashSet<>();
            in = new HashSet<>();
            boolean isValid = true;
            caseN++;

            do {
                if (s == 0 || s == -1) break;
                nodes.add(s);
                nodes.add(e);
                if (in.contains(e)) {
                    isValid = false;
                } else {
                    in.add(e);
                }
                s = read(); e = read();
            } while (s != 0);

            if (isValid && (nodes.size() == 0 || nodes.size() - in.size() == 1)) {
                res.append("Case ").append(caseN).append(" is a tree.").append("\n");
            } else {
                res.append("Case ").append(caseN).append(" is not a tree.").append("\n");
            }

            s = read(); e = read();
            if (s == -1) {
                break;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        boolean isNeg = false;
        if (b == 45) {
            isNeg = true;
            b = System.in.read();
        }
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return isNeg ? -n : n;
    }
}