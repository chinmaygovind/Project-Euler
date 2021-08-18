package problems;

//Digit Fifth Power.
public class Problem31 {
    private static int totalCombinations = 0;

    public static void main(String[] args){
        for (int oneP = 0; oneP <= 200; oneP++){
            for (int twoP = 0; twoP <= 100; twoP++){
                for (int fiveP = 0; fiveP <= 40; fiveP++){
                    for (int tenP = 0; tenP <= 20; tenP++){
                        for (int twentyP = 0; twentyP <= 10; twentyP++){
                            for (int fiftyP = 0; fiftyP <= 4; fiftyP++){
                                for (int onePound = 0; onePound <= 2; onePound++){
                                    for (int twoPound = 0; twoPound <= 1; twoPound++){
                                        if (oneP + 2 * twoP + 5 * fiveP + 10 * tenP + 20 * twentyP + 50 * fiftyP + 100 * onePound + 200 * twoPound == 200){
                                            totalCombinations++;
                                            //System.out.println("1: " + oneP + ", 2: " + twoP + ", 5: " + fiveP + ", 10: " + tenP + ", 20: " + twentyP + ", 50: " + fiftyP + ", 100: " + onePound + ", 200: " + twoPound);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("The total number of combinations of coins to make two pounds is: " + totalCombinations);
    }
}
