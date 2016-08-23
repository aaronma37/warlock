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

public class ProjectileSprite
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
    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 2;
    static float spriteCoords[] = {
            -1f,  1f,   // top left
            -1f, -1f,   // bottom left
            1f, -1f,   // bottom right
            1f,  1f}; //top right

    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; //Order to draw vertices
    private final int vertexStride = COORDS_PER_VERTEX * 4; //Bytes per vertex

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 1f, 1f, 1f, 1f };

    public ProjectileSprite(final Context activityContext, int k)
    {
        mActivityContext = activityContext;
        s = k;

        //Initialize Vertex Byte Buffer for Shape Coordinates / # of coordinate values * 4 bytes per float
        ByteBuffer bb = ByteBuffer.allocateDirect(spriteCoords.length * 4);
        //Use the Device's Native Byte Order
        bb.order(ByteOrder.nativeOrder());
        //Create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        //Add the coordinates to the FloatBuffer
        vertexBuffer.put(spriteCoords);
        //Set the Buffer to Read the first coordinate
        vertexBuffer.position(0);


        final float cubeTextureCoordinateData[] = new float[5*8];
        for (int j=0;j<5;j++){
/*            cubeTextureCoordinateData[8*j+0]=.075f+.079f*j;
            cubeTextureCoordinateData[8*j+2]=cubeTextureCoordinateData[8*j+0];
            cubeTextureCoordinateData[8*j+4]=cubeTextureCoordinateData[8*j+0]-.075f;
            cubeTextureCoordinateData[8*j+6]=cubeTextureCoordinateData[8*j+0]-.075f;


            cubeTextureCoordinateData[8*j+1]=.131f;
            cubeTextureCoordinateData[8*j+3]=.231f;
            cubeTextureCoordinateData[8*j+5]=.231f;
            cubeTextureCoordinateData[8*j+7]=.131f;*/

            cubeTextureCoordinateData[8*j+0]=1f;
            cubeTextureCoordinateData[8*j+2]=cubeTextureCoordinateData[8*j+0];
            cubeTextureCoordinateData[8*j+4]=cubeTextureCoordinateData[8*j+0]-1f;
            cubeTextureCoordinateData[8*j+6]=cubeTextureCoordinateData[8*j+0]-1f;


            cubeTextureCoordinateData[8*j+1]=0;
            cubeTextureCoordinateData[8*j+3]=1f;
            cubeTextureCoordinateData[8*j+5]=1f;
            cubeTextureCoordinateData[8*j+7]=0;

        }


        mCubeTextureCoordinates = ByteBuffer.allocateDirect(cubeTextureCoordinateData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mCubeTextureCoordinates.put(cubeTextureCoordinateData).position(0);

        //Initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(spriteCoords.length * 2);
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

        //Load the texture
        if (s<11){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.fire_projectile);
        }else if (s < 21){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.water_projectile);
        }else if (s < 31){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.nature_projectile);
        }else if (s < 41){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.light_projectile);
        }else if (s < 51){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.dark_projectile);
        }else if (s < 61){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.fire_water_projectile);
        }else if (s < 71){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.fire_nature_projectile);
        }else if (s < 81){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.fire_light_projectile);
        }else if (s < 91){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.fire_dark_projectile);
        }else if (s < 101){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.water_nature_projectile);
        }else if (s < 111){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.water_light_projectile);
        }else if (s < 121){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.water_dark_projectile);
        }else if (s < 131){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.nature_light_projectile);
        }else if (s < 141){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.nature_dark_projectile);
        }else if (s < 151){
            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.dark_light_projectile);
        }

    }


    public void Draw(float[] mvpMatrix, boolean k, int i)
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
        mCubeTextureCoordinates.position(i*8);
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