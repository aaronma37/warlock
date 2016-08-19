package com.example.warlock;

/**
 * Created by aaron on 8/15/16.
 */
public class User {
    public String fullName;
    public int g;

    public MainActivity.Player playa[] = new MainActivity.Player[2];


    public User() {

        for (int i=0; i<2;i++){
            playa[i] = new MainActivity.Player("Aaron");
        }

    }
    public User(String fullName) {
        this.fullName = fullName;
        this.g=0;
        for (int i=0; i<2;i++){
            playa[i] = new MainActivity.Player("Aaron");
        }

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName=fullName;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g=g;
    }

}

