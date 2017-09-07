
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        String all = "";
        while (sc.hasNextLine()) {
            all += sc.nextLine();
        }
        Stack<String> stack = new Stack<>();

        boolean isD = true;


        for (int pos = 0; pos < all.length(); pos++) {
            char c = all.charAt(pos);

            // text
            if (stack.isEmpty()) {
                if (c == '(') {
                    // comment start
                    if (pos + 1 < all.length()) {
                        if (all.charAt(pos + 1) == '*') {
                            stack.add("[");
                            pos++;
                            continue;
                        }
                    }
                    // arithmetic start
                    stack.add("(");
                } else if(c == ')'){
                    isD = false;
                    break;
                }
                continue;
            }


            // comments
            if (stack.peek().equals("[")) {
                // end of comment
                if (c == '*') {
                    if (pos + 1 < all.length()) {
                        if (all.charAt(pos + 1) == ')') {
                            stack.pop();
                            pos++;
                            continue;
                        }
                    }
                }
continue;
            }
            
            // arithmetic
            if (stack.peek().equals("(")) {
                // end of arithmetic
                if (c == ')') {
                    stack.pop();
                    continue;
                }
                if (c == '(') {
                    if (pos + 1 < all.length()) {
                        if (all.charAt(pos + 1) == '*') {
                            stack.add("[");
                            pos++;
                            continue;
                        }
                    }
                    stack.add("(");
                    continue;
                }
                if (("=+-*/0123456789)(").contains("" + c)) {
                    continue;
                } else {
                    isD = false;
                    break;
                }
            }
        }
        
        if(!stack.isEmpty()){
            isD = false;
        }

        if(isD){
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
        
    }
}