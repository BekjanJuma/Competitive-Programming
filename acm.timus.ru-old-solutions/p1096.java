
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

// 1096. Get the Right Route Plate!
public class Main {

    public static int[][] g;

    public static int bfs(int s, int d, int[] prev) {
        boolean[] visited = new boolean[g.length];
        boolean[] added = new boolean[g.length];
        int[] dist = new int[g.length];
        Arrays.fill(visited, false);
        Arrays.fill(added, false);
        Arrays.fill(dist, 10000);
        dist[s] = 0;
        LinkedList<Integer> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            int v = q.removeFirst();
            visited[v] = true;
            for (int u = 0; u < g.length; u++) {
                if (g[v][u] >= 1 && !visited[u]) {
                    if (dist[u] > dist[v] + g[v][u]) {
                        dist[u] = dist[v] + 1;
                        prev[u] = v;
                    }
                    if (u == d) {
                        return dist[u];
                    } else if (!added[u]) {
                        q.addLast(u);
                        added[u] = true;
                    }
                }
            }
        }

        return 10000;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
//        StreamTokenizer in = new StreamTokenizer(new BufferedReader(reader));
        Scanner sc = new Scanner(reader);

        int K = sc.nextInt();
        g = new int[2001][2001];
        for (int i = 0; i < K; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g[u][v] = i + 1;
        }

        int dest = sc.nextInt();
        int s1 = sc.nextInt();
        int s2 = sc.nextInt();
        g[s1][s2] = g[s2][s1] = K + 1;

        int[] prev1 = new int[g.length];
        int[] prev2 = new int[g.length];

        int dist1 = bfs(s1, dest, prev1);
        int dist2 = bfs(s2, dest, prev2);

        if (dist1 >= 10000 && dist2 >= 10000) {
            System.out.println("IMPOSSIBLE");
        } else {

            ArrayList<Integer> path1 = new ArrayList<>();
            ArrayList<Integer> path2 = new ArrayList<>();
            int tmp = dest;
            while (tmp != s1) {
                path1.add(0,tmp);
                tmp = prev1[tmp];
            }
            path1.add(0,tmp);
            tmp = dest;
            while (tmp != s2) {
                path2.add(0,tmp);
                tmp = prev2[tmp];
            }
            path2.add(0,tmp);

            if (dist1 < dist2) {
                System.out.println(dist1);
                for (int i = 0; i < path1.size() - 1; i++) {
                    System.out.println(g[path1.get(i)][path1.get(i + 1)]);
                }
            } else {
                System.out.println(dist2);
                for (int i = 0; i < path2.size() - 1; i++) {
                    System.out.println(g[path2.get(i)][path2.get(i + 1)]);
                }
            }
        }

    }

}