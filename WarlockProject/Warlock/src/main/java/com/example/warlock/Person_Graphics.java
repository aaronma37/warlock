package com.example.warlock;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 9/12/16.
 */
public class Person_Graphics {

    public Person_Graphics_Asset hair;
    public Person_Graphics_Asset face;
    public Person_Graphics_Asset eyes;
    public Person_Graphics_Asset upper_body;
    public Person_Graphics_Asset lower_body;
    public Person_Graphics_Asset upper_right_arm;
    public Person_Graphics_Asset lower_right_arm;
    public Person_Graphics_Asset upper_left_arm;
    public Person_Graphics_Asset lower_left_arm;
    public Person_Graphics_Asset upper_left_leg;
    public Person_Graphics_Asset lower_left_leg;
    public Person_Graphics_Asset upper_right_leg;
    public Person_Graphics_Asset lower_right_leg;

    public int i_eye=0, num_eye_assets=0;
    public int i_body=0, num_body_assets=0;
    public int i_legs=0, num_legs_assets=0;
    public int i_hair=0, num_hair_assets=0;
    public int i_face=0, num_face_assets=0;

    private int MAX_EYE_MODEL=5,MAX_BODY_MODEL=5,MAX_HAIR_MODEL=5,MAX_LEGS_MODEL=5,MAX_FACE_MODEL=5;

    public Asset_Motion_Model eye_motion_model[] = new Asset_Motion_Model[MAX_EYE_MODEL];
    public Asset_Motion_Model body_motion_model[] = new Asset_Motion_Model[MAX_BODY_MODEL];
    public Asset_Motion_Model hair_motion_model[] = new Asset_Motion_Model[MAX_HAIR_MODEL];
    public Asset_Motion_Model legs_motion_model[] = new Asset_Motion_Model[MAX_LEGS_MODEL];
    public Asset_Motion_Model face_motion_model[] = new Asset_Motion_Model[MAX_FACE_MODEL];



    public Skeleton skeleton;

    public Context myContext;

    public int head_counter=0;
    public int HEAD_STATIC_MAX=30;
    public float ADJ_SCALE=1.5f;
    static int X_LOC=0;
    static int Y_LOC=1;
    public float head_position_static[][] = new float[HEAD_STATIC_MAX][2];
    public int body_sprite_count=0;
    public float overall_scale=.7f;

    public List<Person_Graphics_Asset_Asset> pre_hair_assets = new ArrayList<>();
    public List<Person_Graphics_Asset_Asset> post_hair_assets = new ArrayList<>();
    public List<Person_Graphics_Asset_Asset> post_body_assets = new ArrayList<>();




