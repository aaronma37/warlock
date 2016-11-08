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
public class Equipment_Assets_Weapon {

    public Context myContext;

    public int HEAD_STATIC_MAX=30;
    public float ADJ_SCALE=1.5f;

    public int LANTERN_1;


    public List<List<Person_Graphics_Asset>> weapons = new ArrayList<List<Person_Graphics_Asset>>();


    public int dynamic_asset_number=0;

    public Equipment_Assets_Weapon(Context context){

        myContext=context;

        List<Person_Graphics_Asset> l_1 = new ArrayList<>();
        l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.2f, 1f/4f, 2f/4f, 0f, 1f, new Motion_Model_2(0,0,0,.5f,.1f,1f,1,0,0,.2f*50f/60f)));
        l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.2f, 2f/4f, 3f/4f, 0f, 1f, new Motion_Model_2(1,0,0,.2f*50f/60f)));


        l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.15f, 3f/4f, 1f, 0f, 1f, new Motion_Model_2(2,0,0,.2f*50f/60f,0,10)));
        l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.2f, 3f/4f, 1f, 0f, 1f, new Motion_Model_2(2,0,0,.2f*50f/60f,3,10)));

        l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.2f, 3f/4f, 1f, 0f, 1f, new Motion_Model_2(0,0,0,.5f,.1f,1f,1,0,0,.2f*50f/60f)));

        l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.1f, 3f/4f, 1f, 0f, 1f, new Motion_Model_2(2,0,0,.2f*50f/60f,6,10)));
        l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.15f, 3f/4f, 1f, 0f, 1f, new Motion_Model_2(2,0,0,.2f*50f/60f,1,10)));
        l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.2f, 3f/4f, 1f, 0f, 1f, new Motion_Model_2(2,0,0,.2f*50f/60f,4,10)));
        l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.1f, 3f/4f, 1f, 0f, 1f, new Motion_Model_2(2,0,0,.2f*50f/60f,7,10)));




        //l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.2f, 0f, 1f/4f, 0f, 1f, new Motion_Model_2(1,0,0,.2f*50f/60f)));
        l_1.add(new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.l_1),50f/66f,.2f, 0f, 1f/4f, 0f, 1f, new Motion_Model_2(1,0,0,.2f*50f/60f,7,50)));




        weapons.add(l_1);

        LANTERN_1=weapons.size()-1;


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

