import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder res = new StringBuilder();
        String input = "";
        while ((input = br.readLine()) != null) {
            int N = Integer.parseInt(input), numD = 1, i = 1;
            while (i % N != 0) {
                numD++;
                i = ((i << 3) + (i << 1) + 1) % N;
            }
            res.append(numD).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }
}
