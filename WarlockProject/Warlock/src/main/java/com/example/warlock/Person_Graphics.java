package com.example.warlock;

import android.content.Context;
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


    public Skeleton skeleton;

    public Context myContext;

    public int head_counter=0;
    public int HEAD_STATIC_MAX=30;
    public float ADJ_SCALE=1.5f;
    static int X_LOC=0;
    static int Y_LOC=1;
    public float head_position_static[][] = new float[HEAD_STATIC_MAX][2];
    public int body_sprite_count=0;

    public List<Person_Graphics_Asset_Asset> pre_hair_assets = new ArrayList<>();
    public List<Person_Graphics_Asset_Asset> post_hair_assets = new ArrayList<>();





    public Person_Graphics(Context context, int hair_index, int face_index, int eyes_index, int body_index){

        myContext=context;

        skeleton = new Skeleton();

        set_non_sprite_movements();

        if (face_index==0){
            face = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_head1), 1f,ADJ_SCALE*44.826f/1000,ADJ_SCALE*.021f,ADJ_SCALE*-.008f);
        }

        if (hair_index==0){
            hair  = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_h1_base),.15f/.15f,ADJ_SCALE*.1f, ADJ_SCALE*(.035f-face.x_off),ADJ_SCALE*(-face.y_off));

            Person_Graphics_Asset_Asset hair_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h1_add_1),.15f/.15f,ADJ_SCALE*.06f, ADJ_SCALE*(-.1f-face.x_off-hair.x_off),ADJ_SCALE*(.02f-face.y_off-hair.y_off),0f,.2f,.4f,.6f,.8f, .1f,2f);
            post_hair_assets.add(hair_asset_1);
        }else if (hair_index==2){
            hair  = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_h2_base),19.879f/46.368f,ADJ_SCALE*46.368f/1000, ADJ_SCALE*(.025f)-face.x_off,ADJ_SCALE*(.03f)-face.y_off);

            Person_Graphics_Asset_Asset hair_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h2_asset_1),99.416f/37.059f,ADJ_SCALE*37.059f/1000, ADJ_SCALE*(0.012f)-face.x_off-hair.x_off,ADJ_SCALE*(-0.075f)-face.y_off-hair.y_off,0f,.2f,.4f,.6f,.8f, .1f,.2f);
            post_hair_assets.add(hair_asset_1);

            Person_Graphics_Asset_Asset hair_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h2_asset_2),50.217f/36.993f,ADJ_SCALE*36.993f/1000, ADJ_SCALE*(.035f)-face.x_off-hair.x_off,ADJ_SCALE*(-.023f)-face.y_off-hair.y_off,0f,.2f,.4f,.6f,.8f, .05f,.13f);
            post_hair_assets.add(hair_asset_2);

            Person_Graphics_Asset_Asset hair_asset_3 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h2_asset_3),59.797f/5.595f,ADJ_SCALE*5.595f/1000, ADJ_SCALE*(.055f)-face.x_off-hair.x_off,ADJ_SCALE*(-.035f)-face.y_off-hair.y_off,0f,.2f,.4f,.6f,.8f, .01f, .13f);
            pre_hair_assets.add(hair_asset_3);
        }

        if (eyes_index==0){
            eyes = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_eyes1),1f,ADJ_SCALE*16.143f/1000,ADJ_SCALE*0.025f,ADJ_SCALE*-.0195f);
        }

        if (body_index==0){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);


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

    public void draw_person(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], float x, float y, int dir){

        for (int i =0; i<pre_hair_assets.size();i++){
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
            Matrix.translateM(scratch, 0, x+face.x_off*dir+hair.x_off*dir+pre_hair_assets.get(i).x_off*dir+head_position_static[head_counter][X_LOC]*dir, y+face.y_off+hair.y_off+pre_hair_assets.get(i).y_off+head_position_static[head_counter][Y_LOC], 1f);
            Matrix.scaleM(scratch, 0, pre_hair_assets.get(i).size,pre_hair_assets.get(i).size*pre_hair_assets.get(i).AR,.5f);
            Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);
            pre_hair_assets.get(i).step();
            pre_hair_assets.get(i).Draw(scratch,false);
        }

        skeleton.lower_left_arm[2]+=1;
        skeleton.recalculate_skeleton();

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+skeleton.lower_right_arm[0]*dir, y+skeleton.lower_right_arm[1], 1f);
        Matrix.rotateM(scratch, 0, skeleton.lower_right_arm[2], 0, 0, 1);
        Matrix.scaleM(scratch, 0, lower_right_arm.size,lower_right_arm.size*lower_right_arm.AR,.5f);
        Matrix.rotateM(scratch, 0, -90+dir*90, 0, 1f, 0);
        lower_right_arm.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+skeleton.upper_right_arm[0]*dir, y+skeleton.upper_right_arm[1], 1f);
        Matrix.rotateM(scratch, 0, skeleton.upper_right_arm[2], 0, 0, 1);
        Matrix.scaleM(scratch, 0, upper_right_arm.size,upper_right_arm.size*upper_right_arm.AR,.5f);
        Matrix.rotateM(scratch, 0, -90+dir*90, 0, 1f, 0);
        upper_right_arm.Draw(scratch,false);




        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+skeleton.lower_bod[0]*dir, y+skeleton.lower_bod[1], 1f);
        Matrix.rotateM(scratch, 0, skeleton.lower_bod[2], 0, 0, 1);
        Matrix.scaleM(scratch, 0, lower_body.size,lower_body.size*lower_body.AR,.5f);
        Matrix.rotateM(scratch, 0, -90+dir*90, 0, 1f, 0);
        lower_body.Draw(scratch,false);


        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+skeleton.upper_bod[0]*dir, y+skeleton.upper_bod[1], 1f);
        Matrix.rotateM(scratch, 0, skeleton.upper_bod[2], 0, 0, 1);
        Matrix.scaleM(scratch, 0, upper_body.size,upper_body.size*upper_body.AR,.5f);
        Matrix.rotateM(scratch, 0, -90+dir*90, 0, 1f, 0);
        upper_body.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+skeleton.lower_left_arm[0]*dir, y+skeleton.lower_left_arm[1], 1f);
        Matrix.rotateM(scratch, 0, skeleton.lower_left_arm[2], 0, 0, 1);
        Matrix.scaleM(scratch, 0, lower_left_arm.size,lower_left_arm.size*lower_left_arm.AR,.5f);
        Matrix.rotateM(scratch, 0, -90+dir*90, 0, 1f, 0);
        lower_left_arm.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+skeleton.upper_left_arm[0]*dir, y+skeleton.upper_left_arm[1], 1f);
        Matrix.rotateM(scratch, 0, skeleton.upper_left_arm[2], 0, 0, 1);
        Matrix.scaleM(scratch, 0, upper_left_arm.size,upper_left_arm.size*upper_left_arm.AR,.5f);
        Matrix.rotateM(scratch, 0, -90+dir*90, 0, 1f, 0);
        upper_left_arm.Draw(scratch,false);





        //HEAD
