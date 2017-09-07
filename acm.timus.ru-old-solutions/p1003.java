

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


// 1003. Parity

public class Main {

    public static Map<Integer, Integer> parity;
    public static Map<Integer, Integer> prev;
    public static Map<Integer, Boolean> defined;

    public static boolean add(int a, int b, int c) {
        if (defined.get(b) == null) {
            defined.put(b, Boolean.TRUE);
            prev.put(b, a);
            parity.put(b, c % 2);
            return true;
        }
        int r = prev.get(b);
        if (r == a) {
            return (c == parity.get(b));
        }
        if (r < a) {
            return add(r, a - 1, (c + parity.get(b)) % 2);
        }
        return add(a, r - 1, (c + parity.get(b)) % 2);
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
//        StreamTokenizer in = new StreamTokenizer(new BufferedReader(reader));
        Scanner sc = new Scanner(reader);

        whileLoop:
        while (true) {
            parity = new HashMap<>();
            prev = new HashMap<>();
            defined = new HashMap<>();

            int length = sc.nextInt();
            if (length == -1) {
                break;
            }
            int questions = sc.nextInt();
            int X = 0;
            for (int q = 0; q < questions; q++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                String s = sc.next();
                int c = 0;
                if (s.equals("odd")) {
                    c = 1;
                }
                if (X >= 0) {
                    boolean query = add(a, b, c);
                    if (query == false) {
                        System.out.println(X);
                        X = -10000;
                    } else {
                        X++;
                    }
                }
            }
            if (X >= 0) {
                System.out.println(X);
            }
        }
    }
}