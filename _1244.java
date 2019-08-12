import java.util.Scanner;
import java.util.stream.Stream;

public class _1244{
	static int max, change;
	static boolean[][] visit = new boolean[1000000][11];

	public static void dfs(int[] digits, int index) {
		int tmp;
		if (change == index) {
			max = Math.max(max, cha(digits));
			return;
		}
		for (int i = 0; i < digits.length - 1; i++) {
			for (int j = i + 1; j < digits.length; j++) {
				swap(i, j, digits);
				tmp = cha(digits);
				if (!visit[tmp][index + 1]) {
					visit[tmp][index + 1] = true;
					dfs(digits, index + 1);
				}
				swap(i, j, digits);
			} 
		} 

	}

	public static void swap(int a, int b, int[] digits) {
		int tmp = digits[a];
		digits[a] = digits[b];
		digits[b] = tmp;
	}
	public static int cha(int[] digits) {
		int res=0, t=1;
		for (int i = digits.length-1; i >=0; i--) {
			res+=digits[i]*t;
			t*=10;
		}
			

		return res;
	}

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int test = kb.nextInt();
		int cur, number;

		for (int i = 1; i <= test; i++) {
			number = kb.nextInt();
			int[] digits = Stream.of(String.valueOf(number).split("")).mapToInt(Integer::parseInt).toArray();

			change = kb.nextInt();

			max = cur = cha(digits);
			dfs(digits, 0);
			System.out.println("#" + i + " " + max);
		} 

	}

}
