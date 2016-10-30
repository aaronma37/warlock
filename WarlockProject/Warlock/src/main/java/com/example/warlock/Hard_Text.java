package com.example.warlock;

import android.content.Context;
import android.opengl.Matrix;

/**
 * Created by aaron on 9/4/16.
 */
public class Hard_Text {

    public String str;
    public int background;
    public float width,height,x,y;
    public float text_size;


    public Hard_Text(String i_str, int i_background, float i_width, float i_height, float i_x, float i_y){
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
    public void set_background(int i){background=i;}

    public void draw(float[] S, float[] M, float[] Z, GeneralGraphic text_box, Cont_Font font){

        if (background==0){
            Matrix.multiplyMM(S, 0, M, 0, Z, 0);
            Matrix.translateM(S, 0, x, y, 0f);
            Matrix.scaleM(S, 0,width,height, 1f);
            text_box.Draw(S,false);

        }


        Matrix.multiplyMM(S, 0, M, 0, Z, 0);
        Matrix.translateM(S, 0, x+width-.1f, y, 0f);
        Matrix.scaleM(S, 0, .05f*text_size,.05f*text_size, 1f);

        for (int j=0;j<str.length();j++){
            font.Draw_Word(S,str.charAt(j));
            Matrix.translateM(S, 0, -1.2f, 0f, 0f);
        }
    }

}
