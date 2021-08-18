package problems;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//Cubic Permutations.
public class Problem62 {

    private static HashMap<Long, ArrayList<Long>> map = new HashMap<>();
    private static boolean notFound = true;
    public static void main(String[] args) {
        int n = 0;
        while (notFound){
            long cube = (long) Math.pow(n, 3);
            map.put(cube, new ArrayList<>());
            for (long m : map.keySet()){
                if (cube != m && checkDigits(m, cube)){
                    map.get(m).add(cube);
                    map.get(cube).add(m);
                    if (map.get(m).size() >= 4 || map.get(cube).size() >= 4){
                        System.out.println("The smallest cube whose digits are permutations of four other cubes is: " + Math.min(m, cube));
                        notFound = false;
                        break;
                    }
                }
            }
            n++;
        }
    }

    private static boolean checkDigits(long a, long b){
        char[] Alist = String.valueOf(a).toCharArray();
        Arrays.sort(Alist);
        char[] Blist = String.valueOf(b).toCharArray();
        Arrays.sort(Blist);
        return Arrays.equals(Alist, Blist);

    }
}
