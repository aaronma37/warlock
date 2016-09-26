package com.example.warlock;

/**
 * Created by aaron on 9/19/16.
 */
public class KeyFrame {
    public float begin;
    public float end;

    public float head;
    public float hair;
    public float neck;
    public float upper_bod;
    public float lower_bod;
    public float upper_left_arm;
    public float lower_left_arm;
    public float upper_right_arm;
    public float lower_right_arm;
    public float upper_right_leg;
    public float lower_right_leg;
    public float upper_left_leg;
    public float lower_left_leg;




    public KeyFrame(float i_head, float i_hair, float i_neck, float i_upper_bod, float i_lower_bod, float i_upper_left_arm, float i_lower_left_arm, float i_upper_right_arm, float i_lower_right_arm, float i_upper_left_leg, float i_lower_left_leg, float i_upper_right_leg, float i_lower_right_leg,  int i_beg, int i_end){
        head=i_head;
        hair=i_hair;
        neck=i_neck;
        upper_bod=i_upper_bod;
        lower_bod=i_lower_bod;
        upper_left_arm=i_upper_left_arm;
        lower_left_arm=i_lower_left_arm;
        upper_right_arm=i_upper_right_arm;
        lower_right_arm=i_lower_right_arm;
        upper_left_leg=i_upper_left_leg;
        lower_left_leg=i_lower_left_leg;
        upper_right_leg=i_upper_right_leg;
        lower_right_leg=i_lower_right_leg;


        end=i_end;
        begin=i_beg;
    }


}
