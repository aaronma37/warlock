package com.example.warlock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 11/2/16.
 */
public class UI_Assets {
    public List<Image_Info> assets = new ArrayList<>();
    public Context myContext;

    public int COMMAND_SEAL=1,NOTHING=0,REWARD=2, START=3, MOVE=4, PAUSE_MENU=5, SELECT_ITEM=6, EQUIP_ITEM=7;

    public int BUTTON=1,ADAPTIVE=2;

    public int BATTLE=0, START_SCREEN=1, SECONDARY_SCREEN=2, DUNGEON=3, DUNGEONS_LEVEL=4;

    public UI_Assets(Context context){

        myContext=context;

        assets.add(new Image_Info(loadTexture(myContext, R.drawable.ui_battle_bottom), 1.8f, .3f,-.8f,0,NOTHING,NOTHING,NOTHING));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.fire_symbol),.1f,.1f,-.85f,-1.35f+0*.7f,COMMAND_SEAL,0,BUTTON));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.water_symbol),.1f,.1f,-.55f,-1.35f+0*.3f,COMMAND_SEAL,3,BUTTON));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.light_symbol),.1f,.1f,-.85f,-1.35f+2*.3f,COMMAND_SEAL,1,BUTTON));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.dark_symbol),.1f,.1f,-.55f,-1.35f+2*.3f,COMMAND_SEAL,2,BUTTON));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.red_button),.15f,.12f,-.85f,.9f,REWARD,2,BUTTON));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.green_button),.15f,.12f,-.85f,.65f,REWARD,3,BUTTON));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.hp_box),.1f,.03f,-.91f,1.45f,NOTHING,NOTHING,ADAPTIVE));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.ui_battle_player_portrait),.23f,.23f,-.65f,1.45f,NOTHING,NOTHING,NOTHING));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.location_arrow), .1f, .1f,-.8f,1.55f,MOVE,1,BUTTON));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.location_arrow),.1f,.1f,-.8f,1.15f,MOVE,-1,BUTTON));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.pause_box),.1f,.1f,.8f,-1.55f,PAUSE_MENU,1,BUTTON));
        assets.add(new Image_Info(loadTexture(myContext, R.drawable.red_box),0, 0,0,0,EQUIP_ITEM,NOTHING,NOTHING));

    }


    public static int loadTexture(final Context context, final int resourceId)
    {
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
