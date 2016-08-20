package com.example.warlock;


        import javax.microedition.khronos.egl.EGLConfig;
        import javax.microedition.khronos.opengles.GL10;

        import android.content.Context;
        import android.opengl.GLES10;
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
    private boolean show_info=false;

    private List<Projectile> active_projectiles = new ArrayList<>();
    private Projectile projectile_swap[] = new Projectile[50];
    private int active_projectile_count=0;
    private List<Person> active_people = new ArrayList<>();
    private List<Person> blue_team = new ArrayList<>();
    private List<Person> red_team = new ArrayList<>();

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
    private final float[] simpleRotationMatrix = new float[4];

    private final float GROUND_LEVEL=-.1f;
    float[] scratch = new float[16];
    float[] scratch2 = new float[16];
    public GeneralGraphic red_dot, stage_1;
    public GeneralGraphic red_box;
    public GeneralGraphic blue_box;
    public GeneralGraphic hp_box,cast_bar,start_button;
    public GeneralGraphic castle_background;
    public GeneralGraphic ice_shard;
    public GeneralGraphic water_symbol;
    public GeneralGraphic water_circle;

    public User user_information = new User();

    public Sprite sprite;

    public Person aaron, luke;
    public Projectile projectile_fireball;
    public Offensive_Physical_Actions fireball;
    private long start_time, end_time;
    private int meta_obs[] = new int[10];
    private int off_obs[] = new int[10];
    private boolean blue_victory_flag, red_victory_flag;
    private int victory=0;

    private Person temp_person = new Person();

    private float width,height;

    private float mAngle;

    public Rend(Context context1) {
        context = context1;
        aaron = new Person("Aaron", -.5f, GROUND_LEVEL);
        luke = new Person("Luke", .5f, GROUND_LEVEL);
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //GLES20.glOrthof(0,2f,1f,0,1f,1f);

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

        sTemp[0]=-.1f;sTemp[1]=.1f;
        sTemp[3]=-.1f;sTemp[4]=-.1f;
        sTemp[6]=.1f;sTemp[7]=-.1f;
        sTemp[9]=.1f;sTemp[10]=.1f;

        c[0]=255;c[1]=255;c[2]=255;c[3]=.2f;
        red_dot = new GeneralGraphic(context,0);
        red_box = new GeneralGraphic(context,1);
        blue_box = new GeneralGraphic(context,2);
        hp_box = new GeneralGraphic(context,4);
        cast_bar = new GeneralGraphic(context,5);
        stage_1 = new GeneralGraphic(context,3);
        start_button= new GeneralGraphic(context,6, .3f, .1f,0,0);
        ice_shard = new GeneralGraphic(context,8,.15f, .05f,0,0);
        water_symbol= new GeneralGraphic(context,9);
        water_circle= new GeneralGraphic(context,10,.3f, .3f,0,0);

        sprite = new Sprite(context,0);
        castle_background = new GeneralGraphic(context,7);

        projectile_fireball = new Projectile(0f, 0f, .001f,5,0,0,0f, new Hitbox(2,2), 0,100);
        fireball = new Offensive_Physical_Actions(100f, 0, projectile_fireball,aaron);
        enterArena();
        float start_time = System.currentTimeMillis();



    }

    public void enterArena(){
        aaron.reset(-.5f, GROUND_LEVEL);
        luke.reset(1f, GROUND_LEVEL);

        game_state=0;



        active_people.clear();
        active_people.add(aaron);
        active_people.add(luke);

        blue_team.clear();
        blue_team.add(aaron);

        red_team.clear();
        red_team.add(luke);

        victory=0;


        aaron.setActionSpace();
        luke.setActionSpace();

        aaron.a[1].o[2]=0;
        aaron.a[1].o[0]=0;
        aaron.a[1].o[1]=0;

        aaron.a[1].o[6]=0;
        aaron.a[1].o[7]=0;
        aaron.a[1].o[8]=0;

        //OFFENSIVE

        aaron.available_offensive_action_space.remove(0);
        aaron.off_a[2].o[8]=100;
        aaron.off_a[2].o[7]=100;

        luke.available_offensive_action_space.remove(2);




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

        if (game_state==0){
            update_battle();
            draw_battle();
        }else if (game_state==1){
            draw_pre_battle();
        }
    }

    public void draw_pre_battle(){
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, start_button.x,start_button.y, 0);
        Matrix.scaleM(scratch, 0, start_button.width,start_button.height, 1f);
        start_button.Draw(scratch,false);
    }

    public void draw_battle(){
        //Load stage
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, 0, 0f, 0f);

        Matrix.scaleM(scratch, 0, 1.8f,1.4f, 1f);
        //battle_backgrounds.Draw(scratch,false,0);
        castle_background.Draw(scratch,false);

