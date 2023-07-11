package util;

import java.util.ArrayList;


public class Timer {
    private static long timer;
    public static void main(String[] args){
        long startTime = System.nanoTime();
        //2523ms for primes up to 100mill
        //1bil exceeds mem - not anymore! takes ~15 secs
        ArrayList<Integer> primes = Numbers.generatePrimes(1000000000);
        System.out.println(primes.size());
        System.out.println("Runtime: " + (System.nanoTime() - startTime)/1000000.0 + "ms");
    }

    public static void startTimer(){
        timer = System.currentTimeMillis();
    }

    public static long elapsedTime(){
        return System.currentTimeMillis() - timer;
    }

    public static void startTimerNano(){
        timer = System.nanoTime();
    }

    public static long elapsedTimeNano(){
        return System.nanoTime() - timer;
    }

    public static void beep() {
        java.awt.Toolkit.getDefaultToolkit().beep();
    }
}