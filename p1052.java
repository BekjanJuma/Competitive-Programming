import java.util.ArrayList;
import java.util.Scanner;

class Point{
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Line{
    public Point p1, p2;
    public int a, b, c;
    
    public static int myGCD(int a, int b) { 
        return b==0 ? a : myGCD(b, a%b); 
    }
    
    private void simplify(){
        int gcd1 = myGCD(a, b);
        int gcd2 = myGCD(gcd1, c);
        a = a / gcd2;
        b = b / gcd2;
        c = c / gcd2;
        if(a<0){
            a = -a;
            b = -b;
            c = -c;
        }
    }

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        a = p1.y - p2.y;
        b = p2.x - p1.x;
        c = (p1.x)*(p2.y) - (p2.x)*(p1.y);
        
        simplify();
    }
    
    public boolean belongs(Point p){
        int r = a*(p.x) + b*(p.y) + c;
        return (r==0);
    }
    
    
}
public class Main {
    
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Point> points = new ArrayList<>();
        for(int i=0; i<n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            points.add(new Point(x, y));
        }

        ArrayList<Line> lines = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                Line l = new Line(points.get(i), points.get(j));
                lines.add(l);
            }
        }
        
       
        
        int maxPoints = 2;
        for(int i=0; i<lines.size(); i++){
            int tmpMax = 0;
            Line l= lines.get(i);
            for(int j=0; j<points.size(); j++){
                Point p = points.get(j);
                if(l.belongs(p)){
                    tmpMax++;
                }
            }
            if(tmpMax>maxPoints){
                maxPoints = tmpMax;
            }
        }
        
        System.out.println(maxPoints);
    }
}