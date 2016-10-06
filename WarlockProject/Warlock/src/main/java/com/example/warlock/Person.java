package com.example.warlock;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 8/9/16.
 */
public class Person {
    private final int OFFENSIVE_OBSERVATION_SPACE_SIZE=10;
    private final int ACTION_SPACE_SIZE=15;
    private final int SUPPORT_SPACE_SIZE=15;
    private final int DEFENSE_SPACE_SIZE=15;
    private static int NUMBER_OF_SPIRITS=15;
    private static int NUMBER_OF_EQUIPMENT_SLOTS=3;
    private static int NUMBER_OF_ATTRIBUTES=5;



    private final int META_SIZE=3;

    public float health;
    public boolean alive =true;
    public String name;
    public int height;
    public int width;
    public Hitbox hitbox;
    public Box box;
    public float center_x;
    public float center_y;
    public int facing_direction=1;
    public int animation=0;
    public int attribute[] = new int[NUMBER_OF_ATTRIBUTES];
    public int last_meta=0;
    //public Person last_target=new Person();
    public int last_spell=0;
    public float action_choose_index[]= new float[ACTION_SPACE_SIZE];
    public Wardrobe wardrobe;
    public Person_Graphics person_graphics;
    public Context myContext;

    public int sprint_timer=0;
    public int SPRINT_TIME=10;

    public Spirit spirit[] = new Spirit[NUMBER_OF_SPIRITS];

    public boolean busy;
    public Offensive_Physical_Actions action = new Offensive_Physical_Actions();
    public RlData belief = new RlData();
    public float temp_sum=0;
    public int a1;
    public action_space_action a[] = new action_space_action[META_SIZE];
    public PhysicalState state = new PhysicalState();

    public action_space_action off_a[] = new action_space_action[ACTION_SPACE_SIZE];
    public action_space_action def_a[] = new action_space_action[ACTION_SPACE_SIZE];
    public int toCast[]= new int[4];
    public int last_cast[] = new int[4];
    public int last_spirit=0;

    public List<Integer> available_offensive_action_space = new ArrayList<>();
    public List<Integer> available_support_action_space = new ArrayList<>();
    public List<Integer> available_defensive_action_space = new ArrayList<>();


    public float max_sum=0;
   // public Offensive_Physical_Actions fireball = new Offensive_Physical_Actions(100f, 0,null, myContext);



    public void recalculate_attributes(Wardrobe w){

        //HARDCODE FOR TESTING
        spirit[0].attribute=4;
        spirit[1].attribute=2;
        spirit[2].attribute=2;
        spirit[3].attribute=2;
        spirit[4].attribute=2;

        for (int i =0;i<NUMBER_OF_ATTRIBUTES;i++){
/*            for (int j=0;j<NUMBER_OF_EQUIPMENT_SLOTS;j++){
                spirit[i].attribute+=w.currently_equipped[j].attributes[i];
            }*/
            spirit[i].setAvailableOffensiveActionSpace();

        }

    }

    public Person(String given_name, float start_x, float start_y, Context context) {
        myContext=context;
        health=100;
        name=given_name;
        height=24;
        width=8;
        hitbox = new Hitbox(4,12);
        box = new Box(.1f,.2f,0,0,0);
        center_x=start_x;
        center_y=start_y;
        busy=false;
        state.setState(0,0,0,0);

        for (int i=0;i<META_SIZE;i++){
            a[i]=new action_space_action(i);
        }

        for (int i=0;i<ACTION_SPACE_SIZE;i++){
            off_a[i]=new action_space_action(i);
            def_a[i]=new action_space_action(i);
        }
        for (int i=0;i<NUMBER_OF_ATTRIBUTES;i++){
            attribute[i]=0;
        }
        for (int i=0;i<NUMBER_OF_SPIRITS;i++){
            spirit[i]=new Spirit(i);
        }
        for (int i=0;i<4;i++){
            toCast[i]=0;
        }

        person_graphics = new Person_Graphics(myContext, 2,0,0,0,0);
        setAvailableOffensiveActionSpace();
        wardrobe= new Wardrobe();
/*        for (int i=0;i<NUMBER_OF_SPIRITS;i++){
            spirit[i].setAvailableOffensiveActionSpace();
        }*/
    }


