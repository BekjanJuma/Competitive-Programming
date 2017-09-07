import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // row reduced echelon form
    // n by n matrix
    public static int[] solve(int[][] A, int[] b) {
        int n = A.length;

        for (int k = 0; k < n; k++) {
            // find kth pivot
            int i_max = k;
            for (int i = k+1; i < n; i++) {
                if(A[i][k] == 1){
                    i_max = i;
                    break;
                }
            }
            // singular
            if(A[i_max][k] == 0){
                return null;
            }
            // swap
            int[] tmp = A[i_max];
            A[i_max] = A[k];
            A[k] = tmp;
            
            int tt = b[i_max];
            b[i_max] = b[k];
            b[k] = tt;
            
            // for all rows below pivot
            for (int i = k+1; i < n; i++) {
                for (int j = k+1; j < n; j++) {
                    A[i][j] = (A[i][j] + A[k][j] * A[i][k]) % 2;
                }
                b[i] = (b[i] + b[k]*A[i][k]) % 2;
                A[i][k] = 0;
            }
        }
        
        int[] x = new int[n];
        
        // backward substitution
        for (int r = n - 1; r >= 0; r--) {
            int sum = b[r];
            for (int i = r+1; i<n; i++) {
                sum = (sum + A[r][i] * x[i])%2;
            }
            x[r] = sum ;
        }

        return x;
    }

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] A = new int[n][n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(A[i], 0);
        }

        Arrays.fill(b, 1);

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            while (x != -1) {
                A[x - 1][i] = 1;
                x = sc.nextInt();
            }
        }

        int[] x = solve(A, b);
        if (x == null) {
            System.out.println("No solution.");
        } else {
            //check for solution
            
            
            for (int i = 0; i < x.length; i++) {
                if (x[i] % 2 != 0) {
                    System.out.print((i + 1) + " ");
                }
            }
            System.out.println("");
        }
    }
}