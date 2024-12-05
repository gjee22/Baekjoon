import java.io.IOException;
import java.util.PriorityQueue;

class Main {
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		int V = nextInt();
		int E = nextInt();
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		for (int i = 0; i < E; i++) {
			Edge e = new Edge(nextInt(), nextInt(), nextInt());
			edges.offer(e);
		}
		
		parents = new int[V + 1];
		int count = 0;
		int total = 0;
		while (count < V - 1) {
			Edge cur = edges.poll();
			if (!union(cur.src, cur.dest)) continue;
			total += cur.cost; 
			count++;
		}
		System.out.println(total);
	}
	
	static int find(int v) {
		if (parents[v] == 0) return v;
		return parents[v] = find(parents[v]);
	}
	
	static boolean union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 != p2) {
			parents[p2] = p1;
			return true;
		}
		return false;
	}
	
	static int nextInt() throws IOException {
		int c; 
		boolean isNegative = false;
		while ((c = System.in.read()) <= 32);
		if (c == 45) {
			isNegative = true;
			c = System.in.read();
		}
		int n = 0; 
		do {
			n = (n << 1) + (n << 3) + (c & 15);
		} while ((c = System.in.read()) < 58 && c > 47);
		return isNegative ? -n : n; 
	}
	
	static class Edge implements Comparable<Edge> {
		int src;
		int dest;
		int cost;
		
		public Edge(int src, int dest, int cost) {
			this.src = src;
			this.dest = dest;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.cost - e.cost;
		}
	}
}