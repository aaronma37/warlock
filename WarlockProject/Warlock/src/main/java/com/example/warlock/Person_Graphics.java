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

    public int i_eye=0, num_eye_assets=0;
    public int i_body=0, num_body_assets=0;
    public int i_legs=0, num_legs_assets=0;
    public int i_hair=0, num_hair_assets=0;
    public int i_face=0, num_face_assets=0;
    public int i_weapon=0;

    private int MAX_EYE_MODEL=5,MAX_BODY_MODEL=5,MAX_HAIR_MODEL=5,MAX_LEGS_MODEL=5,MAX_FACE_MODEL=5;

    public Asset_Motion_Model eye_motion_model[] = new Asset_Motion_Model[MAX_EYE_MODEL];
    public Asset_Motion_Model body_motion_model[] = new Asset_Motion_Model[MAX_BODY_MODEL];
    public Asset_Motion_Model hair_motion_model[] = new Asset_Motion_Model[MAX_HAIR_MODEL];

    public List<Motion_Model_2> hair_motion_model2 = new ArrayList<>();
    public List<Motion_Model_2> weapon_motion_model2 = new ArrayList<>();


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





    public Person_Graphics(Context context, int hair_index, int face_index, int eyes_index, int body_index, int leg_index, int  weapon_index, Global_Assets assets){

        myContext=context;

        i_eye=eyes_index;
        i_body=body_index;
        i_legs=leg_index;
        i_hair=hair_index;
        i_face=face_index;
        i_weapon=weapon_index;

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

        hair_motion_model2.clear();

        for (int i=0; i<assets.hair_assets.hairs.get(i_hair).size();i++){
            hair_motion_model2.add(assets.hair_assets.hairs.get(i_hair).get(i).model);
        }

        weapon_motion_model2.clear();


        for (int i=0; i<assets.weapon_assets.weapons.get(i_weapon).size();i++){
            weapon_motion_model2.add(assets.weapon_assets.weapons.get(i_weapon).get(i).model);
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

    public void set_equipment_indices_from_items(Items items, Global_Assets assets){

        i_body=items.equipped[0].index;
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
            }


            for (int i =0; i<num_body_assets;i++){
                body_motion_model[i].add_force(x_movement,dir);

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

                }
            }

        }

    }

    public void step(PhysicalState i_state, int dir, float x, float y){
        skeleton.step(i_state,dir, x, y);

        step_dynamic(x+overall_scale*skeleton.hair[0]*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir,y+overall_scale*skeleton.hair[1]+overall_scale*head_position_static[head_counter][Y_LOC],dir, hair_motion_model2);

        if (dir==1){
            step_dynamic(x+overall_scale*skeleton.lower_left_arm[0]*dir+(float) Math.sin(skeleton.lower_left_arm[2]*3.14f/180f)*skeleton.LOWER_ARM_LENGTH,y+overall_scale*skeleton.lower_left_arm[1]-(float) Math.cos(skeleton.lower_left_arm[2]*3.14f/180f)*skeleton.LOWER_ARM_LENGTH, dir,weapon_motion_model2);
        }else{
            step_dynamic(x+overall_scale*skeleton.lower_right_arm[0]+(float) Math.sin(skeleton.lower_right_arm[2]*3.14f/180f)*skeleton.LOWER_ARM_LENGTH,y+overall_scale*skeleton.lower_right_arm[1]-(float) Math.cos(skeleton.lower_right_arm[2]*3.14f/180f)*skeleton.LOWER_ARM_LENGTH, dir,weapon_motion_model2);

        }
    }

    public void step_dynamic(float x, float y,int dir, List<Motion_Model_2> m){
/*        asset_list.get(0).model.x=x;
        asset_list.get(0).model.y=y;*/

/*        m.get(0).x=x;
        m.get(0).y=y;*/
        m.get(0).update_force(x,y,0);
        m.get(0).step();

/*        for (int i=1;i<asset_list.size();i++){
            if (asset_list.get(i).model.dynamic){
                asset_list.get(i).model.update_force(asset_list.get(asset_list.get(i).model.hinge_index).model.x+overall_scale*dir*(float)Math.cos(asset_list.get(asset_list.get(i).model.hinge_index).model.alpha)*asset_list.get(i).model.off_x+overall_scale*dir*(float)Math.sin(asset_list.get(asset_list.get(i).model.hinge_index).model.alpha)*asset_list.get(i).model.off_y, asset_list.get(asset_list.get(i).model.hinge_index).model.y+(float)Math.cos(asset_list.get(asset_list.get(i).model.hinge_index).model.alpha)*asset_list.get(i).model.off_y+(float)Math.sin(asset_list.get(asset_list.get(i).model.hinge_index).model.alpha)*asset_list.get(i).model.off_x);
                asset_list.get(i).model.step();
            }
        }*/

        for (int i=1;i<m.size();i++){
            if (m.get(i).dynamic){
                m.get(i).update_force(m.get(m.get(i).hinge_index).x+overall_scale*dir*(float)Math.cos(m.get(m.get(i).hinge_index).alpha)*m.get(i).off_x+overall_scale*dir*(float)Math.sin(m.get(m.get(i).hinge_index).alpha)*m.get(i).off_y, m.get(m.get(i).hinge_index).y+(float)Math.cos(m.get(m.get(i).hinge_index).alpha)*m.get(i).off_y+(float)Math.sin(m.get(m.get(i).hinge_index).alpha)*m.get(i).off_x, m.get(m.get(i).hinge_index).alpha);
                m.get(i).step();
            }
        }
    }



    public void draw_person(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], float x, float y, int dir, PhysicalState i_state, Global_Assets assets){

        step(i_state,dir, x, y);

        if (dir==-1){
            for (int i=0;i<assets.weapon_assets.weapons.get(i_weapon).size();i++){
                draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,dir,weapon_motion_model2.get(i).x+overall_scale*weapon_motion_model2.get(i).lever_y*(float)Math.sin(weapon_motion_model2.get(i).alpha*3.14/180),weapon_motion_model2.get(i).y-weapon_motion_model2.get(i).lever_y*(float)Math.cos(weapon_motion_model2.get(i).alpha*3.14/180),weapon_motion_model2.get(i).alpha,assets.weapon_assets.weapons.get(i_weapon).get(i), weapon_motion_model2.get(i).alph );
            }
        }

        for (int i=1;i<hair_motion_model2.size();i++){
            if (hair_motion_model2.get(i).dynamic && hair_motion_model2.get(i).layer==-1){
                draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,dir,hair_motion_model2.get(i).x+hair_motion_model2.get(i).printx*dir,hair_motion_model2.get(i).y+hair_motion_model2.get(i).printy,hair_motion_model2.get(i).alpha,assets.hair_assets.hairs.get(i_hair).get(i));
            }
        }

