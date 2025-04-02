import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        int A1 = read(), B1 = read(), A2 = read(), B2 = read();
        int A = (A1 * B2) + (A2 * B1);
        int B = B1 * B2;
        int gcd = gcd(Math.max(A, B), Math.min(A, B));

        System.out.println((A / gcd) + " " + (B / gcd));
    }
    
    static int gcd(int g, int l) {
	    if (g % l == 0) return l;
	    return gcd(l, g % l);
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do {
            n = (n << 3)+ (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return n;
    }
}