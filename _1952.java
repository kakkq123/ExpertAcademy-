import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1952 {
	static int[] fee, month;
	static int cost, max;

	public static void solve(int m, int c) {
		if (m > max || cost < c) {
			if (cost > c)
				cost = c;
			return;
		}
		for (int i = m; i <= max; i++) {
			if (month[i] != 0)
				break;
			m++;
		}
		solve(m + 1, c + month[m] * fee[0]);
		solve(m + 1, c + fee[1]);
		solve(m + 3, c + fee[2]);
		solve(m + 12, c + fee[3]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			fee = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++)
				fee[i] = Integer.parseInt(st.nextToken());
			month = new int[12];
			max = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
				if (month[i] > 0)
					max = i;
			}
			cost = Integer.MAX_VALUE;
			solve(0, 0);
			System.out.println("#" + test + " " + cost);
		}
		br.readLine();
	}
}