/*        for (int i=1;i<assets.equipment_assets_Hair[i_hair].asset_list.size();i++){
            if (assets.equipment_assets_Hair[i_hair].asset_list.get(i).model.dynamic && assets.equipment_assets_Hair[i_hair].asset_list.get(i).model.layer==-1){
                draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,dir,assets.equipment_assets_Hair[i_hair].asset_list.get(i).model.x,assets.equipment_assets_Hair[i_hair].asset_list.get(i).model.y,assets.equipment_assets_Hair[i_hair].asset_list.get(i).model.alpha,assets.equipment_assets_Hair[i_hair].asset_list.get(i));
            }
        }*/

/*
        for (int i =0; i<num_hair_assets;i++){
            if (assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).order==0){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, x+overall_scale*skeleton.hair[0]*dir+overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).x_off*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir,  y+overall_scale*skeleton.hair[1]+overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).y_off+overall_scale*head_position_static[head_counter][Y_LOC], 1f);
                Matrix.scaleM(scratch, 0, overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).size,overall_scale*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).size*assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).AR,.5f);
                Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);
                hair_motion_model[i].step(assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i));
                assets.equipment_assets_Hair[i_hair].dynamic_assets.get(i).Draw(scratch,false);
            }
        }*/



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


        draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,dir,hair_motion_model2.get(0).x+hair_motion_model2.get(0).printx*dir,hair_motion_model2.get(0).y+hair_motion_model2.get(0).printy,hair_motion_model2.get(0).alpha,assets.hair_assets.hairs.get(i_hair).get(0));

/*
        draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,dir,assets.equipment_assets_Hair[i_hair].asset_list.get(0).model.x,assets.equipment_assets_Hair[i_hair].asset_list.get(0).model.y,assets.equipment_assets_Hair[i_hair].asset_list.get(0).model.alpha,assets.equipment_assets_Hair[i_hair].asset_list.get(0));
*/


