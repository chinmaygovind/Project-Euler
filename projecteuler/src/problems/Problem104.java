package problems;

import util.Numbers;
import util.Timer;

import java.math.BigInteger;

//Pandigital Fibonacci Ends
public class Problem104 {
    private static BigInteger a = BigInteger.ONE;
    private static BigInteger aLast = BigInteger.ONE;
    private static BigInteger b = BigInteger.ONE;
    private static BigInteger bLast = BigInteger.ONE;
    private static BigInteger c;
    private static BigInteger modLimit = BigInteger.valueOf(1000000000);
    private static int k = 2;
    public static void main(String[] args) {
        while (bLast.toString().length() < 9
                || !Numbers.isPandigital(Integer.parseInt(b.toString().substring(0, 9)), 1, 9)
                || !Numbers.isPandigital(Integer.parseInt(bLast.toString().substring(bLast.toString().length()-9)), 1, 9)
        ){
            c = bLast;
            bLast = bLast.add(aLast).mod(modLimit);
            aLast = c;
            c = b;
            b = b.add(a);
            a = c;
            if (a.toString().length() >= 30){
                a = a.divide(modLimit);
                b = b.divide(modLimit);
            }
            //System.out.println(b);
            k++;
        }
        System.out.println(k);
    }


}
