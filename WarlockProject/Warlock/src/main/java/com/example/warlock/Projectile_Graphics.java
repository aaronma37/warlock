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
public class Projectile_Graphics {

    public Person_Graphics_Asset projectile_base;

    public Context myContext;

    public int head_counter=0;
    public int HEAD_STATIC_MAX=30;
    public float ADJ_SCALE=1.5f;
    static int X_LOC=0;
    static int Y_LOC=1;
    public float head_position_static[][] = new float[HEAD_STATIC_MAX][2];
    public int body_sprite_count=0;
    public float overall_scale=.7f;
    public float rotation=0;


    public List<Projectile_Graphics_Asset> pre_base_assets = new ArrayList<>();
    public List<Projectile_Graphics_Asset> post_base_assets = new ArrayList<>();

    public Particle_Info particle_info[] = new Particle_Info[10];

    public Projectile_Graphics(Context context, int projectile_index){

        myContext=context;


        if (projectile_index==0){
            projectile_base = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.fire_1_1_base), 1f,1.3f*ADJ_SCALE*100f/1000,ADJ_SCALE*.021f,ADJ_SCALE*-.008f);
            rotation=90;
        }else if (projectile_index==1){
            projectile_base = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.fire_1_1_base), 1f,1.3f*ADJ_SCALE*100f/1000,ADJ_SCALE*.021f,ADJ_SCALE*-.008f);
            rotation=90;
        }else if (projectile_index==2){
            projectile_base = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.fire_1_1_base), 1f,1.3f*ADJ_SCALE*100f/1000,ADJ_SCALE*.021f,ADJ_SCALE*-.008f);
            rotation=90;
        }else if (projectile_index==3){
            projectile_base = new Person_Graphics_Asset(myContext, loadTexture(myContext, R.drawable.fire_1_1_base), 1f,1.3f*ADJ_SCALE*100f/1000,ADJ_SCALE*.021f,ADJ_SCALE*-.008f);
            rotation=90;
        }



    }

    public void draw_projectile_base(float x, float y, float dir, float scratch[],float mMVPMatrix[],float zeroRotationMatrix[]){


        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, x, y, 1f);
        Matrix.scaleM(scratch, 0, overall_scale*projectile_base.size,overall_scale*projectile_base.size*projectile_base.AR,.5f);
        Matrix.rotateM(scratch, 0, -90+dir*90, 0, 1f, 0);
        projectile_base.Draw(scratch,false);
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
