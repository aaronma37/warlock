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
public class Particle_Graphics {

    public Person_Graphics_Asset particle_base;

    public Context myContext;

    public float ADJ_SCALE=1.5f;
    public float overall_scale=.7f;
    public float rotation=0;



    public Particle_Graphics(Context context, int particle_index){

        myContext=context;


        if (particle_index==0){
            particle_base = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.fire_1_1_asset_1), 1f,1.3f*ADJ_SCALE*25f/1000,ADJ_SCALE*.021f,ADJ_SCALE*-.008f);
            rotation=90;
        }



    }

    public void draw_particle(float x, float y, float dir, float scratch[],float mMVPMatrix[],float zeroRotationMatrix[], float alpha){
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x, y, 1f);
        Matrix.scaleM(scratch, 0, alpha*overall_scale*particle_base.size,alpha*overall_scale*particle_base.size*particle_base.AR,.5f);
        Matrix.rotateM(scratch, 0, -90+dir*90, 0, 1f, 0);
        particle_base.Draw(scratch,false);
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
