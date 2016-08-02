package com.example.warlock;


        import javax.microedition.khronos.egl.EGLConfig;
        import javax.microedition.khronos.opengles.GL10;

        import android.content.Context;
        import android.opengl.GLES20;
        import android.opengl.GLSurfaceView;
        import android.opengl.Matrix;
        import android.util.Log;

/*        import org.ros.node.ConnectedNode;
        import org.ros.node.DefaultNodeFactory;
        import org.ros.node.Node;
        import org.ros.node.NodeConfiguration;
        import org.ros.node.NodeFactory;*/


        import java.nio.FloatBuffer;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.HashMap;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Map;
        import java.util.Set;

/**
 * Provides drawing instructions for a GLSurfaceView object. This class
 * must override the OpenGL ES drawing lifecycle methods:
 * <ul>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceCreated}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onDrawFrame}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceChanged}</li>
 * </ul>
 */
public class Rend implements GLSurfaceView.Renderer {

    private static final String TAG = "MyGLRenderer";

    //FOR SURFACE PLOT

    private float geoDistance[][]= new float[100][100];
    public boolean SINFO_FLAG=true;
    private FloatBuffer textureBuffer;
    public Context context;

    float Coords[] = {
            -0.5f,  0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
            0.5f, -0.5f, 0.0f,   // bottom right
            0.5f,  0.5f, 0.0f }; // top right

    private float texture[] = {
            0.0f, 1.0f,     // top left     (V2)
            0.0f, 0.0f,     // bottom left  (V1)
            1.0f, 1.0f,     // top right    (V4)
            1.0f, 0.0f      // bottom right (V3)
    };


    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] stockMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];
    private final float[] zeroRotationMatrix = new float[16];
    float[] scratch = new float[16];
    float[] scratch2 = new float[16];
    public GeneralGraphic red_dot;


    private float width,height;

    private float mAngle;

    public Rend(Context context1) {
        context = context1;

    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);


        float sTemp[] = {
                -0.5f,  0.5f, 0.0f,   // top left
                -0.5f, -0.5f, 0.0f,   // bottom left
                0.5f, -0.5f, 0.0f,   // bottom right
                0.5f,  0.5f, 0.0f };
        float c[] = { 0f,255f,255f, 1.0f };

        sTemp[0]=-width/height;sTemp[1]=height/height;
        sTemp[3]=-width/height;;sTemp[4]=-height/(height*2);
        sTemp[6]=width/height;;sTemp[7]=-height/(height*2);
        sTemp[9]=width/height;sTemp[10]=height/height;

        sTemp[0]=-(width-100)/height;sTemp[1]=(height-5)/height;
        sTemp[3]=-(width-100)/height;sTemp[4]=-(height-10)/(height*2);
        sTemp[6]=(width-100)/height;;sTemp[7]=-(height-10)/(height*2);
        sTemp[9]=(width-100)/height;sTemp[10]=(height-5)/height;

        c[0]=255;c[1]=255;c[2]=255;c[3]=.2f;
        red_dot = new GeneralGraphic(context,0);

    }


    @Override
    public void onDrawFrame(GL10 unused) {

        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0, 0, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        Matrix.setRotateM(zeroRotationMatrix, 0, 0, 0, 0, 1.0f);
        Matrix.multiplyMM(stockMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        red_dot.Draw(stockMatrix,false);

    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

}