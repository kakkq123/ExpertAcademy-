import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;
 
public class _2382 {
    static int n, m, k;
    static int[] dr = { 0, -1, 1, 0, 0 }, dc = { 0, 0, 0, -1, 1 };
    static LinkedList<Microbe> list;
 
    static class Microbe implements Comparable<Microbe> {
        int r, c, dir, count;
 
        public Microbe(int r, int c, int count, int dir) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.dir = dir;
        }
 
        @Override
        public int compareTo(Microbe o) {
            return this.count < o.count ? 1 : -1;
        }
    }
 
    public static void solve() {
        // move
        for (Iterator<Microbe> iter = list.iterator(); iter.hasNext();) {
            Microbe i = iter.next();
            i.r += dr[i.dir];
            i.c += dc[i.dir];
            if (i.r == 0 || i.r == n - 1 || i.c == 0 || i.c == n - 1) {
                i.count /= 2;
                if (i.dir == 1)
                    i.dir = 2;
                else if (i.dir == 2)
                    i.dir = 1;
                else if (i.dir == 3)
                    i.dir = 4;
                else if (i.dir == 4)
                    i.dir = 3;
 
            }
        }
        Collections.sort(list);
        // check
        int[][] cnt = new int[n][n];
        for (Iterator<Microbe> iter = list.iterator(); iter.hasNext();) {
            Microbe i = iter.next();
            if (cnt[i.r][i.c] == 0) {
                cnt[i.r][i.c] = i.count;
            }else {
                cnt[i.r][i.c] += i.count;
                iter.remove();
            }
        }
        //update
        for (Iterator<Microbe> iter = list.iterator(); iter.hasNext();) {
            Microbe i = iter.next();
            i.count = cnt[i.r][i.c];
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            list = new LinkedList<Microbe>();
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                list.add(new Microbe(r, c, count, dir));
            }
            for (int i = 0; i < m; i++) 
                solve();
                 
            int sum = 0;
            for (Iterator<Microbe> iter = list.iterator(); iter.hasNext();) {
                Microbe i = iter.next();
                sum += i.count;
            }
            System.out.println("#" + test + " " + sum);
        }
        br.readLine();
    }
 
}