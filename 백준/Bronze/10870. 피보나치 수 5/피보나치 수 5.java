import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Integer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
	  if (n < 2) {
		  System.out.println(n);  
		  return;
		}
		int a = 0, b = 1, temp = 0;
		for (int i = 2; i <= n; i++) {
			temp = a + b;
			a = b;
			b = temp;
		}
		System.out.println(temp);
	}
}