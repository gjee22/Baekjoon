import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
    static int[] arr;
    static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder res = new StringBuilder();
        int N = read();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            int cur = read();
            if (cur == 0) {
                if (idx == 0) {
                    res.append("0\n");
                } else {
                    res.append(getMax()).append("\n");
                }
            } else {
                arr[idx] = cur;
                heapify(idx++);
            }
        }

        bw.write(res.toString());
        bw.close();
    }

    static int getMax() {
        int p = 0, l = 2*p + 1, r = 2*p + 2;
        int max = arr[p];
        arr[p] = arr[--idx];
        arr[idx] = 0;
        while (l < idx) {
            if (arr[l] > arr[p] && (r >= idx || arr[l] > arr[r])) {
                swap(l, p);
                p = l;
            }
            else if (r < idx && arr[r] > arr[p]) {
                swap(r, p);
                p = r;
            }
            else {
                break;
            }
            l = 2*p + 1;
            r = 2*p + 2;
        }
        return max;
    }

    static void heapify(int child) {
        int parent = getParent(child);
        while (parent >= 0) {
            if (arr[child] > arr[parent]) {
                swap(child, parent);
                child = parent;
                parent = getParent(child);
            } else {
                return;
            }
        }
    }

    static int getParent(int child) {
        if (child == 0) return -1;
        return (child - 1) / 2;
    }

    static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static int read() throws IOException {
        int b;
        boolean isNeg = false;
        int n = 0;
        while ((b = System.in.read()) <= 32);
        if (b == 45) {
            isNeg = true;
            b = System.in.read();
        }
        do {
            n = (n << 3) + (n << 1) + (b & 15);
            b = System.in.read();
        } while (b > 47 && b < 58);

        return isNeg ? ~n + 1 : n;
    }
}