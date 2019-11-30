import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class _5653 {
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
	static int[][] map;
	static PriorityQueue<Cell> cell = new PriorityQueue<Cell>();
	static PriorityQueue<Cell> copy = new PriorityQueue<Cell>();

	static public class Cell implements Comparable<Cell> {
		public int r, c, activation_time, life_time, activation;

		public Cell(int r, int c, int activation, int activation_time, int life_time) {
			this.r = r;
			this.c = c;
			this.activation = activation;
			this.activation_time = activation_time;
			this.life_time = life_time;
		}

		@Override
		public int compareTo(Cell c) {
			return this.activation < c.activation ? 1 : -1;
		}
	}

	public static void activate(int r, int c, int act) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (map[nr][nc] == 1)
				continue;
			map[nr][nc] = 1;
			copy.add(new Cell(nr, nc, act, act, act));
		}
	}

	public static void check() {
		copy.clear();
		while (!cell.isEmpty()) {
			Cell t = cell.poll();
			if (t.activation_time > 0)
				copy.add(new Cell(t.r, t.c, t.activation, t.activation_time - 1, t.life_time));
			else if (t.activation_time == 0) {
				activate(t.r, t.c, t.activation);
				copy.add(new Cell(t.r, t.c, t.activation, t.activation_time - 1, t.life_time - 1));
			} else {
				copy.add(new Cell(t.r, t.c, t.activation, t.activation_time, t.life_time - 1));
			}
		}
		cell.addAll(copy);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());

		for (int test = 1; test <= t; test++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			map = new int[700][700];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if (tmp > 0) {
						cell.add(new Cell(i + 300, j + 300, tmp, tmp, tmp));
						map[i + 300][j + 300] = 1;
					}
				}
			}
			for (int i = 1; i <= k; i++) {
				check();
			}
			int count = 0;
			while (!cell.isEmpty()) {
				Cell c = cell.poll();
				if (c.life_time > 0)
					count++;
			}
			System.out.println("#" + test + " " + count);
		}
		br.close();
	}

}
