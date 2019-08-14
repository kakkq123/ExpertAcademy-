import java.util.Arrays;
import java.util.Scanner;

public class _1208 {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int[] box = new int[100];
		int d;

		for (int i = 1; i <= 10; i++) {
			d = kb.nextInt();

			for (int j = 0; j < 100; j++)
				box[j] = kb.nextInt();
			
			Arrays.sort(box);
			for (int j =1; j <= d; j++) {
				if(box[box.length-1]-box[0]<=1) 
					break;
				
				box[box.length-1]--;
				box[0]++;
				Arrays.sort(box);
				
			}
			System.out.println("#" + i + " " + (box[box.length-1]-box[0]));
		} // test

	}
}
