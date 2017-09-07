import java.util.Scanner;

public class Main {

    static long INF = Integer.MAX_VALUE/2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // init and reading input
        long l1 = sc.nextLong();
        long l2 = sc.nextLong();
        long l3 = sc.nextLong();
        long c1 = sc.nextLong();
        long c2 = sc.nextLong();
        long c3 = sc.nextLong();

        int n = sc.nextInt();
        long[] w = new long[n + 1];
        long[] dp = new long[n + 1];

        int a = sc.nextInt();
        int b = sc.nextInt();

        for (int i = 2; i <= n; i++) {
            w[i] = sc.nextLong();
        }

        // algo
        // use dynamic programming
        for (int i = 2; i <= n; i++) {
            dp[i] = INF;
        }
        // swap if a>b
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }
        int i1 = a;
        int i2 = a;
        int i3 = a;
        dp[a] = 0;
        for (int i = a+1; i <= b; i++) {
            while ((w[i] - w[i1]) > l1) {
                i1++;
            }
            while ((w[i] - w[i2]) > l2) {
                i2++;
            }
            while ((w[i] - w[i3]) > l3) {
                i3++;
            }

            if (i1 < i) {
                dp[i] = min(dp[i1] + c1, dp[i2] + c2, dp[i3] + c3);
            } else if (i2 < i) {
                dp[i] = min(dp[i2] + c2, dp[i3] + c3);
            } else {
                dp[i] = dp[i3] + c3;
            }
            
        }
        System.out.println(dp[b]);
    }

    private static long min(long a, long b, long c) {
        return min(min(a, b), c);
    }

    private static long min(long a, long b) {
        return Math.min(a, b);
    }
}