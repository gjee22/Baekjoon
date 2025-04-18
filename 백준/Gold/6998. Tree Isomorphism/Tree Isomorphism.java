import java.io.*;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Hashtable;


class Main {
	private static final String ISOMORPHIC = "The two trees are isomorphic.";
	private static final String NOT_ISOMORPHIC = "The two trees are not isomorphic.";

	static class Node {
		char id;
		Node p;
		ArrayList<Node> children;

		public Node(char id, Node p) {
			this.id = id;
			this.p = p;
			this.children = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < N; i++) {
			Node t1 = readTree(br.readLine());
			Node t2 = readTree(br.readLine());
			res.append(isIsomorphic(t1, t2) ? ISOMORPHIC : NOT_ISOMORPHIC).append("\n");
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(res.toString());
		bw.close();
	}

	private static boolean isIsomorphic(Node t1, Node t2) {
		if (t1 == null && t2 == null) {
			return true;
		}
		if (t1 == null || t2 == null) {
			return false;
		}
		if (t1.children.size() == 0 && t2.children.size() == 0) {
			return true;
		}
		if (t1.children.size() != t2.children.size()) {
			return false;
		}
		Hashtable<Integer, ArrayList<Node>> count = new Hashtable<>();
		for (Node n : t1.children) {
			count.putIfAbsent(n.children.size(), new ArrayList<>());
			count.get(n.children.size()).add(n);
		}
		boolean flag = false;
		for (Node n : t2.children) {
			flag = false;
			int key = n.children.size();
			if (!count.containsKey(key)) {
				return false;
			}
			for (Node cmp : count.get(key)) {
				flag |= isIsomorphic(n, cmp);
			}
			if (!flag) {
				return false;
			}
		}
		return flag;
	}

	private static Node readTree(String line) {
		Node root = new Node(line.charAt(0), null);
		Node cur = root;
		if (root.id == '#') return null;
		for (int i = 2; i < line.length(); i += 2) {
			char c = line.charAt(i);
			if (c == '#') {
				cur = cur.p;
				continue;
			}
			Node next = new Node(c, cur);
			cur.children.add(next);
			cur = next;
		}
		return root;
	}
}