/*        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.scaleM(scratch, 0, 10f/100f,10/100f,1f);
        water_symbol.Draw(scratch,false);*/

        //Load characters
        for (int i = 0; i< active_people.size();i++){
            float ratio = (float) width / height;
            if (active_people.get(i).state.state==1 && active_people.get(i).action.meta_type==0 && active_people.get(i).action.spell_type==0 || 1==1){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                //Matrix.orthoM(scratch,0,-ratio,ratio,-1,1,-1,1);

                Matrix.translateM(scratch, 0, active_people.get(i).center_x, active_people.get(i).center_y-active_people.get(i).height*1.5f/100f, 1f);

                Matrix.scaleM(scratch, 0, 25/100f,25 /100f,1f);
                Matrix.rotateM(scratch, 0, 85, 1f, 0, 0);
                Matrix.rotateM(scratch, 0, active_people.get(i).center_x*10, 0f, 1f, 0);
                //Matrix.rotateM(scratch, 0, active_people.get(i).animation, 0f, 0, 1f);

                water_circle.Draw(scratch,false);
            }

            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
            Matrix.translateM(scratch, 0, active_people.get(i).center_x+active_people.get(i).facing_direction*.05f, active_people.get(i).center_y, 1f);
            Matrix.scaleM(scratch, 0, active_people.get(i).hitbox.x*5.5f/100f,active_people.get(i).hitbox.y*4/100f,1f);
            Matrix.rotateM(scratch, 0, 90-active_people.get(i).facing_direction*90, 0, 1f, 0);
            sprite.Draw(scratch,false,(int)active_people.get(i).animation/5);




            if (show_info){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, active_people.get(i).center_x, active_people.get(i).center_y, 1f);
                Matrix.scaleM(scratch, 0, active_people.get(i).hitbox.x*2/100f,active_people.get(i).hitbox.y*2/100f,.5f);

                blue_box.Draw(scratch,false);

                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, active_people.get(i).center_x+active_people.get(i).hitbox.x*2/100f-active_people.get(i).health/1600f, active_people.get(i).center_y+.15f, 1f);
                Matrix.scaleM(scratch, 0, active_people.get(i).health/1600f,1/100f,.5f);
                hp_box.Draw(scratch,false);

                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, active_people.get(i).center_x+active_people.get(i).hitbox.x*2/100f-active_people.get(i).action.cast_time/active_people.get(i).action.total_cast_time/16f, active_people.get(i).center_y+.12f, 1f);
                Matrix.scaleM(scratch, 0, active_people.get(i).action.cast_time/active_people.get(i).action.total_cast_time/16f,1/100f,.5f);
                cast_bar.Draw(scratch,false);


            }

        }

        for (int i = 0; i< active_projectiles.size();i++){
            if (show_info){
                if (active_projectiles.get(i).active){
                    Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                    Matrix.translateM(scratch, 0, active_projectiles.get(i).location_x, active_projectiles.get(i).location_y, 1);
                    Matrix.scaleM(scratch, 0, active_projectiles.get(i).hitbox.x*2/100f,active_projectiles.get(i).hitbox.y*2/100f,.5f);
                    red_box.Draw(scratch,false);
                }
            }
            if (active_projectiles.get(i).active){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, active_projectiles.get(i).location_x, active_projectiles.get(i).location_y, 1);
                Matrix.scaleM(scratch, 0, ice_shard.width, ice_shard.height,.5f);
                Matrix.rotateM(scratch, 0, -90, 0, 0, 1f);

                ice_shard.Draw(scratch,false);
            }
        }
    }

    public void update_battle(){
        //Update game
        victory=1;
        red_victory_flag=true;
        for (int i =0; i<blue_team.size();i++){
            if (resolve_people(blue_team.get(i),red_team.get(0))){
                red_victory_flag=false;
            }
        }
        blue_victory_flag=true;
        for (int i =0; i<red_team.size();i++){
            if (resolve_people(red_team.get(i),blue_team.get(0))){
                blue_victory_flag=false;
            }
        }

        if (blue_victory_flag==true){
            game_state=1;
        }else if (red_victory_flag==true){
            game_state=1;
        }

        for (int i = active_projectiles.size()-1; i >= 0;i--){
            resolve_projectile(active_projectiles.get(i),i);
        }
    }

    public boolean resolve_people(Person origin, Person target){
        origin.animate();
            if (origin.alive){
                //temp_person = active_people.get(i);
                if(origin.state.state==0){
                    //Choose what to do::

                        calculateMetaObs(origin,target);
                        calculateOffObs(origin,target);
                    origin.choose(meta_obs,off_obs,target);

                }else if (origin.state.state==1 && origin.action.active){
                    //Complete action
                    origin.action.step(projectile_swap, active_projectiles);

                }else if(origin.state.state==2){
                    origin.step();
                }
                return true;
            }
        return false;
    }

    public void resolve_projectile(Projectile inner_projectile, int i){
        if (!inner_projectile.active){
            projectile_swap[i].reset();
            active_projectiles.remove(i);
            return;
        }else{
            inner_projectile.step();
        }
        //Check hit
        for (int j = 0; j< inner_projectile.active_targets.size();j++){
            if (checkCollision(inner_projectile.active_targets.get(j).hitbox,inner_projectile.active_targets.get(j).center_x,inner_projectile.active_targets.get(j).center_y,inner_projectile.hitbox,inner_projectile.location_x,inner_projectile.location_y)){
                inner_projectile.on_hit();
                inner_projectile.active_targets.get(j).hitBy(inner_projectile);
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
        }else if (Math.abs(self.center_x-enemy.center_x)>.25f){
            off_obs[7]=1;
        }else{
            off_obs[8]=1;
        }
    }

}