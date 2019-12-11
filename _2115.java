import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2115 {
	static int n, m, c, total, a_profit, b_profit;
	static int[][] honey;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test = 01; test <= t; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			honey = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					honey[i][j] = Integer.parseInt(st.nextToken());
			}
			total = 0;
			a_solve();
			System.out.println("#" + test + " " + total);
		}
		br.close();
	}

	public static void a_cal(int row, int col, int d, int sum, int profit) {
		if (profit > a_profit)
			a_profit = profit;
		for (int i = col; i < d; i++) {
			if (honey[row][i] + sum > c)
				continue;
			a_cal(row, i + 1, d, sum + honey[row][i], profit + honey[row][i] * honey[row][i]);
			a_cal(row, i + 1, d, sum, profit);
		}
	}

	public static void b_cal(int row, int col, int d, int sum, int profit) {
		if (profit > b_profit)
			b_profit = profit;
		for (int i = col; i < d; i++) {
			if (honey[row][i] + sum > c)
				continue;
			b_cal(row, i + 1, d, sum + honey[row][i], profit + honey[row][i] * honey[row][i]);
			b_cal(row, i + 1, d, sum, profit);
		}
	}

	public static void a_solve() {
		a_profit = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= n - m; j++) {
				if (i == n - 1 && j > n - m)
					return;
				a_cal(i, j, j + m, 0, 0);
				b_solve(i, j + m);
			}
		}
	}

	public static void b_solve(int row, int col) {
		b_profit = 0;
		int r = col <= n - m ? row : row + 1;
		for (int i = r; i < n; i++) {
			int c = row == i ? col : 0;
			for (int j = c; j <= n - m; j++) {
				b_cal(i, j, j + m, 0, 0);
				if (total < a_profit + b_profit)
					total = a_profit + b_profit;
			}
		}
	}

}
