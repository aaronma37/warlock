package com.example.warlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 8/9/16.
 */
public class Person {
    public int health;
    public boolean alive =true;
    public String name;
    public int height;
    public int width;
    public Hitbox hitbox;
    public float center_x;
    public float center_y;
    public int facing_direction=1;
    public int animation=0;
    public boolean busy;
    private final int ACTION_SPACE_SIZE=10;
    private final int META_SIZE=3;
    public Offensive_Physical_Actions action = new Offensive_Physical_Actions();
    public RlData belief = new RlData();
    public float temp_sum=0;
    public int a1;
    public action_space_action a[] = new action_space_action[META_SIZE];
    public PhysicalState state = new PhysicalState();

    public action_space_action off_a[] = new action_space_action[ACTION_SPACE_SIZE];
    public action_space_action def_a[] = new action_space_action[ACTION_SPACE_SIZE];

    public List<Integer> available_offensive_action_space = new ArrayList<>();
    public List<Integer> available_defensive_action_space = new ArrayList<>();

    public float max_sum=0;
    public Projectile projectile_fireball = new Projectile(0f, 0f, .001f,5,0,0,0f, new Hitbox(2,2), 0,100);
    public Offensive_Physical_Actions fireball = new Offensive_Physical_Actions(100f, 0, projectile_fireball,null);


    public Person() {
        health=100;
        name="default";
        hitbox=new Hitbox(3,5);
        height=10;
        width=6;
        busy=false;
        state.setState(0,0,0,0);

        center_x=0;
        center_y=0;

        for (int i=0;i<META_SIZE;i++){
            a[i]=new action_space_action(i);
        }
        for (int i=0;i<ACTION_SPACE_SIZE;i++){
            off_a[i]=new action_space_action(i);
            def_a[i]=new action_space_action(i);
            available_offensive_action_space.add(i);
            available_defensive_action_space.add(i);
        }


    }

    public void reset(float start_x, float start_y){
        health=100;
        height=10;
        alive=true;
        width=6;
        hitbox = new Hitbox(3,5);
        center_x=start_x;
        center_y=start_y;
        busy=false;
        state.setState(0,0,0,0);

        for (int i=0;i<ACTION_SPACE_SIZE;i++){
            available_offensive_action_space.add(i);
            available_defensive_action_space.add(i);

        }
    }


    public Person(String given_name, float start_x, float start_y) {
        health=100;
        name=given_name;
        height=10;
        width=6;
        hitbox = new Hitbox(3,5);
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
            available_offensive_action_space.add(i);
            available_defensive_action_space.add(i);

        }
    }

/*    public void cast(Offensive_Physical_Actions desired_action){
        busy=true;
        //action= new Offensive_Physical_Actions();
        action=desired_action;
        //action.set(0,ini);
    }*/

    public void cast(int meta_index, int action_index, Person target){
        busy=true;
        state.setState(1,meta_index,action_index,0);

        fireball.reset();
        action.set(meta_index,action_index,projectile_fireball, target, this);

        //action= new Offensive_Physical_Actions();
/*        if (action_index==0){
            action.set(meta_index,0,projectile_fireball, target);
        }
        if (action_index==1){
            action.set(meta_index,0, projectile_fireball, target);
        }*/
        //action=desired_action;
        //action.set(0,ini);
    }

    public void hitBy(Projectile projectile_index){
        if (projectile_index.damage-action.block > 0){
            health = health - (int)(projectile_index.damage - action.block);
        }
        if (state.state==1){
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

    public void choose(int o[], int off_o[], Person target){
        //choose meta

        int meta_decision=0;
        int action_decision=0;

        max_sum=0;
        for (int i=0; i< META_SIZE; i++){
            temp_sum=0;
            for (int j=0;j<10;j++){
                temp_sum+=a[i].o[j]*o[j];
            }
            if (temp_sum>max_sum){
                max_sum=temp_sum;
                meta_decision=i;
            }
        }

        if (meta_decision == 0){

            //Calculate feasible
            max_sum=0;
            for (int i =0;i<ACTION_SPACE_SIZE;i++){
                //if (checkFeasibility(off_a[i].index, target, available_offensive_action_space)){
                if (checkFeasibility(0,i,target,available_offensive_action_space)){
                    temp_sum=0;
                    for (int j=0;j<10;j++){
                        temp_sum+=off_a[i].o[j]*off_o[j];
                    }
                    System.out.println(this.name+"casts attack: " + action_decision + "score: " + temp_sum);

                    if (temp_sum>max_sum){
                        max_sum=temp_sum;
                        action_decision=i;
                    }
                }
            }
            cast(meta_decision, action_decision, target);
        }else if (meta_decision==1){
            cast(meta_decision, 0, target);
            System.out.println("casts block ");

        }
        //System.out.println("Choose to: " + a1);
        //choose action
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
        OOB();
    }

    public void setActionSpace(){
        available_offensive_action_space.clear();


        for (int i=0;i<META_SIZE;i++){
            a[i]=new action_space_action(i);
        }
        for (int i=0;i<ACTION_SPACE_SIZE;i++){
            off_a[i]=new action_space_action(i);
            def_a[i]=new action_space_action(i);
            available_offensive_action_space.add(i);
            available_defensive_action_space.add(i);
        }
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

        if (state.state==2){
            state.time_in_state--;
            if (state.time_in_state < 1){
                state.state=0;
            }else{
                center_x=center_x+state.force*state.knock_back_direction;
                OOB();
            }
        }
    }

    public void OOB(){
        if (center_x<-1.3f){
            center_x=-1.3f;
        }else if (center_x>1.3f){
            center_x=1.3f;
        }
    }

    public void animate(SpellCircle circle){
        if (state.state==0){
        }else if (state.state==1){
            if (action.meta_type==0){
                if (action.spell_type==0){
                    circle.animate(action.cast_time/action.total_cast_time);
                }
                if (action.spell_type==1){
                    this.animation++;
                    if (this.animation>20){
                        this.animation=0;
                    }
                }
            }
        }
    }





}
