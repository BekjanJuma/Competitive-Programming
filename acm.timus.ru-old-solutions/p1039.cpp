import java.util.ArrayList;
import java.util.Scanner;

class Tree {

    int used;
    int notUsed;
    ArrayList<Tree> children;
    int index;

    public Tree(int index, int rating) {
        this.index = index;
        if (rating > 0) {
            used = rating;
        } else {
            used = 0;
        }
        notUsed = 0;
        children = new ArrayList<>();
    }

    public void createTree(int[] conv, int[] boss, int n) {
        // first create children
        for (int i = 1; i <= n; i++) {
            if (boss[i] == index) {
                children.add(new Tree(i, conv[i]));
            }
        }
        for (Tree child : children) {
            child.createTree(conv, boss, n);
        }
    }

    public void max() {
        for (Tree child : children) {
            child.max();
        }
        for (Tree child : children) {
            used += child.notUsed;
        }
        for (Tree child : children) {
            notUsed += child.used > child.notUsed ? child.used : child.notUsed;
        }
    }
}

public class Main {

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] conv = new int[n + 1];
        int[] boss = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            conv[i] = sc.nextInt();
        }

        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a + b == 0) {
                break;
            }
            boss[a] = b;
        }

        int rootIndex = 0;
        // find the root
        for (int i = 1; i <= n; i++) {
            if (boss[i] == 0) {
                rootIndex = i;
            }
        }

        Tree root = new Tree(rootIndex, conv[rootIndex]);
        root.createTree(conv, boss, n);
        
        root.max();
        int max = root.used>root.notUsed?root.used:root.notUsed;
        System.out.println(max);
    }
}