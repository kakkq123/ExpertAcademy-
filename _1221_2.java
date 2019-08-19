import java.util.Scanner;

public class _1221_2 {

	static String[] number = { "ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN" };

	public enum Number {
		ZRO, ONE, TWO, THR, FOR, FIV, SIX, SVN, EGT, NIN
	}

	public static void main(String[] args) {
		int c, len;
		String input;
		Scanner kb = new Scanner(System.in);
		int test = kb.nextInt();
		for (int i = 1; i <= test; i++) {
			String str = kb.next();
			c = Integer.parseInt(str.substring(1));
			len = kb.nextInt();
			kb.nextLine();
			int[] index = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			for (int j = 1; j <= len; j++) {
				Number n = Number.valueOf(kb.next());
				index[n.ordinal()]++;
			}
			System.out.println("#" + i);
			for (int a = 0; a <= 9; a++)
				for (int b = 0; b < index[a]; b++)
					System.out.printf("%s ", number[a]);

		}
	}

}