    public void reset(float start_x, float start_y){
        health=100;
        height=24;
        alive=true;
        width=8;
        hitbox = new Hitbox(4,12);
        box = new Box(.1f,.2f,0,0,0);
        center_x=start_x;
        center_y=start_y;
        busy=false;
        state.setState(0,0,0,0);

        for (int i=0;i<NUMBER_OF_ATTRIBUTES;i++){
            attribute[i]=0;
        }

        for (int i=0;i<4;i++){
            toCast[i]=0;
        }

        setAvailableOffensiveActionSpace();

        for (int i=0;i<NUMBER_OF_SPIRITS;i++){
            spirit[i].setAvailableOffensiveActionSpace();
        }
        recalculate_attributes(wardrobe);

    }


    public void cast(int meta_index, int action_index, Person target, int spirit_type){
        busy=true;
        state.setState(1,meta_index,action_index,0);
        action.set(meta_index,action_index, spirit_type, target, this);
    }

    public void hitBy(Projectile projectile_index){
        if (projectile_index.damage-action.block > 0){
            health = health - (int)(projectile_index.damage - action.block);
        }
        if (state.state==0 || state.state==3 || state.state == 4){
            state.setState(2,projectile_index.knock_back_time,projectile_index.knock_back_force, projectile_index.knockback_direction);
        }else if (state.state==1){
            if (state.interrupt_level <= projectile_index.interrupt_level){
                action.cast_time=0;
                state.setState(2,projectile_index.knock_back_time,projectile_index.knock_back_force, projectile_index.knockback_direction);
            }
        }




        checkVitals();
    }

    public void checkVitals(){
        if (health <= 0){
            alive = false;
            health=0;
        }
    }

    public void command_spirit(int meta_o[], int target_o[],int off_o[], Person target, int spirit_type){
        toCast=spirit[spirit_type].choose(meta_o,target_o,off_o,target,this);
        if (toCast[2]==-1){
            return;
        }
        cast(toCast[0], toCast[2], target, toCast[3]);
        last_cast=toCast;
        last_spirit=spirit_type;

    }



    public boolean checkFeasibility(int meta_type, int spell_type, Person target, List<Integer> action_list){

        if (!action_list.contains(spell_type)){
            return false;
        }

        if (!action.returnFeasibility(this,target,meta_type,spell_type)){
            return false;
        }

        return true;
    }

    public void motion(float speed, float direction){
        center_x=center_x+speed*direction;
        person_graphics.resolve_movement(direction*speed,0, facing_direction, state.state);
        OOB();
    }

    public void reset_box(){
        box.x = center_x;
        box.y = center_y;
    }

    public void step(){
        //reset cooldowns
        for (int i=0;i<ACTION_SPACE_SIZE;i++){
            if (off_a[i].cool_down_timer>0){
                off_a[i].cool_down_timer--;
            }else if (def_a[i].cool_down_timer>0){
                def_a[i].cool_down_timer--;
            }
        }


        if (state.state==0 || state.state==1){
            person_graphics.resolve_movement(0,0,facing_direction,state.state);
        } else if (state.state==2){
            state.time_in_state--;
            if (state.time_in_state < 1){
                state.state=0;
            }else{
                motion(state.force,state.knock_back_direction);
                //center_x=center_x+state.force*state.knock_back_direction;
                OOB();
            }
        }else if (state.state==3 || state.state==4){
            sprint_timer--;
            if (Math.abs(center_x-state.destination_x)>.01f){
                facing_direction=return_direction(center_x,state.destination_x);
                motion(state.ms,return_direction(center_x,state.destination_x));
            }else{
                state.setState(0,0,0,0);
            }
        }
        reset_box();

    }

    public int return_direction(float x_1, float x_2){
        if (x_1>x_2){
            return -1;
        }else{
            return 1;
        }
    }

