import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4008_2 {
	static int n, max, min;
	static int[] card, oper;

	public static void dfs(int index, int value) {
		if (index == n) {
			if (min > value)
				min = value;
			if (max < value)
				max = value;
			return;
		}
		if (oper[0] >= 1) {
			oper[0]--;
			dfs(index + 1, value + card[index]);
			oper[0]++;
		}
		if (oper[1] >= 1) {
			oper[1]--;
			dfs(index + 1, value - card[index]);
			oper[1]++;
		}
		if (oper[2] >= 1) {
			oper[2]--;
			dfs(index + 1, value * card[index]);
			oper[2]++;
		}
		if (oper[3] >= 1) {
			oper[3]--;
			dfs(index + 1, value / card[index]);
			oper[3]++;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			card = new int[n];
			oper = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++)
				oper[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				card[i] = Integer.parseInt(st.nextToken());
			dfs(1, card[0]);

			System.out.println("#" + test + " " + (max - min));

		}

		br.close();

	}

}
