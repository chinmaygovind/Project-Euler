package util;

import problems.*;


public class Timer {
    private static long timer;
    public static void main(String[] args){
        long startTime = System.nanoTime();
        //2523ms for 100mill
        //1bil exceeds mem
        Problem143.main(new String[]{});
        System.out.println("Runtime: " + (System.nanoTime() - startTime)/1000000.0 + "ms");
    }

    public static void startTimer(){
        timer = System.currentTimeMillis();
    }

    public static long elapsedTime(){
        return System.currentTimeMillis() - timer;
    }
}