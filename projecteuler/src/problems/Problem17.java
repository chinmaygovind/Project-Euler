package problems;

public class Problem17 {

    private static long totalLetters = 0;
    public static void main(String args[]){
        for (int i = 1; i <= 1000; i++){
            System.out.println(numberToWord(i));
            totalLetters += numberToWord(i).length();
        }

        System.out.println("The total letters in all the integers from 1-1000 is: " + totalLetters);
    }

    private static String numberToWord(int num){
        final String[] specials = new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

        final String[] tens = new String[] {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        if (num >= 1000){
            //TODO: expand to over 1000
            return "onethousand";
        }
        if (num >= 100){
            if (num%100 == 0){
                return specials[(int) (Math.floor(num/100.0) - 1)] + "hundred";
            }
            if (num%100 <= 19){
                return specials[(int) (Math.floor(num/100.0) - 1)] + "hundredand" + specials[num%100 -1];
            }
            if (num%10 == 0){
                return specials[(int) (Math.floor(num/100.0) - 1)] + "hundredand" + tens[(int) (Math.floor(num%100 / 10.0) - 2)];
            }
            return specials[(int) (Math.floor(num/100.0) - 1)] + "hundredand" + tens[(int) (Math.floor(num%100 / 10.0) - 2)] + specials[num%10 - 1];
        }
        if (num <= 19){
            return specials[num%100 -1];
        }
        if (num%10 == 0){
            return tens[(int) (Math.floor(num%100 / 10.0) - 2)];
        }
        return tens[(int) (Math.floor(num%100 / 10.0) - 2)] + specials[num%10 - 1];
    }


}
