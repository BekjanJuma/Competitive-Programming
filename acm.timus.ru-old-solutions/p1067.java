
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.LinkedList;

//    1067. Disk Tree
class Tree implements Comparable<Tree> {

    String name;

    public Tree(String name) {
        this.name = name;
        children = new LinkedList<>();
    }

    public void addChild(LinkedList<String> lst) {
        if (lst == null || lst.size() == 0) {
            return;
        }
        // if contains a node
        for (Tree child : children) {
            if (child.name.equals(lst.getFirst())) {
                lst.removeFirst();
                child.addChild(lst);
                return;
            }
        }
        // add new child
        children.addFirst(new Tree(lst.pollFirst()));
        children.getFirst().addChild(lst);
    }

    public void printDFS(String space) {
        if (!name.equals(":")) {
            System.out.println(space + name);
            space += " ";
        }
        Collections.sort(children);
        for (Tree child : children) {
            child.printDFS(space);
        }
    }

    public LinkedList<Tree> children;

    @Override
    public int compareTo(Tree t) {
        return name.compareTo(t.name);
    }

}

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
//        StreamTokenizer in = new StreamTokenizer(new BufferedReader(reader));
//        Scanner sc = new Scanner(reader);
        BufferedReader br = new BufferedReader(reader);
        int n = Integer.parseInt(br.readLine());

        Tree root = new Tree(":");

        for (int i = 0; i < n; i++) {
            String[] slst = br.readLine().replace('\\', ':').split(":");
            LinkedList lst = new LinkedList();
            for (String s : slst) {
                lst.addLast(s);
            }
            root.addChild(lst);
        }
        root.printDFS("");

    }

}