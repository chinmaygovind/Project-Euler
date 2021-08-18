package problems;



import java.util.ArrayList;

//Integer Right Triangles.
public class Problem039 {
    private static int bestPerimeter = 0;
    private static ArrayList<int[]> solutions = new ArrayList<>();

    public static void main(String[] args){
        for (int i = 3; i <= 1000; i++){
            ArrayList<int[]> tempSolutions = getSolutions(i);
            if (tempSolutions.size() > solutions.size()){
                solutions = tempSolutions;
                bestPerimeter = i;
            }
        }
        System.out.println("The perimeter of a right triangle with integer side lengths under 1000 that makes the most possible triangles is: " + bestPerimeter);
    }

    private static ArrayList<int[]> getSolutions(int perimeter){
        ArrayList<int[]> solutions = new ArrayList<>();
        for (int a = 1; a < perimeter; a++){
            for (int b = a; b < perimeter; b++){
                if (a + b > perimeter) break;
                for (int c = b; c < perimeter; c++){
                    if (a + b + c > perimeter) break;
                    if (a + b + c == perimeter && (Math.pow(a, 2) + Math.pow(b, 2)) == Math.pow(c, 2)){
                        solutions.add(new int[] {a, b, c});
                    }
                }
            }
        }
        return solutions;
    }
}
