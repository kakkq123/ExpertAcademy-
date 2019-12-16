import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class _1953 {
	static int n, m, sr, sc, time, count;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
	static int[][] board;
	static boolean[][] visit;
	static Queue<Pos> q;

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static boolean range(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}

	public static void tunnel(int n, int r, int c) {
		// 위
		if (n == 1 || n == 2 || n == 4 || n == 7) {
			int nr = r + dr[0], nc = c + dc[0];
			if (range(nr, nc) && !visit[nr][nc]
					&& (board[nr][nc] == 1 || board[nr][nc] == 2 || board[nr][nc] == 5 || board[nr][nc] == 6)) {
				visit[nr][nc] = true;
				count++;
				q.add(new Pos(nr, nc));
			}
		}
		// 오
		if (n == 1 || n == 3 || n == 4 || n == 5) {
			int nr = r + dr[1], nc = c + dc[1];
			if (range(nr, nc) && !visit[nr][nc]
					&& (board[nr][nc] == 1 || board[nr][nc] == 3 || board[nr][nc] == 6 || board[nr][nc] == 7)) {
				visit[nr][nc] = true;
				count++;
				q.add(new Pos(nr, nc));
			}
		}
		// 아래
		if (n == 1 || n == 2 || n == 5 || n == 6) {
			int nr = r + dr[2], nc = c + dc[2];
			if (range(nr, nc) && !visit[nr][nc]
					&& (board[nr][nc] == 1 || board[nr][nc] == 2 || board[nr][nc] == 4 || board[nr][nc] == 7)) {
				visit[nr][nc] = true;
				count++;
				q.add(new Pos(nr, nc));
			}
		}
		// 왼
		if (n == 1 || n == 3 || n == 6 || n == 7) {
			int nr = r + dr[3], nc = c + dc[3];
			if (range(nr, nc) && !visit[nr][nc]
					&& (board[nr][nc] == 1 || board[nr][nc] == 3 || board[nr][nc] == 4 || board[nr][nc] == 5)) {
				visit[nr][nc] = true;
				count++;
				q.add(new Pos(nr, nc));
			}
		}

	}

	public static void bfs(int nr, int nc) {
		count = 1;
		visit[nr][nc] = true;
		q.add(new Pos(nr, nc));
		int t = 1;
		while (t < time && !q.isEmpty()) {
			int size = q.size();
			t++;
			for (int i = 0; i < size; i++) {
				int r = q.peek().r;
				int c = q.peek().c;
				q.poll();
				if (board[r][c] > 0)
					tunnel(board[r][c], r, c);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			board = new int[n][m];
			visit = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++)
					board[i][j] = Integer.parseInt(st.nextToken());
			}
			bfs(sr, sc);
			System.out.println("#" + test + " " + count);
		}
		br.readLine();
	}

}
