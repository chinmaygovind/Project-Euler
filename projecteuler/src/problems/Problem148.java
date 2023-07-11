package problems;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Exploring Pascal's Triangle.
public class Problem148 {

    public static void main(String[] args) {

        //for first seven, 1 + 2 + 3 + 4... + 7
        //for next seven, 2 + 4 + 6 + 8... + 14
        //holds up to group #7, then resets but multiplied by next number
        //first group of 7 (first 49) = 1 * 28 + 2 * 28 + 3 * 28... + 7 * 28 = 28 * 28 = 784
        //first 343 = 28^3
        //first 2401 = 28^4
        //first 7^10 (282475249) = 28^10
        //first 3 * 7^10 (847425747) = 6 * 28^10, 152574253 remain
        //same as 4 * sum of first 152574253
        //first 3 * 7^9 (121060821) = 6 * 28^9, 31513432 remain
        //same as 4 * sum of first 31513432
        //first 5 * 7^8 (28824005) = 15 * 28^8, 2689427 remain
        //same as 6 * sum of first 2689427
        //first 3 * 7^7 (2470629) = 6 * 28^7, 218798 remain
        //same as 4 * sum of first 218798
        //first 7^6 (117649) = 28^6, 101149 remain
        //same as 2 * sum of first 101149
        //first 6 * 7^5 (100842) = 21 * 28^5, 307 remain
        //same as 7 * sum of first 307
        //first 6 * 7^2 (294) = 21 * 28^2, 13 remain
        //same as 7 * sum of first 13
        //sum of first 13 is 70
        //okay
        System.out.println("The number of entries not divisble by 7 in the first billion rows of Pascal's triangle is: " +
            BigInteger.valueOf(28).pow(10).multiply(BigInteger.valueOf(6)).add(BigInteger.valueOf(4).multiply(
                    BigInteger.valueOf(28).pow(9).multiply(BigInteger.valueOf(6)).add(BigInteger.valueOf(4).multiply(
                            BigInteger.valueOf(28).pow(8).multiply(BigInteger.valueOf(15)).add(BigInteger.valueOf(6).multiply(
                                    BigInteger.valueOf(28).pow(7).multiply(BigInteger.valueOf(6)).add(BigInteger.valueOf(4).multiply(
                                            BigInteger.valueOf(28).pow(6).add(BigInteger.valueOf(2).multiply(
                                                    BigInteger.valueOf(28).pow(5).multiply(BigInteger.valueOf(21)).add(BigInteger.valueOf(7).multiply(
                                                            BigInteger.valueOf(28).pow(2).multiply(BigInteger.valueOf(21)).add(BigInteger.valueOf(7).multiply(
                                                                    BigInteger.valueOf(70)
                                                            ))
                                                    ))
                                            ))
                                    ))
                            ))
                    ))
            )));

        //maximum sacrilegious
    }

}



