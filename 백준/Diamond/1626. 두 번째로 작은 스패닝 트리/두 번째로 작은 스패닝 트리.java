import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

class Main {
    static int V, E, K, total;
    static int[][] edges;
    static ArrayList<int[]>[] nodes;
    static ArrayList<int[]>[] mst;
    static int[][] parent;
    static int[][] max;
    static int[][] sec;
    static int[] depth;
    static UnionFind uf;

    public static void main(String[] args) throws IOException {
        V = read();
        E = read();
        edges = new int[E][3];
        nodes = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int a = read(), b = read(), c = read();
            edges[i][0] = a;
            edges[i][1] = b;
            edges[i][2] = c;
            nodes[a].add(new int[] { b, c });
            nodes[b].add(new int[] { a, c });
        }

        // 최소 신장 트리 찾기
        Arrays.sort(edges, Comparator.comparingInt(x -> x[2]));
        uf = new UnionFind(V);
        mst = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            mst[i] = new ArrayList<>();
        }
        MST();
        if (mst[1] == null) {
            System.out.println("-1");
            return;
        }

        //사이클 파악을 위한 LCA + DP 준비
        depth = new int[V + 1];
        for (int i = 1; i <= V; i *= 2) {
            K++;
        }
        parent = new int[V + 1][K];
        max = new int[V + 1][K];
        sec = new int[V + 1][K];
        Arrays.fill(max[0], -1);
        Arrays.fill(max[1], -1);
        Arrays.fill(sec[0], -1);
        Arrays.fill(sec[1], -1);
        makeTree(1, 1);
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= V; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
                max[j][i] = Math.max(max[j][i - 1], max[parent[j][i - 1]][i - 1]);
                if (max[j][i - 1] != max[parent[j][i - 1]][i - 1]) {
                    sec[j][i] = Math.max(Math.max(sec[j][i - 1], sec[parent[j][i - 1]][i - 1]),
                            Math.min(max[j][i - 1], max[parent[j][i - 1]][i - 1]));
                } else {
                    sec[j][i] = sec[j][i - 1];
                }
            }
        }

        // 각 간선들을 넣고 사이클을 발생시키는 가장 비싼 간선을 제거
        int sec = Integer.MAX_VALUE;
        for (int[] e : edges) {
            if (e[0] == -1) {
                continue;
            }
            int dec = LCA(e[0], e[1], e[2]);
            if (dec != -1) {
                sec = Math.min(sec, total - dec + e[2]);
            }
        }
        sec = sec == Integer.MAX_VALUE ? -1 : sec;
        System.out.println(sec);
    }

    static int LCA(int a, int b, int lim) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        int m = -1;
        for (int i = K - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                if (max[a][i] != lim) {
                    m = Math.max(m, max[a][i]);
                } else {
                    m = Math.max(m, sec[a][i]);
                }
                a = parent[a][i];
            }
        }
        if (a == b) {
            return m;
        }
        for (int i = K - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                int aCmp = max[a][i] < lim ? max[a][i] : -1;
                int bCmp = max[b][i] < lim ? max[b][i] : -1;
                int cmp = Math.max(aCmp, bCmp);
                if (cmp != -1) {
                    m = Math.max(m, cmp);
                } else {
                    m = Math.max(m, Math.max(sec[a][i], sec[b][i]));
                }
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        int aCmp = max[a][0] < lim ? max[a][0] : -1;
        int bCmp = max[b][0] < lim ? max[b][0] : -1;
        int cmp = Math.max(aCmp, bCmp);
        if (cmp != -1) {
            m = Math.max(m, cmp);
        } else {
            m = Math.max(m, Math.max(sec[a][0], sec[b][0]));
        }

        return m;
    }

    static void makeTree(int n, int d) {
        depth[n] = d;
        for (int[] neighbor : mst[n]) {
            if (depth[neighbor[0]] == 0) {
                parent[neighbor[0]][0] = n;
                sec[neighbor[0]][0] = -1;
                max[neighbor[0]][0] = neighbor[1];
                makeTree(neighbor[0], d + 1);
            }
        }
    }

    static void MST() {
        int count = 0;
        for (int i = 0; i < E; i++) {
            if (uf.union(edges[i][0], edges[i][1])) {
                total += edges[i][2];
                mst[edges[i][0]].add(new int[] { edges[i][1], edges[i][2] });
                mst[edges[i][1]].add(new int[] { edges[i][0], edges[i][2] });
                edges[i][0] = -1;
                if (count++ == V - 1) {
                    break;
                }
            }
        }
        if (count != V - 1) {
            mst[1] = null;
        }
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return n;
    }

    static class UnionFind {
        int[] parents;

        public UnionFind(int n) {
            parents = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parents[i] = i;
            }
        }

        int find(int n) {
            if (parents[n] == n) return n;
            return parents[n] = find(parents[n]);
        }

        boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) return false;
            if (pa < pb) {
                parents[pb] = pa;
            } else {
                parents[pa] = pb;
            }
            return true;
        }
    }
}