

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class Main {

    
    public static int maxTime = 60*12;
    
    public static int convMinutes(String s){
        if(s.charAt(0) == '0'){
            s = s.substring(1);
        }
        if(s.charAt(0) == '0'){
            s = s.substring(1);
        }
        int min = (int) (100*Double.parseDouble(s));
        return 60*(min/100) + min%100;
        
    }
    // t2-t1
    public static int diff(int t1, int t2){
        if(Math.abs(t2-t1)>maxTime){
            if(t2>t1){
                return t2-t1-24*60;
            } else{
                return t2+24*60-t1;
            }
        } else{
            return t2-t1;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
//        StreamTokenizer in = new StreamTokenizer(new BufferedReader(reader));
        Scanner sc = new Scanner(reader);
        
        String s = sc.next();
        int t1 = convMinutes(s);
        s = sc.next();
        int t2 = convMinutes(s);
        
//        System.out.println("t1: " + t1);
//        System.out.println("t2: " + t2);
        
        int dt1 = diff(t1,t2);
        
        s = sc.next();
        t1 = convMinutes(s);
        s = sc.next();
        t2 = convMinutes(s);
        
//        System.out.println("t1: " + t1);
//        System.out.println("t2: " + t2);
        
        int dt2 = diff(t1,t2);
        
//        System.out.println("dt1: " + dt1);
//        System.out.println("dt2: " + dt2);

        
        int x = (Math.abs(dt1+dt2))/2;
        
        //x-t
        int xmt = dt1>dt2?dt2:dt1;
        //x+t
        int xpt = dt1<dt2?dt2:dt1;
        
        System.out.println((-xmt+x+10)/60);
        
        
    }

}