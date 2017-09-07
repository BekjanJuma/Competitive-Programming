import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int numberOfJumps = 0;

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> lst) {

        int sz = lst.size();
        if (sz == 1) {
            return lst;
        }

        int m = sz / 2;

        ArrayList<Integer> lst1 = new ArrayList<>();
        ArrayList<Integer> lst2 = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            lst1.add(lst.get(i));
        }
        for (int i = m; i < sz; i++) {
            lst2.add(lst.get(i));
        }

        ArrayList<Integer> slst1 = mergeSort(lst1);
        ArrayList<Integer> slst2 = mergeSort(lst2);

        lst = merge(slst1, slst2);

        return lst;
    }

    // two sorted arrays
    public static ArrayList<Integer> merge(ArrayList<Integer> lst1, ArrayList<Integer> lst2) {
        ArrayList<Integer> mergedList = new ArrayList<>();
        int i1 = 0;
        int i2 = 0;

        while (true) {
            if (i1 == lst1.size()) {
                // use up second list
                for (int i = i2; i < lst2.size(); i++) {
                    mergedList.add(lst2.get(i));
                }
                break;
            }
            if (i2 == lst2.size()) {
                // use up first list
                for (int i = i1; i < lst1.size(); i++) {
                    mergedList.add(lst1.get(i));
                }
                break;
            }

            int x1 = lst1.get(i1);
            int x2 = lst2.get(i2);

            if (x1 > x2) {
                mergedList.add(x2);
                numberOfJumps += (lst1.size() - i1);
                i2++;
            } else {
                mergedList.add(x1);
                i1++;
            }
        }
        return mergedList;
    }

    public static void main(String[] args){
        ArrayList<Integer> jumps = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int j = 0; j < k; j++) {
            numberOfJumps = 0;
            ArrayList<Integer> lst = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                lst.add(sc.nextInt());
            }

            lst = mergeSort(lst);
       //     for (Integer i : lst) {
       //         System.out.print(i + " ");
       //     }
         //   System.out.println();
            jumps.add(numberOfJumps);
         //   System.out.println(numberOfJumps);
        }
        int maxIdx = 0;;
        for(int i=0; i<jumps.size(); i++){
            if(jumps.get(i)>jumps.get(maxIdx)){
                maxIdx = i;
            }
        }
        System.out.println(maxIdx+1);
    }
}