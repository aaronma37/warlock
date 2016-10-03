package com.example.warlock;

/**
 * Created by aaron on 9/27/16.
 */
public class Arrow_Data {
    public float x,y,height,width;
    public int direction;
    public boolean active;
    public boolean is_active;

    public Arrow_Data(float i_x, float i_y, float i_height, float i_width, int dir, boolean act){
        x=i_x;
        y=i_y;
        direction=dir;
        active=act;
        height=i_height;
        width=i_width;
        is_active=false;
    }


}
