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

    public Item_Info item_info[] = new Item_Info[50];
    public int hi=0;
    private int INVENTORY_SIZE=50;

    private int NUMBER_OF_ATTRIBUTES=4;
    private int NUMBER_OF_EQUIPMENT_SLOTS=5;
    public Item_Info_Small equipped[]= new Item_Info_Small[5];
    public Item_Info_Small inventory[] = new Item_Info_Small[INVENTORY_SIZE];
    public Item_Info_Small selected_item;



    public Items(Context context){

        myContext=context;

        equipped[0]= new Item_Info_Small();
        equipped[1]= new Item_Info_Small();
        equipped[2]= new Item_Info_Small();
        equipped[3]= new Item_Info_Small();
        equipped[4]= new Item_Info_Small();


        selected_item= new Item_Info_Small();



        item_info[hi]  = new Item_Info(myContext, loadTexture(myContext, R.drawable.pause_box),1f,.15f, 0,0,0,0,0,0,0);hi++;
        item_info[hi]  = new Item_Info(myContext, loadTexture(myContext, R.drawable.f_body_2_portrait),1f,.15f, 0,0,1,0,0,0,0);hi++;
        item_info[hi]  = new Item_Info(myContext, loadTexture(myContext, R.drawable.f_body_2),1f,.15f, 0,0,1,0,0,0,0);hi++;

        for (int i=0;i<INVENTORY_SIZE;i++){
            inventory[i] = new Item_Info_Small();
        }

        equip(0);
        set_inventory();


    }

    public void set_inventory(){
        inventory[0].index=0;
        inventory[1].index=1;

    }


    public void equip(int i){
        equipped[item_info[i].slot].index=i;
    }

    public void recalculate_attributes(Spirit[] spirit){
        for (int i =0;i<NUMBER_OF_ATTRIBUTES;i++){
            spirit[i].attribute=0;
            //body slot
            for (int j=0;j<NUMBER_OF_EQUIPMENT_SLOTS;j++){
                spirit[i].attribute+=item_info[equipped[j].index].attributes[i];
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
