import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;

class Main {
    static Stack<Long> st;
    static boolean isErr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String inst = br.readLine();
        while (!inst.equals("QUIT")) {
            LinkedList<String> instructions = new LinkedList<>();
            while (!inst.equals("END")) {
                instructions.offer(inst);
                inst = br.readLine();
            }
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new Stack<>();
                isErr = false;
                st.push(Long.parseLong(br.readLine()));
                for (String in : instructions) {
                    if (in.startsWith("N")) { NUM(Long.parseLong(in.substring(4))); }
                    else if (in.equals("POP")) { POP(); }
                    else if (in.equals("INV")) { INV(); }
                    else if (in.equals("DUP")) { DUP(); }
                    else if (in.equals("SWP")) { SWP(); }
                    else if (in.equals("ADD")) { ADD(); }
                    else if (in.equals("SUB")) { SUB(); }
                    else if (in.equals("MUL")) { MUL(); }
                    else if (in.equals("DIV")) { DIV(); }
                    else { MOD(); }
                    if (isErr) break;
                }
                if (st.size() != 1) {
                    isErr = true;
                }
                if (isErr) {
                    bw.write("ERROR\n");
                }	else {
                    bw.write(st.pop() + "\n");
                }
            }
            br.readLine();
            bw.write("\n");
            inst = br.readLine();
        }
        bw.close();
    }

    static void NUM(long x) {
        st.push(x);
    }

    static void POP() {
        if (st.empty()) {
            isErr = true;
        } else {
            st.pop();
        }
    }

    static void INV() {
        if (st.empty()) {
            isErr = true;
        } else {
            st.push(-1 * st.pop());
        }
    }

    static void DUP() {
        if (st.empty()) {
            isErr = true;
        } else {
            st.push(st.peek());
        }
    }

    static void SWP() {
        if (st.size() < 2) {
            isErr = true;
        } else {
            long first = st.pop();
            long second = st.pop();
            st.push(first);
            st.push(second);
        }
    }

    static void ADD() {
        if (st.size() < 2) {
            isErr = true;
        } else {
            long res = st.pop() + st.pop();
            if (Math.abs(res) > 1000000000) {
                isErr = true;
            } else {
                st.push(res);
            }
        }
    }

    static void SUB() {
        if (st.size() < 2) {
            isErr = true;
        } else {
            long res = (-1 * st.pop()) + st.pop();
            if (Math.abs(res) > 1000000000) {
                isErr = true;
            } else {
                st.push(res);
            }
        }
    }

    static void MUL() {
        if (st.size() < 2) {
            isErr = true;
        } else {
            long res = st.pop() * st.pop();
            if (Math.abs(res) > 1000000000) {
                isErr = true;
            } else {
                st.push(res);
            }
        }
    }

    static void DIV() {
        if (st.size() < 2) {
            isErr = true;
        } else {
            long n1 = st.pop();
            long n2 = st.pop();
            if (n1 == 0) {
                isErr = true;
            } else {
                st.push(n2 / n1);
            }
        }
    }

    static void MOD() {
        if (st.size() < 2) {
            isErr = true;
        } else {
            long n1 = st.pop();
            long n2 = st.pop();
            if (n1 == 0) {
                isErr = true;
            } else {
                st.push(n2 % n1);
            }
        }
    }
}