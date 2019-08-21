import java.util.Scanner;

public class _1210 {
	static int[][] ladder;

	public static boolean left_check(int a, int b) {
		if (b == 1 || ladder[a][b - 1] == 0)
			return false;

		return true;

	}

	public static boolean right_check(int a, int b) {
		if (b == 100 || ladder[a][b + 1] == 0)
			return false;
		return true;

	}

	public static boolean solve(int a, int b) {
		if (a == 100) {
			if (ladder[a][b] == 2)
				return true;
			return false;
		}
		if(left_check(a,b)) {
			do {
				b--;
			}while(left_check(a,b));
		}else if(right_check(a,b)) {
			do {
				b++;
			}while(right_check(a,b));
		}
		return solve(a + 1, b);
	}

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		ladder = new int[101][101];

		for (int test = 1; test <= 10; test++) {
			kb.nextInt(); // test number
			//
			for (int i = 1; i <= 100; i++)
				for (int j = 1; j <= 100; j++)
					ladder[i][j] = kb.nextInt();

			for (int k = 1; k <= 100; k++) {
				if (ladder[1][k] == 0)
					continue;
				if (solve(1, k)) {
					System.out.printf("\n#%d  %d", test, k-1);
					break;
				}
			}
		} // test

	}

}
