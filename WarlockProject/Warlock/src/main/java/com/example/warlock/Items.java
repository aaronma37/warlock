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
public class Items {

    public Context myContext;

    public Item_Info item_info[] = new Item_Info[5];
    public int hi=0;




    public Items(Context context){

        myContext=context;
        item_info[hi]  = new Item_Info(myContext, loadTexture(myContext, R.drawable.f_body_2_portrait),1f,.15f, 0,0);hi++;

    }





    public void draw_portrait(float[] S, float[] M, float[] Z, float x, float y, float size, float AR, int item_index){
        Matrix.multiplyMM(S, 0, M, 0, Z, 0);
        Matrix.translateM(S, 0, x+item_info[item_index].x,  y+item_info[item_index].y, 1f);
        Matrix.scaleM(S, 0, size, size*AR,.5f);
        Matrix.rotateM(S, 0, 0, 0, 1f, 0);
        item_info[item_index].Draw(S,false);
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
