import java.io.*;
import java.util.*;
import java.awt.Point;

public class Solution {
	static int N, answer;
	static int[][] map;
	static int[] stair, a = new int[2];
	static Point[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			int[][] s = new int[2][2];

			int idx = 0, cnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 1) {
						s[idx][0] = i;
						s[idx][1] = j;
						a[idx] = map[i][j];
						idx++;
					}
					if (map[i][j] == 1) {
						cnt++;
					}
				}
			}
			stair = new int[cnt];
			p = new Point[cnt];
			for (int i = 0; i < cnt; i++)
				p[i] = new Point();

			int num = 0, len;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						// 1번 계단
						len = Math.abs(i - s[0][0]) + Math.abs(j - s[0][1]);
						p[num].x = len + 1;
						// 2번 계단
						len = Math.abs(i - s[1][0]) + Math.abs(j - s[1][1]);
						p[num].y = len + 1;
						++num;
					}
				}
			}
			answer = 10000000;
			dfs(0, cnt);
			bw.write("#" + test + " " + answer + "\n");

		}
		br.close();
		bw.close();
	}

	private static void dfs(int cur, int cnt) {
		if (cur == cnt) {
			// 계산
			PriorityQueue<Integer> s1 = new PriorityQueue<Integer>();
			PriorityQueue<Integer> s2 = new PriorityQueue<Integer>();
			for (int i = 0; i < cnt; i++) {
				if (stair[i] == 1)
					s1.add(p[i].x);
				else
					s2.add(p[i].y);
			}

			Queue<Integer> _s1 = new LinkedList<Integer>();
			Queue<Integer> _s2 = new LinkedList<Integer>();

			int time = 0;
			while (true) {
				time++;
				if (s1.isEmpty() && _s1.isEmpty() && s2.isEmpty() && _s2.isEmpty())
					break;
				// 계단1
				while (!s1.isEmpty()) {
					if (time >= s1.peek() && _s1.size() < 3) {
						_s1.add(1);
						s1.poll();
					} else
						break;
				}
				int size = _s1.size();
				for (int i = 0; i < size; i++) {
					int t = _s1.poll();
					if (t == a[0])
						continue;
					_s1.add(t + 1);
				}
				// 계단2
				while (!s2.isEmpty()) {
					if (time >= s2.peek() && _s2.size() < 3) {
						_s2.add(1);
						s2.poll();
					} else
						break;
				}
				size = _s2.size();
				for (int i = 0; i < size; i++) {
					int t = _s2.poll();
					if (t == a[1])
						continue;
					_s2.add(t + 1);
				}
			}
			answer = Math.min(answer, time);
			return;
		}
		for (int i = 1; i <= 2; i++) {
			stair[cur] = i; //i번 계단을 이용
			dfs(cur + 1, cnt);
		}
	}

}
