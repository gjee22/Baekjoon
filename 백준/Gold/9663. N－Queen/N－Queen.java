import java.io.IOException;

class Main {
    static int[] board;
    static int N;
    static int acc;
    public static void main(String[] args) throws IOException {
        N = System.in.read() & 15;
        int b = System.in.read();
        if (b < 58 && b > 47) N = (N << 3) + (N << 1) + (b & 15);
        board = new int[N];
        move(0);
        System.out.println(acc);
    }

    static void move(int n) {
        if (n == N) {
            acc++;
            return;
        }
        for (int i = 0; i < N; i++) {
		        // n이 열이 되고 i가 행이 된다.
            board[n] = i;
            if (isValid(n)) {
                move(n + 1);
            }
        }
    }

    static boolean isValid(int n) {
			  // 룹이 n보다 작은 수까지 돌기 때문에 같은 열에 위치할 경우에 룹을 돌지 않고 바로 true를 리턴한다.
			  // 이후에 move 함수에서는 순차적으로 n이 증가되기 때문에 isValid는 무조건 다른 열에 대해 호출된다.
        for (int i = 0; i < n; i++) {
		        // 같은 행(=배열의 값)에 있는지 확인한다. 
            if (board[n] == board[i]) return false;
            // 열의 차이가 행의 차이와 같으면 같은 대각선에 있는 것이므로 false를 리턴한다.
            if (Math.abs(n - i) == Math.abs(board[n] - board[i])) return false;
        }
        return true;
    }
}