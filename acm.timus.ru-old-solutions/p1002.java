

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;


// 1002. Phone Numbers

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        Reader reader = oj ? new InputStreamReader(System.in) : new FileReader("input1");
//        StreamTokenizer in = new StreamTokenizer(new BufferedReader(reader));
        Scanner sc = new Scanner(reader);

        
        String s;

        while (true) {
            s = sc.next();
            if (s.equals("-1")) {
                break;
            }
            int n = sc.nextInt();
            Dictionary dic = new Dictionary(n);
            dic.setDigits(s.toCharArray());
            String[] words = new String[n];
            for (int i = 0; i < n; i++) {
                words[i] = sc.next();
            }
            dic.setWords(words);
            dic.run();
            dic.printMinimumState();
        }

    }

}

class State {

    public State() {
        usedWords = new ArrayList<>();
        buffer = "";
        used = "";
    }
    public ArrayList<String> usedWords;
    public String buffer;
    public String used;
}

class Dictionary {

    int n;
    String[] words;
    char[] digits;
    ArrayList<State> activeStateList;

    public Dictionary(int n) {
        this.n = n;
        activeStateList = new ArrayList<>();
        activeStateList.add(new State());
    }

    public void printWords() {
        for (String s : words) {
            System.out.println(s);
        }
    }

    public int getN() {
        return n;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public State getMinimumState() {
        State minState = new State();
        for (State s : activeStateList) {
            minState.usedWords.size();
            if (s.buffer.equals("")) {
                if (minState.usedWords.size() >= s.usedWords.size() || minState.usedWords.isEmpty()) {
                    minState = s;
                }
            }
        }
        return minState;
    }

    public void printMinimumState() {
        State minState = getMinimumState();
        if (minState.usedWords.size() > 0) {
            for (String s : minState.usedWords) {
                System.out.print(s + " ");
            }
            System.out.println();
        } else {
            System.out.println("No solution.");
        }
    }


    public void run() {
        for (int i = 0; i < digits.length; i++) {
            char c = digits[i];

            for (int k = 0; k < activeStateList.size(); k++) {
                State s = activeStateList.get(k);
                s.buffer = s.buffer + c;
                for (String str : words) {
                    // if buffer corresponds to a word add to state list
                    if (check(str, s.buffer)) {
                        State newState = new State();
                        newState.usedWords = (ArrayList<String>) s.usedWords.clone();
                        newState.usedWords.add(str);
                        newState.used += s.buffer;
                        newState.buffer = "";
                        boolean addNewState = true;
                        for(State ss : activeStateList){
                            if(ss.buffer.equals("") && ss.used.equals(newState.used)){
                                if(ss.usedWords.size() > newState.usedWords.size()){
                                    ss = newState;
                                    addNewState = false;
                                }
                                else{
                                    addNewState = false;
                                }
                            }
                        }
                        if (addNewState) {
                            activeStateList.add(0, newState);
                            k++;
                        }
                    }
                }
            }

        }
    }

    public boolean check(String word, String digits) {
        // word should the same size as digits
        if (word.length() == digits.length()) {
            for (int i = 0; i < word.length(); i++) {
                if (belongs(word.charAt(i), digits.charAt(i)) == false) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean belongs(char c, char d) {
        switch (d) {
            case '1':
                if (c == 'i' || c == 'j') {
                    return true;
                }
                break;
            case '2':
                if (c == 'a' || c == 'b' || c == 'c') {
                    return true;
                }
                break;
            case '3':
                if (c == 'd' || c == 'e' || c == 'f') {
                    return true;
                }
                break;
            case '4':
                if (c == 'g' || c == 'h') {
                    return true;
                }
                break;
            case '5':
                if (c == 'k' || c == 'l') {
                    return true;
                }
                break;
            case '6':
                if (c == 'm' || c == 'n') {
                    return true;
                }
                break;
            case '7':
                if (c == 'p' || c == 'r' || c == 's') {
                    return true;
                }
                break;
            case '8':
                if (c == 't' || c == 'u' || c == 'v') {
                    return true;
                }
                break;
            case '9':
                if (c == 'w' || c == 'x' || c == 'y') {
                    return true;
                }
                break;
            case '0':
                if (c == 'o' || c == 'q' || c == 'z') {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    public char[] getDigits() {
        return digits;
    }

    public void setDigits(char[] digits) {
        this.digits = digits;
    }
}