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
    public Location_Graphics_Asset static_assets[] = new Location_Graphics_Asset[15];

    public Location_Graphics_Asset arrow;
    public Arrow_Data arrow_datas[] = new Arrow_Data[4];
    public Location location;
    private float ARROW_WIDTH=100f/1000f;
    private float ARROW_AR=28f/50f;
    private int STATIC_ASSET_SIZE=0;
    public Person people_in_scene[] = new Person[5];
    public int PEOPLE_IN_SCENE_SIZE=0;
    private float GROUND_LEVEL=-.1f;


    public Context myContext;

    public Location_Data(Context context, Location loc){
        myContext=context;

        location= new Location(loc.location_index,loc.right,loc.left,loc.up,loc.down);

        arrow = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_arrow), 28f/50f, 100f/1000f, 0, 0);


        if (location.location_index==0){
            //NOTHING HERE
        }else if (location.location_index==1) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 2f*.25f , 2f, 0, 0);

            arrow_datas[0]= new Arrow_Data(-1.5f,0f, ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,0,true);
            arrow_datas[1]= new Arrow_Data(1.5f,0f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,2,false);
            arrow_datas[2]= new Arrow_Data(0f,-.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,1,false);
            arrow_datas[3]= new Arrow_Data(0f,.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,3,false);

            static_assets[0]= new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_daytime_base), 1f , 3f, 0, 0);STATIC_ASSET_SIZE++;
            static_assets[1]= new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 2f*.15f  , 2.5f, 0, -.35f);STATIC_ASSET_SIZE++;

            people_in_scene[0]= new Person("default 1", -.5f, GROUND_LEVEL, myContext);PEOPLE_IN_SCENE_SIZE++;
            people_in_scene[0].reset(0,GROUND_LEVEL); people_in_scene[0].state.state=0;

        }else if (location.location_index==2) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0);
            arrow_datas[0]= new Arrow_Data(-1.5f,0f, ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,0,true);
            arrow_datas[1]= new Arrow_Data(1.5f,0f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,2,false);
            arrow_datas[2]= new Arrow_Data(0f,-.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,1,false);
            arrow_datas[3]= new Arrow_Data(0f,.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,3,false);



        }else if (location.location_index==3) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0);
            arrow_datas[0]= new Arrow_Data(-1.5f,0f, ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,0,true);
            arrow_datas[1]= new Arrow_Data(1.5f,0f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,2,false);
            arrow_datas[2]= new Arrow_Data(0f,-.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,1,false);
            arrow_datas[3]= new Arrow_Data(0f,.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,3,false);
        }else if (location.location_index==4) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0);
            arrow_datas[0]= new Arrow_Data(-1.5f,0f, ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,0,true);
            arrow_datas[1]= new Arrow_Data(1.5f,0f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,2,false);
            arrow_datas[2]= new Arrow_Data(0f,-.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,1,false);
            arrow_datas[3]= new Arrow_Data(0f,.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,3,false);
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

    public void step_people(){
        for (int i=0;i<PEOPLE_IN_SCENE_SIZE;i++){
            people_in_scene[i].step();
        }
    }


    public void draw_location(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], float x){


        for (int i=0;i<STATIC_ASSET_SIZE;i++){
            draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,static_assets[i],0);
        }

        for (int i=0;i<PEOPLE_IN_SCENE_SIZE;i++){
            people_in_scene[i].person_graphics.draw_person(scratch,mMVPMatrix,zeroRotationMatrix,people_in_scene[i].center_x,people_in_scene[i].center_y,people_in_scene[i].facing_direction,people_in_scene[i].state.state);
        }


        for (int i=0;i<4;i++){
            if (set_arrow_active(x, arrow_datas[i].x,i) && arrow_datas[i].active){
                draw_asset_custom(scratch,mMVPMatrix,zeroRotationMatrix,arrow,arrow_datas[i].direction, arrow_datas[i].x, arrow_datas[i].y);
            }

        }

    }

    public boolean set_arrow_active(float x1, float x2, int arr_index){
        if (Math.abs(x1-x2)<.3f){
            arrow_datas[arr_index].is_active=true;
            return true;
        }else{
            arrow_datas[arr_index].is_active=false;
            return false;
        }
    }

    public void draw_location_battle(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[]){

        for (int i=0;i<STATIC_ASSET_SIZE;i++){
            draw_asset(scratch,mMVPMatrix,zeroRotationMatrix,static_assets[i],0);
        }
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
