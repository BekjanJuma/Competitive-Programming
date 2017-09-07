import java.util.Scanner;

class Graph{
    int[][] connections; // possible values: 1 --> connected, 0 --> not connected
    int[] node; // possible values are: -1 --> grey, 0 --> red, 1 --> blue
    int MAX_N = 100;
    int N;
    public Graph(int N){
        this.N = N;
        connections = new int[MAX_N][MAX_N];
        node = new int[N+2];
        // init colors to -1 --> grey
        for(int i=0; i<(N+2); i++){
            node[i] = -1;
        }
        // init connections
        for(int i=0; i<MAX_N; i++){
            for(int j=0; j<MAX_N; j++){
                connections[i][j] = 0;
            }
        }
    }
    
    public void printColors(){
        for(int i=0; i<N; i++){
            System.out.print(node[i]);
        }
        System.out.println();
    }
    
    public boolean dfs(int v, int color){
        
        // It should have a valid color and valid color of it's neighbors
        
        boolean returnValue = true;
        
        // it has a color
        if(node[v] >= 0){
            // "should have color" and node[v] colors match
            if(node[v] == color){
                returnValue = true;
            }
            else{
                returnValue = false;
            }
        }
        else{
            // if not colored assign a color
            node[v] = color;
            // renew the color to its opposit
            int newColor = (color+1) % 2;
            
            // now traverse it's neighbors
            for(int i=0; i<N; i++){
                if(connections[v][i] == 1){
                    // has a neighbor
                    boolean checkNeighbor = dfs(i, newColor);
                    returnValue = returnValue && checkNeighbor;
                }
            }
        }
        return returnValue;
    }
    
}


public class Main {

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        Graph g = new Graph(N);
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                int x = sc.nextInt();
                if(x == 0){
                    break;
                }
                g.connections[i][x-1] = 1;
                g.connections[x-1][i] = 1;
            }
        }
        
        if(g.dfs(0, 0)){
            g.printColors();
        }
        else{
            System.out.println(-1);
        }
        
    }
}