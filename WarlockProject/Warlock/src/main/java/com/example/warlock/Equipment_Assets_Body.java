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
public class Equipment_Assets_Body {


    public Person_Graphics_Asset upper_body;
    public Person_Graphics_Asset lower_body;
    public Person_Graphics_Asset upper_right_arm;
    public Person_Graphics_Asset lower_right_arm;
    public Person_Graphics_Asset upper_left_arm;
    public Person_Graphics_Asset lower_left_arm;


    public Context myContext;

    public int head_counter=0;
    public int HEAD_STATIC_MAX=30;
    public float ADJ_SCALE=1.5f;
    static int X_LOC=0;
    static int Y_LOC=1;
    public float head_position_static[][] = new float[HEAD_STATIC_MAX][2];
    public int body_sprite_count=0;
    public float overall_scale=.7f;

    public List<Person_Graphics_Asset_Asset> dynamic_assets = new ArrayList<>();


    public int dynamic_asset_number=0;


    public Equipment_Assets_Body(Context context, int body_index){

        myContext=context;

        if (body_index==0){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_1), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);

            Person_Graphics_Asset_Asset body_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_1_asset_1),1,ADJ_SCALE*27f/1000, ADJ_SCALE*(.060f),ADJ_SCALE*(-.032f),0f,.1f,.1f,.2f,.2f, .03f, .13f,1);
            dynamic_assets.add(body_asset_2);

            Person_Graphics_Asset_Asset body_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_1_asset_2),1,ADJ_SCALE*30f/1000, ADJ_SCALE*(.045f),ADJ_SCALE*(-.035f),0f,.1f,.1f,.2f,.2f, .03f, .13f,1);
            dynamic_assets.add(body_asset_1);
            dynamic_asset_number=2;

        }else if (body_index==1){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_2), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);

            Person_Graphics_Asset_Asset body_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_2_asset_1),1,ADJ_SCALE*27f/1000, ADJ_SCALE*(.060f),ADJ_SCALE*(-.032f),0f,.1f,.1f,.2f,.2f, .03f, .13f,1);
            dynamic_assets.add(body_asset_2);

            Person_Graphics_Asset_Asset body_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_2_asset_2),1,ADJ_SCALE*30f/1000, ADJ_SCALE*(.045f),ADJ_SCALE*(-.035f),0f,.1f,.1f,.2f,.2f, .03f, .13f,1);
            dynamic_assets.add(body_asset_1);
            dynamic_asset_number=2;
        }else if (body_index==2){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_3), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);

            Person_Graphics_Asset_Asset body_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_3_asset_1),1,ADJ_SCALE*27f/1000, ADJ_SCALE*(.060f),ADJ_SCALE*(-.032f),0f,.1f,.1f,.2f,.2f, .03f, .13f,1);
            dynamic_assets.add(body_asset_2);

            Person_Graphics_Asset_Asset body_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_3_asset_2),1,ADJ_SCALE*30f/1000, ADJ_SCALE*(.045f),ADJ_SCALE*(-.035f),0f,.1f,.1f,.2f,.2f, .03f, .13f,1);
            dynamic_assets.add(body_asset_1);
            dynamic_asset_number=2;
        }else if (body_index==3){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_4), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);

            Person_Graphics_Asset_Asset body_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_4_asset_1),1,ADJ_SCALE*27f/1000, ADJ_SCALE*(.060f),ADJ_SCALE*(-.032f),0f,.1f,.1f,.2f,.2f, .03f, .13f,1);
            dynamic_assets.add(body_asset_2);

            Person_Graphics_Asset_Asset body_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_4_asset_2),1,ADJ_SCALE*30f/1000, ADJ_SCALE*(.045f),ADJ_SCALE*(-.035f),0f,.1f,.1f,.2f,.2f, .03f, .13f,1);
            dynamic_assets.add(body_asset_1);
            dynamic_asset_number=2;
        }else if (body_index==4){
            //body = new Person_Graphics_Asset_Sprite(myContext, loadTexture(myContext, R.drawable.f_body_clothes_1), 182.756f/57.366f ,ADJ_SCALE*90.366f/1000,ADJ_SCALE*0.02f,ADJ_SCALE*-.285f);
            upper_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, 0f, .5f);
            lower_body = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 0f, 1f/3f, .5f, 1f);

            upper_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, 0f, .5f);

            lower_right_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 2f/3f, 1f, .5f, 1f);

            upper_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 1f/3f, 2f/3f, 0f, .5f);

            lower_left_arm = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_body_5), 75f/50f ,ADJ_SCALE*90.366f/1000, 1/3f, 2f/3f, .5f, 1f);

            Person_Graphics_Asset_Asset body_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_5_asset_1),1,ADJ_SCALE*27f/1000, ADJ_SCALE*(.060f),ADJ_SCALE*(-.032f),0f,.1f,.1f,.2f,.2f, .03f, .13f,1);
            dynamic_assets.add(body_asset_2);

            Person_Graphics_Asset_Asset body_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_body_5_asset_2),1,ADJ_SCALE*30f/1000, ADJ_SCALE*(.045f),ADJ_SCALE*(-.035f),0f,.1f,.1f,.2f,.2f, .03f, .13f,1);
            dynamic_assets.add(body_asset_1);
            dynamic_asset_number=2;
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
