package problems;

//Largest Prime Factors.
public class Problem003 {
    private static long largestFactor = 2;
    private static long target = 600851475143L;

    public static void main(String[] args){
        while (largestFactor < target){
            if (target%largestFactor == 0){
                target = target / largestFactor;
            }
            largestFactor++;
        }
        System.out.println("The largest prime factor of 600851475143 is: " + largestFactor);
    }
}
