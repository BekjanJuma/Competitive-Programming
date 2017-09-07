import java.util.Scanner;

public class Main {

    public static String rakamlar(int N) {
        if (N < 10) {
            return "" + N;
        } else {
            if (N % 9 == 0) {
                return rakamlar(N / 9) + 9;
            } else if (N % 8 == 0) {
                return rakamlar(N / 8) + 8;
            } else if (N % 7 == 0) {
                return rakamlar(N / 7) + 7;
            } else if (N % 6 == 0) {
                return rakamlar(N / 6) + 6;
            } else if (N % 5 == 0) {
                return rakamlar(N / 5) + 5;
            } else if (N % 4 == 0) {
                return rakamlar(N / 4) + 4;
            } else if (N % 3 == 0) {
                return rakamlar(N / 3) + 3;
            } else if (N % 2 == 0) {
                return rakamlar(N / 2) + 2;
            } else {
                return "a";
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if (N == 0) {
            System.out.println(10);
        } else {
            String cevap = rakamlar(N);
            if(cevap.startsWith("a")){
                System.out.println("-1");
            } else {
                System.out.println(cevap);
            }
        }

    }

}