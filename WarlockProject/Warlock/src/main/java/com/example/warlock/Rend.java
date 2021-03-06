package com.example.warlock;


        import javax.microedition.khronos.egl.EGLConfig;
        import javax.microedition.khronos.opengles.GL10;

        import android.content.Context;
        import android.opengl.GLES10;
        import android.opengl.GLES20;
        import android.opengl.GLSurfaceView;
        import android.opengl.Matrix;
        import android.provider.ContactsContract;
        import android.util.Log;

/*        import org.ros.node.ConnectedNode;
        import org.ros.node.DefaultNodeFactory;
        import org.ros.node.Node;
        import org.ros.node.NodeConfiguration;
        import org.ros.node.NodeFactory;*/


        import com.firebase.client.Firebase;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;

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

    private int BATTLE=0, STARTING_SCREEN=1, SECONDARY_SCREEN=2, DUNGEONS=3, DUNGEON_LEVEL=4, EXPLORE=5;

    private int STARTING_STATE=EXPLORE;


    private static final String TAG = "MyGLRenderer";

    //FOR SURFACE PLOT

    private float geoDistance[][]= new float[100][100];
    public boolean SINFO_FLAG=true;
    private FloatBuffer textureBuffer;
    public Context context;
    private boolean show_info=true;
    private Firebase ref;

    private List<Projectile> active_projectiles = new ArrayList<>();
    private List<Particle_Info> active_particles = new ArrayList<>();

    private Projectile projectile_swap[] = new Projectile[50];
    private Particle_Info particle_swap[] = new Particle_Info[50];

    private int active_projectile_count=0;
    private List<Person> active_people = new ArrayList<>();
    private List<Person> blue_team = new ArrayList<>();
    private List<Person> red_team = new ArrayList<>();
    public float screen_x;
    private float MAX_SCREEN_CLIP=2f;

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
    private final float[] mMVPMatrix2 = new float[16];

    private final float[] personSizeMatrix = new float[16];

    private final float[] stockMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];
    private final float[] zeroRotationMatrix = new float[16];
    private final float[] simpleRotationMatrix = new float[4];

    private final float GROUND_LEVEL=.15f;
    private final int OFFENSIVE_SPELL_COUNT=150;
    float[] scratch = new float[16];
    float[] scratch2 = new float[16];
    public GeneralGraphic red_dot, stage_1;
    public GeneralGraphic red_box;
    public GeneralGraphic blue_box;
    public GeneralGraphic hp_box,cast_bar,start_button;
    public GeneralGraphic castle_background;
    public GeneralGraphic ice_shard;
    public GeneralGraphic command_symbol[]= new GeneralGraphic[5];
    public GeneralGraphic blue_apparition;
    public GeneralGraphic buttons;
    public GeneralGraphic text_box;

    public Environment_Data env;
    public UI_Umbrella ui_umbrella;
    public Global_Assets global_assets;

    public Cont_Font font_1;
    public Text_Collection text_collection,pause_text_collection;
    private List<Hard_Text> active_text = new ArrayList<>();

    public SpellCircle water_circle;
    public Projectile_Graphics projectile_sprite[] = new Projectile_Graphics[OFFENSIVE_SPELL_COUNT];
    public Particle_Graphics particle_sprite[] = new Particle_Graphics[OFFENSIVE_SPELL_COUNT];

    public User user_information = new User();

    public Sprite sprite;

    public Person player, luke;
    private long start_time, end_time;
    private int meta_obs[] = new int[10];
    private int off_obs[] = new int[10];
    private int target_obs[] = new int[10];
    public float pointer[] = new float[2];


    //game_states!
    public int game_state=STARTING_STATE;
    public int pause_state=0;




    private boolean blue_victory_flag, red_victory_flag;
    private int victory=0;

    private float width,height;

    private float mAngle;

    public Rend(Context context1, Firebase mref) {
        context = context1;
        ref=mref;
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
        buttons = new GeneralGraphic(context,11,.4f, .1f,-.4f,0);
        font_1 = new Cont_Font(context,0);
        text_box = new GeneralGraphic(context,18);

        global_assets= new Global_Assets(context);

        ui_umbrella= new UI_Umbrella(context);

        text_collection=new Text_Collection(context);
        pause_text_collection = new Text_Collection(context);

        player = new Person("Aaron", -.5f, GROUND_LEVEL, context,global_assets);
        luke = new Person("Luke", .5f, GROUND_LEVEL, context,global_assets);




        for (int i=0;i<5;i++){
            command_symbol[i] = new GeneralGraphic(context,12+i,.1f, .1f,-.7f,-1.35f+i*.7f);
        }

        blue_apparition= new GeneralGraphic(context,10);
        water_circle= new SpellCircle(context,10,.3f, .3f,0,0);

        sprite = new Sprite(context,0);
        castle_background = new GeneralGraphic(context,7);

        float start_time = System.currentTimeMillis();

        for (int i=0;i<OFFENSIVE_SPELL_COUNT;i++){
            projectile_sprite[i] = new Projectile_Graphics(context, i);
            particle_sprite[i] = new Particle_Graphics(context,i);
        }
        for (int i=0;i<2;i++){
            pointer[i]=0;
        }

        env = new Environment_Data(context,1,global_assets);



    }

    public void enterArena(){

        game_state=BATTLE;

        player.reset(-.5f, GROUND_LEVEL);
        luke.reset(1f, GROUND_LEVEL);

        active_people.clear();
        active_people.add(player);
        active_people.add(luke);

        blue_team.clear();
        blue_team.add(player);

        red_team.clear();
        red_team.add(luke);

        victory=0;


        //aaron.setActionSpace();
       // luke.setActionSpace();

        player.a[1].o[2]=0;
        player.a[1].o[0]=0;
        player.a[1].o[1]=0;

        player.a[1].o[6]=0;
        player.a[1].o[7]=0;
        player.a[1].o[8]=0;

        //OFFENSIVE

        player.off_a[2].o[8]=100;
        player.off_a[2].o[7]=100;


        player.setAvailableOffensiveActionSpace();
        luke.setAvailableOffensiveActionSpace();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Firebase sref = ref.child("users").child(user.getUid()).child("spirit").child("0").child("ma").child("0").child("a").child("0").child("c").child("0").child("val");
        sref.setValue(25);


        for (int i =0; i<50;i++){
            projectile_swap[i]= new Projectile(context);
            particle_swap[i]= new Particle_Info(.01f,.1f,.1f);
        }

/*        text_collection.add_to_active_text(10);
        text_collection.add_to_active_text(11);*/
    }

    public void exitArena(){
        active_people.clear();
    }

    public void enter_game_state(int i){
        if (i==1){


            game_state=5;
        }

        if (i==5){
            game_state=5;
        }
    }

    public boolean checkCollision(Box b1, Box b2){
        if (b1.x-b1.width > b2.x+b2.width) return false;
        if (b1.x+b1.width < b2.x-b2.width) return false;
        if (b1.y-b1.height > b2.y + b2.height)  return false;
        if (b1.y+b1.height < b2.y-b2.height) return false; 

        return true;
    }


    @Override
    public void onDrawFrame(GL10 unused) {
        end_time = System.currentTimeMillis();

        if (end_time - start_time < 33)
            try{ Thread.sleep(33-(end_time - start_time)); }catch(InterruptedException e){ }
        start_time = System.currentTimeMillis();



        if (game_state == EXPLORE){
            screen_x=player.center_x;
            if (screen_x>env.all_locations[env.current_location.location_index].width-MAX_SCREEN_CLIP){
                screen_x=env.all_locations[env.current_location.location_index].width-MAX_SCREEN_CLIP;
            }else if (screen_x<-env.all_locations[env.current_location.location_index].width+MAX_SCREEN_CLIP){
                screen_x=-env.all_locations[env.current_location.location_index].width+MAX_SCREEN_CLIP;
            }
        }else if (game_state == BATTLE) {
            screen_x = player.center_x;
            if (screen_x > env.all_locations[env.current_location.location_index].width - MAX_SCREEN_CLIP) {
                screen_x = env.all_locations[env.current_location.location_index].width - MAX_SCREEN_CLIP;
            } else if (screen_x < -env.all_locations[env.current_location.location_index].width + MAX_SCREEN_CLIP) {
                screen_x = -env.all_locations[env.current_location.location_index].width + MAX_SCREEN_CLIP;
            }
        }
        else{
            screen_x=0;
        }


        Matrix.setLookAtM(mViewMatrix, 0, screen_x, 0, -3, screen_x, 0, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0, 0, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix2, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        Matrix.setRotateM(zeroRotationMatrix, 0, 0, 0, 0, 1.0f);
        Matrix.multiplyMM(stockMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);




        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);



        if (game_state==0){
            update_battle();
            draw_battle();
        }else if (game_state==1){
            text_collection.add_to_active_text(0);
        }else if (game_state==5){
            update_explore();
            draw_explore();
        }


        draw_ui();
    }


