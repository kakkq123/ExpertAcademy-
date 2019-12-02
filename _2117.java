import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2117 {
	static int n, m, max_count, count;
	static int[][] home;

	public static boolean range(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}

	public static int cost(int a) {
		return a * a + (a - 1) * (a - 1);
	}

	public static void search(int a, int r, int c) {
		for (int i = r - a; i <= r + a; i++) {
			for (int j = c - a; j <= c + a; j++) {
				if (!range(i, j) || (Math.abs((r - i)) + Math.abs(j - c)) > a)
					continue;
				if (home[i][j] == 1)
					count++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test = 01; test <= t; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			home = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					home[i][j] = Integer.parseInt(st.nextToken());
			}
			max_count = 0;
			for (int i = 1; i <= n+1; i++) {
				for (int j = 0; j < n; j++)
					for (int z = 0; z < n; z++) {
						count = 0;
						search(i - 1, j, z);
						if (count * m - cost(i) >= 0 && max_count < count)
							max_count = count;
					}
			}
			System.out.println("#" + test + " " + max_count);
		}
		br.close();
	}

}
