package problems;


import java.math.BigDecimal;
import java.math.MathContext;

// Problem Sqrt13
public class ProblemRoot13 {
    // ahaha
    public static void main(String[] args) {
        BigDecimal s = (new BigDecimal(13)).sqrt(new MathContext(1200));
        int i = 0;
        System.out.println(s);
        String myString = s.toString().substring(2, 1002);
        //System.out.println(myString);
        for (char c : myString.toString().toCharArray()) {
            i += Integer.parseInt(String.valueOf(c));
        }
        System.out.printf("S(13, 1000) = %d\n", i);
        // how am i supposed to be creative here uhh
        // degoutant
    }
}

