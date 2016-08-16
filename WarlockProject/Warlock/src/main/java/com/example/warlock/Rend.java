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


        import java.lang.reflect.Array;
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
    public int game_state=0;
    private boolean show_info=true;

    private List<Projectile> active_projectiles = new ArrayList<>();
    private Projectile projectile_swap[] = new Projectile[50];
    private int active_projectile_count=0;
    private List<Person> active_people = new ArrayList<>();

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
    public GeneralGraphic red_dot, stage_1;
    public GeneralGraphic red_box;
    public GeneralGraphic blue_box;
    public GeneralGraphic hp_box,cast_bar;
    public Person aaron, luke;
    public Projectile projectile_fireball;
    public Offensive_Physical_Actions fireball;
    private long start_time, end_time;
    private int meta_obs[] = new int[10];
    private int off_obs[] = new int[10];

    private Person temp_person = new Person();

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
        red_box = new GeneralGraphic(context,1);
        blue_box = new GeneralGraphic(context,2);
        hp_box = new GeneralGraphic(context,4);
        cast_bar = new GeneralGraphic(context,5);
        stage_1 = new GeneralGraphic(context,3);

        aaron = new Person("Aaron", -.5f, -.1f);
        luke = new Person("Luke", .5f, -.1f);
        projectile_fireball = new Projectile(0f, 0f, .001f,5,0,0,0f, new Hitbox(2,2), 0,100);
        fireball = new Offensive_Physical_Actions(100f, 0, projectile_fireball,aaron);
        enterArena();
        float start_time = System.currentTimeMillis();


    }

    public void enterArena(){
        active_people.clear();
        active_people.add(aaron);
        active_people.add(luke);

/*        aaron.cast(new Offensive_Physical_Actions(100f, 0, new Projectile(aaron.center_x, aaron.center_y, .001f,5,0,0,0f, new Hitbox(2,2), 0, 5),luke));
        aaron.action.active=false;
        aaron.busy=false;

        luke.cast(new Offensive_Physical_Actions(100f, 0, new Projectile(luke.center_x, luke.center_y, .001f,5,0,0,0f, new Hitbox(2,2), 0, 5),aaron));
        luke.action.active=false;
        luke.busy=false;*/
        //active_projectiles.clear();

        aaron.a[1].o[2]=0;
        aaron.a[1].o[0]=0;
        aaron.a[1].o[1]=0;

        aaron.a[1].o[6]=0;
        aaron.a[1].o[7]=0;
        aaron.a[1].o[8]=0;

        //OFFENSIVE

        aaron.available_action_space.remove(0);
        aaron.off_a[2].o[8]=100;
        aaron.off_a[2].o[7]=100;

        luke.available_action_space.remove(2);




        for (int i =0; i<50;i++){
            projectile_swap[i]= new Projectile();
        }
    }

    public void exitArena(){
        active_people.clear();
    }

    public boolean checkCollision(Hitbox hbox1, float x_1, float y_1, Hitbox hbox2, float x_2, float y_2){
        //System.out.println("size of hitbox: " + hbox1.x);
        if (x_1-hbox1.x/100<=x_2+hbox2.x/100 && x_1+hbox1.x/100 >= x_2-hbox2.x/100){
            if (y_1-hbox1.y/100<=y_2+hbox2.y/100 && y_1+hbox1.y/100 >= y_2-hbox2.y/100){
                return true;
            }
        }

        return false;
    }


    @Override
    public void onDrawFrame(GL10 unused) {
        end_time = System.currentTimeMillis();

        if (end_time - start_time < 33)
            try{ Thread.sleep(33-(end_time - start_time)); }catch(InterruptedException e){ }
        start_time = System.currentTimeMillis();


        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0, 0, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        Matrix.setRotateM(zeroRotationMatrix, 0, 0, 0, 0, 1.0f);
        Matrix.multiplyMM(stockMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //red_dot.Draw(stockMatrix,false);
        //_box.Draw(stockMatrix,false);

        if (game_state==0){


            for (int i = 0; i < active_people.size(); i++){
                //temp_person = active_people.get(i);
                if(!active_people.get(i).busy){
                    //Choose what to do::
                    if (i==0){
                        calculateMetaObs(active_people.get(i),luke);
                        calculateOffObs(active_people.get(i),luke);
                        active_people.get(i).choose(meta_obs,off_obs,luke);
                        //active_people.get(i).cast(new Offensive_Physical_Actions(100f, 0, new Projectile(active_people.get(i).center_x, active_people.get(i).center_y, .001f,5,0,0,0f, new Hitbox(2,2), 0, 5),luke));
                       //active_people.get(i).cast(0,luke);
                        // fireball.set(0,projectile_fireball, luke);
                      //  active_people.get(i).cast(fireball);

                    }else{
                        calculateMetaObs(active_people.get(i),aaron);
                        calculateOffObs(active_people.get(i),aaron);

                        active_people.get(i).choose(meta_obs,off_obs,aaron);
                        //active_people.get(i).cast(0,aaron);

                       // active_people.get(i).cast(new Offensive_Physical_Actions(100f, 0, new Projectile(active_people.get(i).center_x, active_people.get(i).center_y, .001f,5,0,0,0f, new Hitbox(2,2), 0, 5),aaron));
                    //    fireball.set(0,projectile_fireball, aaron);

                //        active_people.get(i).cast(fireball);
                    }
                }else if (active_people.get(i).action.active){
                    //Complete action
                    active_people.get(i).action.step();
                    if (active_people.get(i).action.move_flag){
                        active_people.get(i).motion(active_people.get(i).action.move_speed,active_people.get(i).action.move_direction);
                    }
                    if (active_people.get(i).action.facing_flag){
                        active_people.get(i).facing_direction=active_people.get(i).action.facing_direction;
                    }
                    if (active_people.get(i).action.make_active){
                        active_people.get(i).action.make_active=false;
                        active_people.get(i).busy=false;
                        //Add new projectile to list
                        if (active_people.get(i).action.projectile_flag){
                            projectile_swap[active_projectiles.size()].reset();
                            projectile_swap[active_projectiles.size()].setSpell(active_people.get(i).action.target,active_people.get(i),active_people.get(i).action.spell_type);
                            active_projectiles.add(projectile_swap[active_projectiles.size()]);
                        }


                        //active_projectiles.get(active_projectiles.size()-1).setSpell(active_people.get(i).action.target,active_people.get(i),0);
                        //active_projectiles.get(active_projectiles.size()-1).addTarget(luke);
                    }
                }
            }

            for (int i = active_projectiles.size()-1; i >= 0;i--){

                if (!active_projectiles.get(i).active){
                    projectile_swap[i].reset();
                    active_projectiles.remove(i);
                    break;
                }else{
                    active_projectiles.get(i).step();
                }
                //Check hit
                for (int j = 0; j< active_projectiles.get(i).active_targets.size();j++){
                    if (checkCollision(active_projectiles.get(i).active_targets.get(j).hitbox,active_projectiles.get(i).active_targets.get(j).center_x,active_projectiles.get(i).active_targets.get(j).center_y,active_projectiles.get(i).hitbox,active_projectiles.get(i).location_x,active_projectiles.get(i).location_y)){
                        active_projectiles.get(i).on_hit();
                        active_projectiles.get(i).active_targets.get(j).hitBy(active_projectiles.get(i));
                    }
                }

            }



            //Load stage
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
            Matrix.scaleM(scratch, 0, 2f,1f, 1f);
            stage_1.Draw(scratch,false);

            //Load characters
            for (int i = 0; i< active_people.size();i++){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, active_people.get(i).center_x, active_people.get(i).center_y, 0);
                Matrix.scaleM(scratch, 0, active_people.get(i).hitbox.x*2/100f,active_people.get(i).hitbox.y*2/100f,.5f);
                blue_box.Draw(scratch,false);

                if (show_info){
                    Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                    Matrix.translateM(scratch, 0, active_people.get(i).center_x+active_people.get(i).hitbox.x*2/100f-active_people.get(i).health/1600f, active_people.get(i).center_y+.15f, 0);
                    Matrix.scaleM(scratch, 0, active_people.get(i).health/1600f,1/100f,.5f);
                    hp_box.Draw(scratch,false);

                    Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                    Matrix.translateM(scratch, 0, active_people.get(i).center_x+active_people.get(i).hitbox.x*2/100f-active_people.get(i).action.cast_time/active_people.get(i).action.total_cast_time/16f, active_people.get(i).center_y+.12f, 0);
                    Matrix.scaleM(scratch, 0, active_people.get(i).action.cast_time/active_people.get(i).action.total_cast_time/16f,1/100f,.5f);
                    cast_bar.Draw(scratch,false);


                }

            }

            for (int i = 0; i< active_projectiles.size();i++){
                if (active_projectiles.get(i).active){
                    Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                    Matrix.translateM(scratch, 0, active_projectiles.get(i).location_x, active_projectiles.get(i).location_y, 0);
                    Matrix.scaleM(scratch, 0, active_projectiles.get(i).hitbox.x*2/100f,active_projectiles.get(i).hitbox.y*2/100f,.5f);
                    red_box.Draw(scratch,false);
                }

            }



        }



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

    public void calculateMetaObs(Person self, Person enemy){
        //FOR META ~

        for (int i = 0; i < 10; i++){
            meta_obs[i]=0;
        }

        // HEALTHS
        if (self.health >= 70){
            meta_obs[0]=1;
        }else if (self.health >= 30){
            meta_obs[1]=1;
        }else{
            meta_obs[2]=1;
        }

        if (enemy.health >= 70){
            meta_obs[3]=1;
        }else if (enemy.health >= 30){
            meta_obs[4]=1;
        }else{
            meta_obs[5]=1;
        }

        //DANGER
        int danger_distance=3;
        for (int i=0;i<active_projectiles.size();i++){
            if (active_projectiles.get(i).active_targets.get(0).name==self.name){
                if (active_projectiles.get(i).distance_from_target<danger_distance){
                    danger_distance=active_projectiles.get(i).distance_from_target;
                }
            }
        }
        if (danger_distance==2){
            meta_obs[6]=1;
        }else if (danger_distance==1){
            meta_obs[7]=1;
        }else if (danger_distance==0){
            meta_obs[8]=1;
        }
    }

    public void calculateOffObs(Person self, Person enemy){
        //FOR META ~

        for (int i = 0; i < 10; i++){
            off_obs[i]=0;
        }

        // HEALTHS
        if (self.health >= 70){
            off_obs[0]=1;
        }else if (self.health >= 30){
            off_obs[1]=1;
        }else{
            off_obs[2]=1;
        }

        if (enemy.health >= 70){
            off_obs[3]=1;
        }else if (enemy.health >= 30){
            off_obs[4]=1;
        }else{
            off_obs[5]=1;
        }

        //DANGER
        if (Math.abs(self.center_x-enemy.center_x)>.5f){
            off_obs[6]=1;
        }else if (Math.abs(self.center_x-enemy.center_x)>.35f){
            off_obs[7]=1;
        }else{
            off_obs[8]=1;
        }
    }

}