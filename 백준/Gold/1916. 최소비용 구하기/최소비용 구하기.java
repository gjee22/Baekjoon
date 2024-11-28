import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

class Main {
	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		int N = r.nextInt(); int M = r.nextInt();
		
		Map<Integer, List<Edge>> nodes = new HashMap<>();
		int[] distances = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			distances[i] = Integer.MAX_VALUE;
			nodes.put(i, new LinkedList<>());
		}
		for (int i = 0; i < M; i++) nodes.get(r.nextInt()).add(new Edge(r.nextInt(), r.nextInt()));
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		int start = r.nextInt(); int dest = r.nextInt();
		distances[start] = 0; 
		pq.add(new Edge(start, 0));
		
		while (!pq.isEmpty()) {
			int cur = pq.poll().dest;
			if (visited[cur]) continue;
			visited[cur] = true;
			for (Edge neighbor : nodes.get(cur)) {
				distances[neighbor.dest] = Math.min(distances[neighbor.dest], distances[cur] + neighbor.cost);
				if (!visited[neighbor.dest]) pq.offer(new Edge(neighbor.dest, distances[neighbor.dest]));
			}
		}
		
		if (visited[dest]) System.out.println(distances[dest]);
	}
	
	static class Edge implements Comparable<Edge> {
		int dest;
		int cost;
		
		public Edge(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		} 
		
		@Override
		public int compareTo(Edge e) {
			return this.cost - e.cost;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;
		
		int nextInt() throws IOException {
			int n = 0;
			boolean isMinus = false;
			byte c;
			while ((c = read()) <= 32) { if (size < 0) return -1; }
			if (c == 45) { c = read(); isMinus = true; }
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return isMinus ? ~n + 1 : n;
		}
		
		boolean isNumber(byte c) {
			return 58 > c && c > 47;
		}
		
		byte read() throws IOException {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0) buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}