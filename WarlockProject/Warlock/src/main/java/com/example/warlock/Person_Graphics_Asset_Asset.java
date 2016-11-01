package com.example.warlock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Person_Graphics_Asset_Asset
{
    //Reference to Activity Context
    private final Context mActivityContext;

    //Added for Textures
    private final FloatBuffer mCubeTextureCoordinates;
    private int mTextureUniformHandle;
    private int mTextureCoordinateHandle;
    private final int mTextureCoordinateDataSize = 2;
    private int mTextureDataHandle, selectedTextureDataHandle;
    public float left,right,up,down;
    public float width, height, y,x;
    public boolean active=false;
    public int s;
    public float vel_x;
    public float force;
    public Asset_Motion_Model model;



    private final String vertexShaderCode =
//Test
            "attribute vec2 a_TexCoordinate;" +
                    "varying vec2 v_TexCoordinate;" +
//End Test
                    "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position =  uMVPMatrix*vPosition;" +
                    //Test
                    "v_TexCoordinate = a_TexCoordinate;" +
                    //End Test
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
//Test
                    "uniform sampler2D u_Texture;" +
                    "varying vec2 v_TexCoordinate;" +
//End Test
                    "void main() {" +
//"gl_FragColor = vColor;" +
                    "gl_FragColor = (vColor * texture2D(u_Texture, v_TexCoordinate));" +
                    "}";

    private final int shaderProgram;
    public FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;
    public float AR;
    public float size;
    public float x_off;
    public float y_off;
    public float alpha;
    public float a1,a2,a3,a4,a5;
    public float mass;
    public float drag;
    public int order;


    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 2;
/*
    static float spriteCoords[] = {
            -1f,  1f,   // top left
            -1f, -1f,   // bottom left
            1f, -1f,   // bottom right
            1f,  1f}; //top right
*/

    public float spriteCoords[] = {


            -1f,  1f,   // top left xy
            -.5f, 1f,
            0, 1f,
            .5f, 1f,
            1f, 1f,

            -1f, .5f,
            -.5f, .5f,
            0f, .5f,
            .5f, .5f,
            1f, .5f,


            -1f, 0f,
            -.5f, 0f,
            0f, 0f,
            .5f, 0f,
            1f, 0f,

            -1f, -.5f,
            -.5f, -.5f,
            0f, -.5f,
            .5f, -.5f,
            1f, -.5f,


            -1f, -1f,
            -.5f, -1f,
            0f, -1f,
            .5f, -1f,
            1f, -1f



    }; //top right*/

    private ByteBuffer bb = ByteBuffer.allocateDirect(spriteCoords.length * 4);

    private short drawOrder[] = { 0,5,6, 0,6,1, 1,6,7, 1,7,2, 2,7,8, 2,8,3,  3,8,9, 3,9,4, 5,10,11, 5,11,6, 6,11,12, 6,12,7, 7,12,13, 7,13,8, 8,13,14, 8,14,9, 10,15,16, 10,16,11, 11,16,17, 11,17,12, 12,17,18, 12,18,13, 13,18,19, 13,19,14,
            15,20,21, 15,21,16, 16,21,22, 16,22,17, 17,22,23, 17,23,18, 18,23,24, 18,24,19


    }; //Order to draw vertices
    private final int vertexStride = COORDS_PER_VERTEX * 4; //Bytes per vertex

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 1f, 1f, 1f, 1f };

    public Person_Graphics_Asset_Asset(final Context activityContext, int k, float i_AR, float i_size, float i_x_off, float i_y_off, float a_1, float a_2, float a_3, float a_4, float a_5, float i_mass, float i_drag, int i_order)
    {
        AR=i_AR;
        size=i_size;
        x_off=i_x_off;
        y_off=i_y_off;
        vel_x=0;
        alpha=0;
        a1=a_1;
        a2=a_2;
        a3=a_3;
        a4=a_4;
        a5=a_5;
        mass=i_mass;
        drag=i_drag;
        order=i_order;

        model = new Asset_Motion_Model(a1,a2,a3,a4,a5,mass,drag);

        mActivityContext = activityContext;
        s = k;

        //Initialize Vertex Byte Buffer for Shape Coordinates / # of coordinate values * 4 bytes per float

        //Use the Device's Native Byte Order
        bb.order(ByteOrder.nativeOrder());
        //Create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        //Add the coordinates to the FloatBuffer
        vertexBuffer.put(spriteCoords);
        //Set the Buffer to Read the first coordinate
        vertexBuffer.position(0);


        final float[] cubeTextureCoordinateData =
                {

                        .99f,  0.01f,   // top left xy
                        .75f, 0.01f,
                        .5f, 0.01f,
                        .25f, 0.01f,
                        0.01f, 0.01f,

                        .99f, .25f,
                        .75f, .25f,
                        .5f, .25f,
                        .25f, .25f,
                        0.01f, .25f,

                        .99f, .5f,
                        .75f, .5f,
                        .5f, .5f,
                        .25f, .5f,
                        0.01f, .5f,

                        .99f, .75f,
                        .75f, .75f,
                        .5f, .75f,
                        .25f, .75f,
                        0.01f, .75f,

                        .99f, .99f,
                        .75f, .99f,
                        .5f, .99f,
                        .25f, .99f,
                        0.01f, .99f};



        mCubeTextureCoordinates = ByteBuffer.allocateDirect(cubeTextureCoordinateData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mCubeTextureCoordinates.put(cubeTextureCoordinateData).position(0);

        //Initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(spriteCoords.length * 4);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        int vertexShader = Rend.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = Rend.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        shaderProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(shaderProgram, vertexShader);
        GLES20.glAttachShader(shaderProgram, fragmentShader);

        //Texture Code
        GLES20.glBindAttribLocation(shaderProgram, 0, "a_TexCoordinate");

        GLES20.glLinkProgram(shaderProgram);

        mTextureDataHandle = k;
        selectedTextureDataHandle=k;
    }

    public void add_force(float i_force, int dir){
        force-=dir*i_force;
    }

    public void step(){


        vel_x=vel_x+.2f*(0-alpha)-force/mass-vel_x*drag;
        alpha=alpha+vel_x;

        if (alpha>1f){
            alpha=1f;
        }else if (alpha <-1f){
            alpha=-1f;
        }

        for (int i = 0;i<5;i++){
            spriteCoords[00+i*2]=(i*.5f-1f)+alpha*a1;
            spriteCoords[10+i*2]=(i*.5f-1f)+alpha*a2;
            spriteCoords[20+i*2]=(i*.5f-1f)+alpha*a3;
            spriteCoords[30+i*2]=(i*.5f-1f)+alpha*a4;
            spriteCoords[40+i*2]=(i*.5f-1f)+alpha*a5;
        }

        force=0;

        vertexBuffer.put(spriteCoords);
        vertexBuffer.position(0);

    }


    public void Draw(float[] mvpMatrix, boolean k)
    {

        GLES20.glUseProgram(shaderProgram);

        //Get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(shaderProgram, "vPosition");

        //Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        //Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        //Get Handle to Fragment Shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(shaderProgram, "vColor");

        //Set the Color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        //Set Texture Handles and bind Texture
        mTextureUniformHandle = GLES20.glGetAttribLocation(shaderProgram, "u_Texture");
        mTextureCoordinateHandle = GLES20.glGetAttribLocation(shaderProgram, "a_TexCoordinate");

        //Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

        //Bind the texture to this unit.
        if (k==false){
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
        }
        else{
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, selectedTextureDataHandle);
        }

        //Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES20.glUniform1i(mTextureUniformHandle, 0);

        //Pass in the texture coordinate information
        mCubeTextureCoordinates.position(0);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false, 0, mCubeTextureCoordinates);
        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);

        //Get Handle to Shape's Transformation Matrix
        mMVPMatrixHandle = GLES20.glGetUniformLocation(shaderProgram, "uMVPMatrix");

        //Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        //GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        GLES20.glEnable(GLES20.GL_BLEND);
        //Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        //Disable Vertex Array
        GLES20.glDisableVertexAttribArray(mPositionHandle);


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