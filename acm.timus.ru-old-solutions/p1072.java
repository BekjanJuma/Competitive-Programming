
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 1072. Routing
class Node {

    int id;
    public ArrayList<Long> subnets;
    String ip;
    String mask;

    public Node(int id, String ip, String mask) {
        this.id = id;
        this.ip = ip;
        this.mask = mask;
        subnets = new ArrayList<>();
    }

    public Node(int id) {
        this.id = id;
        subnets = new ArrayList<>();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

}

public class Main {

    public static int[][] g;

    public static int[] bfs(int source, int dest) {
        int[] prev = new int[g.length];
        ArrayList<Integer> q = new ArrayList<>();
        boolean[] vis = new boolean[g.length];
        Arrays.fill(vis, false);
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.remove(0);
            vis[u] = true;
            for (int v = 0; v < g.length; v++) {
                if (g[u][v] == 1 && !vis[v]) {
                    prev[v] = u;
                    if (v == dest) {
                        return prev;
                    } else  {
                        q.add(v);
                    }
                }
            }
        }

        return null;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
//        StreamTokenizer in = new StreamTokenizer(new BufferedReader(reader));
        Scanner sc = new Scanner(reader);

        int n = sc.nextInt();
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Node node = new Node(i);
            int k = sc.nextInt();
            for (int j = 0; j < k; j++) {
                String sip = sc.next();
                String smask = sc.next();
                long ip = changeToBits(sip);
                long mask = changeToBits(smask);
                long subnet = ip & mask;
                node.subnets.add(subnet);
                node.setIp(sip);
                node.setMask(smask);
            }
            nodes.add(node);
        }

        int source = sc.nextInt();
        int dest = sc.nextInt();

        g = new int[n][n];

        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                if (connected(node, nodes.get(j))) {
                    g[i][j] = g[j][i] = 1;
                }
            }
        }

        int[] prev = bfs(source - 1, dest - 1);
        ArrayList<Integer> out = new ArrayList<>();
        if (prev != null) {
            System.out.println("Yes");
            int tmp = dest - 1;
            while (tmp != (source - 1)) {
                out.add(0,tmp+1);
                tmp = prev[tmp];
            }
            out.add(0,source);
            for(int e : out){
                System.out.print(e + " ");
            }
            System.out.println("");
        } else {
            System.out.println("No");
        }

    }

    private static long changeToBits(String sip) {
        // Parse IP parts into an int array
        long[] ip = new long[4];
        String[] parts = sip.split("\\.");

        for (int i = 0; i < 4; i++) {
            ip[i] = Long.parseLong(parts[i]);
        }

        // Add the above IP parts into an int number representing your IP 
        // in a 32-bit binary form
        long ipNumbers = 0;
        for (int i = 0; i < 4; i++) {
            ipNumbers += ip[i] << (24 - (8 * i));
        }
        return ipNumbers;
    }

    private static boolean connected(Node u, Node v) {
        for (Long subnet : u.subnets) {
            if (v.subnets.contains(subnet)) {
                return true;
            }
        }
        return false;
    }

}