package com.fruitsmanager.fruits.utils;


import java.util.List;

public class PageUtil {

    public static <T> List<T> handlePage(List<T> source, int start, int end){
        int total = source.size();
        end = end > total ? total : end;
        if (end <= start) {
            return null;
        }
        return source.subList(start,end);
    }

}
