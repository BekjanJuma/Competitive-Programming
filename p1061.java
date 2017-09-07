
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
        BufferedReader br = new BufferedReader(reader);
//        Scanner sc = new Scanner(reader);
        StringBuilder s = new StringBuilder("");
        String line = br.readLine();
        int N = Integer.parseInt(line.substring(0, line.indexOf(" ")));
        int K = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.length()));
        while (br.ready()) {
            s.append(br.readLine());
        }

        int L = -1;
        long minSum = 9000001L;
        long tmpSum = 0L;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                tmpSum += 9000002L;
            } else {
                tmpSum += (long) s.charAt(i);
            }
            if (i - K + 1 >= 0) {
                if (tmpSum < minSum) {
                    minSum = tmpSum;
                    L = i - K + 1;
                }
                if (s.charAt(i - K + 1) == '*') {
                    tmpSum -= 9000002L;
                } else {
                    tmpSum -= s.charAt(i - K + 1);
                }
            }
        }

        if (L >= 0) {
            System.out.println(L + 1);
        } else {
            System.out.println(0);
        }

    }

}