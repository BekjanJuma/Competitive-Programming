
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

// 1077. Travelling Tours
public class Main {

    public int[][] graph;
    public int[][] edges;
    public boolean[] visited;
    public int[] prev;

    ArrayList< ArrayList<Integer>> tours;

    public Main(int N) {
        graph = new int[N][N];
        visited = new boolean[N];
        prev = new int[N];
        Arrays.fill(prev, -1);
        tours = new ArrayList<>();
        edges = new int[N][N];
    }

    private void solve() {
        // for each unvisited vertex dfs
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        // now print each tour
        System.out.println(tours.size());
        for (ArrayList<Integer> tour : tours) {
            System.out.print(tour.size() + " ");
            for (int v : tour) {
                System.out.print((v + 1) + " ");
            }
            System.out.println("");
        }
    }

    // detects cycle and construct routes
    private void dfs(int v) {
//        System.out.println(pre + "dfs:" + v);
        visited[v] = true;
        for (int u = 0; u < graph.length; u++) {
            if (graph[v][u] == 1 && prev[v] != u && edges[v][u] == 0) {
                edges[u][v] = edges[v][u] = 1;
                // cycle
                if (visited[u]) {
                    constructRoute(v, u);
                } else {
                    prev[u] = v;
                    dfs(u);
                }
            }
        }
    }

    private void constructRoute(int u, int v) {
        ArrayList<Integer> tour = new ArrayList<>();
        int tmp = u;
        while (tmp != -1) {
            tour.add(0, tmp);
            tmp = prev[tmp];
        }
        tmp = v;
        while (tmp != -1) {
            tour.add(tmp);
            tmp = prev[tmp];
        }
        int last = -1;
        while (Objects.equals(tour.get(0), tour.get(tour.size() - 1))) {
            last = tour.get(0);
            tour.remove(tour.size() - 1);
            tour.remove(0);
        }
        if (last != -1) {
            tour.add(0, last);
        }
        tours.add(tour);
//        System.out.println("route " + u + " " + v + " constructed: ");
//        for (Integer vert : tour) {
//            System.out.print(vert + " ");
//        }
//        System.out.println("");
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
//        StreamTokenizer in = new StreamTokenizer(new BufferedReader(reader));
        Scanner sc = new Scanner(reader);

        int N = sc.nextInt();
        int M = sc.nextInt();
        Main m = new Main(N);
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            m.graph[u][v] = m.graph[v][u] = 1;
        }
        m.solve();
    }

}