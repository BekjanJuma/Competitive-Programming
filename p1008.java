import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {

    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void print() {
        System.out.println(x + " " + y);
    }
}

class Pixels {

    ArrayList<Point> pixels;
    int[][] table;
    int[][] visited;
    int[][] explored;

    public Pixels() {
        pixels = new ArrayList<>();
        table = new int[12][12];
        visited = new int[12][12];
        explored = new int[12][12];
    }

    public void setupTableFromCoords(Scanner sc, int nn, int xx, int yy) {
        int n = nn;
        pixels.add(new Point(xx, yy));

        // pixels
        for (int i = 1; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            pixels.add(new Point(x, y));
        }

        // table
        for (Point p : pixels) {
            table[p.x][p.y] = 1;
        }
    }

    public void setUpTableFromDescr(Scanner sc, int xx, int yy, String ss) {
        Queue<Point> q = new LinkedList<>();
        int x = xx;
        int y = yy;
        // first coord
        q.add(new Point(x, y));
        table[x][y] = 1;
        String s = ss;
        while (q.isEmpty() == false) {
            Point p = q.poll();
            if (s.contains("R")) {
                table[p.x + 1][p.y] = 1;
                q.add(new Point(p.x + 1, p.y));
            }
            if (s.contains("T")) {
                table[p.x][p.y + 1] = 1;
                q.add(new Point(p.x, p.y + 1));
            }
            if (s.contains("L")) {
                table[p.x - 1][p.y] = 1;
                q.add(new Point(p.x - 1, p.y));
            }
            if (s.contains("B")) {
                table[p.x][p.y - 1] = 1;
                q.add(new Point(p.x, p.y - 1));
            }
            if (s.contains(".")) {
                break;
            }
            s = sc.next();
        }

        createCoordsFromTable();
    }

    public void createCoordsFromTable() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (table[i][j] == 1) {
                    pixels.add(new Point(i, j));
                }
            }
        }
    }

    public void printCoord() {
        System.out.println(pixels.size());
        for (Point p : pixels) {
            p.print();
        }
    }

    public void printNeighbor() {
        pixels.get(0).print();
        Queue<Point> q = new LinkedList<>();
        // add the first element
        q.add(pixels.get(0));
        while (q.isEmpty() == false) {
            Point p = q.poll();
            explored[p.x][p.y] = 1;
            if (visited[p.x][p.y] == 0) {
                visited[p.x][p.y] = 1;
                if (table[p.x + 1][p.y] == 1 && explored[p.x + 1][p.y] == 0) {
                    explored[p.x + 1][p.y] = 1;
                    System.out.print("R");
                    q.add(new Point(p.x + 1, p.y));
                }
                if (table[p.x][p.y + 1] == 1 && explored[p.x][p.y + 1] == 0) {
                    explored[p.x][p.y + 1] = 1;
                    System.out.print("T");
                    q.add(new Point(p.x, p.y + 1));
                }
                if (table[p.x - 1][p.y] == 1 && explored[p.x - 1][p.y] == 0) {
                    explored[p.x - 1][p.y] = 1;
                    System.out.print("L");
                    q.add(new Point(p.x - 1, p.y));
                }
                if (table[p.x][p.y - 1] == 1 && explored[p.x][p.y - 1] == 0) {
                    explored[p.x][p.y - 1] = 1;
                    System.out.print("B");
                    q.add(new Point(p.x, p.y - 1));
                }
                // end of line
                if (q.isEmpty() == false) {
                    System.out.println(",");
                }
            }
            
        }
        // end of text
        System.out.println(".");
    }
}

public class Main {

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        // first detect which type of input it is
        String s1 = sc.next();
        String s2 = sc.next();
        String s3 = sc.next();
        Pixels pixels = new Pixels();
        if (isInteger(s3)) {
            pixels.setupTableFromCoords(sc, Integer.parseInt(s1), Integer.parseInt(s2), Integer.parseInt(s3));
            pixels.printNeighbor();
        } else {
            pixels.setUpTableFromDescr(sc, Integer.parseInt(s1), Integer.parseInt(s2), s3);
            pixels.printCoord();
        }
    }
}