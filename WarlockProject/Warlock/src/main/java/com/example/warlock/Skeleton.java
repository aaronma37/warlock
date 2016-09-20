package com.example.warlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 9/19/16.
 */
public class Skeleton {

    public int state=0;
    public int count=0;

    public float head[] = new float[3];
    public float hair[] = new float[3];
    public float neck[] = new float[3];
    public float upper_bod[] = new float[3];
    public float UPPER_BOD_LENGTH = .15f;
    public float UPPER_ARM_LENGTH = .13f;
    public float lower_bod[] = new float[3];
    public float upper_left_arm[] = new float[3];
    public float lower_left_arm[] = new float[3];
    public float upper_right_arm[] = new float[3];
    public float lower_right_arm[] = new float[3];
    //1 x, 2 y, 3 theta

    public List<KeyFrame> idle_animation = new ArrayList<>();

    public Skeleton(){
        upper_bod[0]=0;
        upper_bod[1]=0;
        upper_bod[2]=5;

        lower_bod[0]=upper_bod[0]+(float)Math.cos(upper_bod[2]*3.14f/180f)*.045f-(float)Math.sin(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH;
        lower_bod[1]=upper_bod[1]+(float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH;
        lower_bod[2]=0;

        upper_left_arm[0]=upper_bod[0]-.02f;
        upper_left_arm[1]=upper_bod[1]-.02f;
        upper_left_arm[2]=0;

        lower_left_arm[0]=upper_left_arm[0]+(float)Math.sin(upper_left_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;
        lower_left_arm[1]=upper_left_arm[1]+(float)Math.cos(upper_left_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;
        lower_left_arm[2]=0;

        upper_right_arm[0]=upper_bod[0]+.08f;
        upper_right_arm[1]=upper_bod[1]-.02f;
        upper_right_arm[2]=0;

        lower_right_arm[0]=upper_right_arm[0]+(float)Math.sin(upper_right_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;
        lower_right_arm[1]=upper_right_arm[1]+(float)Math.cos(upper_right_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;
        lower_right_arm[2]=0;

        head[0]=upper_bod[0]+(float)Math.sin(upper_bod[2]*3.14f/180f)*.2f;
        head[1]=upper_bod[1]+(float)Math.cos(upper_bod[2]*3.14f/180f)*.2f;
        head[2]=0;

        hair[0]=head[0]+(float)Math.sin(head[2])*.2f;
        hair[1]=head[1]+(float)Math.cos(head[2])*.2f;
        hair[2]=0;

        neck[0]=upper_bod[0]+(float)Math.sin(upper_bod[2]*3.14f/180f)*.2f;
        neck[1]=upper_bod[1]+(float)Math.cos(upper_bod[2]*3.14f/180f)*.2f;
        neck[2]=0;

        set_keyframes();
    }

    public void set_keyframes(){
        idle_animation.add(new KeyFrame(0,0,0,0,0,0,0,0,0,30));
        idle_animation.add(new KeyFrame(0,0,0,0,0,0,0,0,30,60));
        idle_animation.add(new KeyFrame(0,0,0,0,0,0,0,0,0,60));
    }

    public void update_state(int i_state){
        state=i_state;
        count=0;
    }

    public void step(){
        count++;
        if (state==0){
            if (count>idle_animation.get(idle_animation.size()-1).end){
                count=0;
            }

            for (int i=0;i<idle_animation.size();i++){
                if(count<idle_animation.get(i).end){

                    upper_bod[2]=idle_animation.get(i+1).upper_bod+(1-count/idle_animation.get(i).end)*(idle_animation.get(i).upper_bod-idle_animation.get(i+1).upper_bod);
                    head[2]=idle_animation.get(i+1).head+(1-count/idle_animation.get(i).end)*(idle_animation.get(i).head-idle_animation.get(i+1).head);
                    hair[2]=idle_animation.get(i+1).hair+(1-count/idle_animation.get(i).end)*(idle_animation.get(i).hair-idle_animation.get(i+1).hair);
                    neck[2]=idle_animation.get(i+1).neck+(1-count/idle_animation.get(i).end)*(idle_animation.get(i).neck-idle_animation.get(i+1).neck);
                    lower_bod[2]=idle_animation.get(i+1).lower_bod+(1-count/idle_animation.get(i).end)*(idle_animation.get(i).lower_bod-idle_animation.get(i+1).lower_bod);
                    upper_left_arm[2]=idle_animation.get(i+1).upper_left_arm+(1-count/idle_animation.get(i).end)*(idle_animation.get(i).upper_left_arm-idle_animation.get(i+1).upper_left_arm);
                    lower_left_arm[2]=idle_animation.get(i+1).lower_left_arm+(1-count/idle_animation.get(i).end)*(idle_animation.get(i).lower_left_arm-idle_animation.get(i+1).lower_left_arm);
                    upper_right_arm[2]=idle_animation.get(i+1).upper_right_arm+(1-count/idle_animation.get(i).end)*(idle_animation.get(i).upper_right_arm-idle_animation.get(i+1).upper_right_arm);
                    lower_right_arm[2]=idle_animation.get(i+1).lower_right_arm+(1-count/idle_animation.get(i).end)*(idle_animation.get(i).lower_right_arm-idle_animation.get(i+1).lower_right_arm);

                    break;
                }

            }

        }

        recalculate_skeleton();
    }

    public void recalculate_skeleton(){
        upper_bod[0]=0;
        upper_bod[1]=0;

        lower_bod[0]=upper_bod[0]+(float)Math.cos(upper_bod[2]*3.14f/180f)*.045f-(float)Math.sin(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH;
        lower_bod[1]=upper_bod[1]+(float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH;

        upper_left_arm[0]=upper_bod[0]-.02f;
        upper_left_arm[1]=upper_bod[1]-.02f;

        lower_left_arm[0]=upper_left_arm[0]+(float)Math.sin(upper_left_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;
        lower_left_arm[1]=upper_left_arm[1]+(float)Math.cos(upper_left_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;

        upper_right_arm[0]=upper_bod[0]+.08f;
        upper_right_arm[1]=upper_bod[1]-.02f;

        lower_right_arm[0]=upper_right_arm[0]+(float)Math.sin(upper_right_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;
        lower_right_arm[1]=upper_right_arm[1]+(float)Math.cos(upper_right_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;

        head[0]=upper_bod[0]+(float)Math.sin(upper_bod[2]*3.14f/180f)*.2f;
        head[1]=upper_bod[1]+(float)Math.cos(upper_bod[2]*3.14f/180f)*.2f;

        hair[0]=head[0]+(float)Math.sin(head[2])*.2f;
        hair[1]=head[1]+(float)Math.cos(head[2])*.2f;

        neck[0]=upper_bod[0]+(float)Math.sin(upper_bod[2]*3.14f/180f)*.2f;
        neck[1]=upper_bod[1]+(float)Math.cos(upper_bod[2]*3.14f/180f)*.2f;
    }



}
