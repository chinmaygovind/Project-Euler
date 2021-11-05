package util;

import java.util.List;
import java.util.NoSuchElementException;

public class Sets {
    public static int sum(List<Integer> e){
        return e.stream().mapToInt(Integer::intValue).sum();
    }
    public static int max(List<Integer> e){
        if (e == null) return Integer.MAX_VALUE;
        return (e.stream().max(Integer::compare).isPresent() ? e.stream().max(Integer::compare).get() : Integer.MAX_VALUE);
    }
    public static int min(List<Integer> e){
        if (e == null) return Integer.MIN_VALUE;
        return (e.stream().min(Integer::compare).isPresent() ? e.stream().min(Integer::compare).get() : Integer.MIN_VALUE);
    }


}