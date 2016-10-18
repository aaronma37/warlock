package com.example.warlock;

import android.animation.Keyframe;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 9/19/16.
 */
public class Skeleton {

    public PhysicalState state;
    public float count=0;

    public float y=0;
    public float diff=0;
    public float height=.5f;
    public float GROUND=.15f;

    public float og_x;
    public float og_y;

    public float action_x;
    public float action_y;

    public boolean cycle=false;
    public int dir;
    public int skeleton_dir=1;

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
    public float UPPER_BOD_LENGTH_2 = .045f;
    public float UPPER_ARM_OFFSET_1 = -.02f;
    public float UPPER_ARM_OFFSET_2 = -.02f;


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
    public List<KeyFrame> sprint_animation = new ArrayList<>();
    public List<KeyFrame> cast_1_animation = new ArrayList<>();
    public List<KeyFrame> knockback_animation = new ArrayList<>();

    public List<List<List<List<KeyFrame>>>> action_animation = new ArrayList<List<List<List<KeyFrame>>>>();





    public Skeleton(){
        state = new PhysicalState();

        upper_bod[0]=0;
        upper_bod[1]=0;
        upper_bod[2]=5;

        lower_bod[0]=upper_bod[0]+(float)Math.cos(upper_bod[2]*3.14f/180f)*UPPER_BOD_LENGTH_2-(float)Math.sin(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH;
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
        idle_animation.add(new KeyFrame(0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0,0, 30,0));
        idle_animation.add(new KeyFrame(0,0,0,3,0,5,15,-5,-5,2,-5,2,-5, 0,0, 30,60,0));
        idle_animation.add(new KeyFrame(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,60,90,0));
        idle_animation.add(new KeyFrame(0,0,0,-3,0,-5,-5,5,15,2,-5,2,-5,0,0,90,120,0));
        idle_animation.add(new KeyFrame(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,120,120,0));

        knockback_animation.add(new KeyFrame(0,0,0,-20,20,    50,50,50,50,   50,50,50,50, 0,0,   0,30,0));
        knockback_animation.add(new KeyFrame(0,0,0,-20,20,    70,70,70,70,   50,50,50,50, 0,0, 30,60,0));
        knockback_animation.add(new KeyFrame(0,0,0,-20,20,    50,50,50,50,   50,50,50,50,  0,0,  60,60,0));

        run_animation.add(new KeyFrame(0,0,0,0,0,   0,10,0,10,        0,0,0,0,0,0,0,5,0));
        run_animation.add(new KeyFrame(0,0,0,0,0,   -15,0,15,30,    20,0,-15,-15,0,0,5,10,0));
        run_animation.add(new KeyFrame(0,0,0,0,0,   -5,0,5,20,      15,0,-10,-10,0,0,10,15,0));
        run_animation.add(new KeyFrame(0,0,0,0,0,   0,10,0,10,        0,0,0,0,0,0,15,20,0));
        run_animation.add(new KeyFrame(0,0,0,0,0,   15,30,-15,10,   -15,-15,20,0,0,0,20,25,0));
        run_animation.add(new KeyFrame(0,0,0,0,0,   5,20,-5,10,     -10,0-10,10,0,0,0,25,30,0));
        run_animation.add(new KeyFrame(0,0,0,0,0,   0,10,0,10,        0,0,0,0,0,0,30,30,0));

        sprint_animation.add(new KeyFrame(50,50,50,-50,-50,   -70,-70,-70,-70,        0,0,0,0,0,0,0,3,0));
        sprint_animation.add(new KeyFrame(50,50,50,-30,-40,   -70,-70,-70,-70,        0,0,-85,-85,0,0,3,6,0));
        sprint_animation.add(new KeyFrame(50,50,50,-40,-45,   -70,-70,-70,-70,        -5,-45,-40,-40,0,0,6,9,0));
        sprint_animation.add(new KeyFrame(50,50,50,-50,-50,   -70,-70,-70,-70,        0,0,0,0,0,0,9,12,0));
        sprint_animation.add(new KeyFrame(50,50,50,-30,-40,   -70,-70,-70,-70,        -85,-85,0,0,0,0,12,15,0));
        sprint_animation.add(new KeyFrame(50,50,50,-40,-45,   -70,-70,-70,-70,        -40,-40,-5,-45,0,0,15,18,0));
        sprint_animation.add(new KeyFrame(50,50,50,-50,-50,   -70,-70,-70,-70,        0,0,0,0,0,0,18,18,0));

        cast_1_animation.add(new KeyFrame(0,0,0,0,0,    50,100,50,100,0,0,0,0,0,0,0,30,0));
        cast_1_animation.add(new KeyFrame(0,0,0,3,0,    70,115,70,115,2,-5,2,-5,0,0,30,60,0));
        cast_1_animation.add(new KeyFrame(0,0,0,0,0,    50,100,50,100,0,0,0,0,0,0,60,60,0));
        
        set_action_keyframes();

    }

    public void set_action_keyframes(){

        //fire
        List<List<List<KeyFrame>>> fire = new ArrayList<List<List<KeyFrame>>>();


        //magic fire
        List<List<KeyFrame>> magical_fire = new ArrayList<List<KeyFrame>>();



        List<KeyFrame> unnamed = new ArrayList<>();
        unnamed.add(new KeyFrame(0,0,0,0,0,    50,100,50,100,0,0,0,0,0,0,0,15,0));
        unnamed.add(new KeyFrame(0,0,0,0,0,    50,100,50,100,0,0,0,0,-.05f,0,15,15,0));
        unnamed.add(new KeyFrame(0,0,0,0,0,    50,100,50,100,0,0,0,0,-.05f,0,15,15,0));

        List<KeyFrame> fireball = new ArrayList<>();
        fireball.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        fireball.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,     0,0,  5,10, 0));
        fireball.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    -.1f,0,  10,15, 0));
        fireball.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    -.1f,0,  15,15, 0));

        List<KeyFrame> firespray = new ArrayList<>();
        firespray.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,   0,0,  0,5, 0));
        firespray.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,     0,0,  5,5, 0));

        List<KeyFrame> fireblast = new ArrayList<>();
        fireblast.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,   0,0,  0,15, 0));
        fireblast.add(new KeyFrame(0,0,0,0,0,    0,0,180,180,   0,0,0,0,     0,0,  15,25, 0));
        fireblast.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,     0,0,  25,25, 0));



        List<KeyFrame> lsa = new ArrayList<>();
        lsa.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,15, 0));
        lsa.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,     0,0,  15,35, 0));
        lsa.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,     0,0,  35,35, 0));

        List<KeyFrame> sunstrike = new ArrayList<>();
        sunstrike.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,15, 0));
        sunstrike.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,     0,0,  15,35, 0));
        sunstrike.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,     0,0,  35,35, 0));

        List<KeyFrame> flameguard = new ArrayList<>();
        flameguard.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,15, 0));
        flameguard.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,     0,0,  15,35, 0));
        flameguard.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,     0,0,  35,35, 0));

        List<KeyFrame> flamewall = new ArrayList<>();
        flamewall.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,15, 0));
        flamewall.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,     0,0,  15,35, 0));
        flamewall.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,     0,0,  35,35, 0));

        List<KeyFrame> inferno = new ArrayList<>();
        inferno.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,15, 0));
        inferno.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,     0,0,  15,35, 0));
        inferno.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,     0,0,  35,35, 0));




        List<KeyFrame> palmblast = new ArrayList<>();
        palmblast.add(new KeyFrame(0,0,0,0,0,    0,0,0,90,   0,0,0,0,0,0,0,5,0));
        palmblast.add(new KeyFrame(0,0,0,-45,0,    0,0,90,90,   0,0,0,0,.05f,0,5,7,0));
        palmblast.add(new KeyFrame(0,0,0,-45,0,    0,0,0,0,   0,0,0,0,-.05f,0,7,7,0));
        palmblast.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,-.05f,0,7,7,0));

        List<KeyFrame> axeattack = new ArrayList<>();
        axeattack.add(new KeyFrame(0,0,0,0,0,    0,0,180,180,   0,0,0,0,0,0,0,10,0));
        axeattack.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,0,0,10,12,0));
        axeattack.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,0,0,12,12,0));
        axeattack.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,0,0,12,12,0));


        List<KeyFrame> spinningaxe = new ArrayList<>();
        spinningaxe.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,0,0,  0,5, 0));
        spinningaxe.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,0,0,  5,10, 1));
        spinningaxe.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,0,0,  10,15, 0));
        spinningaxe.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,0,0,  15,20, 1));
        spinningaxe.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,0,0,  20,25, 0));
        spinningaxe.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,0,0,  25,30, 1));
        spinningaxe.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,0,0,  30,30, 0));
        spinningaxe.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,0,0,  30,30, 1));


        List<KeyFrame> dragon_kick = new ArrayList<>();
        dragon_kick.add(new KeyFrame(0,0,0,0,0,    0,0,0,90,   0,0,0,0,0,0,0,10,0));
        dragon_kick.add(new KeyFrame(0,0,0,45,45,    0,0,90,90,   0,0,0,0,1.05f,0,10,12,0));
        dragon_kick.add(new KeyFrame(0,0,0,45,45,    0,0,0,0,   0,0,0,0,1f,0,12,12,0));
        dragon_kick.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,1f,0,12,12,0));

        List<KeyFrame> dragonpunch = new ArrayList<>();
        dragonpunch.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        dragonpunch.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,     0,0,  5,10, 0));
        dragonpunch.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    .4f,0,  10,15, 0));
        dragonpunch.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    .4f,0,  15,15, 0));

        List<KeyFrame> dragonwheel = new ArrayList<>();
        dragonwheel.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        dragonwheel.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,     0,0,  5,10, 0));
        dragonwheel.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    .4f,-.4f,  10,15, 0));
        dragonwheel.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    .4f,-.4f,  15,15, 0));

        List<KeyFrame> charge = new ArrayList<>();
        charge.add(new KeyFrame(0,0,0,0,0,    0,0,0,90,   0,0,0,0,0,0,0,10,0));
        charge.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,1.05f,0,10,12,0));
        charge.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,1f,0,12,12,0));
        charge.add(new KeyFrame(0,0,0,0,0,    0,0,0,0,   0,0,0,0,1f,0,12,12,0));

        List<KeyFrame> lance_barrage = new ArrayList<>();
        lance_barrage.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        lance_barrage.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,     0,0,  5,10, 0));
        lance_barrage.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,    .4f,0,  10,15, 0));
        lance_barrage.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    .4f,0,  15,20, 0));
        lance_barrage.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,    .4f,0,  20,25, 0));
        lance_barrage.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    .4f,0,  25,30, 0));
        lance_barrage.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,    .4f,0,  30,35, 0));
        lance_barrage.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    .4f,0,  35,40, 0));
        lance_barrage.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,    .4f,0,  40,40, 0));

        List<KeyFrame> shield_bash = new ArrayList<>();
        shield_bash.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        shield_bash.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,     0,0,  5,10, 0));
        shield_bash.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,    .4f,0,  10,15, 0));
        shield_bash.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    .4f,0,  15,20, 0));

        List<KeyFrame> wham = new ArrayList<>();
        wham.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        wham.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,     0,0,  5,10, 0));
        wham.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,    .4f,.4f,  10,15, 0));
        wham.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,    .4f,0,  15,20, 0));
        wham.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,    .4f,0,  20,20, 0));

        List<KeyFrame> upper_slash = new ArrayList<>();
        upper_slash.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        upper_slash.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,     0,0,  5,10, 0));
        upper_slash.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,    .1f,.8f,  10,15, 0));
        upper_slash.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,    .1f,.8f,  15,15, 0));


        List<KeyFrame> heal = new ArrayList<>();
        heal.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        heal.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,     0,0,  5,10, 0));
        heal.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    0,0,  10,15, 0));
        heal.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    0,0,  15,15, 0));

        List<KeyFrame> HoT = new ArrayList<>();
        HoT.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        HoT.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,     0,0,  5,10, 0));
        HoT.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    0,0,  10,15, 0));
        HoT.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    0,0,  15,15, 0));

        List<KeyFrame> Dispel = new ArrayList<>();
        Dispel.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        Dispel.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,     0,0,  5,10, 0));
        Dispel.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    0,0,  10,15, 0));
        Dispel.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    0,0,  15,15, 0));

        List<KeyFrame> HolyShield = new ArrayList<>();
        HolyShield.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        HolyShield.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,     0,0,  5,10, 0));
        HolyShield.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    0,0,  10,15, 0));
        HolyShield.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    0,0,  15,15, 0));

        List<KeyFrame> silence = new ArrayList<>();
        silence.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,   0,0,  0,5, 0));
        silence.add(new KeyFrame(0,0,0,0,0,    0,0,-45,90,   0,0,0,0,     0,0,  5,10, 0));
        silence.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    0,0,  10,15, 0));
        silence.add(new KeyFrame(0,0,0,0,0,    0,0,90,90,   0,0,0,0,    0,0,  15,15, 0));






        magical_fire.add(fireball);
        magical_fire.add(firespray);
        magical_fire.add(fireblast);
        magical_fire.add(lsa);
        magical_fire.add(sunstrike);
        magical_fire.add(flameguard);
        magical_fire.add(flamewall);
        magical_fire.add(inferno);
        magical_fire.add(unnamed);

        fire.add(magical_fire);

        //physical fire
        List<List<KeyFrame>> physical_fire = new ArrayList<List<KeyFrame>>();

            physical_fire.add(palmblast);
            physical_fire.add(axeattack);
            physical_fire.add(spinningaxe);
            physical_fire.add(dragon_kick);
            physical_fire.add(dragonpunch);
            physical_fire.add(dragonwheel);

            fire.add(physical_fire);

            action_animation.add(fire);

        //light
        List<List<List<KeyFrame>>> light = new ArrayList<List<List<KeyFrame>>>();
        //magic light
        List<List<KeyFrame>> magical_light = new ArrayList<List<KeyFrame>>();

        magical_light.add(heal);
        magical_light.add(HoT);
        magical_light.add(Dispel);
        magical_light.add(HolyShield);
        magical_light.add(silence);
        magical_light.add(unnamed);
        magical_light.add(unnamed);
        magical_light.add(unnamed);
        magical_light.add(unnamed);
        magical_light.add(unnamed);

        light.add(magical_light);

        //physical light
        List<List<KeyFrame>> physical_light = new ArrayList<List<KeyFrame>>();

        physical_light.add(charge);
        physical_light.add(lance_barrage);
        physical_light.add(shield_bash);
        physical_light.add(wham);
        physical_light.add(upper_slash);
        physical_light.add(unnamed);
        physical_light.add(unnamed);
        physical_light.add(unnamed);
        physical_light.add(unnamed);
        physical_light.add(unnamed);

        light.add(physical_light);

        action_animation.add(light);

        //dark
        List<List<List<KeyFrame>>> dark = new ArrayList<List<List<KeyFrame>>>();
        //magic dark
        List<List<KeyFrame>> magical_dark = new ArrayList<List<KeyFrame>>();

        magical_dark.add(unnamed);
        magical_dark.add(unnamed);
        magical_dark.add(unnamed);
        magical_dark.add(unnamed);
        magical_dark.add(unnamed);
        magical_dark.add(unnamed);
        magical_dark.add(unnamed);
        magical_dark.add(unnamed);
        magical_dark.add(unnamed);


        dark.add(magical_dark);

        //physical dark
        List<List<KeyFrame>> physical_dark = new ArrayList<List<KeyFrame>>();

        physical_dark.add(unnamed);
        physical_dark.add(unnamed);
        physical_dark.add(unnamed);
        physical_dark.add(unnamed);
        physical_dark.add(unnamed);
        physical_dark.add(unnamed);
        physical_dark.add(unnamed);
        physical_dark.add(unnamed);
        physical_dark.add(unnamed);
        physical_dark.add(unnamed);
        physical_dark.add(unnamed);


        dark.add(physical_dark);

        action_animation.add(dark);

        //water
        List<List<List<KeyFrame>>> water = new ArrayList<List<List<KeyFrame>>>();
        //magic light
        List<List<KeyFrame>> magical_water= new ArrayList<List<KeyFrame>>();

        magical_water.add(unnamed);
        magical_water.add(unnamed);
        magical_water.add(unnamed);
        magical_water.add(unnamed);
        magical_water.add(unnamed);
        magical_water.add(unnamed);
        magical_water.add(unnamed);
        magical_water.add(unnamed);
        magical_water.add(unnamed);

        water.add(magical_water);

        //physical fire
        List<List<KeyFrame>> physical_water = new ArrayList<List<KeyFrame>>();

        physical_water.add(unnamed);
        physical_water.add(unnamed);
        physical_water.add(unnamed);
        physical_water.add(unnamed);
        physical_water.add(unnamed);
        physical_water.add(unnamed);
        physical_water.add(unnamed);
        physical_water.add(unnamed);
        physical_water.add(unnamed);
        physical_water.add(unnamed);
        physical_water.add(unnamed);

        water.add(physical_water);

        action_animation.add(water);

    }

    public void set_xy(float i_x, float i_y){
        if (state.state!=7){
            og_x=i_x;
            og_y=i_y;
        }
    }

    public void update_state(PhysicalState i_state){


        if (state.state!=i_state.state){
            count=0;
            state.state=i_state.state;
            state.spirit_type=i_state.spirit_type;
            state.meta_type=i_state.meta_type;
            state.spell_type=i_state.spell_type;

            set_lengths(state.state);
        }
    }

    public void step(PhysicalState i_state, int i_dir, float i_x, float i_y){
        y=i_y;
        dir=i_dir;
        count++;
        cycle=false;

        set_xy(i_x, i_y);
        update_state(i_state);

        if (state.state==0){

            if (count>idle_animation.get(idle_animation.size()-1).end){
                count=0;
            }

            for (int i=0;i<idle_animation.size();i++){
                if(count<idle_animation.get(i).end){
                    update_angle(dir, idle_animation.get(i),idle_animation.get(i+1), (1f-(count-idle_animation.get(i).begin)/(idle_animation.get(i).end-idle_animation.get(i).begin)));
                    break;
                }

            }

        }else if (state.state==1){
            //CAST ANIMATION
            if (count>cast_1_animation.get(cast_1_animation.size()-1).end){
                count=0;
            }
            for (int i=0;i<cast_1_animation.size();i++){
                if(count<cast_1_animation.get(i).end){
                    update_angle(dir, cast_1_animation.get(i),cast_1_animation.get(i+1), (1f-(count-cast_1_animation.get(i).begin)/(cast_1_animation.get(i).end-cast_1_animation.get(i).begin)));
                    break;
                }

            }


        }else if (state.state==2){
            //KNOCKBACK ANIMATION
            if (count>knockback_animation.get(knockback_animation.size()-1).end){
                count=0;
            }
            for (int i=0;i<knockback_animation.size();i++){
                if(count<knockback_animation.get(i).end){
                    update_angle(dir, knockback_animation.get(i),knockback_animation.get(i+1), (1f-(count-knockback_animation.get(i).begin)/(knockback_animation.get(i).end-knockback_animation.get(i).begin)));
                    break;
                }

            }


        } else if (state.state==3){
            //RUN ANIMATION
            if (count>run_animation.get(run_animation.size()-1).end){
                count=0;
            }
            for (int i=0;i<run_animation.size();i++){
                if(count<run_animation.get(i).end){
                    update_angle(dir, run_animation.get(i),run_animation.get(i+1), (1f-(count-run_animation.get(i).begin)/(run_animation.get(i).end-run_animation.get(i).begin)));
                    break;
                }

            }


        }else if (state.state==4){
            //Sprint ANIMATION
            if (count>sprint_animation.get(sprint_animation.size()-1).end){
                count=0;
            }
            for (int i=0;i<sprint_animation.size();i++){
                if(count<sprint_animation.get(i).end){
                    update_angle(dir, sprint_animation.get(i),sprint_animation.get(i+1), (1f-(count-sprint_animation.get(i).begin)/(sprint_animation.get(i).end-sprint_animation.get(i).begin)));
                    break;
                }

            }
        }else if (state.state==7){
            //ACTION ANIMATION
            if (count>action_animation.get(state.spirit_type).get(state.meta_type).get(state.spell_type).get(action_animation.get(state.spirit_type).get(state.meta_type).get(state.spell_type).size()-1).end){
                count=0;
                cycle=true;
            }
            for (int i=0;i<action_animation.get(state.spirit_type).get(state.meta_type).get(state.spell_type).size();i++){
                if(count<action_animation.get(state.spirit_type).get(state.meta_type).get(state.spell_type).get(i).end){
                    update_angle(dir,action_animation.get(state.spirit_type).get(state.meta_type).get(state.spell_type).get(i),action_animation.get(state.spirit_type).get(state.meta_type).get(state.spell_type).get(i+1), (1f-(count-action_animation.get(state.spirit_type).get(state.meta_type).get(state.spell_type).get(i).begin)/(action_animation.get(state.spirit_type).get(state.meta_type).get(state.spell_type).get(i).end-action_animation.get(state.spirit_type).get(state.meta_type).get(state.spell_type).get(i).begin)));
                    skeleton_dir=action_animation.get(state.spirit_type).get(state.meta_type).get(state.spell_type).get(i).dir;
                    break;
                }

            }
        }

        recalculate_skeleton();
    }

    public void set_lengths(int st){
        if (st==0 || st == 1 || st == 2 || st == 3){
            UPPER_BOD_LENGTH = .15f;
            UPPER_BOD_LENGTH_2 = .045f;
            UPPER_ARM_LENGTH = .13f;
            NECK_LENGTH = .11f;
            LOW_BOD_LENGTH =.14f;
            UPPER_LEG_LENGTH = .2f;
            LOWER_LEG_LENGTH = .15f;
            UPPER_ARM_OFFSET_1 = -.02f;
            UPPER_ARM_OFFSET_2 = -.02f;
        }else if (st == 4){
            UPPER_BOD_LENGTH = .15f;
            UPPER_BOD_LENGTH_2 = .07f;
            UPPER_ARM_LENGTH = .13f;
            NECK_LENGTH = .11f;
            LOW_BOD_LENGTH =.12f;
            UPPER_LEG_LENGTH = .2f;
            LOWER_LEG_LENGTH = .15f;
            UPPER_ARM_OFFSET_1 = -.02f;
            UPPER_ARM_OFFSET_2 = .01f;
        }
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

        action_x=og_x+dir*(anim_2.x+rng*(anim_1.x-anim_2.x));
        action_y=og_y+dir*(anim_2.y+rng*(anim_1.y-anim_2.y));

    }

    public float calculate_y(){
        if (state.state==0||state.state==3 || state.state==1 || state.state==4){
            height=-GROUND+y;

            if (((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_left_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_left_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH) < ((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_right_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH)){
                return ((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_left_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_left_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH);
            }else{
                return ((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_right_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH);
            }
        } else{
            height=-GROUND+y;
            if (((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_left_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_left_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH) < ((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_right_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH)){
                return ((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_left_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_left_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH);
            }else{
                return ((float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH-(float)Math.cos(lower_right_leg[2]*3.14f/180f)*LOWER_LEG_LENGTH);
            }
        }

    }


    public void recalculate_skeleton(){
        diff = calculate_y();


        upper_bod[0]=0;
        upper_bod[1]=-.5f+height-diff;

        lower_bod[0]=dir*upper_bod[0]+(float)Math.cos(upper_bod[2]*3.14f/180f)*UPPER_BOD_LENGTH_2+dir*(float)Math.sin(upper_bod[2]*3.14f/180f)*UPPER_BOD_LENGTH;
        lower_bod[1]=upper_bod[1]+(float)Math.cos(upper_bod[2]*3.14f/180f)*-UPPER_BOD_LENGTH;

        upper_left_arm[0]=upper_bod[0]+UPPER_ARM_OFFSET_1;
        upper_left_arm[1]=upper_bod[1]+UPPER_ARM_OFFSET_2;

        lower_left_arm[0]=dir*upper_left_arm[0]+(float)Math.sin(upper_left_arm[2]*3.14f/180f)*UPPER_ARM_LENGTH;
        lower_left_arm[1]=upper_left_arm[1]+(float)Math.cos(upper_left_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;

        upper_right_arm[0]=upper_bod[0]+.065f;
        upper_right_arm[1]=upper_bod[1]-.02f;

        lower_right_arm[0]=dir*upper_right_arm[0]+(float)Math.sin(upper_right_arm[2]*3.14f/180f)*UPPER_ARM_LENGTH;
        lower_right_arm[1]=upper_right_arm[1]+(float)Math.cos(upper_right_arm[2]*3.14f/180f)*-UPPER_ARM_LENGTH;


        neck[0]=upper_bod[0];
        neck[1]=upper_bod[1];

        head[0]=upper_bod[0]+dir*(float)Math.sin(neck[2]*3.14f/180f)*NECK_LENGTH;
        head[1]=upper_bod[1]+(float)Math.cos(neck[2]*3.14f/180f)*NECK_LENGTH;

        hair[0]=head[0]-dir*(float)Math.sin(head[2])*.07f;
        hair[1]=head[1]+(float)Math.cos(head[2])*.07f;

        upper_left_leg[0]=lower_bod[0]+(float)Math.cos(lower_bod[2]*3.14f/180f)*-.05f+dir*(float)Math.sin(lower_bod[2]*3.14f/180f)*LOW_BOD_LENGTH;
        upper_left_leg[1]=lower_bod[1]+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH;

        upper_right_leg[0]=lower_bod[0]+(float)Math.cos(lower_bod[2]*3.14f/180f)*.01f+dir*(float)Math.sin(lower_bod[2]*3.14f/180f)*LOW_BOD_LENGTH;
        upper_right_leg[1]=lower_bod[1]+(float)Math.cos(lower_bod[2]*3.14f/180f)*-LOW_BOD_LENGTH;

        lower_left_leg[0]=dir*upper_left_leg[0]+dir*(float)Math.cos(upper_left_leg[2]*3.14f/180f)*.021f+(float)Math.sin(upper_left_leg[2]*3.14f/180f)*UPPER_LEG_LENGTH;
        lower_left_leg[1]=upper_left_leg[1]+(float)Math.cos(upper_left_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH;

        lower_right_leg[0]=dir*upper_right_leg[0]+dir*(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-.005f+(float)Math.sin(upper_right_leg[2]*3.14f/180f)*UPPER_LEG_LENGTH;
        lower_right_leg[1]=upper_right_leg[1]+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*-UPPER_LEG_LENGTH;

        //diff= y-lower_left_leg[1]+(float)Math.cos(upper_right_leg[2]*3.14f/180f)*.1f;
    }



}
