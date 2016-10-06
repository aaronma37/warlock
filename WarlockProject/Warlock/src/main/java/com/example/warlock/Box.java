package com.example.warlock;

/**
 * Created by aaron on 10/3/16.
 */
public class Box {
    public float width,height,x,y;

    public int response;

    public int BATTLE=0;

    public Box(float i_width, float i_height, float i_x, float i_y, int i_response){
        x=i_x;
        y=i_y;
        width=i_width;
        height=i_height;
        response=i_response;
    }

}
