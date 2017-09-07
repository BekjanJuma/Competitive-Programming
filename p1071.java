

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Object[] toBase(int X, int b) {
        ArrayList<Integer> bits = new ArrayList<>();
        while (X > 0) {
            bits.add(X % b);
            X /= b;
        }
        return bits.toArray();
    }

    public static void print(Object[] lst) {
        for (int i = lst.length - 1; i >= 0; i--) {
            System.out.print(lst[i] + " ");
        }
        System.out.println("");
    }

    public static boolean check(Object[] sub, Object[] sup) {
        int pos = 0;

        for (int i = 0; i < sup.length; i++) {
            if (sup[i].equals( sub[pos])) {
                pos++;
            }
            if (pos == sub.length) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int Y = sc.nextInt();
        boolean solution = false;
        for (int i = 2; i <= X; i++) {
            Object[] sup = toBase(X, i);
            Object[] sub = toBase(Y, i);
            if(check(sub, sup)){
                System.out.println(i);
                solution = true;
                break;
            }
        }
        if(solution == false){
            System.out.println("No solution");
        }
    }
}