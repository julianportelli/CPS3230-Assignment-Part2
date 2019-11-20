package com.uom.cps3230.website;

public class slp {
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }
}