/*        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+overall_scale*skeleton.hair[0]*dir+overall_scale*head_position_static[head_counter][X_LOC]*dir, y+overall_scale*skeleton.hair[1]+overall_scale*head_position_static[head_counter][Y_LOC], 1f);
        Matrix.scaleM(scratch, 0, overall_scale*assets.equipment_assets_Hair[i_hair].hair.size,overall_scale*assets.equipment_assets_Hair[i_hair].hair.size*assets.equipment_assets_Hair[i_hair].hair.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        assets.equipment_assets_Hair[i_hair].hair.Draw(scratch,false);*/

        for (int i =0; i<num_body_assets;i++){
            if (assets.equipment_assets_body[i_body].dynamic_assets.get(i).order==1){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, x+overall_scale*skeleton.upper_bod[0]*dir+overall_scale*assets.equipment_assets_body[i_body].dynamic_assets.get(i).x_off*dir, y+overall_scale*skeleton.upper_bod[1]+overall_scale*assets.equipment_assets_body[i_body].dynamic_assets.get(i).y_off, 1f);
                Matrix.scaleM(scratch, 0, overall_scale*assets.equipment_assets_body[i_body].dynamic_assets.get(i).size,overall_scale*assets.equipment_assets_body[i_body].dynamic_assets.get(i).size*assets.equipment_assets_body[i_body].dynamic_assets.get(i).AR,.5f);
                Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);
                body_motion_model[i].step(assets.equipment_assets_body[i_body].dynamic_assets.get(i));
                assets.equipment_assets_body[i_body].dynamic_assets.get(i).Draw(scratch,false);
            }
        }

        draw_body_part(x,y,1,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.lower_left_arm, assets.equipment_assets_body[i_body].lower_left_arm);
        draw_body_part(x,y,dir,dir,scratch,mMVPMatrix,zeroRotationMatrix, skeleton.upper_left_arm, assets.equipment_assets_body[i_body].upper_left_arm);

        for (int i=1;i<assets.hair_assets.hairs.get(i_hair).size();i++){
            if (assets.hair_assets.hairs.get(i_hair).get(i).model.dynamic && assets.hair_assets.hairs.get(i_hair).get(i).model.layer==1){
                draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,dir,hair_motion_model2.get(i).x+hair_motion_model2.get(i).printx*dir,hair_motion_model2.get(i).y+hair_motion_model2.get(i).printy,hair_motion_model2.get(i).alpha,assets.hair_assets.hairs.get(i_hair).get(i));
            }
        }

        if (dir==1){
            for (int i=0;i<assets.weapon_assets.weapons.get(i_weapon).size();i++){
                draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,dir,weapon_motion_model2.get(i).x+overall_scale*weapon_motion_model2.get(i).lever_y*(float)Math.sin(weapon_motion_model2.get(i).alpha*3.14/180),weapon_motion_model2.get(i).y-weapon_motion_model2.get(i).lever_y*(float)Math.cos(weapon_motion_model2.get(i).alpha*3.14/180),weapon_motion_model2.get(i).alpha,assets.weapon_assets.weapons.get(i_weapon).get(i));
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

    public void draw_asset(float[] S, float[] M, float[] Z, int dir, float x, float y, float alpha, Person_Graphics_Asset img){
        Matrix.multiplyMM(S, 0, M, 0, Z, 0);
        Matrix.translateM(S, 0, x, y, 1f);
        Matrix.rotateM(S, 0, alpha, 0, 0, 1);
        Matrix.scaleM(S, 0, overall_scale*img.size,overall_scale*img.size*img.AR,.5f);
        Matrix.rotateM(S, 0, -90+dir*90, 0, 1f, 0);
        img.Draw(S,false);
    }

    public void draw_asset(float[] S, float[] M, float[] Z, int dir, float x, float y, float alpha, Person_Graphics_Asset img, float alph){
        Matrix.multiplyMM(S, 0, M, 0, Z, 0);
        Matrix.translateM(S, 0, x, y, 1f);
        Matrix.rotateM(S, 0, alpha, 0, 0, 1);
        Matrix.scaleM(S, 0, overall_scale*img.size,overall_scale*img.size*img.AR,.5f);
        Matrix.rotateM(S, 0, -90+dir*90, 0, 1f, 0);
        img.Draw(S,false);
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
