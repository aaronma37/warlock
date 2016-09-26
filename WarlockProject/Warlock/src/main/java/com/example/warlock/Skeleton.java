package com.example.warlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 9/19/16.
 */
public class Skeleton {

    public int state=0;
    public float count=0;

    public float y=0;
    public float diff=0;

    public int dir;

    public float head[] = new float[3];
    public float hair[] = new float[3];
    public float neck[] = new float[3];
    public float upper_bod[] = new float[3];
    public float UPPER_BOD_LENGTH = .15f;
    public float UPPER_ARM_LENGTH = .13f;
    public float NECK_LENGTH = .11f;
    public float LOW_BOD_LENGTH =.14f;
    public float UPPER_LEG_LENGTH = .2f;
    public float LOWER_LEG_LENGTH = .15f;
    public float lower_bod[] = new float[3];
    public float upper_left_arm[] = new float[3];
    public float lower_left_arm[] = new float[3];
    public float upper_right_arm[] = new float[3];
    public float lower_right_arm[] = new float[3];
    public float lower_left_leg[] = new float[3];
    public float upper_left_leg[]= new float [3];
    public float upper_right_leg[] = new float[3];
    public float lower_right_leg[] = new float[3];

    public float lower_left_arm_offset[] = new float[2];
    //1 x, 2 y, 3 theta

    public List<KeyFrame> idle_animation = new ArrayList<>();
    public List<KeyFrame> run_animation = new ArrayList<>();


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

