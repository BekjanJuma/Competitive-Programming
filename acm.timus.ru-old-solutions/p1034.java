
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Queen {

    public int x, y;

    public Queen(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "|" + x + ", " + y + "|";
    }

    public boolean hit(Queen q) {
        if (q.x == x || q.y == y || ((q.x - x) == (q.y - y)) || ((q.x - x) == (y - q.y))) {
            return true;
        }
        return false;
    }
}

public class Main {

    /**
     * @param args the command line arguments
     */
    static boolean check(Queen[] queens) {
//        System.out.print("checked: ");
//        print(queens);
        for (int i = 0; i < queens.length; i++) {
            Queen q = queens[i];
            for (int j = 0; j < queens.length; j++) {
                if (queens[j] != q && queens[j].hit(q)) {
                    return true;
                }
            }
        }
        return false;
    }

    static void print(Queen[] s) {
        for (int i = s.length - 1; i >= 0; i--) {
            System.out.print(s[i]);
        }
        System.out.println();
    }

    static void print(int[] s) {
        for (int i = s.length - 1; i >= 0; i--) {
            System.out.print(s[i]);
        }
        System.out.println();
    }

    static boolean tryClock(Queen[] qs, int[] pos) {
//        System.out.print("before: ");
//        print(qs);
        int a0 = qs[pos[0]].y;
        int a1 = qs[pos[1]].y;
        int a2 = qs[pos[2]].y;
        qs[pos[0]].y = a1;
        qs[pos[1]].y = a2;
        qs[pos[2]].y = a0;
        boolean res = !check(qs);

        qs[pos[0]].y = a0;
        qs[pos[1]].y = a1;
        qs[pos[2]].y = a2;

        return res;
    }

    static boolean tryCClock(Queen[] qs, int[] pos) {
//        System.out.print("before: ");
//        print(qs);
        int a0 = qs[pos[0]].y;
        int a1 = qs[pos[1]].y;
        int a2 = qs[pos[2]].y;
        qs[pos[0]].y = a2;
        qs[pos[1]].y = a0;
        qs[pos[2]].y = a1;
        boolean res = !check(qs);

        qs[pos[0]].y = a0;
        qs[pos[1]].y = a1;
        qs[pos[2]].y = a2;

        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queen[] queens = new Queen[n];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            queens[i] = new Queen(a, b);
//            System.out.println(queens[i]);
        }
////        queens[0] = new Queen(1, 4);
////        queens[1] = new Queen(2, 3);
////        queens[2] = new Queen(3, 4);
////        queens[3] = new Queen(4, 2);
//        
        Arrays.sort(queens, new Comparator<Queen>() {
            @Override
            public int compare(Queen o1, Queen o2) {
                return o1.x - o2.x;
            }
        });

        // select 3 out of n

        int k = n - 1;
        int[] start = new int[3];
        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int r = j + 1; r < n; r++) {
                    start[0] = i;
                    start[1] = j;
                    start[2] = r;
                    if (tryClock(queens, start)) {
                        c++;
                    }
                    if (tryCClock(queens, start)) {
                        c++;
                    }
                }
            }
        }
        System.out.println(c);



//        for (int i = 0; i < n; i++) {
//            start[i] = 0;
//            queens[i] = new Queen(i+1, 1);
//        }
//        int k = n-1;
//        outer_loop:
//        while (true) {
//            if( !check(queens) ){
//                print(queens);
//            }
//            int c1 = 0;
//            while (start[c1] == k) {
//                start[c1] = 0;
//                queens[c1].y = 1;
//                if (c1 == (n - 1)) {
//                    break outer_loop;
//                }
//                c1++;
//            }
//            start[c1]++;
//            queens[c1].y++;
//        }
    }
}