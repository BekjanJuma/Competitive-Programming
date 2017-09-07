
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);

        double a_0, a_1, b_0, b_1, c_0, c_1;

        a_0 = sc.nextDouble();
        a_1 = sc.nextDouble();
        b_0 = sc.nextDouble();
        b_1 = sc.nextDouble();
        c_0 = sc.nextDouble();
        c_1 = sc.nextDouble();

        double D = (a_0 - c_0) * (b_1 - c_1) - (b_0 - c_0) * (a_1 - c_1);

        double p_0 = (((a_0 - c_0) * (a_0 + c_0) + (a_1 - c_1) * (a_1 + c_1)) / 2 * (b_1 - c_1)
                - ((b_0 - c_0) * (b_0 + c_0) + (b_1 - c_1) * (b_1 + c_1)) / 2 * (a_1 - c_1))
                / D;
        double p_1 = (((b_0 - c_0) * (b_0 + c_0) + (b_1 - c_1) * (b_1 + c_1)) / 2 * (a_0 - c_0)
                - ((a_0 - c_0) * (a_0 + c_0) + (a_1 - c_1) * (a_1 + c_1)) / 2 * (b_0 - c_0))
                / D;
        double r = Math.sqrt((c_0 - p_0) * (c_0 - p_0) + (c_1 - p_1) * (c_1 - p_1));
        
        
        double rl = p_0 - r;
        double rr = p_0 + r;
        double rt = p_1 + r;
        double rb = p_1 - r;
        
//        double d_ab = dis(a_0, a_1, b_0, b_1);
//        double d_ca = dis(c_0, c_1, a_0, a_1);
//        double d_cb = dis(c_0, c_1, b_0, b_1);
//        
//        double cosACB = cosC(d_ca, d_cb, d_ab);
//        double cosALB = cosC( dis(rl, p_1, a_0, a_1), dis(rl, p_1, b_0, b_1), d_ab);
//        double cosARB = cosC( dis(rr, p_1, a_0, a_1), dis(rr, p_1, b_0, b_1), d_ab);
//        double cosATB = cosC( dis(p_0, rt, a_0, a_1), dis(p_0, rt, b_0, b_1), d_ab);
//        double cosABB = cosC( dis(p_0, rb, a_0, a_1), dis(p_0, rb, b_0, b_1), d_ab);
        
        
        double leftmost = Math.min(Math.min(a_0, b_0), c_0);
        if(sameSide(a_0, a_1, b_0, b_1, c_0, c_1, rl, p_1)){
            leftmost = Math.min(leftmost, rl);
        }
        double rightmost = Math.max(Math.max(a_0, b_0), c_0);
        if(sameSide(a_0, a_1, b_0, b_1, c_0, c_1, rr, p_1)){
            rightmost = Math.max(rightmost, rr);
        }
        double topmost = Math.max(Math.max(a_1, b_1), c_1);
        if(sameSide(a_0, a_1, b_0, b_1, c_0, c_1, p_0, rt)){
            topmost = Math.max(topmost, rt);
        }
        double bottommost = Math.min(Math.min(a_1, b_1), c_1);
        if(sameSide(a_0, a_1, b_0, b_1, c_0, c_1, p_0, rb)){
            bottommost = Math.min(bottommost, rb);
        }
//        System.out.println("l " + leftmost + " " + Math.floor(leftmost));
//        System.out.println("r " + rightmost + " " + Math.floor(rightmost));
//        System.out.println("t " + topmost + " " + Math.floor(topmost));
//        System.out.println("b " + bottommost + " " + Math.floor(bottommost));
        
        System.out.println(bounds(leftmost, rightmost, topmost, bottommost));
        
    }

    public static double dis(double a0, double b0, double a1, double b1) {
        return Math.sqrt((a0 - a1) * (a0 - a1) + (b0 - b1) * (b0 - b1));
    }
    
    public static double cosC(double d_ca, double d_cb, double d_ab){
        return (d_ca * d_ca + d_cb * d_cb - d_ab * d_ab) / (2 * d_ca * d_cb);
    }
    
    public static boolean sameSide(double x1, double y1, double x2, double y2, double ax, double ay, double bx, double by){
        return ((y1-y2)*(ax-x1) +(x2-x1)*(ay-y1))*((y1-y2)*(bx-x1)+ (x2-x1)*(by-y1))>=0;
    }
    
    public static int bounds(double l, double r, double t, double b){
        int il = (int) ((l<-1000)?1000:Math.floor(l));
        int ir = (int) ((r>1000)?1000:Math.floor(r+0.999999));
        int it = (int) ((t>1000)?1000:Math.floor(t+0.999999));
        int ib = (int) ((b<-1000)?1000:Math.floor(b));
        
        return (ir-il)*(it-ib);
        
    }

}