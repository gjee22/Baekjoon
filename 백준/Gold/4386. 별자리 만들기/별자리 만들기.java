import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

class Main {
    static float[][] stars;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        stars = new float[N][2];
        for (int i = 0; i < N; i++) {
            stars[i][0] = readFloat();
            stars[i][1] = readFloat();
        }
        int comb = N * (N - 1) / 2;
        float[][] edges = new float[comb][3];
        int idx = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                edges[idx][0] = i;
                edges[idx][1] = j;
                edges[idx][2] = getDistance(i, j);
                idx++;
            }
        }
        Arrays.sort(edges, Comparator.comparingDouble(x -> x[2]));

        UnionFind uf = new UnionFind(N);
        int count = 0;
        float cost = 0;
        for (int i = 0; i < comb; i++) {
            if (uf.union((int) edges[i][0], (int) edges[i][1])) {
                cost += edges[i][2];
                if (++count == N - 1) break;
            }
        }

        System.out.format("%.2f%n", cost);
    }

    static float getDistance(int i, int j) {
        return (float) Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));
    }

    static class UnionFind {
        int[] parents;

        public UnionFind(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) this.parents[i] = i;
        }

        public int find(int n) {
            if (parents[n] == n) return n;
            return parents[n] = find(parents[n]);
        }

        public boolean union(int x, int y) {
            int xp = find(x);
            int yp = find(y);
            if (xp == yp) return false;
            if (xp <= yp) {
                parents[yp] = xp;
            } else {
                parents[xp] = yp;
            }
            return true;
        }
    }

    static int readInt() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return n;
    }

    static float readFloat() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        float n = 0;
        float div = 1;
        do {
            n = (n * 10) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        b = System.in.read();
        do {
            n += (b & 15) / (div *= 10);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return n;
    }
}
