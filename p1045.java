
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public Main(int n) {
        graph = new int[n][n];
        visited = new boolean[n];
        Arrays.fill(visited, false);
    }

    public int[][] graph;
    public boolean[] visited;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
        Scanner sc = new Scanner(reader);

        int n = sc.nextInt();
        int k = sc.nextInt() - 1;

        Main m = new Main(n);

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            m.graph[u][v] = m.graph[v][u] = 1;
        }

        if (m.firstWin(k, k)) {
            System.out.println("First player wins flying to airport " + (prev + 1));
        } else {
            System.out.println("First player loses");
        }

    }

    public static int prev = -100;

    public boolean firstWin(int v, int root) {

        visited[v] = true;
        boolean found = false;
        for (int u = graph.length - 1; u >= 0; u--) {
            if (v != root && graph[v][u] == 1 && !visited[u]) {
                if (!secondWin(u, root)) {
                    found = true;
//                    System.out.println("first wins: " + (v + 1));
                    return true;
                }
            } else if (graph[v][u] == 1 && v == root && !visited[u]) {
                if (!secondWin(u, root)) {
                    found = true;
                    prev = u;
                }
            }
        }
//        if (found) {
//            System.out.println("first wins: " + (v + 1));
//        } else {
//            System.out.println("first loses: " + (v + 1));
//        }

        return found;
    }

    public boolean secondWin(int v, int root) {
        visited[v] = true;
        for (int u = 0; u < graph.length; u++) {
            if (graph[v][u] == 1 && !visited[u]) {
                if (!firstWin(u, root)) {
//                    System.out.println("second wins: " + (v + 1));
                    return true;
                }
            }
        }
//        System.out.println("second loses: " + (v + 1));
        return false;
    }

}