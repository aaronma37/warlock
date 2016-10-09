package com.example.warlock;

/**
 * Created by aaron on 10/5/16.
 */
public class Projectile_Keyframe {

    public float x,y,width,height;


    public float begin,end;

    public Projectile_Keyframe(float ix,float iy, float iwidth, float iheight, float b, float e){
        x=ix;
        y=iy;
        width=iwidth;
        height=iheight;
        begin=b;
        end=e;
    }


}
