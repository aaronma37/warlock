package com.example.warlock;

import android.content.ClipData;
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
    private int NUMBER_OF_ATTRIBUTES=4;
    private int NUMBER_OF_EQUIPMENT_SLOTS=5;
    public Item_Info equipped[]= new Item_Info[5];
    public Item_Info selected_item;



    public Items(Context context){

        myContext=context;

        equipped[0]= new Item_Info(myContext, loadTexture(myContext, R.drawable.pause_box),1f,.15f, 0,0,0,0,0,0,0);
        equipped[1]= new Item_Info(myContext, loadTexture(myContext, R.drawable.pause_box),1f,.15f, 0,0,0,0,0,0,1);
        equipped[2]= new Item_Info(myContext, loadTexture(myContext, R.drawable.pause_box),1f,.15f, 0,0,0,0,0,0,2);
        equipped[3]= new Item_Info(myContext, loadTexture(myContext, R.drawable.pause_box),1f,.15f, 0,0,0,0,0,0,3);
        equipped[4]= new Item_Info(myContext, loadTexture(myContext, R.drawable.pause_box),1f,.15f, 0,0,0,0,0,0,4);

        selected_item= new Item_Info(myContext, loadTexture(myContext, R.drawable.pause_box),1f,.15f, 0,0,0,0,0,0,0);



        item_info[hi]  = new Item_Info(myContext, loadTexture(myContext, R.drawable.f_body_2_portrait),1f,.15f, 0,0,1,0,0,0,0);hi++;
        item_info[hi]  = new Item_Info(myContext, loadTexture(myContext, R.drawable.f_body_1),1f,.15f, 0,0,1,0,0,0,0);hi++;
        item_info[hi]  = new Item_Info(myContext, loadTexture(myContext, R.drawable.f_body_2),1f,.15f, 0,0,1,0,0,0,0);hi++;



    }

    public void recalculate_attributes(Spirit[] spirit){
        for (int i =0;i<NUMBER_OF_ATTRIBUTES;i++){
            spirit[i].attribute=0;
            //body slot
            for (int j=0;j<NUMBER_OF_EQUIPMENT_SLOTS;j++){
                spirit[i].attribute+=equipped[j].attributes[i];
            }
            spirit[i].setAvailableOffensiveActionSpace();
        }
    }





    public void draw_portrait(float[] S, float[] M, float[] Z, float x, float y, float size, float AR, Item_Info i){
        Matrix.multiplyMM(S, 0, M, 0, Z, 0);
        Matrix.translateM(S, 0, x+i.x,  y+i.y, 1f);
        Matrix.scaleM(S, 0, size, size*AR,.5f);
        Matrix.rotateM(S, 0, 0, 0, 1f, 0);
        i.Draw(S,false);
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
