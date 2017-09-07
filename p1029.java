
import java.util.ArrayList;
import java.util.Scanner;

class Node {

    public int f, r;
    public int cost;
    
    public Node(int f, int r, int cost) {
        this.f = f;
        this.r = r;
        this.cost = cost;
    }
}

public class Main {
    
    public static void main(String[] args) {
        int MAX_F = 101;
        int MAX_R = 501;
        int[][] val;
        int[][] cost;
        
        
        Scanner sc = new Scanner(System.in);
        int floors = sc.nextInt();
        int rooms = sc.nextInt();
        val = new int[floors][rooms];
        cost = new int[floors][rooms];
        
        for (int f = 0; f < floors; f++) {
            for (int r = 0; r < rooms; r++) {
                val[f][r] = sc.nextInt();
                cost[f][r] = Integer.MAX_VALUE;
            }
        }

        // assign cost of the  1st floor to the val
        System.arraycopy(val[0], 0, cost[0], 0, rooms);

        // Dijkstra for each floor
        
        for (int f = 1; f < floors; f++) {
            // at each floor

            // set initial values from floor below
            for (int r = 0; r < rooms; r++) {
                cost[f][r] = cost[f - 1][r] + val[f][r];
            }


            // set minimum set
            ArrayList<Node> minimumSet = new ArrayList<>();
            for (int r = 0; r < rooms; r++) {
                minimumSet.add(new Node(f, r, cost[f][r]));
            }
            
            while (minimumSet.isEmpty() == false) {
                Node n = getMin(minimumSet);
                if (n.r > 0) {
                    int leftCost = val[n.f][n.r - 1] + cost[n.f][n.r];
                    if (cost[n.f][n.r - 1] > leftCost) {
                        cost[n.f][n.r - 1] = leftCost;
                        update(minimumSet, n.f, n.r - 1, leftCost);
                    }
                }
                if (n.r < (rooms - 1)) {
                    int rightCost = val[n.f][n.r + 1] + cost[n.f][n.r];
                    if (cost[n.f][n.r + 1] > rightCost) {
                        cost[n.f][n.r + 1] = rightCost;
                        update(minimumSet, n.f, n.r + 1, rightCost);
                    }
                }
                minimumSet.remove(n);
            }
            
        }
        // print the min path
        ArrayList<Node> lst = new ArrayList<>();
        lst.add(new Node(floors - 1, 0, cost[floors - 1][0]));
        for (int r = 0; r < rooms; r++) {
            if (cost[floors - 1][r] < lst.get(0).cost) {
                lst.get(0).r = r;
                lst.get(0).cost = cost[floors - 1][r];
            }
        }
        while (true) {
            Node n = lst.get(0);
            if (n.f == 0) {
                break;
            }
            Node tmp = new Node(n.f-1, n.r, cost[n.f-1][n.r]);
            if (n.r > 0) {
                if (cost[n.f][n.r - 1] < tmp.cost) {
                    tmp = new Node(n.f, n.r - 1, cost[n.f][n.r-1]);
                }
            }
            if (n.r < (rooms - 1)) {
                if (cost[n.f][n.r + 1] < tmp.cost) {
                    tmp = new Node(n.f, n.r + 1, cost[n.f][n.r+1]);
                }
            }
            lst.add(0, tmp);
        }
        /*
        // print cost
        for(int[] f : cost ){
            for(int r : f){
                System.out.print(r + " ");
            }
            System.out.println();
        }
        */
        for(Node n : lst){
            System.out.print( (n.r+1) + " ");
        }
        System.out.println();
    }
    
    public static void update(ArrayList<Node> lst, int f, int r, int cost) {
        for (Node n : lst) {
            if (n.f == f && n.r == r) {
                n.cost = cost;
                break;
            }
        }
    }
    
    public static Node getMin(ArrayList<Node> lst) {
        Node min = lst.get(0);
        
        for (Node n : lst) {
            if (n.cost < min.cost) {
                min = n;
            }
        }
        return min;
    }
}