import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.LinkedList;

public class _5644 {

	static class AP implements Comparable<AP> {
		int id, p;

		public AP(int id, int p) {
			this.id = id;
			this.p = p;
		}

		@Override
		public int compareTo(AP a) {
			return this.p > a.p ? -1 : 1;
		}
	}

	public static boolean isrange(int r, int c) {
		return r >= 1 && r <= 10 && c >= 1 && c <= 10;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] dc = { 0, 0, 1, 0, -1 }, dr = { 0, -1, 0, 1, 0 };
		int test = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test; t++) {
			int[][] board = new int[11][11];
			LinkedList<AP>[][] ap = new LinkedList[11][11];
			for (int i = 1; i < 11; i++)
				for (int j = 1; j < 11; j++)
					ap[i][j] = new LinkedList<AP>();

			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int sum = 0;
			// USER
			int[] user1 = new int[m + 1];
			user1[0] = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++)
				user1[i] = Integer.parseInt(st.nextToken());
			int[] user2 = new int[m + 1];
			user2[0] = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++)
				user2[i] = Integer.parseInt(st.nextToken());
			// AP
			for (int i = 1; i <= a; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				ap[y][x].add(new AP(i, p));
				board[y][x] = 1;//

				for (int j = y - c; j <= y + c; j++) {
					for (int k = x - c; k <= x + c; k++) {
						if (!isrange(j, k) || (j == y && k == x) || (Math.abs((x - k)) + Math.abs(y - j)) > c)
							continue;
						board[j][k] = 1;
						ap[j][k].add(new AP(i, p));
					}
				}
			}
			// 정렬
			for (int i = 1; i < 11; i++)
				for (int j = 1; j < 11; j++)
					Collections.sort(ap[i][j]);

			
			int r1 = 1, c1 = 1, r2 = 10, c2 = 10; // 출발위치
			for (int i = 0; i <= m; i++) {
				r1 += dr[user1[i]];
				c1 += dc[user1[i]];
				r2 += dr[user2[i]];
				c2 += dc[user2[i]];
				// 같은 지역 모두 BC영역
				if (r1 == r2 && c1 == c2 && board[r1][c1] == 1) {
					// BC 하나인 경우
					if (ap[r1][c1].size() == 1)
						sum += ap[r1][c1].get(0).p;
					// BC 두개인 경우
					else
						sum += ap[r1][c1].get(0).p + ap[r1][c1].get(1).p;

				} // BC 같은 구역이지만 다른 곳에 있는 USER
				else if (board[r1][c1] == 1 && board[r2][c2] == 1 && ap[r1][c1].get(0).id == ap[r2][c2].get(0).id) {
					// 모두 한 BC영역에만 속해있는 경우
					if (ap[r1][c1].size() == 1 && ap[r2][c2].size() == 1)
						sum += ap[r1][c1].get(0).p;
					// BC영역 2개이상인 USER1과 BC영역 하나인 USER2
					else if (ap[r1][c1].size() > 1 && ap[r2][c2].size() == 1)
						sum += ap[r1][c1].get(1).p + ap[r2][c2].get(0).p;
					// BC영역 하나인 USER1과 BC영역 2개 이상인 USER2
					else if (ap[r1][c1].size() == 1 && ap[r2][c2].size() > 1)
						sum += ap[r1][c1].get(0).p + ap[r2][c2].get(1).p;
					// BC영역 2개 이상인 USER1,2
					else
						sum += ap[r1][c1].get(0).p + Math.max(ap[r1][c1].get(1).p, ap[r2][c2].get(1).p);

				} else {
					if (board[r1][c1] == 1)
						sum += ap[r1][c1].get(0).p;
					if (board[r2][c2] == 1)
						sum += ap[r2][c2].get(0).p;
				}
			}
			System.out.println("#" + t + " " + sum);
		}
		br.close();
	}
}
