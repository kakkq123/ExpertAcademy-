import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4014 {
	static int n, x, count;
	static int[][] board;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
	static boolean[][] slope;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());

		for (int test = 1; test <= t; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			board = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					board[i][j] = Integer.parseInt(st.nextToken());
			}
			count = 0;
			slope = new boolean[n][n];
			for (int i = 0; i < n; i++)
				row_check(i);
			slope = new boolean[n][n];
			for (int i = 0; i < n; i++)
				col_check(i);
			System.out.println("#" + test + " " + count);
		}
		br.close();
	}

	public static void row_check(int r) {
		int height = board[r][0];
		boolean check = true;
		for (int i = 1; i < n; i++) {
			if (height == board[r][i] - 1) {
				check = check(r, i - 1, height, 3);
				height++;
			}
			if (height == board[r][i] + 1) {
				height--;
				check = check(r, i, height, 1);
			}
			if (!check || Math.abs(height -board[r][i]) > 1)
				return;
		}
		count++;
	}
	public static void col_check(int c) {
		int height = board[0][c];
		boolean check = true;
		for (int i = 1; i < n; i++) {
			if(height == board[i][c])
				continue;
			if (height == board[i][c] - 1) {
				check = check(i - 1, c, height, 0);
				height++;
			}
			else if (height == board[i][c] + 1) {
				height--;
				check = check(i, c, height, 2);
			}
			if (!check || Math.abs(height -board[i][c]) > 1)
				return;	
		}
		count++;
	}

	public static boolean check(int r, int c, int height, int d) {
		for (int i = 0; i < x; i++) {
			if (r < 0 || c < 0 || r >= n || c >= n || board[r][c] != height || slope[r][c]) 
				return false;
			slope[r][c] = true;
			r += dr[d];
			c += dc[d];
		}
		return true;
	}

}