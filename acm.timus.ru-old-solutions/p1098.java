
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        int max_n = 800000;
        int[] jos = new int[max_n];

        jos[0] = 0;
        jos[1] = 0;
        for (int i = 2; i < max_n; i++) {
            jos[i] = (jos[i - 1] + 1999) % i;
        }

        Scanner sc = new Scanner(System.in);
        String all = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            all += line;
        }
        if (all.isEmpty()) {
            System.out.println("No comments");

        } else {
            int pos = jos[all.length()];
            if (all.charAt(pos) == '?') {
                System.out.println("Yes");
            } else if (all.charAt(pos) == ' ') {
                System.out.println("No");
            } else {
                System.out.println("No comments");
            }
        }
    }

}