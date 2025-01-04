import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> freq = new HashMap<>();
        TreeSet<String> species = new TreeSet<>();
        String t = "";
        float total = 0;
        while ((t = br.readLine()) != null) {
            species.add(t);
            if (freq.containsKey(t)) {
                freq.put(t, freq.get(t) + 1);
            } else {
                freq.put(t, 1);
            }
            total++;
        }

        StringBuilder res = new StringBuilder();
        Iterator<String> iter = species.iterator();
        while (iter.hasNext()) {
            t = iter.next();
            res.append(t).append(" ").append(String.format("%.4f", freq.get(t) / total * 100)).append("\n");
//            res.append(t).append(" ").append(Math.round(freq.get(t) / total * 1000000) / 10000F).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }
}