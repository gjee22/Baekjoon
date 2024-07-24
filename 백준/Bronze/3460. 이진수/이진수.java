import java.util.*;
import java.lang.Math;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCases = Integer.parseInt(br.readLine());
        for (int i = 0; i < numCases; i++) {
            int cur = Integer.parseInt(br.readLine());
            int pos = 0;
            while (cur > 0) {
                if (cur % 2 == 1) {
                    System.out.print(pos + " ");
                }
                cur /= 2;
                pos++;
            }
            System.out.println();
        }
    }
}