import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

class Pair {

    int[] dist;
    int[] prev;

    public Pair(int[] dist, int[] prev) {
        this.dist = dist;
        this.prev = prev;
    }
}

public class Main {

    /**
     * @param g graph[n][n]
     * @param s source
     * @return shortest path to all vertices
     */
    public static Pair dijsktra(int[][] g, int s) {
        int n = g.length;
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];
        final int maxInt = 1000000000;
        Arrays.fill(dist, maxInt);
        Arrays.fill(visited, false);

        dist[s] = 0;

        for (int i = 0; i < n; i++) {
            int u = minIndex(dist, visited);
            visited[u] = true;
            for (int v = 0; v < n; v++) {
                if (!visited[v] && g[u][v] > 0) {
                    int d = dist[u] + g[u][v];
                    if (d < dist[v]) {
                        dist[v] = d;
                        prev[v] = u;
                    }
                }
            }
        }

        return new Pair(dist, prev);
    }

    private static int minIndex(int[] dist, boolean[] visited) {
        int minCost = 1000000001;
        int minInd = 0;
        for (int u = 0; u < dist.length; u++) {
            if (visited[u] == false && minCost > dist[u]) {
                minInd = u;
                minCost = dist[u];
            }
        }
        return minInd;
    }

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int numberOfVertices = sc.nextInt();

            if (numberOfVertices == -1) {
                break;
            }

            int numberOfEdges = sc.nextInt();

            int[][] graph = new int[numberOfVertices][numberOfVertices];

            for (int i = 0; i < numberOfEdges; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();

                if (graph[u - 1][v - 1] == 0 || graph[u - 1][v - 1] > w) {
                    graph[u - 1][v - 1] = w;
                    graph[v - 1][u - 1] = w;
                }
            }

            // now for the actual algorithm
            int[][] dist = new int[numberOfVertices][numberOfVertices];
            for (int i = 0; i < numberOfVertices; i++) {
                for (int j = 0; j < numberOfVertices; j++) {
                    if (graph[i][j] > 0) {
                        dist[i][j] = graph[i][j];
                    } else {
                        dist[i][j] = 1000000000;
                    }
                }
            }

            int minU = 0, minV = 0, minK = 0, minCost = 1000000000;

            for (int k = 0; k < numberOfVertices; k++) {
                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < i; j++) {
                        if (graph[j][k] > 0 && graph[k][i] > 0) {
                            int tmpCost = dist[i][j] + graph[j][k] + graph[k][i];
                            if (tmpCost < minCost) {
                                minCost = tmpCost;
                                minU = i;
                                minV = j;
                                minK = k;
                            }
                        }
                    }
                }
                for (int i = 0; i < numberOfVertices; i++) {
                    for (int j = 0; j < i; j++) {
                        int temp = dist[i][k] + dist[k][j];
                        if (temp < dist[i][j]) {
                            dist[i][j] = dist[j][i] = temp;
                        }
                    }
                }
            }

            if (minCost < 1000000000) {
                int[] prev = null;
                graph[minK][minU] = 0;
                graph[minK][minV] = 0;
                graph[minU][minK] = 0;
                graph[minV][minK] = 0;
                Pair p = dijsktra(graph, minU);
                prev = p.prev;
                int tmp = minV;
                System.out.print((minK+1) + " ");
                while (prev != null && tmp != minU) {
                    System.out.print((tmp + 1) + " ");
                    tmp = prev[tmp];
                }
                System.out.println(minU + 1);
            } else {
                System.out.println("No solution.");
            }

//            for (int u = 0; u < numberOfVertices; u++) {
//                for (int v = u + 1; v < numberOfVertices; v++) {
//                    if (graph[u][v] > 0) {
//                        int e = graph[u][v];
//                        graph[u][v] = 0;
//                        Pair pair = dijsktra(graph, u);
//                        int[] dist = pair.dist;
//                        graph[u][v] = e;
//                        if (dist[v] < 1000000000) {
//                            int cost = e + dist[v];
//                            if(cost<minCost){
//                                minCost = cost;
//                                minU = u;
//                                minV = v;
//                                prev = pair.prev;
//                            }
//                        }
//                    }
//                }
//            }
        }

    }

}