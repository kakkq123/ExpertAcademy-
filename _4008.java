import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4008 {
	static int n, max, min, plus, minus, multi, division;
	static int[] card;

	public static void dfs(int index, int value, int plus, int minus, int multi, int division) {
		if (index == n) {
			if (min > value)
				min = value;
			if (max < value)
				max = value;
			return;
		}
		if (plus >= 1)
			dfs(index + 1, value + card[index], plus - 1, minus, multi, division);
		if (minus >= 1)
			dfs(index + 1, value - card[index], plus, minus - 1, multi, division);
		if (multi >= 1)
			dfs(index + 1, value * card[index], plus, minus, multi - 1, division);
		if (division >= 1)
			dfs(index + 1, value / card[index], plus, minus, multi, division - 1);

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			plus = Integer.parseInt(st.nextToken());
			minus = Integer.parseInt(st.nextToken());
			multi = Integer.parseInt(st.nextToken());
			division = Integer.parseInt(st.nextToken());

			card = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				card[i] = Integer.parseInt(st.nextToken());
			dfs(1, card[0], plus, minus, multi, division);

			System.out.println("#" + test + " " + (max - min));

		}

		br.close();

	}

}