    public Person_Graphics(Context context, int hair_index, int face_index, int eyes_index, int body_index, int leg_index, Global_Assets assets){

        myContext=context;

        i_eye=eyes_index;
        i_body=body_index;
        i_legs=leg_index;
        i_hair=hair_index;
        i_face=face_index;

        for (int i =0;i<MAX_EYE_MODEL;i++){
            eye_motion_model[i]= new Asset_Motion_Model(0,0,0,0,0,0,0);
        }
        for (int i =0;i<MAX_BODY_MODEL;i++){
            body_motion_model[i]= new Asset_Motion_Model(0,0,0,0,0,0,0);
        }
        for (int i =0;i<MAX_HAIR_MODEL;i++){
            hair_motion_model[i]= new Asset_Motion_Model(0,0,0,0,0,0,0);
        }
        for (int i =0;i<MAX_LEGS_MODEL;i++){
            legs_motion_model[i]= new Asset_Motion_Model(0,0,0,0,0,0,0);
        }
        for (int i =0;i<MAX_FACE_MODEL;i++){
            face_motion_model[i]= new Asset_Motion_Model(0,0,0,0,0,0,0);
        }



        skeleton = new Skeleton();

        set_non_sprite_movements();
/*

        if (face_index==0){
            face = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_head1), 1f,1.3f*ADJ_SCALE*44.826f/1000,ADJ_SCALE*.021f,ADJ_SCALE*-.008f);
        }

        if (hair_index==0){
            hair  = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_h1_base),.15f/.15f,ADJ_SCALE*.1f, ADJ_SCALE*(.035f-face.x_off),ADJ_SCALE*(-face.y_off));

            Person_Graphics_Asset_Asset hair_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h1_add_1),.15f/.15f,ADJ_SCALE*.06f, ADJ_SCALE*(-.1f-face.x_off-hair.x_off),ADJ_SCALE*(.02f-face.y_off-hair.y_off),0f,.2f,.4f,.6f,.8f, .1f,2f);
            post_hair_assets.add(hair_asset_1);
        }else if (hair_index==2){
            hair  = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_h2_base),19.879f/46.368f,1.3f*ADJ_SCALE*46.368f/1000, ADJ_SCALE*(.025f)-face.x_off,ADJ_SCALE*(.03f)-face.y_off);

            Person_Graphics_Asset_Asset hair_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h2_asset_1),99.416f/37.059f,1.3f*ADJ_SCALE*37.059f/1000, 1.3f*ADJ_SCALE*(0.005f)-face.x_off-hair.x_off,1.3f*ADJ_SCALE*(-0.080f)-face.y_off-hair.y_off,0f,.2f,.4f,.6f,.8f, .1f,.2f);
            post_hair_assets.add(hair_asset_1);

            Person_Graphics_Asset_Asset hair_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h2_asset_2),50.217f/36.993f,1.3f*ADJ_SCALE*36.993f/1000, 1.3f*ADJ_SCALE*(.028f)-face.x_off-hair.x_off,1.3f*ADJ_SCALE*(-.028f)-face.y_off-hair.y_off,0f,.2f,.4f,.6f,.8f, .05f,.13f);
            post_hair_assets.add(hair_asset_2);

            Person_Graphics_Asset_Asset hair_asset_3 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h2_asset_3),59.797f/5.595f,1.3f*ADJ_SCALE*5.595f/1000, 1.3f*ADJ_SCALE*(.04f)-face.x_off-hair.x_off,1.3f*ADJ_SCALE*(-.08f)-face.y_off-hair.y_off,0f,.2f,.4f,.6f,.8f, .01f, .13f);
            pre_hair_assets.add(hair_asset_3);
        }

        if (eyes_index==0){
            eyes = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_eyes1),1f,1.3f*ADJ_SCALE*16.143f/1000,1.3f*ADJ_SCALE*-.003f,1.3f*ADJ_SCALE*-.008f);
        }

        if (body_index==0){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);

            Person_Graphics_Asset_Asset body_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_1_asset_1),1,ADJ_SCALE*27f/1000, ADJ_SCALE*(.060f),ADJ_SCALE*(-.032f),0f,.1f,.1f,.2f,.2f, .03f, .13f);
            post_body_assets.add(body_asset_2);

            Person_Graphics_Asset_Asset body_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_1_asset_2),1,ADJ_SCALE*30f/1000, ADJ_SCALE*(.045f),ADJ_SCALE*(-.035f),0f,.1f,.1f,.2f,.2f, .03f, .13f);
            post_body_assets.add(body_asset_1);


        }else if (body_index==1){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);

            Person_Graphics_Asset_Asset body_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_2_asset_1),1,ADJ_SCALE*27f/1000, ADJ_SCALE*(.060f),ADJ_SCALE*(-.032f),0f,.1f,.1f,.2f,.2f, .03f, .13f);
            post_body_assets.add(body_asset_2);

            Person_Graphics_Asset_Asset body_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_2_asset_2),1,ADJ_SCALE*30f/1000, ADJ_SCALE*(.045f),ADJ_SCALE*(-.035f),0f,.1f,.1f,.2f,.2f, .03f, .13f);
            post_body_assets.add(body_asset_1);
        }else if (body_index==2){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);

            Person_Graphics_Asset_Asset body_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_3_asset_1),1,ADJ_SCALE*27f/1000, ADJ_SCALE*(.060f),ADJ_SCALE*(-.032f),0f,.1f,.1f,.2f,.2f, .03f, .13f);
            post_body_assets.add(body_asset_2);

            Person_Graphics_Asset_Asset body_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_3_asset_2),1,ADJ_SCALE*30f/1000, ADJ_SCALE*(.045f),ADJ_SCALE*(-.035f),0f,.1f,.1f,.2f,.2f, .03f, .13f);
            post_body_assets.add(body_asset_1);
        }else if (body_index==3){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);

            Person_Graphics_Asset_Asset body_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_4_asset_1),1,ADJ_SCALE*27f/1000, ADJ_SCALE*(.060f),ADJ_SCALE*(-.032f),0f,.1f,.1f,.2f,.2f, .03f, .13f);
            post_body_assets.add(body_asset_2);

            Person_Graphics_Asset_Asset body_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_4_asset_2),1,ADJ_SCALE*30f/1000, ADJ_SCALE*(.045f),ADJ_SCALE*(-.035f),0f,.1f,.1f,.2f,.2f, .03f, .13f);
            post_body_assets.add(body_asset_1);
        }else if (body_index==4){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);

            Person_Graphics_Asset_Asset body_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_5_asset_1),1,ADJ_SCALE*27f/1000, ADJ_SCALE*(.060f),ADJ_SCALE*(-.032f),0f,.1f,.1f,.2f,.2f, .03f, .13f);
            post_body_assets.add(body_asset_2);

            Person_Graphics_Asset_Asset body_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_5_asset_2),1,ADJ_SCALE*30f/1000, ADJ_SCALE*(.045f),ADJ_SCALE*(-.035f),0f,.1f,.1f,.2f,.2f, .03f, .13f);
            post_body_assets.add(body_asset_1);
        }

        if (leg_index==0){
            upper_left_leg = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_legs_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 0, 1f/3f, 0f, .5f);
            lower_left_leg = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_legs_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 0, 1f/3f, .5f, 1f);
            upper_right_leg = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_legs_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);
            lower_right_leg = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_legs_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, .5f, 1f);
        }
*/

        justify_number_of_assets(assets);
    }

