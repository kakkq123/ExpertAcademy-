import java.util.*;

//
class Matrix implements Comparable<Matrix> {
	public int row, col, area = 0;

	public Matrix(int row, int col) {
		this.row = row;
		this.col = col;
		area = this.row * col;
	}

	@Override
	public int compareTo(Matrix m) {
		if (this.area < m.area)
			return -1;
		else if (this.area == m.area && this.row < m.row)
			return -1;
		return 1;
	}

}

public class _1258 {
	static int[][] arr;
	static PriorityQueue<Matrix> m;

	//
	public static void goRight(int row, int col, int num) {
		for(int i=col; i<col+num; i++)
			arr[row][i]=0;
	}
	public static void solve(int row, int col, int n) {
		int r=1, c=1;
		arr[row][col]=0; //방문
		int tmp=col;
		
		while(arr[row][col+1]>0 && col+1<=n) {
			c++;
			col++;
			arr[row][col]=0;
		}
		
		while(row+1<=n && arr[row+1][col]>0) {
			r++;
			row++;
			goRight(row, tmp, c);
		}
		
		m.offer(new Matrix(r,c));
	}

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int test = kb.nextInt();

		for (int i = 1; i <= test; i++) {
			int n = kb.nextInt();
			arr = new int[n + 1][n + 1];
			m = new PriorityQueue<Matrix>();

			// 행렬 입력
			for (int row = 1; row <= n; row++)
				for (int col = 1; col <= n; col++)
					arr[row][col] = kb.nextInt();

			for (int row = 1; row <= n; row++)
				for (int col = 1; col <= n; col++)
					if (arr[row][col] > 0)
						solve(row, col, n);

			System.out.printf("#" + i + " "+m.size()+" ");
			while (!m.isEmpty()) {
				Matrix matrix=m.poll();
				System.out.printf("%d %d ",matrix.row, matrix.col);
			}
			System.out.println();
		} //
	}

}
