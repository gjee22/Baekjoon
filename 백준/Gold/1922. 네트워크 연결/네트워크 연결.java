import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

class Main {
	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		int N = r.readInt(), M = r.readInt();
		UnionFind uf = new UnionFind(N);
		int[][] edges = new int[M][3];
		for (int i = 0; i < M; i++) {
			edges[i][0] = r.readInt();
			edges[i][1] = r.readInt();
			edges[i][2] = r.readInt();
		}
		Arrays.sort(edges, Comparator.comparingInt(x -> x[2]));
		int count = 0;
		int total = 0;
		int p = 0;
		while (count < N - 1) {
			if (uf.union(edges[p][0], edges[p][1])) {
				total += edges[p][2];
				count++;
			}
			p++;
		}
		System.out.println(total);
	}
	
	static class UnionFind {
		int[] parents;
		
		public UnionFind(int n) {
			this.parents = new int[n + 1];
			for (int i = 0; i < n; i++) {
				this.parents[i] = i;
			}
		}
		
		int find(int x) {
			if (parents[x] == x) {
				return x;
			}
			return parents[x] = find(parents[x]);
		}
		
		boolean union(int x, int y) {
			int px = find(x);
			int py = find(y);
			if (px == py) {
				return false;
			}
			if (px <= py) {
				parents[py] = px;
			} else {
				parents[px] = py;
			}
			return true;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buf = new byte[SIZE];
		int size, i;
		
		int readInt() throws IOException {
			int n = 0;
			byte b;
			do {
				b = read();
			} while (b <= 32);
			do {
				n = (n << 3) + (n << 1) + (b & 15);
				b = read();
			} while (b > 47 && b < 58);
			return n; 
		}
		
		byte read() throws IOException {
			if (i == size) {
				size = System.in.read(buf, i = 0, SIZE);
				if (size < 0) {
					buf[0] = -1;
				}
			}
			return buf[i++];
		}
	}
}