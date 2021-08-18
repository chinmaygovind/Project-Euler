package problems;

//Largest Palindrome Product.
public class Problem004 {
    private static int a = 100;
    private static int b = 100;
    private static int largestPalindrome = 0;
    public static void main(String[] args){
        while (b < 1000){
            if (isPalindrome(a * b) && a * b > largestPalindrome){
                largestPalindrome = a * b;
            }
            a++;
            if (a == 1000){
                a = b + 1;
                b++;
            }
        }
        System.out.println("The largest palindrome product of two 3 digit integers is: " + largestPalindrome);
    }

    public static boolean isPalindrome(int num){
        return new StringBuilder(String.valueOf(num)).reverse().toString().equals(String.valueOf(num));
    }
}
