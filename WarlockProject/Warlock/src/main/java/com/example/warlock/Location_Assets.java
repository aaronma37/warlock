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
public class Location_Assets {
    public List<Location_Graphics_Asset> assets = new ArrayList<>();
    public Context myContext;

    public int ARROW=0,GRASS=1,SKY=2,MOUNTAIN_1=3,BARE_TREE_BUNCH=4,LEDGE_GRASS_1=5,LEDGE_GRASS_2=6,LEDGE_GRASS_3=7,ROCK_1=8,ROCK_2=9,MAPLE_1=10,CLOUD_1=11,CLOUD_2=12;

    public Location_Assets(Context context){

        myContext=context;

        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_arrow), 28f/50f, 100f/1000f, 0, 0, 1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 2f*.25f , 2f, 0, 0, 1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_daytime_base), 1f , 1*10, 0, 0, 4f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.mountain_1), 161.327f/250f , .3f*1, 1.7f, .3f, 1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.bare_tree_bunch_1), 1f  , 1f, 1f, 1f, 1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.ledge_grass_1), 1  , 1, 1, 1, 1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.ledge_grass_2), 1  , 1, 1, 1, 1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.ledge_grass_3), 1  , 1, 1, 1, 1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.rock_1), 36.000f/45f , .1f, 1.75f, -.55f, .1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.rock_2), 36.000f/45f , .1f, 1.75f, -.55f, .1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.maple_1), 1, .03f, 0, 0, 1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.cloud_1), 1, .03f, 0, 0, 1f));
        assets.add(new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.cloud_2), 1, .03f, 0, 0, 1f));


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
