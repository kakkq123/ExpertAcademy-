import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4012 {
	static int difference;
	static int[] team1, team2;
	static int[][] value;
	static int n;

	public static void dfs(int index, int t1, int t2) {
		if (index == n) {
			int sum1 = 0, sum2 = 0;
			// team1
			for (int i = 1; i <= n - 1; i++)
				for (int j = i + 1; j <= n; j++)
					sum1 += value[team1[i]][team1[j]] + value[team1[j]][team1[i]];
			// team2
			for (int i = 1; i <= n - 1; i++)
				for (int j = i + 1; j <= n; j++)
					sum2 += value[team2[i]][team2[j]] + value[team2[j]][team2[i]];
			if (difference > Math.abs(sum1 - sum2))
				difference = Math.abs(sum1 - sum2);
			return;
		}
		if (t1 < n / 2) {
			team1[t1+1] = index+1;
			dfs(index + 1, t1 + 1, t2);
		}
		if (t2 < n / 2) {
			team2[t2+1] = index+1;
			dfs(index + 1, t1, t2 + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			n = Integer.parseInt(br.readLine());
			team1 = new int[n + 1];
			team2 = new int[n + 1];
			value = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++)
					value[i][j] = Integer.parseInt(st.nextToken());
			}
			difference = 1000000;
			dfs(0,0,0);
			System.out.println("#" + test + " " + difference);
		}
		br.close();
	}
}
