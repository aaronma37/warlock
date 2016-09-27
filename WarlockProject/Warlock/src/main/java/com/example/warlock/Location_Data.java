package com.example.warlock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

/**
 * Created by aaron on 9/27/16.
 */
public class Location_Data {

    public Location_Graphics_Asset base;
    public Location_Graphics_Asset arrow;
    public Arrow_Data arrow_datas[] = new Arrow_Data[4];
    public Location location;
    private float ARROW_WIDTH=2f;
    private float ARROW_AR=.25f;

    public Context myContext;

    public Location_Data(Context context, Location loc){
        myContext=context;

        location=loc;

        arrow = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_arrow), 28f/50f, 100f/1000f, 0, 0);


        if (location.location_index==0){
            //NOTHING HERE
        }else if (location.location_index==1) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), ARROW_WIDTH*ARROW_AR , ARROW_WIDTH, 0, 0);
            arrow_datas[0]= new Arrow_Data(-1.5f,0f, 2f*.25f, 2f,0,true);
            arrow_datas[1]= new Arrow_Data(1.5f,0f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,2,false);
            arrow_datas[2]= new Arrow_Data(0f,-.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,1,false);
            arrow_datas[3]= new Arrow_Data(0f,.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,3,false);




        }else if (location.location_index==2) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0);
        }else if (location.location_index==3) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0);
        }else if (location.location_index==4) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0);
        }else if (location.location_index==5) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0);
        }else if (location.location_index==6) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0);
        }else if (location.location_index==7) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0);
        }else if (location.location_index==8) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0);
        }

    }


    public void draw_location(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[]){

        draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,base,0);

        for (int i=0;i<4;i++){
            draw_asset_custom(scratch,mMVPMatrix,zeroRotationMatrix,arrow,arrow_datas[i].direction, arrow_datas[i].x, arrow_datas[i].y);
        }

    }

    public void draw_location_battle(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[]){

        draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,base,0);


    }

    private void draw_asset(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], Location_Graphics_Asset asset, int dir){
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, asset.x_off, asset.y_off, 1f);
        Matrix.scaleM(scratch, 0, asset.size,asset.size*asset.AR,.5f);
        Matrix.rotateM(scratch, 0, 90*dir, 0, 0, 1f);
        asset.Draw(scratch);
    }

    private void draw_asset_custom(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], Location_Graphics_Asset asset, int dir, float x, float y){
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x, y, 1f);
        Matrix.rotateM(scratch, 0, 90*dir, 0, 0, 1f);

        Matrix.scaleM(scratch, 0, asset.size,asset.size*asset.AR,1f);

        asset.Draw(scratch);

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
