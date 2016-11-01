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
public class Equipment_Assets_Eyes {

    public Person_Graphics_Asset eyes;

    public Context myContext;

    public float ADJ_SCALE=1.5f;


    public List<Person_Graphics_Asset_Asset> dynamic_assets = new ArrayList<>();


    public int dynamic_asset_number=0;




    public Equipment_Assets_Eyes(Context context, int eyes_index){

        myContext=context;



        if (eyes_index==0){
            eyes = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_eyes1),1f,1.3f*ADJ_SCALE*16.143f/1000,1.3f*ADJ_SCALE*-.003f,1.3f*ADJ_SCALE*-.008f);
            dynamic_asset_number=0;
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
