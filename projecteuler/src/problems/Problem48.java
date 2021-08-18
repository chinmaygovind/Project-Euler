package problems;


//Self Powers
public class Problem48 {
    private static long lastTen = 0;
    private static long limit = (long) Math.pow(10, 10);
    public static void main(String[] args){
        for (int i = 1; i <= 1000; i++){
            lastTen += getLastTen(i);
            lastTen = lastTen%limit;
        }
        System.out.println("The last ten digits of the series 1^1 + 2^2 + 3^3... + 1000^1000 is: " + lastTen);
    }

    private static long getLastTen(int num){
        long currentLastTen = 1;
        for (int i = 0; i < num; i++){
            currentLastTen *= num;
            currentLastTen = currentLastTen%limit;
        }
        return currentLastTen;
    }
}
