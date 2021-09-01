package util;

import problems.*;


public class Timer {
    private static long timer;
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        Problem100.main(new String[]{});
        System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void startTimer(){
        timer = System.currentTimeMillis();
    }

    public static long elapsedTime(){
        return System.currentTimeMillis() - timer;
    }
}