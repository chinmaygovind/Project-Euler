package problems;


import util.Numbers;

//Counting Summations.
public class Problem076 {
    //While researching this, I found the answer on wikipedia. Oops!
    public static void main(String[] args) {
        System.out.println("The number of unique summations that add to 100 is: " + (Numbers.partition(100) - 1));
    }




}
