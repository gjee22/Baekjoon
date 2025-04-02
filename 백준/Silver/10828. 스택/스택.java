import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.StringTokenizer;
import java.util.LinkedList;

class Main {
    static StringBuilder res;
    static StringTokenizer st;
    static Stack stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        res = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        stack = new Stack();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            processCmd(st.nextToken());
        }
        bw.write(res.toString());
        bw.flush();
    }

    static void processCmd(String inst) {
        if (inst.equals("push")) {
            stack.push(Integer.parseInt(st.nextToken()));
            return;
        }
        if (inst.equals("pop")) {
            res.append(stack.pop()).append("\n");
            return;
        }
        if (inst.equals("size")) {
            res.append(stack.size()).append("\n");
            return;
        }
        if (inst.equals("empty")) {
            res.append(stack.empty()).append("\n");
            return;
        }
        if (inst.equals("top")) {
            res.append(stack.top()).append("\n");
            return;
        }
    }

    static class Stack {
        LinkedList<Integer> stack;
        int size;

        public Stack() {
            this.stack = new LinkedList<>();
            this.size = 0;
        }

        int push(int n) {
            stack.add(n);
            size++;
            return n;
        }

        int pop() {
            if (this.size == 0) {
                return -1;
            }
            this.size--;
            return stack.pollLast();
        }

        int size() {
            return this.size;
        }

        int empty() {
            return this.size == 0 ? 1 : 0;
        }

        int top() {
            if (this.size == 0) {
                return -1;
            }
            return stack.peekLast();
        }
    }
}