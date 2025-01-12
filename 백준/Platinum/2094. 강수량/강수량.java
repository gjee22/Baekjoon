import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;

class Main {
    static Weather[] st;
    static int SIZE = 1;

    public static void main(String[] args) throws IOException {
        int N = read();
        while ((SIZE <<= 1) < N);
        st = new Weather[SIZE << 1];
        HashMap<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int y = read();
            indices.put(y, SIZE + i);
            st[SIZE + i] = new Weather(y, read());
        }
        for (int i = SIZE + N - 1; i > 1; i--) {
            if (st[i] == null) continue;
            if (st[i/2] == null) {
                st[i/2] = st[i];
            } else {
                st[i/2] = (st[i/2].r <= st[i].r) ? st[i] : st[i/2];
            }
        }

        int M = read();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int y = read();
            int x = read();
            int yi = indices.containsKey(y) ? indices.get(y) : Math.abs(Arrays.binarySearch(st, SIZE, SIZE + N, new Weather(y, 0))) - 1;
            int xi = indices.containsKey(x) ? indices.get(x) : Math.abs(Arrays.binarySearch(st, SIZE, SIZE + N, new Weather(x, 0))) - 2;

            // 경계 년도들이 모두 있다:
            // 1. y가 x보다 적다 => false.
            // 2. y와 x 사이에 모든 해들의 강수량이 x보다 적다.
            //      a. 사이에 있는 모든 해들에 대한 정보가 있다 => true
            //      b. 사이에 있는 모든 해들에 대한 정보가 없다 => maybe
            // 3. y와 x 사이에 해들 중에서 x보다 강수량이 많은 해가 있다 => false
            if (indices.containsKey(y) && indices.containsKey(x)) {
                if (st[yi].r < st[xi].r) {
                    res.append("false\n");
                }
                else {
                    if (yi + 1 > xi - 1) {
                        res.append(yi - xi == y - x ? "true\n" : "maybe\n");
                    } else if (query(yi + 1, xi - 1) < st[xi].r) {
                        res.append(yi - xi == y - x ? "true\n" : "maybe\n");
                    } else {
                        res.append("false\n");
                    }
                }
            }
            // x와 y의 가장 가까운 해를 찾으면서 두 인덱스가 엊갈렸다면 관련된 모든 해들에 대한 정보가 없는 것이다 => maybe
            else if (yi > xi) {
                res.append("maybe\n");
            }
            // 경계 년도들이 모두 없다 => maybe
            else if (!indices.containsKey(y) && !indices.containsKey(x)) {
                res.append("maybe\n");
            }
            // 경계 년도들 중에 y가 없고 x가 있다:
            // 1. x가 1이면 사이에 있는 해들의 강수량이 1보다 적을 수 없다 (y와 x가 붙어있는 것이 아닌 이상) => false
            // 2. 올림한 yi가 xi와 똑같다 => maybe
            // 3. yi ~ xi - 1의 최대값이 x보다 적다 => maybe
            // 4. yi ~ xi - 1의 최대값이 x보다 같거나 크다 => false
            else if (!indices.containsKey(y)) {
                if (yi == xi || query(yi, xi - 1) < st[xi].r) {
                    res.append("maybe\n");
                } else {
                    res.append("false\n");
                }
            }
            // 경계 년도들 중에 y가 있고 x가 없다:
            // 1. y가 1이면 사이에 있는 해들의 강수량이 1보다 적을 수 없다 (y와 x가 붙어있는 것이 아닌 이상) => false
            // 2. 내림한 xi가 yi와 똑같다 => maybe
            // 3. yi + 1 ~ xi의 최대값이 y보다 적다 => maybe
            // 4. yi + 1 ~ xi의 최대값이 y보다 같거나 크다 => false
            else {
                if (yi == xi || query(yi + 1, xi) < st[yi].r) {
                    res.append("maybe\n");
                } else {
                    res.append("false\n");
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.toString());
        bw.flush();
    }

    static int query(int s, int e) {
        int r = 0;
        while (s <= e) {
            if (s % 2 == 1) r = Math.max(r, st[s].r);
            if (e % 2 == 0) r = Math.max(r, st[e].r);
            s = (s + 1) / 2;
            e = (e - 1) / 2;
        }
        return r;
    }

    static class Weather implements Comparable<Weather> {
        int y, r;

        public Weather(int y, int r) {
            this.y = y;
            this.r = r;
        }

        @Override
        public int compareTo(Weather w) {
            return this.y - w.y;
        }
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        boolean isNeg = false;
        if (b == 45) {
            b = System.in.read();
            isNeg = true;
        }
        int n = 0;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);
        return isNeg ? -n : n;
    }
}