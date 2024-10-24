import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		char[] parentheses = br.readLine().toCharArray();
		Stack<Integer> stack = new Stack<>();
		int totalAcc = 0;
		int curAcc = 1;
		boolean flag = false;
		for (char p : parentheses) {
			if (p == '(') { 
				stack.push(2);
				curAcc *= 2;
				flag = true; 
			}
			if (p == '[') {
				stack.push(3);
				curAcc *= 3;
				flag = true;
			}
 			else if (p == ')') {
 			 if (stack.isEmpty() || stack.peek() != 2) {
					System.out.println(0);
					return;
				} 
				int c = stack.pop();
				if (flag) totalAcc += curAcc;
				flag = false;
				curAcc /= c;
			} 
			else if (p == ']') {
				if (stack.isEmpty() || stack.peek() != 3) {
					System.out.println(0);
					return;
				} 
				int c = stack.pop();
				if (flag) totalAcc += curAcc;
				flag = false;
				curAcc /= c;
			}	
	}
	if (stack.isEmpty()) {
		System.out.println(totalAcc);
	} else {
		System.out.println(0);
	}
 }
}