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




    public Person_Graphics(Context context, int hair_index, int face_index, int eyes_index, int body_index, int leg_index){

        myContext=context;

        skeleton = new Skeleton();

        set_non_sprite_movements();

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

        if (state==3){
            for (int i =0; i<pre_hair_assets.size();i++){
                pre_hair_assets.get(i).add_force(x_movement,dir);

            }
            for (int i =0; i<post_hair_assets.size();i++){
                post_hair_assets.get(i).add_force(x_movement,dir);

            }
            for (int i =0; i<post_body_assets.size();i++){
                post_body_assets.get(i).add_force(x_movement,-dir);

            }
        }else if (state==0){
            //head bob
            head_counter+=1;
            if (head_counter>=HEAD_STATIC_MAX){
                head_counter=0;
            }
            if (head_counter>3 && head_counter<10){
                for (int i =0; i<pre_hair_assets.size();i++){
                    pre_hair_assets.get(i).add_force(Math.abs(head_position_static[head_counter][X_LOC]-head_position_static[head_counter-1][X_LOC])*1f,dir);

                }
                for (int i =0; i<post_hair_assets.size();i++){
                    post_hair_assets.get(i).add_force(Math.abs(head_position_static[head_counter][X_LOC]-head_position_static[head_counter-1][X_LOC])*1f,dir);
                }
            }

        }

    }

    public void draw_person(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], float x, float y, int dir, PhysicalState i_state){

        for (int i =0; i<pre_hair_assets.size();i++){
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
            Matrix.translateM(scratch, 0, x+overall_scale*skeleton.hair[0]*dir+overall_scale*pre_hair_assets.get(i).x_off*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir,  y+overall_scale*skeleton.hair[1]+overall_scale*pre_hair_assets.get(i).y_off+overall_scale*head_position_static[head_counter][Y_LOC], 1f);
            Matrix.scaleM(scratch, 0, overall_scale*pre_hair_assets.get(i).size,overall_scale*pre_hair_assets.get(i).size*pre_hair_assets.get(i).AR,.5f);
            Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);
            pre_hair_assets.get(i).step();
            pre_hair_assets.get(i).Draw(scratch,false);
        }

        skeleton.step(i_state,dir, x, y);


        draw_body_part(x,y,1,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_right_arm, lower_right_arm);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_right_arm, upper_right_arm);
        draw_body_part(x,y,1,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_right_leg, lower_right_leg);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_right_leg, upper_right_leg);
        draw_body_part(x,y,1,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_left_leg, lower_left_leg);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_left_leg, upper_left_leg);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_bod, lower_body);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_bod, upper_body);



        //HEAD
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+overall_scale*skeleton.head[0]*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir, y+overall_scale*skeleton.head[1]+overall_scale*head_position_static[head_counter][Y_LOC], 1f);
        Matrix.scaleM(scratch, 0, overall_scale*face.size,overall_scale*face.size*face.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        face.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+overall_scale*skeleton.head[0]*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir+.01f*dir+overall_scale*eyes.x_off, y+overall_scale*skeleton.head[1]+overall_scale*head_position_static[head_counter][Y_LOC]+overall_scale*eyes.y_off, 1f);
        Matrix.scaleM(scratch, 0, overall_scale*eyes.size,overall_scale*eyes.size*eyes.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        eyes.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+overall_scale*skeleton.hair[0]*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir, y+overall_scale*skeleton.hair[1]+overall_scale*head_position_static[head_counter][Y_LOC], 1f);
        Matrix.scaleM(scratch, 0, overall_scale*hair.size,overall_scale*hair.size*hair.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        hair.Draw(scratch,false);

        for (int i =0; i<post_body_assets.size();i++){
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
            Matrix.translateM(scratch, 0, x+overall_scale*skeleton.upper_bod[0]*dir+overall_scale*post_body_assets.get(i).x_off*dir, y+overall_scale*skeleton.upper_bod[1]+overall_scale*post_body_assets.get(i).y_off, 1f);
            Matrix.scaleM(scratch, 0, overall_scale*post_body_assets.get(i).size,overall_scale*post_body_assets.get(i).size*post_body_assets.get(i).AR,.5f);
            Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);
            post_body_assets.get(i).step();
            post_body_assets.get(i).Draw(scratch,false);
        }



        draw_body_part(x,y,1,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_left_arm, lower_left_arm);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_left_arm, upper_left_arm);

        for (int i =0; i<post_hair_assets.size();i++){
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
            Matrix.translateM(scratch, 0, x+overall_scale*skeleton.hair[0]*dir+overall_scale*post_hair_assets.get(i).x_off*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir, y+overall_scale*skeleton.hair[1]+overall_scale*post_hair_assets.get(i).y_off+overall_scale*head_position_static[head_counter][Y_LOC], 1f);
            Matrix.scaleM(scratch, 0, overall_scale*post_hair_assets.get(i).size,overall_scale*post_hair_assets.get(i).size*post_hair_assets.get(i).AR,.5f);
            Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);
            post_hair_assets.get(i).step();
            post_hair_assets.get(i).Draw(scratch,false);
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
