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
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class UI_Graphics
{
    //Reference to Activity Context
    private final Context mActivityContext;
    public int COMMAND_SEAL=1,NOTHING=0,REWARD=2, START=3, MOVE=4, PAUSE_MENU=5;

    public int BUTTON=1,ADAPTIVE=2;

    public int BATTLE=0, START_SCREEN=1, SECONDARY_SCREEN=2, DUNGEON=3, DUNGEONS_LEVEL=4;
    public int char_loadout=0;
    //Added for Textures
    private final FloatBuffer mCubeTextureCoordinates;
    private int mTextureUniformHandle;
    private int mTextureCoordinateHandle;
    private final int mTextureCoordinateDataSize = 2;
    private int mTextureDataHandle[] = new int[35];
    private int selectedTextureDataHandle[] = new int[35];
    public Image_Info images[] = new Image_Info[35];
    public float left,right,up,down;
    public int number_of_images=0;
    public float width, height, y,x;
    public boolean active=false;
    public int s;

    private float PAUSE_MENU_BORDER_SIZE=.01f;
    private float PAUSE_MENU_ITEM_BORDER_SIZE=.1f;


    private float PAUSE_MENU_WIDTH=1.6f;
    private float PAUSE_MENU_HEIGHT=.8f;
    private float PAUSE_MENU_X=0;
    private float PAUSE_MENU_Y=0;

    private float PAUSE_MENU_EXIT_BAR_HEIGHT=PAUSE_MENU_HEIGHT-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_EXIT_BAR_WIDTH=.1f-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_EXIT_BAR_X=PAUSE_MENU_X+PAUSE_MENU_WIDTH-PAUSE_MENU_EXIT_BAR_WIDTH-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_EXIT_BAR_Y=PAUSE_MENU_Y;

    private float PAUSE_MENU_BUTTON_WIDTH=1.6f*1.75f/4f-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_BUTTON_HEIGHT=.2f-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_BUTTON_X=PAUSE_MENU_EXIT_BAR_X-PAUSE_MENU_EXIT_BAR_WIDTH-PAUSE_MENU_BUTTON_WIDTH-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_BUTTON_Y=PAUSE_MENU_Y+PAUSE_MENU_HEIGHT-PAUSE_MENU_BUTTON_HEIGHT-PAUSE_MENU_BORDER_SIZE;

    private float PAUSE_MENU_RIGHT_QUAD_WIDTH=PAUSE_MENU_WIDTH/2f-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_RIGHT_QUAD_HEIGHT=PAUSE_MENU_HEIGHT/2f-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_RIGHT_QUAD_X=PAUSE_MENU_X-PAUSE_MENU_RIGHT_QUAD_WIDTH-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_RIGHT_QUAD_Y=PAUSE_MENU_Y+PAUSE_MENU_HEIGHT-PAUSE_MENU_RIGHT_QUAD_HEIGHT-PAUSE_MENU_BORDER_SIZE;

    private float PAUSE_MENU_LEFT_HALF_WIDTH=PAUSE_MENU_WIDTH/2f-PAUSE_MENU_EXIT_BAR_WIDTH-PAUSE_MENU_BORDER_SIZE*2f;
    private float PAUSE_MENU_LEFT_HALF_HEIGHT=PAUSE_MENU_HEIGHT-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_LEFT_HALF_X=PAUSE_MENU_X+PAUSE_MENU_WIDTH-PAUSE_MENU_RIGHT_QUAD_WIDTH-PAUSE_MENU_BORDER_SIZE-PAUSE_MENU_EXIT_BAR_WIDTH;
    private float PAUSE_MENU_LEFT_HALF_Y=PAUSE_MENU_Y;

    private float PAUSE_MENU_RIGHT_LOWER_QUAD_WIDTH=PAUSE_MENU_WIDTH/2f-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_RIGHT_LOWER_QUAD_HEIGHT=PAUSE_MENU_HEIGHT/2f-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_RIGHT_LOWER_QUAD_X=PAUSE_MENU_X-PAUSE_MENU_RIGHT_QUAD_WIDTH-PAUSE_MENU_BORDER_SIZE;
    private float PAUSE_MENU_RIGHT_LOWER_QUAD_Y=PAUSE_MENU_Y-PAUSE_MENU_HEIGHT+PAUSE_MENU_RIGHT_QUAD_HEIGHT+PAUSE_MENU_BORDER_SIZE;

    public float PAUSE_MENU_ITEM_PORTRAIT_WIDTH=.2f-PAUSE_MENU_ITEM_BORDER_SIZE;
    public float PAUSE_MENU_ITEM_PORTRAIT_HEIGHT=.2f-PAUSE_MENU_ITEM_BORDER_SIZE;
    public float PAUSE_MENU_ITEM_PORTRAIT_X=PAUSE_MENU_LEFT_HALF_X+PAUSE_MENU_LEFT_HALF_WIDTH-PAUSE_MENU_ITEM_PORTRAIT_WIDTH-PAUSE_MENU_ITEM_BORDER_SIZE;
    public float PAUSE_MENU_ITEM_PORTRAIT_Y=PAUSE_MENU_LEFT_HALF_Y+PAUSE_MENU_LEFT_HALF_HEIGHT-PAUSE_MENU_ITEM_PORTRAIT_HEIGHT-PAUSE_MENU_ITEM_BORDER_SIZE;
    public float PAUSE_MENU_ITEM_X_DELTA=.3f;
    public float PAUSE_MENU_ITEM_Y_DELTA=.3f;

    public int num_show_items=0;



    public float PAUSE_MENU_PLAYER_LOADOUT_X=-PAUSE_MENU_WIDTH/2f;
    public float PAUSE_MENU_PLAYER_LOADOUT_Y=PAUSE_MENU_HEIGHT/2.3f;

    public List<Integer> text_index_list = new ArrayList<>();


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

    public UI_Graphics(final Context activityContext, int k)
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


        final float[] cubeTextureCoordinateData =
                {
                        1f,  0f,
                        1f, 1f,
                        0f, 1f,
                        0f, 0f
                };



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


        if (k==0){
            //BATTLE
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.ui_battle_bottom), 1.8f, .3f,-.8f,0,NOTHING,NOTHING,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.fire_symbol),.1f,.1f,-.85f,-1.35f+0*.7f,COMMAND_SEAL,0,BUTTON);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.water_symbol),.1f,.1f,-.55f,-1.35f+0*.3f,COMMAND_SEAL,3,BUTTON);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.light_symbol),.1f,.1f,-.85f,-1.35f+2*.3f,COMMAND_SEAL,1,BUTTON);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.dark_symbol),.1f,.1f,-.55f,-1.35f+2*.3f,COMMAND_SEAL,2,BUTTON);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.red_button),.15f,.12f,-.85f,.15f,REWARD,0,BUTTON);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.green_button),.15f,.12f,-.85f,-.1f,REWARD,1,BUTTON);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.red_button),.15f,.12f,-.85f,.9f,REWARD,2,BUTTON);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.green_button),.15f,.12f,-.85f,.65f,REWARD,3,BUTTON);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.hp_box),.1f,.03f,-.91f,1.45f,NOTHING,NOTHING,ADAPTIVE);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.ui_battle_player_portrait),.23f,.23f,-.65f,1.45f,NOTHING,NOTHING,NOTHING);number_of_images++;
        }else if (k==1){
            //STARTING SCREEN
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.red_dot), .3f, .3f,-.8f,0,START,SECONDARY_SCREEN,BUTTON);number_of_images++;
        }else if (k==2){
            //SECONDARY SCREEN
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.ui_battle_player_portrait), 1f, .3f,.8f,.5f,START,DUNGEON,BUTTON);number_of_images++;
        }else if (k==3){
            //DUNGEON SCREEN
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.ui_battle_player_portrait),1f, .3f,.8f,.5f,START,DUNGEONS_LEVEL,BUTTON);number_of_images++;
        }else if (k==4){
            //DUNGEON LEVEL  SCREEN
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.ui_battle_player_portrait), 1f, .3f,.8f,.5f,START,BATTLE,BUTTON);number_of_images++;
        }else if (k==5){
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.location_arrow), .1f, .1f,-.8f,1.55f,MOVE,1,BUTTON);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.location_arrow),.1f,.1f,-.8f,1.15f,MOVE,-1,BUTTON);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),.1f,.1f,.8f,-1.55f,PAUSE_MENU,1,BUTTON);number_of_images++;

        }else if (k==11){
            //Pause screen
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_EXIT_BAR_WIDTH, PAUSE_MENU_EXIT_BAR_HEIGHT,PAUSE_MENU_EXIT_BAR_Y,PAUSE_MENU_EXIT_BAR_X,PAUSE_MENU,0,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_BUTTON_WIDTH, PAUSE_MENU_BUTTON_HEIGHT,PAUSE_MENU_BUTTON_Y,PAUSE_MENU_BUTTON_X,PAUSE_MENU,2,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_BUTTON_WIDTH, PAUSE_MENU_BUTTON_HEIGHT,PAUSE_MENU_BUTTON_Y-(PAUSE_MENU_BUTTON_HEIGHT+PAUSE_MENU_BORDER_SIZE)*2f,PAUSE_MENU_BUTTON_X,PAUSE_MENU,3,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_BUTTON_WIDTH, PAUSE_MENU_BUTTON_HEIGHT,PAUSE_MENU_BUTTON_Y-(PAUSE_MENU_BUTTON_HEIGHT+PAUSE_MENU_BORDER_SIZE)*4f,PAUSE_MENU_BUTTON_X,PAUSE_MENU,4,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_BUTTON_WIDTH, PAUSE_MENU_BUTTON_HEIGHT,PAUSE_MENU_BUTTON_Y-(PAUSE_MENU_BUTTON_HEIGHT+PAUSE_MENU_BORDER_SIZE)*6f,PAUSE_MENU_BUTTON_X,PAUSE_MENU,5,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_RIGHT_QUAD_WIDTH, PAUSE_MENU_RIGHT_QUAD_HEIGHT,PAUSE_MENU_RIGHT_QUAD_Y,PAUSE_MENU_RIGHT_QUAD_X,NOTHING,NOTHING,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_RIGHT_LOWER_QUAD_WIDTH, PAUSE_MENU_RIGHT_LOWER_QUAD_HEIGHT,PAUSE_MENU_RIGHT_LOWER_QUAD_Y,PAUSE_MENU_RIGHT_LOWER_QUAD_X,NOTHING,NOTHING,NOTHING);number_of_images++;

            for (int i=20;i<28;i++){
                text_index_list.add(i);
            }

            for (int i=40;i<44;i++){
                text_index_list.add(i);
            }
            char_loadout=1;

        }else if (k==12){
            //Pause screen
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_EXIT_BAR_WIDTH, PAUSE_MENU_EXIT_BAR_HEIGHT,PAUSE_MENU_EXIT_BAR_Y,PAUSE_MENU_EXIT_BAR_X,PAUSE_MENU,1,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_RIGHT_QUAD_WIDTH, PAUSE_MENU_RIGHT_QUAD_HEIGHT,PAUSE_MENU_RIGHT_QUAD_Y,PAUSE_MENU_RIGHT_QUAD_X,NOTHING,NOTHING,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_RIGHT_LOWER_QUAD_WIDTH, PAUSE_MENU_RIGHT_LOWER_QUAD_HEIGHT,PAUSE_MENU_RIGHT_LOWER_QUAD_Y,PAUSE_MENU_RIGHT_LOWER_QUAD_X,NOTHING,NOTHING,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box),PAUSE_MENU_LEFT_HALF_WIDTH, PAUSE_MENU_LEFT_HALF_HEIGHT,PAUSE_MENU_LEFT_HALF_Y,PAUSE_MENU_LEFT_HALF_X,NOTHING,NOTHING,NOTHING);number_of_images++;

            num_show_items=10;
            char_loadout=1;
        }else if (k==13){
            //Pause screen
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box), PAUSE_MENU_WIDTH, PAUSE_MENU_HEIGHT,PAUSE_MENU_Y,PAUSE_MENU_X,NOTHING,NOTHING,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box), PAUSE_MENU_EXIT_BAR_WIDTH, PAUSE_MENU_EXIT_BAR_HEIGHT,PAUSE_MENU_EXIT_BAR_Y,PAUSE_MENU_EXIT_BAR_X,PAUSE_MENU,1,NOTHING);number_of_images++;
        }else if (k==14){
            //Pause screen
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box), PAUSE_MENU_WIDTH, PAUSE_MENU_HEIGHT,PAUSE_MENU_Y,PAUSE_MENU_X,NOTHING,NOTHING,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box), PAUSE_MENU_EXIT_BAR_WIDTH, PAUSE_MENU_EXIT_BAR_HEIGHT,PAUSE_MENU_EXIT_BAR_Y,PAUSE_MENU_EXIT_BAR_X,PAUSE_MENU,1,NOTHING);number_of_images++;
        }else if (k==15){
            //Pause screen
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box), PAUSE_MENU_WIDTH, PAUSE_MENU_HEIGHT,PAUSE_MENU_Y,PAUSE_MENU_X,NOTHING,NOTHING,NOTHING);number_of_images++;
            images[number_of_images] = new Image_Info(loadTexture(mActivityContext, R.drawable.pause_box), PAUSE_MENU_EXIT_BAR_WIDTH, PAUSE_MENU_EXIT_BAR_HEIGHT,PAUSE_MENU_EXIT_BAR_Y,PAUSE_MENU_EXIT_BAR_X,PAUSE_MENU,1,NOTHING);number_of_images++;
        }




    }

    public void Draw(float[] mvpMatrix, boolean k, int g)
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
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, images[g].returnTextureData());
        }
        else{
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, selectedTextureDataHandle[0]);
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

        for (int i=0;i<num_show_items;i++){

        }

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