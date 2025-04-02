import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BigInteger P = new BigInteger(st.nextToken());
        BigInteger K = new BigInteger(st.nextToken());
        boolean flag = false;

        BigInteger i = new BigInteger("2");
        while (i.compareTo(K) < 0) {
            if (P.remainder(i).equals(BigInteger.ZERO)) {
                flag = true;
                break;
            }
            i = i.add(BigInteger.ONE);
        }

        System.out.println(!flag ? "GOOD" : "BAD " + i.intValue());
    }
}