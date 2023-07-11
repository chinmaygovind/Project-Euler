package problems;


import util.Fraction;

//Disc Game Prize Fund.
public class Problem121 {
    public static void main(String[] args) {
        //System.out.println(wins);
        System.out.println("The maximum allocated payout for the disc game is: " + (long) winProbability(15).reciprocal().value);
    }

    public static Fraction winProbability(int turns) {
        Fraction win = Fraction.ZERO;
        for (int i = 0; i < Math.pow(2, turns); i++){
            int counts = 0;
            int temp = i;
            int blues = 0;
            Fraction probabilityOfWin = null;
            while (counts < turns) {
                if (probabilityOfWin == null)
                    probabilityOfWin = new Fraction(counts + 2 - (temp%2 == 0 ? 1 : counts + 1),
                        counts + 2);
                else probabilityOfWin.multiply(
                        new Fraction(counts + 2 - (temp%2 == 0 ? 1 : counts + 1),
                                counts + 2));
                blues += temp%2;
                counts++;
                temp/=2;
            }
            if (blues > turns - blues) {
                win.add(probabilityOfWin);
            }
        }
        return win;
    }


}

