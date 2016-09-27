package com.example.warlock;

/**
 * Created by aaron on 9/27/16.
 */
public class Arrow_Data {
    public float x,y;
    public int direction;
    public boolean active;

    public Arrow_Data(float i_x, float i_y, int dir, boolean act){
        x=i_x;
        y=i_y;
        direction=dir;
        active=act;
    }


}
