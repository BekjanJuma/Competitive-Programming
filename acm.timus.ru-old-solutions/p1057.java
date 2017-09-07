
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // combinations
    public static long[][] C;

    // generates n'th number in base b containing only k 1's
    // 000111:1-001011:2-001101:3-001110:4-010011:5-...-111000:x
    // 0011,0101,0110,1001,1010,1100
    // 000111
    // 001011 001101 001110
    // 010011 010101 010110 011001 011010 011100
    // 100011 100101 100110 101001 101010 101100 ... 
    // 0011
    // 0101 0110
    // 1001 1010 1100
    // 001 010 100
//    3,2 p/1 3
//    4,2 p/0 3
//    3,2 p/0 3
//    2,2 p/1 2
//    1,1 p/1 1
//    0,0 p/0 1
    public static long gen(int nn, int kk, int b) {

        if (nn > C[32][kk]) {
            return 0;
        }

        int[] A = new int[33];
        Arrays.fill(A, 0);

        long n = nn;
        int k = kk;
        for (int bit = 32; bit >= 0; bit--) {
            if (C[bit][k] < n) {
                A[bit] = 1;
                n = n - C[bit][k];
                k--;
            }
        }

        long sum = 0;
        long prod = 1;
        for (int bit = 0; bit <= 32; bit++) {
            if (A[bit] == 1) {
                if (prod >= 2147483648L) {
                    sum = 2147483648L;
                    break;
                }
                sum += prod;
            }
            prod *= b;
            if (prod > 2147483648L) {
                prod = 2147483648L;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int Y = sc.nextInt();
        int K = sc.nextInt();
        int B = sc.nextInt();

        C = new long[50][50];

        for (int i = 0; i < C.length; i++) {
            C[i][i] = 1;
            C[i][1] = i;
        }
        for (int i = 0; i < C.length; i++) {
            C[0][i] = 0;
            C[i][0] = 1;
        }
        for (int i = 1; i < C.length; i++) {
            for (int j = 1; j < C[i].length; j++) {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }

        int genMin = 1;
        int genMax = (int) C[32][K]-1;
        int x = 0;
        int y = 0;
        // binary search [genMax, genMin]
        // first x that is >= than X
        int low = genMin;
        int high = genMax;
        while (high > (low + 1)) {
            int mid = low + (high - low) / 2;
            long vmid = gen(mid, K, B);
//            System.out.println("high:" + high + " mid:" + mid + " low:" + low);
//            System.out.println("X:" + X + " vmid:" + vmid);
            if (vmid > X) {
                high = mid;
            } else if (vmid < X) {
                low = mid;
            } else {
                x = mid;
                break;
            }
        }
        // X = 505
        // high 6, low 4, mid 5, vmid 500
        // high 6, low 5, mid 5, vmid 500
        if (high <= (low + 1)) {
            if (gen(high, K, B) >= X && gen(low, K, B) < X) {
                x = high;
            } else {
                x = low;
            }
        }

        // first y that is <= than Y
        low = genMin;
        high = genMax;
        while (high > (low + 1)) {
            int mid = low + (high - low) / 2;
            long vmid = gen(mid, K, B);
//            System.out.println("high:" + high + " mid:" + mid + " low:" + low);
//            System.out.println("Y:" + Y + " vmid:" + vmid);
            if (vmid > Y) {
                high = mid;
            } else if (vmid < Y) {
                low = mid;
            } else {
                y = mid;
                break;
            }
        }
        if (high <= (low + 1)) {
            if (gen(high, K, B) > Y && gen(low, K, B) <= Y) {
                y = low;
            }
        }

//        System.out.println("gen:" + gen(genMin, K, B));
//        System.out.println("x:" + x);
//        System.out.println("y:" + y);

        if (x * y == 0 || y < x) {
            System.out.println(0);
        } else {
            System.out.println(y - x + 1);
        }

    }

}