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
    public boolean busy;
    public Offensive_Physical_Actions action = new Offensive_Physical_Actions();
    public RlData belief = new RlData();
    public float temp_sum=0;
    public int a1;
    public action_space_action a[] = new action_space_action[3];
    public PhysicalState state = new PhysicalState();

    public action_space_action off_a[] = new action_space_action[3];
    public List<Integer> available_action_space = new ArrayList<>();

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

        for (int i=0;i<3;i++){
            a[i]=new action_space_action(i);
            off_a[i]=new action_space_action(i);
            available_action_space.add(i);
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

        for (int i=0;i<3;i++){
            a[i]=new action_space_action(i);
            off_a[i]=new action_space_action(i);
            available_action_space.add(i);

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
        for (int i=0; i< 2; i++){
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
            for (int i =0;i<3;i++){
                if (checkFeasibility(off_a[i].index, target)){
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

    public boolean checkFeasibility(int action_index, Person target){

        if (!available_action_space.contains(action_index)){
            return false;
        }

        if (action_index==0){
            return true;
        }else if (action_index==1){
            return true;
        }else if (action_index==2){
            if (Math.abs(target.center_x-center_x)>.25f){
                return false;
            }
        }

        return true;
    }

    public void motion(float speed, float direction){
        center_x=center_x+speed*direction;
    }

    public void setActionSpace(){
        available_action_space.clear();

        for (int i=0;i<3;i++){
            a[i]=new action_space_action(i);
            off_a[i]=new action_space_action(i);
            available_action_space.add(i);
        }
    }

    public void step(){
        if (state.state==2){
            state.time_in_state--;
            if (state.time_in_state < 1){
                state.state=0;
            }else{
                center_x=center_x+state.force*state.knock_back_direction;
            }
        }
    }




}
