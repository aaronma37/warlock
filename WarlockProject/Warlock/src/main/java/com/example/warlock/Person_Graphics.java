package com.example.warlock;

import android.content.Context;
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
public class Person_Graphics {

    public Person_Graphics_Asset hair;
    public Person_Graphics_Asset face;
    public Person_Graphics_Asset eyes;
    public Context myContext;

    public List<Person_Graphics_Asset_Asset> hair_assets = new ArrayList<>();


    public Person_Graphics(Context context, int hair_index, int face_index, int eyes_index){

        myContext=context;



        if (face_index==0){
            face = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_head1), 1f,.06f,.021f,-.008f);
        }

        if (hair_index==0){
            hair  = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_h1_base),.15f/.15f,.1f, .035f-face.x_off,-face.y_off);

            Person_Graphics_Asset_Asset hair_asset_1 = new Person_Graphics_Asset_Asset(myContext, loadTexture(myContext,R.drawable.f_h1_add_1),.15f/.15f,.06f, -.1f-face.x_off-hair.x_off,.02f-face.y_off-hair.y_off);
            hair_assets.add(hair_asset_1);
        }

        if (eyes_index==0){
            eyes = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.f_eyes1),1f,.02f,0.058f,-.0195f);
        }

    }

    public void resolve_movement(float x_movement, float y_movement, int dir){

        for (int i =0; i<hair_assets.size();i++){
            hair_assets.get(i).step(x_movement,dir);
        }
    }

    public void draw_person(float scratch[], float mMVPMatrix[], float zeroRotationMatrix[], float x, float y, int dir){

        Matrix.translateM(scratch, 0, x+face.x_off*dir, y+face.y_off, 1f);
        Matrix.scaleM(scratch, 0, face.size,face.size*face.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        face.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+eyes.x_off*dir, y+eyes.y_off, 1f);
        Matrix.scaleM(scratch, 0, eyes.size,eyes.size*eyes.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        eyes.Draw(scratch,false);

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x+face.x_off*dir+hair.x_off*dir, y+face.y_off+hair.y_off, 1f);
        Matrix.scaleM(scratch, 0, hair.size,hair.size*hair.AR,.5f);
        Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

        hair.Draw(scratch,false);

        for (int i =0; i<hair_assets.size();i++){
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
            Matrix.translateM(scratch, 0, x+face.x_off*dir+hair.x_off*dir+hair_assets.get(i).x_off*dir, y+face.y_off+hair.y_off+hair_assets.get(i).y_off, 1f);
            Matrix.scaleM(scratch, 0, hair_assets.get(i).size,hair_assets.get(i).size*hair_assets.get(i).AR,.5f);
            Matrix.rotateM(scratch, 0, 90-dir*90, 0, 1f, 0);

            hair_assets.get(i).Draw(scratch,false);
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