    public void justify_number_of_assets(Global_Assets assets){
        num_eye_assets=assets.equipment_assets_Eyes[i_eye].dynamic_asset_number;
        for (int i =0;i<num_eye_assets;i++){
            eye_motion_model[i]=assets.equipment_assets_Eyes[i_eye].dynamic_assets.get(i).model;
        }

        num_body_assets=assets.equipment_assets_body[i_body].dynamic_asset_number;
        for (int i =0;i<num_body_assets;i++){
            body_motion_model[i]=assets.equipment_assets_body[i_body].dynamic_assets.get(i).model;
        }

        num_hair_assets=assets.equipment_assets_Hair[i_hair].dynamic_asset_number;
        for (int i =0;i<num_hair_assets;i++){
            hair_motion_model[i]= assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).model;
        }

        num_legs_assets=assets.equipment_assets_legs[i_legs].dynamic_asset_number;
        for (int i =0;i<num_legs_assets;i++){
            legs_motion_model[i]= assets.equipment_assets_legs[i_legs].dynamic_assets.get(i).model;
        }

        num_face_assets=assets.equipment_assets_Face[i_face].dynamic_asset_number;
        for (int i =0;i<num_face_assets;i++){
            face_motion_model[i]= assets.equipment_assets_Face[i_face].dynamic_assets.get(i).model;
        }
    }

    public void set_equipment_indices(int eye_index, int hair_index, int body_index, int leg_index, int face_index, Global_Assets assets){
        i_eye=eye_index;
        i_body=body_index;
        i_legs=leg_index;
        i_hair=hair_index;
        i_face=face_index;

        justify_number_of_assets(assets);
    }

    public void set_non_sprite_movements(){


        for (int i=0;i<HEAD_STATIC_MAX;i++){
            head_position_static[i][X_LOC]=0;
            head_position_static[i][Y_LOC]=0;
        }

        head_position_static[1][X_LOC]=0.001f/2;
        head_position_static[2][X_LOC]=0.002f/2;
        head_position_static[3][X_LOC]=0.003f/2;
        head_position_static[4][X_LOC]=0.004f/2;
        head_position_static[5][X_LOC]=.005f/2;
        head_position_static[6][X_LOC]=.004f/2;
        head_position_static[7][X_LOC]=.003f/2;
        head_position_static[8][X_LOC]=.002f/2;
        head_position_static[9][X_LOC]=0.001f/2;

    }

    public void set_body_sprite(int state){

        if (state==0 || state==1 || state ==2){
            body_sprite_count=0;
        }else if (state==3){
            if (body_sprite_count==0){
                body_sprite_count=5;
            }else{
                body_sprite_count+=1;
            }

            if (body_sprite_count>7*5){
                body_sprite_count=5;
            }
        }
    }


    public void resolve_movement(float x_movement, float y_movement, int dir, int state){

        set_body_sprite(state);

        if (state==3 || state==4){
            for (int i =0; i<num_hair_assets;i++){
                hair_motion_model[i].add_force(x_movement,dir);
                //pre_hair_assets.get(i).add_force(x_movement,dir);

            }
/*            for (int i =0; i<post_hair_assets.size();i++){

                post_hair_assets.get(i).add_force(x_movement,dir);

            }*/

            for (int i =0; i<num_body_assets;i++){
                body_motion_model[i].add_force(x_movement,dir);
                //post_body_assets.get(i).add_force(x_movement,-dir);

            }
        }else if (state==0){
            //head bob
            head_counter+=1;
            if (head_counter>=HEAD_STATIC_MAX){
                head_counter=0;
            }
            if (head_counter>3 && head_counter<10){
                for (int i =0; i<num_hair_assets;i++){
                    hair_motion_model[i].add_force(Math.abs(head_position_static[head_counter][X_LOC]-head_position_static[head_counter-1][X_LOC])*1f,dir);
                    //pre_hair_assets.get(i).add_force(x_movement,dir);

                }
            }

        }

    }

    public void draw_person(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], float x, float y, int dir, PhysicalState i_state, Global_Assets assets){

        for (int i =0; i<num_hair_assets;i++){
            if (assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).order==0){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, x+overall_scale*skeleton.hair[i_hair]*dir+overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).x_off*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir,  y+overall_scale*skeleton.hair[1]+overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).y_off+overall_scale*head_position_static[head_counter][Y_LOC], 1f);
                Matrix.scaleM(scratch, 0, overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).size,overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).size*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).AR,.5f);
                Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);
                hair_motion_model[i].step(assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i));
                assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).Draw(scratch,false);
            }
        }

        skeleton.step(i_state,dir, x, y);


        draw_body_part(x,y,1,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_right_arm, assets.equipment_assets_body[i_body].lower_right_arm);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_right_arm, assets.equipment_assets_body[i_body].upper_right_arm);
        draw_body_part(x,y,1,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_right_leg, assets.equipment_assets_legs[i_legs].lower_right_leg);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_right_leg, assets.equipment_assets_legs[i_legs].upper_right_leg);
        draw_body_part(x,y,1,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_left_leg, assets.equipment_assets_legs[i_legs].lower_left_leg);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_left_leg, assets.equipment_assets_legs[i_legs].upper_left_leg);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_bod, assets.equipment_assets_body[i_body].lower_body);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_bod, assets.equipment_assets_body[i_body].upper_body);



        //HEAD
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+overall_scale*skeleton.head[0]*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir, y+overall_scale*skeleton.head[1]+overall_scale*head_position_static[head_counter][Y_LOC], 1f);
        Matrix.scaleM(scratch, 0, overall_scale*assets.equipment_assets_Face[i_face].face.size,overall_scale*assets.equipment_assets_Face[i_face].face.size*assets.equipment_assets_Face[i_face].face.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        assets.equipment_assets_Face[i_face].face.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+overall_scale*skeleton.head[0]*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir+.01f*dir+overall_scale*assets.equipment_assets_Eyes[i_eye].eyes.x_off, y+overall_scale*skeleton.head[1]+overall_scale*head_position_static[head_counter][Y_LOC]+overall_scale*assets.equipment_assets_Eyes[i_eye].eyes.y_off, 1f);
        Matrix.scaleM(scratch, 0, overall_scale*assets.equipment_assets_Eyes[i_eye].eyes.size,overall_scale*assets.equipment_assets_Eyes[i_eye].eyes.size*assets.equipment_assets_Eyes[i_eye].eyes.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        assets.equipment_assets_Eyes[i_eye].eyes.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+overall_scale*skeleton.hair[0]*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir, y+overall_scale*skeleton.hair[1]+overall_scale*head_position_static[head_counter][Y_LOC], 1f);
        Matrix.scaleM(scratch, 0, overall_scale*assets.equipment_assets_Hair[i_hair].hair.size,overall_scale*assets.equipment_assets_Hair[i_hair].hair.size*assets.equipment_assets_Hair[i_hair].hair.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        assets.equipment_assets_Hair[i_hair].hair.Draw(scratch,false);

        for (int i =0; i<num_body_assets;i++){
            if (assets.equipment_assets_body[i_body].dynamic_assets.get(i).order==1){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, x+overall_scale*skeleton.upper_bod[0]*dir+overall_scale*assets.equipment_assets_body[i_body].dynamic_assets.get(i).x_off*dir, y+overall_scale*skeleton.upper_bod[1]+overall_scale*assets.equipment_assets_body[i_body].dynamic_assets.get(i).y_off, 1f);
                Matrix.scaleM(scratch, 0, overall_scale*assets.equipment_assets_body[i_body].dynamic_assets.get(i).size,overall_scale*assets.equipment_assets_body[i_body].dynamic_assets.get(i).size*assets.equipment_assets_body[i_body].dynamic_assets.get(i).AR,.5f);
                Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);
                assets.equipment_assets_body[i_body].dynamic_assets.get(i).step();
                assets.equipment_assets_body[i_body].dynamic_assets.get(i).Draw(scratch,false);
            }
        }

        draw_body_part(x,y,1,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_left_arm, assets.equipment_assets_body[i_body].lower_left_arm);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_left_arm, assets.equipment_assets_body[i_body].upper_left_arm);

        for (int i =0; i<num_hair_assets;i++){
            if (assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).order==1){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, x+overall_scale*skeleton.hair[i_hair]*dir+overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).x_off*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir,  y+overall_scale*skeleton.hair[1]+overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).y_off+overall_scale*head_position_static[head_counter][Y_LOC], 1f);
                Matrix.scaleM(scratch, 0, overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).size,overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).size*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).AR,.5f);
                Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);
                hair_motion_model[i].step(assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i));
                assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).Draw(scratch,false);
            }
        }
    }


    public void draw_body_part(float x, float y, int dir1, int dir2,float scratch[],float mMVPMatrix[],float zeroRotationMatrix[], float part[], Person_Graphics_Asset img){
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+overall_scale*part[0]*dir1, y+overall_scale*part[1], 1f);
        Matrix.rotateM(scratch, 0, part[2], 0, 0, 1);
        Matrix.scaleM(scratch, 0, overall_scale*img.size,overall_scale*img.size*img.AR,.5f);
        Matrix.rotateM(scratch, 0, -90+dir2*90, 0, 1f, 0);
        img.Draw(scratch,false);
    }



    public static int loadTexture(final Context context, final int resourceId) {
        final int[] textureHandle = new int[1];

        GLES20.glGenTextures(1, textureHandle, 0);

        if (textureHandle[0] != 0)
        {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;   // No pre-scaling

            // Read in the resource
            final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

            // Bind to the texture in OpenGL
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);

            // Set filtering
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

            // Load the bitmap into the bound texture.
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            // Recycle the bitmap, since its data has been loaded into OpenGL.
            bitmap.recycle();
        }

        if (textureHandle[0] == 0)
        {
            throw new RuntimeException("Error loading texture.");
        }

        return textureHandle[0];
    }

}
