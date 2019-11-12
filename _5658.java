import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class _5658 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		for (int t = 1; t <= test; t++) {
			TreeSet<Integer> number = new TreeSet<Integer>();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int a = n / 4;
			int[] r = { 0, a, a * 2, a * 3 };
			char[] c = br.readLine().toCharArray();

			for (int i = 0; i < a; i++) {
				for (int j = 0; j < 4; j++) {
					String str = "";
					for (int l = 0; l < a; l++) {
						str += Character.toString(c[(n - i + r[j] + l) % n]);
					}
					int num = Integer.valueOf(str, 16);
					number.add(num);
				}
			}
			int index = -1;
			for (Integer num : number) {
				index++;
				if (index == number.size() - k) {
					System.out.println("#" + t + " " + num);
					break;
				}
			}

		}
		br.close();

	}

}
