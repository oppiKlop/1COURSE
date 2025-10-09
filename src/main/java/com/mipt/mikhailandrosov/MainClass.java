package com.mipt.mikhailandrosov;

public class MainClass {
    private int privateField;
    private String privateStringField;
    
    protected static double protectedStaticField;
    
    public final long publicFinalField = 100L;

    public static void main(String[] args) {
        for (int i = 0; i <= 15; i++) {
            System.out.println("Iter: " + i);
        }
    }
}
