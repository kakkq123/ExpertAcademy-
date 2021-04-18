import java.io.*;
import java.util.*;

public class Solution {
	static int[][] a;
	static int[] dr = { 1, 1, -1, -1 }, dc = { 1, -1, -1, 1 };
	static boolean[] dessert;
	static boolean[][] visited;
	static int N, sr, sc, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int Test = Integer.parseInt(br.readLine());
		for (int test = 1; test <= Test; test++) {
			N = Integer.parseInt(br.readLine());
			a = new int[N][N];
			// 입력
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++)
					a[r][c] = Integer.parseInt(st.nextToken());
			}
			//
			result = -1;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 셋팅
					dessert = new boolean[101];
					visited = new boolean[N][N];
					sr = r;
					sc = c;
					dfs(r, c, 0, 0);
				}
			}
			bw.write("#" + test + " " + result);
			bw.newLine();
		}
		br.close();
		bw.close();

	}

	private static void dfs(int r, int c, int dir, int move) {
		// 이 방향은 나올 수가 없음
		if (dir == 4)
			return;
		// 사각형을 만들고 시작위치로 왔다면
		if (dir == 3 && r == sr && c == sc) {
			result = Math.max(result, move);
			return;
		}

		int _r = r + dr[dir], _c = c + dc[dir];
		// 범위에 벗어나는가?
		if (_r < 0 || _r >= N || _c < 0 || _c >= N)
			return;
		//이미 방문한 곳인가?, 이미 먹은 디저트인가?
		if (visited[_r][_c] || dessert[a[_r][_c]])
			return;
		
		visited[_r][_c] = true;
		dessert[a[_r][_c]] = true;
		
		// 방향 그대로 유지
		dfs(_r, _c, dir, move + 1);
		// 방향 바꿈
		dfs(_r, _c, dir + 1, move + 1);
		
		visited[_r][_c] = false;
		dessert[a[_r][_c]] = false;
	}

}