    public void OOB(){
/*        if (center_x<-1.3f){
            center_x=-1.3f;
        }else if (center_x>1.3f){
            center_x=1.3f;
        }*/
    }

    public void animate(SpellCircle circle){
        if (state.state==0){
        }else if (state.state==1){
            if (action.meta_type==0){
                if (action.spell_type==0){
                    circle.animate(action.cast_time/action.total_cast_time);
                }
                if (action.spell_type==1){

                }
            }
        }else if (state.state==3){
            this.animation++;
            if (this.animation>20){
                this.animation=0;
            }
        }
    }

    public void setAvailableOffensiveActionSpace(){
        available_offensive_action_space.clear();
        for (int i=0;i<ACTION_SPACE_SIZE;i++){
            if(checkIndex(i,0,attribute)){
                available_offensive_action_space.add(i);
            }
        }
    }

    public void reward_spirit(int reward_type){
        spirit[last_spirit].reward_spirit(reward_type,last_cast);
    }

    public void reward_function(int reward_type){
        System.out.println("reward sent: " + reward_type);
        if (reward_type>0){
            for(int i=0;i<OFFENSIVE_OBSERVATION_SPACE_SIZE;i++){
                if (off_a[last_spell].o[i]<100){
                    off_a[last_spell].o[i]+=1;
                }

                if (off_a[last_spell].c[i]<2){
                    off_a[last_spell].c[i]=off_a[last_spell].c[i]/2;
                }else{
                    off_a[last_spell].c[i]-=1;
                }
            }
        }else{
            for(int i=0;i<OFFENSIVE_OBSERVATION_SPACE_SIZE;i++){
                if (off_a[last_spell].c[i]<100){
                    off_a[last_spell].c[i]+=1;
                }

                if (off_a[last_spell].o[i]<2){
                    off_a[last_spell].o[i]=off_a[last_spell].c[i]/2;
                }else{
                    off_a[last_spell].o[i]-=1;
                }
            }
        }
    }

    private void reset_action_choose_index(){
        for (int i=0;i<ACTION_SPACE_SIZE;i++){
            action_choose_index[i]=0;
        }
    }

    public void move_request(float dest_x){
        if (sprint_timer<1){
            state.setState(3,dest_x,0,0);
        }else{
            state.setState(4,dest_x,0,0);
        }
        sprint_timer=SPRINT_TIME;
    }


