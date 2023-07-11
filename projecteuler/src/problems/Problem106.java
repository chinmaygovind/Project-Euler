package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.Sets;

//Special Subset Sums: Meta-Testing
public class Problem106 {
    public static void main(String[] args) {
        System.out.println("The number of subset pairs that need to be checked for equality is: " + equalityCheck(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)));
    }

    public static int equalityCheck(List<Integer> set){
        int checksNeeded = 0;
        List<List<List<Integer>>> workingPairs = new ArrayList<>();
        for (int i = 0; i < Math.pow(3, set.size()); i++){
            int mask = i;
            ArrayList<Integer> b = new ArrayList<>();
            ArrayList<Integer> c = new ArrayList<>();
            int indx = -1;
            while (mask >= 1){
                indx++;
                if (mask%3 == 1) b.add(set.get(indx));
                if (mask%3 == 2) c.add(set.get(indx));
                mask /= 3;
            }

            List<List<Integer>> pair = new ArrayList<>();
            if (Sets.min(c) < Sets.min(b)){
                pair.add(b); pair.add(c);
            } else {
                pair.add(c); pair.add(b);
            }
            if (b.size() <= 1 || c.size() <= 1) continue;


            if (b.size() == c.size()) {
                boolean checkNeeded = false;
                for (int j = 0; j < b.size(); j++){
                    if (b.get(j) > c.get(j)) {
                        checkNeeded = true;
                        break;
                    }
                }
                if (checkNeeded && Sets.min(c) < Sets.max(b) && Sets.sum(c) >= Sets.sum(b) && !workingPairs.contains(pair)){
                    checksNeeded++;
                    workingPairs.add(pair);
                }
            }
        }
        return checksNeeded;
    }
}
