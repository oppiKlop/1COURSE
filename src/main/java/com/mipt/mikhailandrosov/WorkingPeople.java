package com.mipt.mikhailandrosov;

public abstract class WorkingPerson {
    public abstract void work(int hours);
    
    public boolean goHome(String currentTime, String endTime) {
        return currentTime.equals(endTime);
    }
}
