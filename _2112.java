import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2112 {
	static int d, w, k, drug;
	static int[][] film;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			film = new int[d][w];
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++)
					film[i][j] = Integer.parseInt(st.nextToken());
			}
			drug = d;
			dfs(0, 0);
			System.out.println("#" + test + " " + drug);
		}
		br.close();

	}

	public static boolean check(int col) {
		int cnt = 1, c = film[0][col];
		for (int i = 1; i < d; i++) {
			if (c == film[i][col])
				cnt++;
			else {
				cnt = 1;
				c = film[i][col];
			}
			if (cnt == k)
				return true;
			if (i == d - k + 1 && cnt == 1)
				return false;
		}
		return false;
	}

	public static boolean inspect() {
		for (int i = 0; i < w; i++) {
			if (!check(i))
				return false;
		}
		return true;
	}

	public static void dfs(int row, int count) {
		if (count > k || count > drug)
			return;
		if (inspect()) {
			drug = count;
			return;
		}
		int[] copy = new int[w];
		for (int r = row; r < d; r++) {
			copy = Arrays.copyOf(film[r], w);
			int c = 0;
			for (int i = 0; i < w; i++) {
				film[r][i] = c;
			}
			dfs(r + 1, count + 1);

			c = 1;
			for (int i = 0; i < w; i++) {
				film[r][i] = c;
			}
			dfs(r + 1, count + 1);
			film[r] = copy;
		}

	}

}
