package com.thewhite.demo.util;

public class Guard {

    public static void check(boolean condition, String error) {
        if (!condition)
            throw new RuntimeException(error);
    }
}