    public boolean checkIndex(int spell_type, int meta_type, int att[]){

            if (spell_type==1){
                if (att[0]>=spell_type){return true;}
            }else if(spell_type==2){
                if (att[0]>=spell_type){return true;}

            }else if(spell_type==3){
                if (att[0]>=spell_type){return true;}

            }else if(spell_type==4){
                if (att[0]>=spell_type){return true;}

            }else if(spell_type==5){
                if (att[0]>=spell_type){return true;}

            }else if(spell_type==6){
                if (att[0]>=spell_type){return true;}

            }else if(spell_type==7){
                if (att[0]>=spell_type){return true;}

            }else if(spell_type==8){
                if (att[0]>=spell_type){return true;}

            }else if(spell_type==9){
                if (att[0]>=spell_type){return true;}

            }else if(spell_type==10){
                if (att[0]>=spell_type){return true;}

            }else if(spell_type==11){
                if (att[1]>=spell_type%10){return true;}

            }else if(spell_type==12){
                if (att[1]>=spell_type%10){return true;}

            }else if(spell_type==13){
                if (att[1]>=spell_type%10){return true;}

            }else if(spell_type==14){
                if (att[1]>=spell_type%10){return true;}

            }else if(spell_type==15){
                if (att[1]>=spell_type%10){return true;}

            }else if(spell_type==16){
                if (att[1]>=spell_type%10){return true;}

            }else if(spell_type==17){
                if (att[1]>=spell_type%10){return true;}

            }else if(spell_type==18){
                if (att[1]>=spell_type%10){return true;}

            }else if(spell_type==19){
                if (att[1]>=spell_type%10){return true;}

            }else if(spell_type==20){
                if (att[1]>=10){return true;}

            }else if(spell_type==21){
                if (att[2]>=spell_type%10){return true;}

            }else if(spell_type==22){
                if (att[2]>=spell_type%10){return true;}

            }else if(spell_type==23){
                if (att[2]>=spell_type%10){return true;}

            }else if(spell_type==24){
                if (att[2]>=spell_type%10){return true;}

            }else if(spell_type==25){
                if (att[2]>=spell_type%10){return true;}

            }else if(spell_type==26){
                if (att[2]>=spell_type%10){return true;}

            }else if(spell_type==27){
                if (att[2]>=spell_type%10){return true;}

            }else if(spell_type==28){
                if (att[2]>=spell_type%10){return true;}

            }else if(spell_type==29){
                if (att[2]>=spell_type%10){return true;}

            }else if(spell_type==30){
                if (att[2]>=10){return true;}

            }else if(spell_type==31){
                if (att[3]>=spell_type%10){return true;}

            }else if(spell_type==32){
                if (att[3]>=spell_type%10){return true;}

            }else if(spell_type==33){
                if (att[3]>=spell_type%10){return true;}

            }else if(spell_type==34){
                if (att[3]>=spell_type%10){return true;}

            }else if(spell_type==35){
                if (att[3]>=spell_type%10){return true;}

            }else if(spell_type==36){
                if (att[3]>=spell_type%10){return true;}

            }else if(spell_type==37){
                if (att[3]>=spell_type%10){return true;}

            }else if(spell_type==38){
                if (att[3]>=spell_type%10){return true;}

            }else if(spell_type==39){
                if (att[3]>=spell_type%10){return true;}

            }else if(spell_type==40){
                if (att[3]>=10){return true;}

            }else if(spell_type==41){
                if (att[4]>=spell_type%10){return true;}

            }else if(spell_type==42){
                if (att[4]>=spell_type%10){return true;}

            }else if(spell_type==43){
                if (att[4]>=spell_type%10){return true;}

            }else if(spell_type==44){
                if (att[4]>=spell_type%10){return true;}

            }else if(spell_type==45){
                if (att[4]>=spell_type%10){return true;}

            }else if(spell_type==46){
                if (att[4]>=spell_type%10){return true;}

            }else if(spell_type==47){
                if (att[4]>=spell_type%10){return true;}

            }else if(spell_type==48){
                if (att[4]>=spell_type%10){return true;}

            }else if(spell_type==49){
                if (att[4]>=spell_type%10){return true;}

            }else if(spell_type==50){
                if (att[4]>=10){return true;}

            }else if(spell_type==51){
                if (att[0]>=1 && att[1]>=1){return true;}

            }else if(spell_type==52){
                if (att[0]>=1 && att[1]>=2){return true;}

            }else if(spell_type==53){
                if (att[0]>=1 && att[1]>=3){return true;}

            }else if(spell_type==54){
                if (att[0]>=1 && att[1]>=4){return true;}

            }else if(spell_type==55){
                if (att[0]>=2 && att[1]>=1){return true;}

            }else if(spell_type==56){
                if (att[0]>=2 && att[1]>=2){return true;}

            }else if(spell_type==57){
                if (att[0]>=2 && att[1]>=3){return true;}

            }else if(spell_type==58){
                if (att[0]>=3 && att[1]>=1){return true;}

            }else if(spell_type==59){
                if (att[0]>=3 && att[1]>=2){return true;}

            }else if(spell_type==60){
                if (att[0]>=4 && att[1]>=1){return true;}

            }else if(spell_type==61){
                if (att[0]>=1 && att[2]>=1){return true;}

            }else if(spell_type==62){
                if (att[0]>=1 && att[2]>=2){return true;}

            }else if(spell_type==63){
                if (att[0]>=1 && att[2]>=3){return true;}

            }else if(spell_type==64){
                if (att[0]>=1 && att[2]>=4){return true;}

            }else if(spell_type==65){
                if (att[0]>=2 && att[2]>=1){return true;}

            }else if(spell_type==66){
                if (att[0]>=2 && att[2]>=2){return true;}

            }else if(spell_type==67){
                if (att[0]>=2 && att[2]>=3){return true;}

            }else if(spell_type==68){
                if (att[0]>=3 && att[2]>=1){return true;}

            }else if(spell_type==69){
                if (att[0]>=3 && att[2]>=2){return true;}

            }else if(spell_type==70){
                if (att[0]>=4 && att[2]>=1){return true;}

            }else if(spell_type==71){
                if (att[0]>=1 && att[3]>=1){return true;}

            }else if(spell_type==72){
                if (att[0]>=1 && att[3]>=2){return true;}

            }else if(spell_type==73){
                if (att[0]>=1 && att[3]>=3){return true;}

            }else if(spell_type==74){
                if (att[0]>=1 && att[3]>=4){return true;}

            }else if(spell_type==75){
                if (att[0]>=2 && att[3]>=1){return true;}

            }else if(spell_type==76){
                if (att[0]>=2 && att[3]>=2){return true;}

            }else if(spell_type==77){
                if (att[0]>=2 && att[3]>=3){return true;}

            }else if(spell_type==78){
                if (att[0]>=3 && att[3]>=1){return true;}

            }else if(spell_type==79){
                if (att[0]>=3 && att[3]>=2){return true;}

            }else if(spell_type==80){
                if (att[0]>=4 && att[3]>=1){return true;}

            }else if(spell_type==81){
                if (att[0]>=1 && att[4]>=1){return true;}

            }else if(spell_type==82){
                if (att[0]>=1 && att[4]>=2){return true;}

            }else if(spell_type==83){
                if (att[0]>=1 && att[4]>=3){return true;}

            }else if(spell_type==84){
                if (att[0]>=1 && att[4]>=4){return true;}

            }else if(spell_type==85){
                if (att[0]>=2 && att[4]>=1){return true;}

            }else if(spell_type==86){
                if (att[0]>=2 && att[4]>=2){return true;}

            }else if(spell_type==87){
                if (att[0]>=2 && att[4]>=3){return true;}

            }else if(spell_type==88){
                if (att[0]>=3 && att[4]>=1){return true;}

            }else if(spell_type==89){
                if (att[0]>=3 && att[4]>=2){return true;}

            }else if(spell_type==90){
                if (att[0]>=4 && att[4]>=1){return true;}

            }else if(spell_type==91){
                if (att[1]>=1 && att[2]>=1){return true;}

            }else if(spell_type==92){
                if (att[1]>=1 && att[2]>=2){return true;}

            }else if(spell_type==93){
                if (att[1]>=1 && att[2]>=3){return true;}

            }else if(spell_type==94){
                if (att[1]>=1 && att[2]>=4){return true;}

            }else if(spell_type==95){
                if (att[1]>=2 && att[2]>=1){return true;}

            }else if(spell_type==96){
                if (att[1]>=2 && att[2]>=2){return true;}

            }else if(spell_type==97){
                if (att[1]>=2 && att[2]>=3){return true;}

            }else if(spell_type==98){
                if (att[1]>=3 && att[2]>=1){return true;}

            }else if(spell_type==99){
                if (att[1]>=3 && att[2]>=2){return true;}

            }else if(spell_type==100){
                if (att[1]>=4 && att[2]>=1){return true;}

            }else if(spell_type==101){
                if (att[1]>=1 && att[3]>=1){return true;}

            }else if(spell_type==102){
                if (att[1]>=1 && att[3]>=2){return true;}

            }else if(spell_type==103){
                if (att[1]>=1 && att[3]>=3){return true;}

            }else if(spell_type==104){
                if (att[1]>=1 && att[3]>=4){return true;}

            }else if(spell_type==105){
                if (att[1]>=2 && att[3]>=1){return true;}

            }else if(spell_type==106){
                if (att[1]>=2 && att[3]>=2){return true;}

            }else if(spell_type==107){
                if (att[1]>=2 && att[3]>=3){return true;}

            }else if(spell_type==108){
                if (att[1]>=3 && att[3]>=1){return true;}

            }else if(spell_type==109){
                if (att[1]>=3 && att[3]>=2){return true;}

            }else if(spell_type==110){
                if (att[1]>=4 && att[3]>=1){return true;}

            }else if(spell_type==111){
                if (att[1]>=1 && att[4]>=1){return true;}

            }else if(spell_type==112){
                if (att[1]>=1 && att[4]>=2){return true;}

            }else if(spell_type==113){
                if (att[1]>=1 && att[4]>=3){return true;}

            }else if(spell_type==114){
                if (att[1]>=1 && att[4]>=4){return true;}

            }else if(spell_type==115){
                if (att[1]>=2 && att[4]>=1){return true;}

            }else if(spell_type==116){
                if (att[1]>=2 && att[4]>=2){return true;}

            }else if(spell_type==117){
                if (att[1]>=2 && att[4]>=3){return true;}

            }else if(spell_type==118){
                if (att[1]>=3 && att[4]>=1){return true;}

            }else if(spell_type==119){
                if (att[1]>=3 && att[4]>=2){return true;}

            }else if(spell_type==120){
                if (att[1]>=4 && att[4]>=1){return true;}

            }else if(spell_type==121){
                if (att[2]>=1 && att[3]>=1){return true;}

            }else if(spell_type==122){
                if (att[2]>=1 && att[3]>=2){return true;}

            }else if(spell_type==123){
                if (att[2]>=1 && att[3]>=3){return true;}

            }else if(spell_type==124){
                if (att[2]>=1 && att[3]>=4){return true;}

            }else if(spell_type==125){
                if (att[2]>=2 && att[3]>=1){return true;}

            }else if(spell_type==126){
                if (att[2]>=2 && att[3]>=2){return true;}

            }else if(spell_type==127){
                if (att[2]>=2 && att[3]>=3){return true;}

            }else if(spell_type==128){
                if (att[2]>=3 && att[3]>=1){return true;}

            }else if(spell_type==129){
                if (att[2]>=3 && att[3]>=2){return true;}

            }else if(spell_type==130){
                if (att[2]>=4 && att[3]>=1){return true;}

            }else if(spell_type==131){
                if (att[2]>=1 && att[4]>=1){return true;}

            }else if(spell_type==132){
                if (att[2]>=1 && att[4]>=2){return true;}

            }else if(spell_type==133){
                if (att[2]>=1 && att[4]>=3){return true;}

            }else if(spell_type==134){
                if (att[2]>=1 && att[4]>=4){return true;}

            }else if(spell_type==135){
                if (att[2]>=2 && att[4]>=1){return true;}

            }else if(spell_type==136){
                if (att[2]>=2 && att[4]>=2){return true;}

            }else if(spell_type==137){
                if (att[2]>=2 && att[4]>=3){return true;}

            }else if(spell_type==138){
                if (att[2]>=3 && att[4]>=1){return true;}

            }else if(spell_type==139){
                if (att[2]>=3 && att[4]>=2){return true;}

            }else if(spell_type==140){
                if (att[2]>=4 && att[4]>=1){return true;}

            }else if(spell_type==141){
                if (att[3]>=1 && att[4]>=1){return true;}

            }else if(spell_type==142){
                if (att[3]>=1 && att[4]>=2){return true;}

            }else if(spell_type==143){
                if (att[3]>=1 && att[4]>=3){return true;}

            }else if(spell_type==144){
                if (att[3]>=1 && att[4]>=4){return true;}

            }else if(spell_type==145){
                if (att[3]>=2 && att[4]>=1){return true;}

            }else if(spell_type==146){
                if (att[3]>=2 && att[4]>=2){return true;}

            }else if(spell_type==147){
                if (att[3]>=2 && att[4]>=3){return true;}

            }else if(spell_type==148){
                if (att[3]>=3 && att[4]>=1){return true;}

            }else if(spell_type==149){
                if (att[3]>=3 && att[4]>=2){return true;}

            }else if(spell_type==150){
                if (att[3]>=4 && att[4]>=1){return true;}

            }
            return false;
        }


}
