import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;

class Main {
    static int[][] sudoku = new int[9][9];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = read();
            }
        }
        sudoku(0, 0);
    }

    static void sudoku(int r, int c) throws IOException {
        if (c == 9) {
            sudoku(r + 1, 0);
            return;
        }
        if (r == 9) {
            StringBuilder res = new StringBuilder();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    res.append(sudoku[i][j]).append(" ");
                }
                res.append("\n");
            }
            bw.write(res.toString());
            bw.flush();
            System.exit(0);
        }

        if (sudoku[r][c] == 0) {
            for (int n = 1; n <= 9; n++) {
                if (isPossible(r, c, n)) {
                    sudoku[r][c] = n;
                    sudoku(r, c + 1);
                }
            }
            sudoku[r][c] = 0;
            return;
        }
        sudoku(r, c + 1);
    }

    static boolean isPossible(int r, int c, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][c] == value || sudoku[r][i] == value) return false;
        }
        int x = 3 * (r / 3);
        int y = 3 * (c / 3);
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (sudoku[i][j] == value) return false;
            }
        }
        return true;
    }

    static int read() throws IOException {
        int b;
        do b = System.in.read(); while (b <= 32);
        int n = 0;
        do n = (n << 3) + (n << 1) + (b & 15); while ((b = System.in.read()) < 58 && b > 47);
        return n;
    }
}