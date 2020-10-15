import java.io.*;
import java.util.*;
import java.awt.Point;
 
public class Solution {
    static int N, M, K, A, B, answer;
    static Window[] _a, _b;
 
    static class Waiting {
        //wt:웨이팅타임, wn:창구번호, n:대기 번호
        int wt, wn, n;
 
        public Waiting(int wt, int wn, int n) {
            this.wt = wt;
            this.wn = wn;
            this.n = n;
        }
 
    }
 
    static class Window {
        //n: 대기번호, ut:사용시간, time: 작업시간
        int n, ut, time;
        boolean flag;
 
        public Window(int time) {
            this.time = time;
            this.flag = true;
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
 
            _a = new Window[N + 1];
            _b = new Window[M + 1];
            //접수 창구
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int t = Integer.parseInt(st.nextToken());
                _a[i] = new Window(t);
            }
            //장비창구
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                int t = Integer.parseInt(st.nextToken());
                _b[i] = new Window(t);
            }
            //손님들 번호(x)와 도착시간(y)을 저장
            Queue<Point> guest = new LinkedList<Point>();
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                int t = Integer.parseInt(st.nextToken());
                guest.add(new Point(i, t));
            }
            Queue<Waiting> w = new LinkedList<Waiting>(); // 어차피 먼저 도착한 순으로, 접수 창구 번호가 작은순으로 들어오기 때문에 우선순위큐 사용 안 함
 
            int time = 0;
            answer = 0;
            while (K > 0) {
                // 접수 창구
                for (int i = 1; i <= N; i++) {
                    //사용중인 접수 창구가 있다면
                    if (_a[i].flag == false) {
                        //이용시간 추가
                        _a[i].ut++;
                        //창구에서 일을 다 해결했다면
                        if (_a[i].ut == _a[i].time) {
                            //이 창구는 사용 가능함
                            _a[i].flag = true;
                            //정비 창구를 기다리는 웨이팅 큐에 넣어줌
                            w.add(new Waiting(time, i, _a[i].n));
                        }
                    }
                }
                // 정비 창구
                for (int i = 1; i <= M; i++) {
                    //사용하는 정비 창구가 있다면
                    if (_b[i].flag == false) {
                        //사용시간 추가
                        _b[i].ut++;
                        //창구에서 일을 다 해결했다면
                        if (_b[i].ut == _b[i].time) {
                            //이 창구는 사용 가능해짐
                            _b[i].flag = true;
                            K--;
                        }
                    }
                }
 
                // 웨이팅 > 정비 창구
                //웨이팅하는 사람이 있다면
                while (!w.isEmpty()) {
                    //정비 창구 사용 가능하다면 넣어줌
                    if (use_end(w.peek().wn, w.peek().n))
                        w.poll();
                    //사용할 창구가 없으면 웨이팅 계속해~
                    else
                        break;
                }
                // 도착
                //도착 시간이 지나도 접수 창구에 가지 못한 손님이 있다면?
                while (!guest.isEmpty() && guest.peek().y <= time) {
                    //접수 창구가 비어있다면 거기로 안내
                    if (use_front(guest.peek().x))
                        guest.poll();
                    //접수 창구 이용 못하면 계속 큐에서 대기
                    else
                        break;
                }
                time++;
 
            }
            answer = answer == 0 ? -1 : answer;
            bw.write("#" + test + " " + answer + "\n");
 
        }
        br.close();
        bw.close();
    }
 
    private static boolean use_end(int wn, int x) {
        for (int i = 1; i <= M; i++)
            if (_b[i].flag) {
                _b[i].n = x;
                _b[i].ut = 0;
                _b[i].flag = false;
                //같은 접수 창구, 정비 창구를 사용하는 사람인가?
                if (A == wn && B == i)
                    answer += x;
                return true;
            }
        return false;
    }
 
    private static boolean use_front(int x) {
        for (int i = 1; i <= N; i++)
            if (_a[i].flag) {
                _a[i].n = x;
                _a[i].ut = 0;
                _a[i].flag = false;
                return true;
            }
        return false;
    }
 
}
