import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//NODE
class Node {
	int a, b;

	public Node(int a, int b) {
		this.a = a;
		this.b = b;
	}

}

public class _1238 {
	static boolean[][] visit = new boolean[11][101];
	static int test;

	// solve
	public static int search(int start, ArrayList<Node> n) {
		int max = 0, max_len = 1, len = 1;
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(start);
		visit[test][start] = true; // �湮
		while (!q.isEmpty()) {
			int qsize = q.size();
			len++;
			for (int j = 0; j < qsize; j++) {
				start = q.poll();
				for (int i = 0; i < n.size(); i++) {
					if (n.get(i).a == start && !visit[test][n.get(i).b]) {
						q.offer(n.get(i).b);
						visit[test][n.get(i).b] = true; // �湮

					}
				} // for i
				if (max_len < len) {
					max_len = len;
					max = start;
				} else if (max_len == len && start > max) {
					max = start;
				}
			} // for j

		} // while
		return max;
	}

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		for (test = 1; test <= 10; test++) {
			int num = kb.nextInt();
			int start = kb.nextInt();

			ArrayList<Node> n = new ArrayList<Node>();
			// �Է�
			for (int j = 0; j < num / 2; j++)
				n.add(new Node(kb.nextInt(), kb.nextInt())); // from & to

			// ���
			System.out.println("#" + test + " " + search(start, n));

		}
	}

}
