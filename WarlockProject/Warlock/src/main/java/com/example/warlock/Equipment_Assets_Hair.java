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
public class Equipment_Assets_Hair {

    public Person_Graphics_Asset hair;
    public Context myContext;

    public int HEAD_STATIC_MAX=30;
    public float ADJ_SCALE=1.5f;


    public List<Person_Graphics_Asset_Asset> dynamic_assets = new ArrayList<>();

    public int dynamic_asset_number=0;

    public Equipment_Assets_Hair(Context context, int hair_index,float x_off, float y_off){

        myContext=context;


        if (hair_index==0){
            hair  = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_h1_base),.15f/.15f,ADJ_SCALE*.1f, ADJ_SCALE*(.035f-x_off),ADJ_SCALE*(-y_off));

            Person_Graphics_Asset_Asset hair_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h1_add_1),.15f/.15f,ADJ_SCALE*.06f, ADJ_SCALE*(-.1f-x_off-hair.x_off),ADJ_SCALE*(.02f-y_off-hair.y_off),0f,.2f,.4f,.6f,.8f, .1f,2f,1);
            dynamic_assets.add(hair_asset_1);
            dynamic_asset_number=1;
        }else if (hair_index==1){
            hair  = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_h1_base),.15f/.15f,ADJ_SCALE*.1f, ADJ_SCALE*(.035f-x_off),ADJ_SCALE*(-y_off));

            Person_Graphics_Asset_Asset hair_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h1_add_1),.15f/.15f,ADJ_SCALE*.06f, ADJ_SCALE*(-.1f-x_off-hair.x_off),ADJ_SCALE*(.02f-y_off-hair.y_off),0f,.2f,.4f,.6f,.8f, .1f,2f,1);
            dynamic_assets.add(hair_asset_1);
            dynamic_asset_number=1;
        }
        else if (hair_index==2){
            hair  = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_h2_base),19.879f/46.368f,1.3f*ADJ_SCALE*46.368f/1000, ADJ_SCALE*(.025f)-x_off,ADJ_SCALE*(.03f)-y_off);

            Person_Graphics_Asset_Asset hair_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h2_asset_1),99.416f/37.059f,1.3f*ADJ_SCALE*37.059f/1000, 1.3f*ADJ_SCALE*(0.005f)-x_off-hair.x_off,1.3f*ADJ_SCALE*(-0.080f)-y_off-hair.y_off,0f,.2f,.4f,.6f,.8f, .1f,.2f,1);
            dynamic_assets.add(hair_asset_1);

            Person_Graphics_Asset_Asset hair_asset_2 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h2_asset_2),50.217f/36.993f,1.3f*ADJ_SCALE*36.993f/1000, 1.3f*ADJ_SCALE*(.028f)-x_off-hair.x_off,1.3f*ADJ_SCALE*(-.028f)-y_off-hair.y_off,0f,.2f,.4f,.6f,.8f, .05f,.13f,1);
            dynamic_assets.add(hair_asset_2);

            Person_Graphics_Asset_Asset hair_asset_3 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h2_asset_3),59.797f/5.595f,1.3f*ADJ_SCALE*5.595f/1000, 1.3f*ADJ_SCALE*(.04f)-x_off-hair.x_off,1.3f*ADJ_SCALE*(-.08f)-y_off-hair.y_off,0f,.2f,.4f,.6f,.8f, .01f, .13f,0);
            dynamic_assets.add(hair_asset_3);
            dynamic_asset_number=3;
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

