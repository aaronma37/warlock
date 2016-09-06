package com.example.warlock;

/**
 * Created by aaron on 8/15/16.
 */
public class User {
    public String fullName;
    public int g;

    public MainActivity.Player player;
    //public Spirit spirit[] = new Spirit[15];

    public User() {


            player = new MainActivity.Player("Aaron");
/*        for (int i =0; i<15;i++){
            spirit[i]= new Spirit(i);
        }*/

    }
    public User(String fullName) {
        this.fullName = fullName;
        this.g=0;

            player = new MainActivity.Player("Aaron");
/*        for (int i =0; i<15;i++){
            spirit[i]= new Spirit(i);
        }*/


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

