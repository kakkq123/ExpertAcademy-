import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4013 {
	static int[][] magnet;
	static int[] dir;

	public static void left_rotaion(int index) {
		int tmp = magnet[index][0];
		for (int i = 0; i < 7; i++)
			magnet[index][i] = magnet[index][i + 1];
		magnet[index][7] = tmp;
	}

	public static void right_rotaion(int index) {
		int tmp = magnet[index][7];
		for (int i = 7; i > 0; i--)
			magnet[index][i] = magnet[index][i - 1];
		magnet[index][0] = tmp;
	}

	public static void rotation(int num, int direction) {
		dir[num] = direction;
		// 왼쪽 자석 검사
		for (int i = num - 1; i >= 0 && dir[i + 1] != 0; i--)
			if (magnet[i + 1][6] != magnet[i][2])
				dir[i] = -dir[i + 1];

		// 오른쪽 자석 검사
		for (int i = num + 1; i < 4 && dir[i - 1] != 0; i++)
			if (magnet[i - 1][2] != magnet[i][6])
				dir[i] = -dir[i - 1];

		// 회전
		for (int i = 0; i < 4; i++) {
			if (dir[i] == 1)
				right_rotaion(i);
			else if (dir[i] == -1)
				left_rotaion(i);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken()); // 자석 회전
			magnet = new int[4][8];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++)
					magnet[i][j] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < k; i++) {
				dir = new int[4];
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				rotation(n - 1, d);
			}

			int sum = 0;
			if (magnet[0][0] == 1)
				sum += 1;
			if (magnet[1][0] == 1)
				sum += 2;
			if (magnet[2][0] == 1)
				sum += 4;
			if (magnet[3][0] == 1)
				sum += 8;

			System.out.println("#" + test + " " + sum);
		}
		br.close();
	}

}
