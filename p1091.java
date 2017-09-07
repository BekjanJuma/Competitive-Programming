
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23};
        
        long[][] C = new long[51][51];
        for(int i=0; i<C.length; i++){
            C[i][i] = 1;
            C[i][1] = i;
        }
        for(int i=0; i<C.length; i++){
            C[0][i] = 0;
            C[i][0] = 1;
        }
        for (int i = 1; i < C.length; i++) {
            for (int j = 1; j < C[i].length; j++) {
                C[i][j] = C[i-1][j-1] + C[i-1][j];
            }
        }
//        for (int i = 0; i < C.length; i++) {
//            for (int j = 0; j <= i; j++) {
//                System.out.println("C["  + i + "][" + j + "]: " + C[i][j]);
//            }
//        }
//        
        
        
        
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int S = sc.nextInt();
        
        int sum = 0;
        for(int i=0; i<primes.length; i++){
            sum += C[S/primes[i]][K];
        }
        
        for(int i=0; i<primes.length; i++){
            for (int j = i+1; j < primes.length; j++) {
                sum -= C[S/(primes[i]*primes[j])][K];
            }
        }
        
        System.out.println(sum>10000?10000:sum);
        
    }

}