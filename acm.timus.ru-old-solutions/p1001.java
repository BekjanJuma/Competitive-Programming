

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(reader));
//        Scanner sc = new Scanner(reader);

        Double x;
	LinkedList<Double> S = new LinkedList<>();
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            x = (double)in.nval;
            S.addFirst(Math.sqrt(x));
        }
	while(!S.isEmpty())
	{
            System.out.printf("%.4f\n",S.remove(0));
	}
    }

}