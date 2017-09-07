

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        ArrayList<Integer> lst = new ArrayList<>();
        int[] sorted;
        PriorityQueue<Integer>[] g;

        int[] degree = new int[8000];
        Arrays.fill(degree, 0);
        
        
        // O(n)
        for(String s : input) {
            int tt = Integer.parseInt(s) - 1;
            lst.add(tt);
            degree[tt]++;
        }

        g = new PriorityQueue[lst.size() + 1];
        sorted = new int[lst.size() + 1];
        // O(n)
        for (int i = 0; i < lst.size() + 1; i++) {
            sorted[i] = i;
            g[i] = new PriorityQueue<>();
        }

        // find initial leaves O(nlogn)
        PriorityQueue<Integer> leaves = new PriorityQueue<>();
        for (Integer y : sorted) {
            if (degree[y] == 0) {
                leaves.add(y);
            }
        }

        // O(nlogn)
        for (int x : lst) {
            degree[x]--;

            int y = leaves.poll();

            g[x].add(y);
            g[y].add(x);
            
            if(degree[x] == 0){
                leaves.add(x);
            }

        }
        
        // O(nlogn)
        for (int i = 0; i < g.length; i++) {
            System.out.print((i + 1) + ":");
            while (!g[i].isEmpty()) {
                System.out.print(" " + (g[i].poll() + 1));
            }
            System.out.println("");
        }

    }
}