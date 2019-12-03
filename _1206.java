import java.util.Scanner;
 
public class _1206 {
 
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int h,a,b;
        int[] ap=new int[1001];
        int[] count=new int[11];
        for (int i = 1; i <= 10; i++) {
            h=kb.nextInt();
            for(int j=1; j<=h; j++) {
                ap[j]=kb.nextInt();
            }
            count[i]=0;
            for(int j=3; j<=h-2; j++) {
                if(ap[j-2]<ap[j]&&ap[j-1]<ap[j] &&ap[j]>ap[j+1]&&ap[j]>ap[j+2]) {
                    a=Math.min(ap[j]-ap[j-2], ap[j]-ap[j-1]);
                    b=Math.min(ap[j]-ap[j+2], ap[j]-ap[j+1]);
                    count[i]+=Math.min(a, b);
                }
            }
        }
 
        // print
        for (int i = 1; i <= 10; i++)
            System.out.println("#" + i + " "+count[i]);
 
    }
 
}