/*        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+face.x_off*dir+head_position_static[head_counter][X_LOC]*dir, y+face.y_off+head_position_static[head_counter][Y_LOC], 1f);
        Matrix.scaleM(scratch, 0, face.size,face.size*face.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        face.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+eyes.x_off*dir+head_position_static[head_counter][X_LOC]*dir, y+eyes.y_off+head_position_static[head_counter][Y_LOC], 1f);
        Matrix.scaleM(scratch, 0, eyes.size,eyes.size*eyes.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        eyes.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+face.x_off*dir+hair.x_off*dir+head_position_static[head_counter][X_LOC]*dir, y+face.y_off+hair.y_off+head_position_static[head_counter][Y_LOC], 1f);
        Matrix.scaleM(scratch, 0, hair.size,hair.size*hair.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        hair.Draw(scratch,false);*/





        for (int i =0; i<post_hair_assets.size();i++){
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
            Matrix.translateM(scratch, 0, x+face.x_off*dir+hair.x_off*dir+post_hair_assets.get(i).x_off*dir+head_position_static[head_counter][X_LOC]*dir, y+face.y_off+hair.y_off+post_hair_assets.get(i).y_off+head_position_static[head_counter][Y_LOC], 1f);
            Matrix.scaleM(scratch, 0, post_hair_assets.get(i).size,post_hair_assets.get(i).size*post_hair_assets.get(i).AR,.5f);
            Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);
            post_hair_assets.get(i).step();
            post_hair_assets.get(i).Draw(scratch,false);
        }

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
