import java.util.Scanner;
import java.util.Vector;


public class Buttons {

	
	public static void main(String[] args) {

		int N = 10000 + 1;
		int L = 0, K = 0;
		// first generate all primes <= N
		Vector<Integer> primes = new Vector<Integer>();
		primes.add(2);
		for(int i=3; i<N; i++){
			boolean newPrime = true;
			for(int j=0; j<primes.size(); j++){
				if(i%primes.elementAt(j) == 0)
				{
					newPrime = false;
					break;
				}					
			}
			if(newPrime)
				primes.add(i);
		}
//		for(int i=0; i<primes.size(); i++) System.out.println(primes.elementAt(i));
		// read input
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		
		// check if K is prime
		for(int i=1; i<primes.size(); i++){
			if(K == primes.elementAt(i)){
				L = primes.elementAt(i) - 1;
				break;
			}
		}
		// if it is not prime, find first number greater than 2 that divides K
		if(L == 0){
			for(int i=3; i<100000001; i++){
				if(K%i == 0){
					L = i-1;
					break;
				}
			}
		}
		System.out.println(L);
	}

}