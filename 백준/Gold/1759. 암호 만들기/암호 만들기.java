import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.lang.StringBuilder;

class Main {
    static int L, C;
    static char[] alphabets = new char[26];
    static boolean[] isVowel = new boolean[26];
    static int lastVowel, lastConsonant = -1;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        L = readInt();
        C = readInt();
        isVowel['a' - 'a'] = true;
        isVowel['e' - 'a'] = true;
        isVowel['i' - 'a'] = true;
        isVowel['o' - 'a'] = true;
        isVowel['u' - 'a'] = true;

        for (int i = 0; i < C; i++) {
            char c = readChar();
            if (isVowel[c - 'a']) {
                lastVowel = Math.max(lastVowel, c - 'a');
            } else {
                lastConsonant = Math.max(lastConsonant, c - 'a');
            }
            alphabets[c - 'a'] = c;
        }
        findPassword(0, new StringBuilder(), false, 0);
        bw.flush();
    }

    static void findPassword(int start, StringBuilder res, boolean v, int con) throws IOException {
        if ((start > lastVowel && !v) || (start > lastConsonant && con < 2)) return;
        if (res.length() == L && (!v || con < 2)) return;
        if (res.length() == L) {
            bw.write(res.toString());
            bw.write("\n");
            return;
        }

        for (int i = start; i < 26; i++) {
            char cur = alphabets[i];
            if ((int) cur == 0) continue;
            res.append(cur);
            if (isVowel[cur - 'a']) {
                findPassword(i + 1, res, true, con);
            } else {
                findPassword(i + 1, res, v, con + 1);
            }
            res.deleteCharAt(res.length() - 1);
        }
    }

    static int readInt() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b < 58 && b > 47);
        return n;
    }

    static char readChar() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        return (char) b;
    }
}