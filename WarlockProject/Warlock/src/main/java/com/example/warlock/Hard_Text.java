package com.example.warlock;

/**
 * Created by aaron on 9/4/16.
 */
public class Hard_Text {

    public String str;
    public int background;
    public float width,height,x,y;
    public float text_size;

    public Hard_Text(String i_str, int i_background,float i_width, float i_height,float i_x, float i_y){
        background=i_background;
        str=i_str;
        width=i_width;
        height=i_height;
        x=i_x;
        y=i_y;
        text_size=1f;

    }

    public void  setSize(float i_width, float i_height,float i_x, float i_y){
        width=i_width;
        height=i_height;
        x=i_x;
        y=i_y;
    }

    public void set_font_size(float i){
        text_size=i;
    }

}
