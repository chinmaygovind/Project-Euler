package problems;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

//Cyclical Figure Numbers.
public class Problem061 {
    private static ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
    private static ArrayList<Integer> numbers = new ArrayList<>();
    private static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    private static boolean foundSequence = false;

    public static void main(String[] args) {
        //load groups
        for (int sides = 3; sides < 9; sides++) {
            groups.add(new ArrayList<>());
            int n = 1;
            while (true) {
                if (sides == 3) {
                    if (n * (n + 1) / 2 >= 1000) numbers.add(n * (n + 1) / 2);
                    groups.get(sides - 3).add(n * (n + 1) / 2);
                    n++;
                    if (n * (n + 1) / 2 >= 10000) {
                        break;
                    }
                } else if (sides == 4) {
                    if (n * n >= 1000) numbers.add(n * n);
                    groups.get(sides - 3).add(n * n);
                    n++;
                    if (n * n >= 10000) {
                        break;
                    }
                } else if (sides == 5) {
                    if (n * (3 * n - 1) / 2 >= 1000) numbers.add(n * (3 * n - 1) / 2);
                    groups.get(sides - 3).add(n * (3 * n - 1) / 2);
                    n++;
                    if (n * (3 * n - 1) / 2 >= 10000) {
                        break;
                    }
                } else if (sides == 6) {
                    if (n * (2 * n - 1) >= 1000) numbers.add(n * (2 * n - 1));
                    groups.get(sides - 3).add(n * (2 * n - 1));
                    n++;
                    if (n * (2 * n - 1) >= 10000) {
                        break;
                    }
                } else if (sides == 7) {
                    if (n * (5 * n - 3) / 2 >= 1000) numbers.add(n * (5 * n - 3) / 2);
                    groups.get(sides - 3).add(n * (5 * n - 3) / 2);
                    n++;
                    if (n * (5 * n - 3) / 2 >= 10000) {
                        break;
                    }
                } else if (sides == 8) {
                    if (n * (3 * n - 2) >= 1000) numbers.add(n * (3 * n - 2));
                    groups.get(sides - 3).add(n * (3 * n - 2));
                    n++;
                    if (n * (3 * n - 2) >= 10000) {
                        break;
                    }
                }
            }
        }
        //load map
        for (int number1 = 0; number1 < numbers.size(); number1++) {
            String num = numbers.get(number1).toString();
            for (int number2 = 0; number2 < numbers.size(); number2++) {
                if (number2 == number1) continue;
                String otherNum = numbers.get(number2).toString();
                if ((num.substring(2, 3) + num.substring(3)).equals(otherNum.substring(0, 1) + otherNum.substring(1, 2))) {
                    if (!map.containsKey(numbers.get(number1))) {
                        map.put(numbers.get(number1), new ArrayList<>());
                    }
                    map.get(numbers.get(number1)).add(numbers.get(number2));
                }
            }
        }

        for (Integer start : map.keySet()){
            ArrayList<Integer> potentialCycle = findCycle(new ArrayList<>(Collections.singletonList(start)));
            if (potentialCycle != null){
                System.out.println("The cycle of only six cyclic 4-digit numbers where all polygonal types 3-8 are represented is: " + potentialCycle);
                System.out.println("Their polygonal sides are: " + potentialCycle.stream().map(Problem061::getGroup).collect(Collectors.toList()));//hehehe
                System.out.println("Their sum is: " + potentialCycle.stream().mapToInt(Integer::intValue).sum());
                break;
            }
        }
    }

    private static ArrayList<Integer> findCycle(ArrayList<Integer> path){
        if (map.get(path.get(path.size() - 1)) == null) return null;
        for (Integer potentialNext : map.get(path.get(path.size() - 1))) {
            //check for duplicate group thingies
            if (path.stream().map(Problem061::getGroup).collect(Collectors.toList()).contains(getGroup(potentialNext))) continue;
            ArrayList<Integer> newPath = new ArrayList<>(path);
            newPath.add(potentialNext);
            if (newPath.size() == 6) {
                if (map.get(newPath.get(newPath.size() - 1)) != null && map.get(newPath.get(newPath.size() - 1)).contains(newPath.get(0))){
                    ArrayList<Integer> totalSides = new ArrayList<>();
                    for (int p : newPath){
                        for (int g : getGroup(p)){
                            totalSides.add(g);
                        }
                    }
                    for (int i = 0; i < 6; i++){
                        if (Collections.frequency(totalSides, i) < 1){
                            return null;
                        }
                    }
                    return newPath;
                } else {
                    return null;
                }
            }
            newPath = findCycle(newPath);
            if (newPath != null) {
                return newPath;
            }
        }
        return null;

    }


    private static ArrayList<Integer> getGroup(int num){
        ArrayList<Integer> returnGroups = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            if (groups.get(i).contains(num)){
                returnGroups.add(i);
            }
        }
        return returnGroups;
    }
}
