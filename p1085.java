
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(reader));
        in.nextToken();
        int N = (int) in.nval;
        in.nextToken();
        int M = (int) in.nval;

        int[][] g = new int[N][N];

        // construct a graph
        for (int i = 0; i < M; i++) {
            in.nextToken();
            int L = (int) in.nval;
            int[] stops = new int[L];
            for (int j = 0; j < L; j++) {
                in.nextToken();
                stops[j] = (int) in.nval - 1;
            }
            for (int ss = 0; ss < stops.length; ss++) {
                for (int k = ss + 1; k < stops.length; k++) {
                    g[stops[ss]][stops[k]] = 4;
                    g[stops[k]][stops[ss]] = 4;
                }
            }
        }
        in.nextToken();
        int K = (int) in.nval;

        int[] money = new int[K];
        int[] stops = new int[K];
        int[] ticket = new int[K];

        for (int i = 0; i < K; i++) {
            in.nextToken();
            money[i] = (int) in.nval;
            in.nextToken();
            stops[i] = (int) in.nval - 1;
            in.nextToken();
            ticket[i] = (int) in.nval;
        }

        // all pair shortest path
        // init
        Float[][] dist = new Float[N][N];
        for (int i = 0; i < dist.length; i++) {
            dist[i][i] = 0f;
            for (int j = i + 1; j < dist.length; j++) {
                dist[i][j] = Float.POSITIVE_INFINITY;
                dist[j][i] = Float.POSITIVE_INFINITY;
                if (g[i][j] > 0) {
                    dist[i][j] = (float) g[i][j];
                }
                if (g[j][i] > 0) {
                    dist[j][i] = (float) g[j][i];
                }
            }
        }

        // floyd
        for (int k = 0; k < dist.length; k++) {
            for (int i = 0; i < dist.length; i++) {
                for (int j = 0; j < dist.length; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int minNode = -1;
        int minSum = 1000000000;
        for (int node = 0; node < N; node++) {
            int tmpSum = 0;
            boolean exist = true;
            for (int j=0; j<stops.length; j++) {
                if(dist[stops[j]][node] < 10000000000f){
                    if(ticket[j]==1){
                        continue;
                    } else if(dist[stops[j]][node]<= money[j]) {
                        tmpSum+=dist[stops[j]][node];
                    } else {
                        exist = false;
                        break;
                    }
                } else {
                    exist = false;
                    break;
                }
            }
            if(exist && minSum>tmpSum){
                minSum = tmpSum;
                minNode = node;
            }
        }
        
        if(minNode>=0){
            System.out.println((minNode+1) + " " + minSum);
        } else {
            System.out.println(0);
        }

    }

}