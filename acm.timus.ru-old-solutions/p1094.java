import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String text="";
        
        while(sc.hasNext()){
            text+=sc.nextLine();
        }
        char[] input = text.toCharArray();
        char[] screen = new char[80];
        for(int i=0; i<80; i++){
            screen[i] = ' ';
        }
        int idx = 0;
        for (char c : input) {
            if (c == '<') {
                if (idx > 0) {
                    idx = (idx - 1) % 80;
                } else {
                    idx = 0;
                }
            } else if (c == '>') {
                idx = (idx + 1) % 80;
            } else {
                screen[idx] = c;
                idx = (idx + 1) % 80;
            }
        }
        System.out.print(screen);
    }
}