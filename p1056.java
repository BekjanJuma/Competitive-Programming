
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

//  1056. Computer Net
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
//        StreamTokenizer in = new StreamTokenizer(new BufferedReader(reader));
        Scanner sc = new Scanner(reader);

        int N = sc.nextInt();
        int[] degree = new int[N];
        int[] parent = new int[N];
        parent[0] = 0;
        for (int cid = 1; cid < N; cid++) {
            int pid = sc.nextInt();
            parent[cid] = pid - 1;
            degree[pid - 1]++;
        }
        boolean[] del = new boolean[N];
        Arrays.fill(del, false);
        int numElem = N;
        while (numElem > 2) {
            for (int j = 0; j < N; j++) {
                if (degree[j] == 1) {
                    if (parent[j] == j || del[parent[j]]) {
                        degree[j] = 0;
                    }
                }
            }

            for (int j = 0; j < N; j++) {
                if (degree[j] == 0 && (!del[j])) {
                    del[j] = true;
                    numElem--;
                    degree[parent[j]]--;
                }
            }
        }
        
        for (int i = 0; i < N; i++) {
            if(!del[i]){
                System.out.print((i+1) + " ");
            }
        }
        System.out.println("");
    }

}