package problems;


import java.util.ArrayList;
import java.util.List;

//A Preference for A5.
public class Problem151 {
    //they changed the name of this problem??? hah
    public static void main(String[] args) {
        ArrayList<Integer> papers = new ArrayList<>(List.of(4));
        System.out.println(pick(papers, 0));
        
    }
    public static double pick(ArrayList<Integer> papers, int depth) {
        System.out.println(papers + " | " + depth);
        double total = 0;
        for (int picked : papers) {
            ArrayList<Integer> temp = new ArrayList<>(papers);
            temp.remove((Integer) picked);
            for (int newPaper = 1; newPaper < picked; newPaper *= 2) {
                temp.add(newPaper);
            }
            if (depth == 1) System.out.println(temp);
            total += pick(temp, depth + 1);

        }
        return total;
    }

}



