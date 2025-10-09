package com.mipt.mikhailandrosov.model;

public class Human {
    private String firstName;
    private String lastName;
    private int age;
    private boolean isWorking;

    // Геттеры
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean isWorking() {
        return isWorking;
    }

    // Сеттеры
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
