package com.example.warlock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.io.CharArrayWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 9/27/16.
 */
public class Location_Data {

    public Location_Graphics_Asset base;
    public List<Location_Assets_Small> asset_list = new ArrayList<>();

    public Location_Graphics_Asset arrow;
    public Arrow_Data arrow_datas[] = new Arrow_Data[4];
    public Location location;
    private float ARROW_WIDTH=100f/1000f;
    private float ARROW_AR=28f/50f;


    public Person people_in_scene[] = new Person[5];
    public int PEOPLE_IN_SCENE_SIZE=0;
    private float GROUND_LEVEL=.15f;
    public List<Box> hitbox_list = new ArrayList<>();
    public List<Dynamic_Location> background_dynamic_location_list = new ArrayList<>();

    public Location_Graphics_Asset background_dynamic_asset[] = new Location_Graphics_Asset[5];

    public float width;
    public boolean DYNAMIC=false;

    public float LEDGE_SIZE=.5f;
    public float LEDGE_HEIGHT=.03f;
    public float LEDGE_AR=24.053f/100.000f;

    public float BARE_TREE_AR=159.690f/250.000f;
    public float BARE_TREE_SIZE=.5f;
    public float BARE_TREE_HEIGHT=.1f+BARE_TREE_AR*BARE_TREE_SIZE;

    public float CLOUD_1_AR=50.645f/150.000f;
    public float CLOUD_2_AR=20.359f/200.000f;

    public int ARROW=0,GRASS=1,SKY=2,MOUNTAIN_1=3,BARE_TREE_BUNCH=4,LEDGE_GRASS_1=5,LEDGE_GRASS_2=6,LEDGE_GRASS_3=7,ROCK_1=8,ROCK_2=9,MAPLE_1=10,CLOUD_1=11,CLOUD_2=12;




    public Context myContext;

