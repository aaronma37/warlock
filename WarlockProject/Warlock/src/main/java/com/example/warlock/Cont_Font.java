package com.example.warlock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cont_Font
{
    //Reference to Activity Context
    private final Context mActivityContext;

    //Added for Textures
    private final FloatBuffer mCubeTextureCoordinates;
    private int mTextureUniformHandle;
    private int mTextureCoordinateHandle;
    private final int mTextureCoordinateDataSize = 2;
    private int mTextureDataHandle, lower_case, special;
    public float left,right,up,down;
    public float width, height, y,x;
    private float[] scratch = new float[16];
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

    public Cont_Font(final Context activityContext, int k)
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


        final float cubeTextureCoordinateData[] = new float[500];
        for (int j=0;j<26;j++){
            cubeTextureCoordinateData[8*j+0]=1f;
            cubeTextureCoordinateData[8*j+2]=cubeTextureCoordinateData[8*j+0];
            cubeTextureCoordinateData[8*j+4]=cubeTextureCoordinateData[8*j+0]-1f;
            cubeTextureCoordinateData[8*j+6]=cubeTextureCoordinateData[8*j+0]-1f;

            cubeTextureCoordinateData[8*j+1]=j*.039f;
            cubeTextureCoordinateData[8*j+3]=cubeTextureCoordinateData[8*j+1]+.033f;
            cubeTextureCoordinateData[8*j+5]=cubeTextureCoordinateData[8*j+1]+.033f;
            cubeTextureCoordinateData[8*j+7]=cubeTextureCoordinateData[8*j+1];
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

            mTextureDataHandle = loadTexture(mActivityContext, R.drawable.cont_font_caps);
            lower_case = loadTexture(mActivityContext, R.drawable.cont_font_lower);
            special = loadTexture(mActivityContext, R.drawable.cont_font_special);



    }


    public void Draw_Word(float[] mvpMatrix, char c)
    {
        scratch=mvpMatrix;

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



        //Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.


        //Pass in the texture coordinate information

            if (c=='A'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(0*8);
            }else if (c=='B'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(1*8);
            }else if (c=='C'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(2*8);
            }else if (c=='D'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(3*8);
            }else if (c=='E'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(4*8);
            }else if (c=='F'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(5*8);
            }else if (c=='G'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(6*8);
            }else if (c=='H'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(7*8);
            }else if (c=='I'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(8*8);
            }else if (c=='J'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(9*8);
            }else if (c=='K'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(10*8);
            }else if (c=='L'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(11*8);
            }else if (c=='M'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(12*8);
            }else if (c=='N'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(13*8);
            }else if (c=='O'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(14*8);
            }else if (c=='P'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(15*8);
            }else if (c=='Q'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(16*8);
            }else if (c=='R'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(17*8);
            }else if (c=='S'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(18*8);
            }else if (c=='T'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(19*8);
            }else if (c=='U'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(20*8);
            }else if (c=='V'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(21*8);
            }else if (c=='W'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(22*8);
            }else if (c=='X'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(23*8);
            }else if (c=='Y'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(24*8);
            }else if (c=='Z'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(25*8);
            }else if (c=='a'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(0*8);
            }else if (c=='b'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(1*8);
            }else if (c=='c'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(2*8);
            }else if (c=='d'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(3*8);
            }else if (c=='e'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(4*8);
            }else if (c=='f'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(5*8);
            }else if (c=='g'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(6*8);
            }else if (c=='h'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(7*8);
            }else if (c=='i'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(8*8);
            }else if (c=='j'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(9*8);
            }else if (c=='k'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(10*8);
            }else if (c=='l'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(11*8);
            }else if (c=='m'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(12*8);
            }else if (c=='n'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(13*8);
            }else if (c=='o'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(14*8);
            }else if (c=='p'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(15*8);
            }else if (c=='q'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(16*8);
            }else if (c=='r'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(17*8);
            }else if (c=='s'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(18*8);
            }else if (c=='t'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(19*8);
            }else if (c=='u'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(20*8);
            }else if (c=='v'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(21*8);
            }else if (c=='w'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(22*8);
            }else if (c=='x'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(23*8);
            }else if (c=='y'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(24*8);
            }else if (c=='z'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, lower_case);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(25*8);
            }else if (c=='!'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(0*8);
            }else if (c=='@'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(1*8);
            }else if (c=='#'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(2*8);
            }else if (c=='$'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(3*8);
            }else if (c=='%'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(4*8);
            }else if (c=='^'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(5*8);
            }else if (c=='&'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(6*8);
            }else if (c=='*'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(7*8);
            }else if (c=='('){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(8*8);
            }else if (c==')'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(9*8);
            }else if (c=='1'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(10*8);
            }else if (c=='2'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(11*8);
            }else if (c=='3'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(12*8);
            }else if (c=='4'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(13*8);
            }else if (c=='5'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(14*8);
            }else if (c=='6'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(15*8);
            }else if (c=='7'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(16*8);
            }else if (c=='8'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(17*8);
            }else if (c=='9'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(18*8);
            }else if (c=='0'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(19*8);
            }else if (c==' '){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(20*8);
            }else if (c=='.'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(21*8);
            }else if (c==';'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(22*8);
            }else if (c=='?'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(23*8);
            }else if (c=='/'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(24*8);
            }else if (c=='<'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(25*8);
            }else if (c=='>'){
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, special);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                mCubeTextureCoordinates.position(25*8);
            }

            GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false, 0, mCubeTextureCoordinates);
            GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);

            //Get Handle to Shape's Transformation Matrix
            mMVPMatrixHandle = GLES20.glGetUniformLocation(shaderProgram, "uMVPMatrix");

            //Apply the projection and view transformation
            GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, scratch, 0);
            GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
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