        lower_left_arm[0]=upper_left_arm[0]+(float)Math.sin(upper_left_arm[2]*3.14f/180f)*UPPER_ARM_LENGTH;
        lower_left_arm[1]=upper_left_arm[1]+(float)Math.cos(upper_left_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;
        lower_left_arm[2]=0;

        upper_right_arm[0]=upper_bod[0]+.05f;
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

        upper_left_leg[0]=lower_bod[0]+(float)Math.cos(lower_bod[2]*3.14f/180f)*-.05f+(float)Math.sin(lower_bod[2]*3.14f/180f)*LOW_BOD_LENGTH;
        upper_left_leg[1]=lower_bod[1]+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH;
        upper_left_leg[2]=0;

        upper_right_leg[0]=lower_bod[0]+(float)Math.cos(lower_bod[2]*3.14f/180f)*.05f+(float)Math.sin(lower_bod[2]*3.14f/180f)*LOW_BOD_LENGTH;
        upper_right_leg[1]=lower_bod[1]+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH;
        upper_right_leg[2]=0;

        lower_left_leg[0]=upper_left_leg[0]+(float)Math.cos(upper_left_leg[2]*3.14f/180f)*0f+(float)Math.sin(upper_left_leg[2]*3.14f/180f)*UPPER_LEG_LENGTH;
        lower_left_leg[1]=upper_left_leg[1]+(float)Math.cos(upper_left_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH;
        lower_left_leg[2]=0;

        lower_right_leg[0]=upper_right_leg[0]+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*0f+(float)Math.sin(upper_right_leg[2]*3.14f/180f)*UPPER_LEG_LENGTH;
        lower_right_leg[1]=upper_right_leg[1]+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH;
        lower_right_leg[2]=0;


        set_keyframes();
    }

    public void set_keyframes(){
        // i_head,  i_hair,  i_neck,  i_upper_bod,  i_lower_bod,  i_upper_left_arm,  i_lower_left_arm,  i_upper_right_arm,  i_lower_right_arm,   i_upper_left_leg,  i_lower_left_leg,  i_upper_right_leg,  i_lower_right_leg,i_beg,  i_end
        idle_animation.add(new KeyFrame(0,0,0,0,0,0,0,0,0,0,0,0,0,0,30));
        idle_animation.add(new KeyFrame(0,0,0,3,0,5,15,-5,-5,2,-5,2,-5,30,60));
        idle_animation.add(new KeyFrame(0,0,0,0,0,0,0,0,0,0,0,0,0,60,90));
        idle_animation.add(new KeyFrame(0,0,0,-3,0,-5,-5,5,15,2,-5,2,-5,90,120));
        idle_animation.add(new KeyFrame(0,0,0,0,0,0,0,0,0,0,0,0,0,120,120));

        run_animation.add(new KeyFrame(0,0,0,0,0,   0,10,0,10,        0,0,0,0,0,5));
        run_animation.add(new KeyFrame(0,0,0,0,0,   -15,0,15,30,    20,0,-15,-15,5,10));
        run_animation.add(new KeyFrame(0,0,0,0,0,   -5,0,5,20,      15,0,-10,-10,10,15));
        run_animation.add(new KeyFrame(0,0,0,0,0,   0,10,0,10,        0,0,0,0,15,20));
        run_animation.add(new KeyFrame(0,0,0,0,0,   15,30,-15,10,   -15,-15,20,0,20,25));
        run_animation.add(new KeyFrame(0,0,0,0,0,   5,20,-5,10,     -10,0-10,10,0,25,30));
        run_animation.add(new KeyFrame(0,0,0,0,0,   0,10,0,10,        0,0,0,0,30,30));


    }

    public void update_state(int i_state){
        state=i_state;
        count=0;
    }

    public void step(int i_state, int i_dir, float i_y){
        y=i_y;
        dir=i_dir;
        count++;

        if (state!=i_state){
            count=0;
            state=i_state;
        }

        if (state==0){
            if (count>idle_animation.get(idle_animation.size()-1).end){
                count=0;
            }

            for (int i=0;i<idle_animation.size();i++){
/*
                System.out.println(idle_animation.get(i).end);
*/
                if(count<idle_animation.get(i).end){
                    update_angle(dir, idle_animation.get(i),idle_animation.get(i+1), (1f-(count-idle_animation.get(i).begin)/(idle_animation.get(i).end-idle_animation.get(i).begin)));
                    break;
                }

            }

        }else if (state==3){
            //RUN ANIMATION
            if (count>run_animation.get(run_animation.size()-1).end){
                count=0;
            }

            for (int i=0;i<run_animation.size();i++){
/*
                System.out.println(idle_animation.get(i).end);
*/
                if(count<run_animation.get(i).end){
                    update_angle(dir, run_animation.get(i),run_animation.get(i+1), (1f-(count-run_animation.get(i).begin)/(run_animation.get(i).end-run_animation.get(i).begin)));
                    break;
                }

            }


        }

        recalculate_skeleton();
    }

    public void update_angle(int dir, KeyFrame anim_1, KeyFrame anim_2, float rng){
        upper_bod[2]= dir*(anim_2.upper_bod+rng*(anim_1.upper_bod-anim_2.upper_bod));
        head[2]= dir*(anim_2.head+rng*(anim_1.head-anim_2.head));
        hair[2]= dir*(anim_2.hair+rng*(anim_1.hair-anim_2.hair));
        neck[2]= dir*(anim_2.neck+rng*(anim_1.neck-anim_2.neck));
        lower_bod[2]= dir*(anim_2.lower_bod+rng*(anim_1.lower_bod-anim_2.lower_bod));
        upper_left_arm[2]= dir*(anim_2.upper_left_arm+rng*(anim_1.upper_left_arm-anim_2.upper_left_arm));
        lower_left_arm[2]= dir*(anim_2.lower_left_arm+rng*(anim_1.lower_left_arm-anim_2.lower_left_arm));
        upper_right_arm[2]= dir*(anim_2.upper_right_arm+rng*(anim_1.upper_right_arm-anim_2.upper_right_arm));
        lower_right_arm[2]= dir*(anim_2.lower_right_arm+rng*(anim_1.lower_right_arm-anim_2.lower_right_arm));
        upper_left_leg[2]= dir*(anim_2.upper_left_leg+rng*(anim_1.upper_left_leg-anim_2.upper_left_leg));
        lower_left_leg[2]= dir*(anim_2.lower_left_leg+rng*(anim_1.lower_left_leg-anim_2.lower_left_leg));
        upper_right_leg[2]= dir*(anim_2.upper_right_leg+rng*(anim_1.upper_right_leg-anim_2.upper_right_leg));
        lower_right_leg[2]= dir*(anim_2.lower_right_leg+rng*(anim_1.lower_right_leg-anim_2.lower_right_leg));
    }


    public void recalculate_skeleton(){
        if (((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_left_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_left_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH) < ((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_right_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH)){
            diff = ((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_left_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_left_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH);
        }else{
            diff= ((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_right_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH);
        }

        upper_bod[0]=0;
        upper_bod[1]=-.5f-diff;

        lower_bod[0]=dir*upper_bod[0]+(float)Math.cos(upper_bod[2]*3.14f/180f)*.045f+dir*(float)Math.sin(upper_bod[2]*3.14f/180f)*UPPER_BOD_LENGTH;
        lower_bod[1]=upper_bod[1]+(float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH;

        upper_left_arm[0]=upper_bod[0]-.02f;
        upper_left_arm[1]=upper_bod[1]-.02f;

        lower_left_arm[0]=dir*upper_left_arm[0]+(float)Math.sin(upper_left_arm[2]*3.14f/180f)*UPPER_ARM_LENGTH;
        lower_left_arm[1]=upper_left_arm[1]+(float)Math.cos(upper_left_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;

        upper_right_arm[0]=upper_bod[0]+.065f;
        upper_right_arm[1]=upper_bod[1]-.02f;

        lower_right_arm[0]=dir*upper_right_arm[0]+(float)Math.sin(upper_right_arm[2]*3.14f/180f)*UPPER_ARM_LENGTH;
        lower_right_arm[1]=upper_right_arm[1]+(float)Math.cos(upper_right_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;

        head[0]=upper_bod[0]+(float)Math.sin(upper_bod[2]*3.14f/180f)*NECK_LENGTH;
        head[1]=upper_bod[1]+(float)Math.cos(upper_bod[2]*3.14f/180f)*NECK_LENGTH;

        hair[0]=head[0]+(float)Math.sin(head[2])*.07f;
        hair[1]=head[1]+(float)Math.cos(head[2])*.07f;

        neck[0]=upper_bod[0]+(float)Math.sin(upper_bod[2]*3.14f/180f)*.2f;
        neck[1]=upper_bod[1]+(float)Math.cos(upper_bod[2]*3.14f/180f)*.2f;

        upper_left_leg[0]=lower_bod[0]+(float)Math.cos(lower_bod[2]*3.14f/180f)*-.05f+(float)Math.sin(lower_bod[2]*3.14f/180f)*LOW_BOD_LENGTH;
        upper_left_leg[1]=lower_bod[1]+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH;

        upper_right_leg[0]=lower_bod[0]+(float)Math.cos(lower_bod[2]*3.14f/180f)*.01f+(float)Math.sin(lower_bod[2]*3.14f/180f)*LOW_BOD_LENGTH;
        upper_right_leg[1]=lower_bod[1]+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH;

        lower_left_leg[0]=dir*upper_left_leg[0]+dir*(float)Math.cos(upper_left_leg[2]*3.14f/180f)*.021f+(float)Math.sin(upper_left_leg[2]*3.14f/180f)*UPPER_LEG_LENGTH;
        lower_left_leg[1]=upper_left_leg[1]+(float)Math.cos(upper_left_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH;

        lower_right_leg[0]=dir*upper_right_leg[0]+dir*(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-.005f+(float)Math.sin(upper_right_leg[2]*3.14f/180f)*UPPER_LEG_LENGTH;
        lower_right_leg[1]=upper_right_leg[1]+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH;

        diff= y-lower_left_leg[1]+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*.1f;
    }



}