    public Location_Data(Context context, Location loc, Global_Assets assets){
        myContext=context;

        location= new Location(loc.location_index,loc.right,loc.left,loc.up,loc.down);

        arrow = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_arrow), 28f/50f, 100f/1000f, 0, 0, 1f);

        if (location.location_index==0){
            //NOTHING HERE
        }else if (location.location_index==1) {


            width=2.5f;

            asset_list.add(new Location_Assets_Small(SKY,  1f , width*10, 0, 0, .8f,false,0,0));
            asset_list.add(new Location_Assets_Small(MOUNTAIN_1, 161.327f/250f , .3f*1, 1.7f, .3f, .8f,false,0,0));
            asset_list.add(new Location_Assets_Small(MOUNTAIN_1, 161.327f/250f , .3f*1, 1.1f, .3f, .8f,false,0,0));
            asset_list.add(new Location_Assets_Small(MOUNTAIN_1, 161.327f/250f , .5f*1, 1.4f, .3f, .8f,false,0,0));
            asset_list.add(new Location_Assets_Small(MOUNTAIN_1, 161.327f/250f , .7f*1, .7f, .3f, .8f,false,0,0));
            asset_list.add(new Location_Assets_Small(CLOUD_1, CLOUD_1_AR, .3f, 0, 0, 1f,true,1,width));
            asset_list.add(new Location_Assets_Small(CLOUD_1, CLOUD_1_AR, .3f, 0, 0, 1f,true,1,width));

            asset_list.add(new Location_Assets_Small(CLOUD_2, CLOUD_2_AR, 1.2f, 0, 0, 1f,true,1,width));
            asset_list.add(new Location_Assets_Small(CLOUD_2, CLOUD_2_AR, 1.2f, 0, 0, 1f,true,1,width));



            asset_list.add(new Location_Assets_Small(GRASS,  2f*.15f  , width, 0, -.5f, 1f,false,0,0));

            asset_list.add(new Location_Assets_Small(BARE_TREE_BUNCH, BARE_TREE_AR  , BARE_TREE_SIZE, width-BARE_TREE_SIZE, BARE_TREE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(BARE_TREE_BUNCH, BARE_TREE_AR  , BARE_TREE_SIZE, width-BARE_TREE_SIZE*2, BARE_TREE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(BARE_TREE_BUNCH, BARE_TREE_AR  , BARE_TREE_SIZE, width-BARE_TREE_SIZE*3, BARE_TREE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(BARE_TREE_BUNCH, BARE_TREE_AR  , BARE_TREE_SIZE, width-BARE_TREE_SIZE*4, BARE_TREE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(BARE_TREE_BUNCH, BARE_TREE_AR  , BARE_TREE_SIZE, width-BARE_TREE_SIZE*5, BARE_TREE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(BARE_TREE_BUNCH, BARE_TREE_AR  , BARE_TREE_SIZE, width-BARE_TREE_SIZE*6, BARE_TREE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(BARE_TREE_BUNCH, BARE_TREE_AR  , BARE_TREE_SIZE, width-BARE_TREE_SIZE*7, BARE_TREE_HEIGHT, 0f,false,0,0));

            asset_list.add(new Location_Assets_Small(LEDGE_GRASS_1, LEDGE_AR  , LEDGE_SIZE, width-LEDGE_SIZE, LEDGE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(LEDGE_GRASS_2, LEDGE_AR  , LEDGE_SIZE, width-LEDGE_SIZE*3, LEDGE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(LEDGE_GRASS_3, LEDGE_AR  , LEDGE_SIZE, width-LEDGE_SIZE*5, LEDGE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(LEDGE_GRASS_1, LEDGE_AR  , LEDGE_SIZE, width-LEDGE_SIZE*7, LEDGE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(LEDGE_GRASS_2, LEDGE_AR  , LEDGE_SIZE, width-LEDGE_SIZE*9, LEDGE_HEIGHT, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(LEDGE_GRASS_3, LEDGE_AR  , LEDGE_SIZE, width-LEDGE_SIZE*11, LEDGE_HEIGHT, 0f,false,0,0));

            asset_list.add(new Location_Assets_Small(ROCK_1,  36.000f/45f , .1f, 1.75f, -.55f, 0f,false,0,0));
            asset_list.add(new Location_Assets_Small(ROCK_2, 30.806f/67.695f , .15f, 1.95f, -.5f, 0f,false,0,0));

            asset_list.add(new Location_Assets_Small(MAPLE_1, 1, .03f, 0, 0, 1f,true,0,width));
            asset_list.add(new Location_Assets_Small(MAPLE_1, 1, .03f, 0, 0, 1f,true,0,width));
            asset_list.add(new Location_Assets_Small(MAPLE_1, 1, .03f, 0, 0, 1f,true,0,width));

            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 2f*.25f , 2f, 0, 0, 1f);

            arrow_datas[0]= new Arrow_Data(-1.5f,0f, ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,0,true);
            arrow_datas[1]= new Arrow_Data(1.5f,0f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,2,false);
            arrow_datas[2]= new Arrow_Data(0f,-.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,1,false);
            arrow_datas[3]= new Arrow_Data(0f,.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,3,false);



            people_in_scene[PEOPLE_IN_SCENE_SIZE]= new Person("default 1", -.5f, GROUND_LEVEL, myContext,2,0,0,1,0,0,1,assets);
            people_in_scene[PEOPLE_IN_SCENE_SIZE].reset(0,GROUND_LEVEL); people_in_scene[0].state.state=0;PEOPLE_IN_SCENE_SIZE++;
/*

            people_in_scene[PEOPLE_IN_SCENE_SIZE]= new Person("default 1", -.1f, GROUND_LEVEL, myContext,2,0,0,2,0,1,assets);
            people_in_scene[PEOPLE_IN_SCENE_SIZE].reset(.4f,GROUND_LEVEL); people_in_scene[0].state.state=0;PEOPLE_IN_SCENE_SIZE++;


            people_in_scene[PEOPLE_IN_SCENE_SIZE]= new Person("default 1", -1f, GROUND_LEVEL, myContext,2,0,0,3,0,0,assets);
            people_in_scene[PEOPLE_IN_SCENE_SIZE].reset(-.4f,GROUND_LEVEL); people_in_scene[0].state.state=0;PEOPLE_IN_SCENE_SIZE++;


            people_in_scene[PEOPLE_IN_SCENE_SIZE]= new Person("default 1", .5f, GROUND_LEVEL, myContext,2,0,0,4,0,1,assets);
            people_in_scene[PEOPLE_IN_SCENE_SIZE].reset(1f,GROUND_LEVEL); people_in_scene[0].state.state=0;PEOPLE_IN_SCENE_SIZE++;*/



            hitbox_list.add(new Box(.2f, .2f, people_in_scene[0].center_x, people_in_scene[0].center_y, 0));





        }else if (location.location_index==2) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0, 1f);
            arrow_datas[0]= new Arrow_Data(-1.5f,0f, ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,0,true);
            arrow_datas[1]= new Arrow_Data(1.5f,0f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,2,false);
            arrow_datas[2]= new Arrow_Data(0f,-.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,1,false);
            arrow_datas[3]= new Arrow_Data(0f,.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,3,false);



        }else if (location.location_index==3) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0, 1f);
            arrow_datas[0]= new Arrow_Data(-1.5f,0f, ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,0,true);
            arrow_datas[1]= new Arrow_Data(1.5f,0f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,2,false);
            arrow_datas[2]= new Arrow_Data(0f,-.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,1,false);
            arrow_datas[3]= new Arrow_Data(0f,.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,3,false);
        }else if (location.location_index==4) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0, 1f);
            arrow_datas[0]= new Arrow_Data(-1.5f,0f, ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,0,true);
            arrow_datas[1]= new Arrow_Data(1.5f,0f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,2,false);
            arrow_datas[2]= new Arrow_Data(0f,-.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,1,false);
            arrow_datas[3]= new Arrow_Data(0f,.5f,ARROW_WIDTH*ARROW_AR,ARROW_WIDTH,3,false);
        }else if (location.location_index==5) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0, 1f);
        }else if (location.location_index==6) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0, 1f);
        }else if (location.location_index==7) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0, 1f);
        }else if (location.location_index==8) {
            base = new Location_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.location_ground_grass_bass), 1f, .5f, 0, 0, 1f);
        }

    }


    public void step(){
        for(int i=0;i<asset_list.size();i++){
            if (asset_list.get(i).dynamic){
                asset_list.get(i).step();
            }
        }

    }


    public void step_people(){
        for (int i=0;i<PEOPLE_IN_SCENE_SIZE;i++){
            people_in_scene[i].step();
        }
    }

    public float check_oob(float i_x){
        if (i_x>width){
            return width;
        }else if (i_x<-width){
            return -width;
        }
        return i_x;
    }



    public void draw_location(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], float x, float cam_x, Global_Assets assets){

        step();

        for (int i=0;i<asset_list.size();i++){
            draw_asset_far(scratch,mMVPMatrix,zeroRotationMatrix,assets.location_assets.assets.get(asset_list.get(i).index), asset_list.get(i).x+cam_x*asset_list.get(i).distance, asset_list.get(i).y, asset_list.get(i).size, asset_list.get(i).AR, 0);
        }


        for (int i=0;i<PEOPLE_IN_SCENE_SIZE;i++){
            people_in_scene[i].step_npc();
            //people_in_scene[i].step();
            people_in_scene[i].DrawSelf(scratch,mMVPMatrix,zeroRotationMatrix,assets);
        }


        for (int i=0;i<4;i++){
            if (set_arrow_active(x, arrow_datas[i].x,i) && arrow_datas[i].active){
                draw_asset_custom(scratch,mMVPMatrix,zeroRotationMatrix,arrow,arrow_datas[i].direction, arrow_datas[i].x, arrow_datas[i].y);
            }

        }

    }

    public void draw_background_dynamic_assets(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], float cam_x){
        if (DYNAMIC){
            for (int i=0;i<background_dynamic_location_list.size();i++){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, background_dynamic_location_list.get(i).x+cam_x*.8f, background_dynamic_location_list.get(i).y, 1f);
                Matrix.scaleM(scratch, 0, background_dynamic_asset[background_dynamic_location_list.get(i).size_index].size, background_dynamic_asset[background_dynamic_location_list.get(i).size_index].AR*background_dynamic_asset[background_dynamic_location_list.get(i).size_index].size,.5f);
                Matrix.rotateM(scratch, 0, background_dynamic_location_list.get(i).xr, background_dynamic_location_list.get(i).yr, background_dynamic_location_list.get(i).zr, 1f);
                background_dynamic_asset[background_dynamic_location_list.get(i).size_index].Draw(scratch);
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

    public void draw_location_battle(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], float cam_x, Global_Assets assets){


        for (int i=0;i<asset_list.size();i++){
            draw_asset_far(scratch,mMVPMatrix,zeroRotationMatrix,assets.location_assets.assets.get(asset_list.get(i).index), asset_list.get(i).x+cam_x*asset_list.get(i).distance, asset_list.get(i).y, asset_list.get(i).size, asset_list.get(i).AR, 0);
        }

    }



    private void draw_asset_far(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], Location_Graphics_Asset asset, float x, float y, float size, float AR, int dir){
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x, y, 1f);
        Matrix.scaleM(scratch, 0, size,size*AR,.5f);
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
