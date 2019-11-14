import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class _5650 {
	static int n, max_score, score, dir = 0, nr, nc;
	static int[][] board;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
	static LinkedList<Wormhole> wormhole;

	static public class Wormhole {
		int num, x, y;

		public Wormhole(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}

	public static void shape(int number) {
		switch (number) {
		// 0 위, 1오른쪽, 2아래, 3 왼쪽
		case 1:
			if (dir == 0 || dir == 1)
				dir = dir + 2;
			else if (dir == 2)
				dir = 1;
			else
				dir = 0;
			break;
		case 2:
			if (dir == 1 || dir == 2)
				dir = (dir + 2) % 4;
			else if (dir == 0)
				dir = 1;
			else
				dir = 2;
			break;
		case 3:
			if (dir == 2 || dir == 3)
				dir = (dir + 2) % 4;
			else if (dir == 0)
				dir = 3;
			else
				dir = 2;
			break;
		case 4:
			if (dir == 0 || dir == 3)
				dir = (dir + 2) % 4;
			else if (dir == 1)
				dir = 0;
			else
				dir = 3;
			break;
		default:
			dir = (dir + 2) % 4;
			break;
		}
	}

	public static void move(int r, int c) {
		while (true) {
			r += dr[dir];
			c += dc[dir];
			// wall
			if (r < 0 || r >= n || c < 0 || c >= n) {
				dir = (dir + 2) % 4;
				score++;
				r += dr[dir];
				c += dc[dir];
			}
			// 블랙홀 또는 원래 위치
			if (board[r][c] == -1 || (r == nr && c == nc)) {
				break;
			} // 도형
			else if (board[r][c] >= 1 && board[r][c] <= 5) {
				shape(board[r][c]);
				score++;
			}
			// worm hole
			if (board[r][c] >= 6 && board[r][c] <= 10) {
				for (Iterator<Wormhole> iter = wormhole.iterator(); iter.hasNext();) {
					Wormhole w = iter.next();
					if (w.num == board[r][c] && !(w.x == r && w.y == c)) {
						r = w.x;
						c = w.y;
						break;
					}
				}
			}
		}

	}

	public static void solve() {
		max_score = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					// 4가지 방향
					for (int k = 0; k < 4; k++) {
						score = 0; nr = i; nc = j; dir = k;
						move(nr, nc);
						if (max_score < score)
							max_score = score;
					} // for k
				} // if
			} // for j
		} // for i
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		// TEST
		for (int t = 1; t <= test; t++) {
			n = Integer.parseInt(br.readLine());
			wormhole = new LinkedList<Wormhole>();
			board = new int[n][n];
			//
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] >= 6 && board[i][j] <= 10)
						wormhole.add(new Wormhole(board[i][j], i, j));
				}
			}
			//
			solve();
			System.out.println("#" + t + " " + max_score);

		}
		br.close();
	}

}
