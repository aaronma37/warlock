package com.example.warlock;

/**
 * Created by aaron on 8/9/16.
 */
public class Person {
    public int health;
    public String name;
    public int height;
    public int width;
    public float center_x;
    public float center_y;
    public boolean busy;
    public Offensive_Physical_Actions action;

    public Person() {
        health=100;
        name="default";
        height=10;
        width=6;
        busy=false;

        center_x=0;
        center_y=0;
    }

    public Person(String given_name, float start_x, float start_y) {
        health=100;
        name=given_name;
        height=10;
        width=6;

        center_x=start_x;
        center_y=start_y;
        busy=false;
    }

    public void cast(Offensive_Physical_Actions desired_action){
        busy=true;
        action=desired_action;
    }


}