/*    public void draw_text(Text_Collection txt){
        for (int i = 0;i<txt.active_text.size();i++){

            txt.active_text.get(i).draw(scratch,mMVPMatrix2,zeroRotationMatrix);
            draw_word(txt.active_text.get(i));
        }

        if (game_state==0){
            for (int i =0;i<10;i++){
                draw_word(txt.active_area_text[i]);
            }
        }
    }

    public void draw_word(Hard_Text hard_text){

        Matrix.multiplyMM(scratch, 0, mMVPMatrix2, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, hard_text.x, hard_text.y, 0f);
        Matrix.scaleM(scratch, 0,hard_text.width,hard_text.height, 1f);
        text_box.Draw(scratch,false);


        Matrix.multiplyMM(scratch, 0, mMVPMatrix2, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, hard_text.x+hard_text.width-.1f, hard_text.y, 0f);
        Matrix.scaleM(scratch, 0, .05f*hard_text.text_size,.05f*hard_text.text_size, 1f);

        for (int j=0;j<hard_text.str.length();j++){
            font_1.Draw_Word(scratch,hard_text.str.charAt(j));
            Matrix.translateM(scratch, 0, -1.2f, 0f, 0f);
        }
    }*/

    public void draw_battle(){
        //Load stage
        env.all_locations[env.current_location.location_index].draw_location_battle(scratch,mMVPMatrix,zeroRotationMatrix, screen_x,global_assets);

        //Load characters
        for (int i = 0; i< active_people.size();i++){
            float ratio = (float) width / height;
            if (active_people.get(i).state.state==1 && active_people.get(i).action.meta_type==0 && active_people.get(i).action.spell_type==0){
                draw_spell_circle(active_people.get(i));
            }

            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
            active_people.get(i).DrawSelf(scratch,mMVPMatrix,zeroRotationMatrix,global_assets);

            if (show_info){
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                Matrix.translateM(scratch, 0, active_people.get(i).box.x, active_people.get(i).box.y, 1f);
                Matrix.scaleM(scratch, 0, active_people.get(i).box.width,active_people.get(i).box.height,.5f);

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
            if (active_projectiles.get(i).active){
                projectile_sprite[active_projectiles.get(i).spell_type].draw_projectile_base(active_projectiles.get(i).location_x,active_projectiles.get(i).location_y,1,scratch,mMVPMatrix,zeroRotationMatrix);
            }
            if (show_info){
                if (active_projectiles.get(i).active){
                    Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
                    Matrix.translateM(scratch, 0, active_projectiles.get(i).box.x, active_projectiles.get(i).box.y, 1);
                    Matrix.scaleM(scratch, 0, active_projectiles.get(i).box.width,active_projectiles.get(i).box.height,.5f);
                    red_box.Draw(scratch,false);
                }
            }
        }


        particle_engine();

        for (int i=0;i<active_particles.size();i++){
                particle_sprite[active_particles.get(i).type].draw_particle(active_particles.get(i).x,active_particles.get(i).y,1,scratch,mMVPMatrix,zeroRotationMatrix,active_particles.get(i).alpha);
        }



    }

    public void update_explore(){
            step_people(player);
            env.all_locations[env.current_location.location_index].step_people();


    }

    public void draw_explore(){
        //Load stage
        env.all_locations[env.current_location.location_index].draw_location(scratch,mMVPMatrix,zeroRotationMatrix, player.center_x,screen_x,global_assets);

            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
            player.DrawSelf(scratch,mMVPMatrix,zeroRotationMatrix,global_assets);
        }



    public void draw_ui(){
        ui_umbrella.draw_ui(game_state,pause_state,scratch,mMVPMatrix2,zeroRotationMatrix, player,text_collection,pause_text_collection, global_assets);
    }


    public void draw_spell_circle(Person origin){
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        //Matrix.orthoM(scratch,0,-ratio,ratio,-1,1,-1,1);
        Matrix.translateM(scratch, 0, origin.center_x, origin.center_y-origin.height*1.5f/100f, 1f);
        Matrix.scaleM(scratch, 0, water_circle.size_animation*25/100f,water_circle.size_animation*25/100f,1f);
        Matrix.rotateM(scratch, 0, 70, 1f, 0, 0);
        //Matrix.rotateM(scratch, 0, active_people.get(i).center_x*10, 0f, 1f, 0);
        Matrix.rotateM(scratch, 0, water_circle.spin_animation, 0f, 0, 1f);

        water_circle.Draw(scratch,false);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, origin.center_x, origin.center_y-origin.height*1.5f/100f, 1f);
        Matrix.scaleM(scratch, 0, water_circle.size_animation*5/100f,water_circle.size_animation*5/100f,1f);
        Matrix.translateM(scratch, 0, (float)Math.cos((float)(water_circle.spin_animation+5)*3.14f/180f)*2.5f, (float)Math.sin((float)(water_circle.spin_animation+5)*3.14f/180f)*.8f, 0);
        blue_apparition.Draw(scratch,false);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, origin.center_x, origin.center_y-origin.height*1.5f/100f, 1f);
        Matrix.scaleM(scratch, 0, water_circle.size_animation*5/100f,water_circle.size_animation*5/100f,1f);
        Matrix.translateM(scratch, 0, (float)Math.cos((float)(water_circle.spin_animation+5+90)*3.14f/180f)*2.5f, (float)Math.sin((float)(water_circle.spin_animation+5+90)*3.14f/180f)*.8f, 0);
        blue_apparition.Draw(scratch,false);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, origin.center_x, origin.center_y-origin.height*1.5f/100f, 1f);
        Matrix.scaleM(scratch, 0, water_circle.size_animation*5/100f,water_circle.size_animation*5/100f,1f);
        Matrix.translateM(scratch, 0, (float)Math.cos((float)(water_circle.spin_animation+5+180)*3.14f/180)*2.5f, (float)Math.sin((float)(water_circle.spin_animation+5+180)*3.14f/180f)*.8f, 0);
        blue_apparition.Draw(scratch,false);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, zeroRotationMatrix, 0);
        Matrix.translateM(scratch, 0, origin.center_x, origin.center_y-origin.height*1.5f/100f, 1f);
        Matrix.scaleM(scratch, 0, water_circle.size_animation*5/100f,water_circle.size_animation*5/100f,1f);
        Matrix.translateM(scratch, 0, (float)Math.cos((float)(water_circle.spin_animation+5+270)*3.14f/180f)*2.5f, (float)Math.sin((float)(water_circle.spin_animation+5+270)*3.14f/180f)*.8f, 0);
        blue_apparition.Draw(scratch,false);
    }

    public void update_battle(){
        //Update game
        victory=1;
        red_victory_flag=true;
        blue_victory_flag=true;
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
            enter_game_state(5);
        }else if (red_victory_flag==true){
            enter_game_state(1);
        }

        for (int i = active_projectiles.size()-1; i >= 0;i--){
            resolve_projectile(active_projectiles.get(i),i);
        }

        //TEAM RED MAKE DECISIONS
        for (int i =0; i<red_team.size();i++){
            if (red_team.get(i).state.state==0){
                red_team.get(i).cast(0,0,blue_team.get(0),0);
            }
        }
    }


    public boolean resolve_people(Person origin, Person target){
        origin.animate(water_circle);
            if (origin.alive){
                //temp_person = active_people.get(i);

                if(origin.state.state==0){
                    //Choose what to do::

                        calculateMetaObs(origin,target);
                        calculateOffObs(origin,target);
                        calculateTargetObs(origin,target);

                }else if (origin.state.state==1 && origin.action.active){
                    //Complete action
                    origin.action.step(projectile_swap, active_projectiles);

                }else if(origin.state.state==2 || origin.state.state==3){
                }
                step_people(origin);
                return true;
            }
        return false;
    }

    public void step_people(Person origin){
        origin.step();
        origin.center_x=env.all_locations[env.current_location.location_index].check_oob(origin.center_x);
    }


    public void resolve_projectile(Projectile inner_projectile, int i){
        if (!inner_projectile.active){
            projectile_swap[i].reset();
            active_projectiles.remove(i);
            return;
        }else{
            inner_projectile.step();
            if (inner_projectile.ask_for_particle()){
                particle_swap[active_particles.size()].reset(inner_projectile.location_x,inner_projectile.location_y,inner_projectile.speed,0,inner_projectile.type);
                active_particles.add(particle_swap[active_particles.size()]);
            }
        }
        //Check hit
        for (int j = 0; j< inner_projectile.active_targets.size();j++){
            if (checkCollision(inner_projectile.active_targets.get(j).box,inner_projectile.box)){
                inner_projectile.on_hit();
                inner_projectile.active_targets.get(j).hitBy(inner_projectile);
            }
        }

    }

    public void particle_engine(){
        for (int i=0;i<active_particles.size();i++){
            if (i>=active_particles.size()){
                break;
            }
            active_particles.get(i).step();
            if (active_particles.get(i).alpha<0){
                active_particles.remove(i);
            }
        }
    }

    public void command_spirit(int spirit_type){
        player.command_spirit(meta_obs,target_obs, off_obs, luke,spirit_type);
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
        Matrix.orthoM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

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

    public void calculateTargetObs(Person self, Person enemy){

        for (int i = 0; i < 10; i++){
            target_obs[i]=0;
        }

    }


        public void calculateMetaObs(Person self, Person enemy){
        //FOR META ~

        for (int i = 0; i < 10; i++){
            meta_obs[i]=0;
            target_obs[i]=0;
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

    public void reward_event(int reward_type){
        player.reward_function(reward_type);
    }

    public void enter_location(int loc){
        text_collection.add_to_active_text(100+loc);
    }

    public void leave_location(int loc){
        text_collection.remove_to_active_text(100+loc);
    }

    public void request_pause_menu(int k){
        for (int i = 0; i < ui_umbrella.pause_graphics[pause_state].text_index_list.size();i++){
            pause_text_collection.remove_to_active_text(ui_umbrella.pause_graphics[pause_state].text_index_list.get(i));
        }
            pause_state=k;
        if (k==1){
            ui_umbrella.items.recalculate_attributes(player.spirit);

            pause_text_collection.text[21].str=Integer.toString(player.spirit[0].attribute);
            pause_text_collection.text[23].str=Integer.toString(player.spirit[1].attribute);
            pause_text_collection.text[25].str=Integer.toString(player.spirit[2].attribute);
            pause_text_collection.text[27].str=Integer.toString(player.spirit[3].attribute);
        }




        for (int i = 0; i < ui_umbrella.pause_graphics[pause_state].text_index_list.size();i++){
            pause_text_collection.add_to_active_text(ui_umbrella.pause_graphics[pause_state].text_index_list.get(i));
        }
    }

}