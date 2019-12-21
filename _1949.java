import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1949 {
	static int n, k, len, max;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visit;

	public static boolean range(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}

	public static void dfs(int r, int c, int num, int length, boolean shave) {
		if (len < length)
			len = length;
		visit[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (!range(nr, nc) || visit[nr][nc])
				continue;
			if (num > map[nr][nc])
				dfs(nr, nc, map[nr][nc], length + 1, shave);
			//ÁöÇüÀ» ±ğÁö ¾Ê¾Ò´Ù¸é ±ğ¾ÆÁÜ
			if (!shave) {
				for (int j = 1; j <= k; j++) {
					if (map[nr][nc] - j >= num)
						continue;
					if (map[nr][nc] - j < 0) {
						break;
					}
					dfs(nr, nc, map[nr][nc] - j, length + 1, true);
				}
			}
		}
		visit[r][c] = false;
	}

	public static void solve() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (map[i][j] == max) {
					visit = new boolean[n][n];
					dfs(i, j, map[i][j], 1, false);
				}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			max = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > max)
						max = map[i][j];
				}
			}
			len = 1;
			solve();
			System.out.println("#" + test + " " + len);
		}
		br.readLine();
	}

}
