package problems;

//Special Pythagorean Triplet.
public class Problem9 {
    private static int a = 1;
    private static int b = 1;

    public static void main(String[] args){
        while (a + b + Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)) != 1000){
            a++;
            if (a == 1000){
                b++;
                a = b;
            }
        }
        System.out.println("The special Pythagorean triple where a + b + c = 1000 is: " + a + ", " + b + ", " + Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)));
        System.out.println("The product abc is: " + String.format("%6f", a * b * Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2))));
    }

}
