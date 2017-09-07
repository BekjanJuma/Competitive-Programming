import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Segment implements Comparable<Segment> {

    int a, b, idx;

    public Segment(int a, int b, int idx) {
        this.a = a;
        this.b = b;
        this.idx = idx;
    }

    public boolean isInside(Segment s1) {
        if (s1.a < a && s1.b > b) {
            return true;
        }
        return false;
    }

    public void print() {
        System.out.println(a + " " + b);
    }

    @Override
    public int compareTo(Segment s) {
        return (a - b) - (s.a - s.b);
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Segment> segments;
        int[] dp = new int[20000];
        int[] prev = new int[20000];
        
        int n = sc.nextInt();
        segments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            Segment s = new Segment(a, b, i+1);
            segments.add(s);
        }
        Collections.sort(segments);


        for (int i = 0; i < n; i++) {
            Segment s = segments.get(i);
            for(int j = i-1; j>=0; j--){
                Segment s2 = segments.get(j);
                if(s.isInside(s2) && dp[s.idx] <= dp[s2.idx]){
                    dp[s.idx] = dp[s2.idx] + 1;
                    prev[s.idx] = s2.idx;
                }
            }
        }
        
        int maxIdx = 0;
        for(int i=1;i<=n; i++){
            if(dp[i]>dp[maxIdx]){
                maxIdx = i;
            }
        }
        
        ArrayList<Integer> lst = new ArrayList<>();
        if(maxIdx == 0){
            lst.add(1);
        }
        while(true){
            if(maxIdx == 0){
                break;
            }
            lst.add(maxIdx);
            maxIdx = prev[maxIdx];
        }
        
        System.out.println(lst.size());
        for(int i=0; i<lst.size()-1; i++){
            System.out.print(lst.get(i) + " ");
        }
        System.out.println(lst.get(lst.size()-1));
        
    }
}