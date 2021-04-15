import java.io.*;
import java.util.*;

public class Solution {
	static int[][] a, store;
	static int[] profit;
	static int N, result, C, M, profit_a, profit_b;

	static class Point {
		int cur, sum, profit;

		public Point(int cur, int sum, int profit) {
			this.cur = cur;
			this.sum = sum;
			this.profit = profit;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int Test = Integer.parseInt(br.readLine());
		for (int test = 1; test <= Test; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			a = new int[N][N];
			store = new int[2][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					a[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = -1;
			solve(0, 0, 0);
			bw.write("#" + test + " " + result + "\n");
		}
		br.close();
		bw.close();

	}

	private static void solve(int r, int c, int cnt) {
		if (cnt == 2) {
			profit = new int[2];
			dfs(0, 0, 0, 0);
			dfs(0, 1, 0, 0);
			result = Math.max(result, profit[0] + profit[1]);
			return;
		}
		int x = r, y = c;
		if (c + M > N) {
			if (r + 1 >= N)
				return;
			x = r + 1;
			y = 0;
		}

		for (int i = 0; i < M; i++)
			store[cnt][i] = a[x][y + i];

		solve(x, y + M, cnt + 1);
		solve(x, y + 1, cnt);
	}

	private static void dfs(int cur, int v, int sum, int p) {
		profit[v] = Math.max(profit[v], p);
		if (cur == M)
			return;
		if (sum + store[v][cur] <= C)
			dfs(cur + 1, v, sum + store[v][cur], p + store[v][cur] * store[v][cur]);

		dfs(cur + 1, v, sum, p);

	}
}
