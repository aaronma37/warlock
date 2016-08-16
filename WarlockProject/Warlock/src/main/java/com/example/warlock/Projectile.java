package com.example.warlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 8/9/16.
 */
public class Projectile {
    public float cast_location_x;
    public float cast_location_y;
    public float speed;
    public float damage;
    public int type;
    public int debuff;
    public boolean active;
    public float persistance;
    public Hitbox hitbox=new Hitbox(2,2);
    public float location_x;
    public float location_y;
    public float direction;
    public List<Person> active_targets = new ArrayList<>();
    public int active_time;
    public int distance_from_target=2;
    public int time_active=0;

    public Projectile(){
        active=false;
    }

    public void reset(){active=false; time_active=0;}

    public Projectile(float init_cast_location_x,float init_cast_location_y, float init_speed, float init_damage, int init_type, int init_debuff, float init_persistance, Hitbox init_hitbox, float init_direction, int init_active_time){
        cast_location_x=init_cast_location_x;
        cast_location_y=init_cast_location_y;
        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=init_speed;
        damage=init_damage;
        type=init_type;
        debuff=init_debuff;
        active=false;
        persistance=init_persistance;
        hitbox=init_hitbox;
        direction=init_direction;
        active_targets.clear();
        active_time=init_active_time;
    }

    public void step(){
        location_x=location_x+speed*(float)Math.cos(direction);
        location_y=location_y+speed*(float)Math.sin(direction);

        if (Math.abs(location_x-active_targets.get(0).center_x)>.5f){
            distance_from_target=2;
        }else if (Math.abs(location_x-active_targets.get(0).center_x)>.35f){
            distance_from_target=1;
        }else{
            distance_from_target=0;
        }
        time_active=time_active+1;
        if (time_active>active_time){
            active=false;
        }
    }

    public void on_hit(){
        active=false;
    }

    public void addTarget(Person target){
        active_targets.add(target);
    }

    public void setSpell(Person target, Person origin, int spell_type){
        active=true;

        if (spell_type==0){
            cast_location_x=origin.center_x;
            cast_location_y=origin.center_y;
            location_x=cast_location_x;
            location_y=cast_location_y;
            speed=.025f;
            damage=5;
            type=0;
            debuff=0;
            persistance=0;
            hitbox.set(2,2);
            direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
            //direction = (float)Math.atan2(target.center_y, target.center_x) - (float)Math.atan2(origin.center_y, origin.center_x);

            active_targets.clear();
            active_targets.add(target);
            active_time=150;
        }

        if (spell_type==2){
            cast_location_x=origin.center_x+origin.facing_direction*.2f;
            cast_location_y=origin.center_y;
            location_x=cast_location_x;
            location_y=cast_location_y;
            speed=0f;
            damage=7;
            type=0;
            debuff=0;
            persistance=0;
            hitbox.set(3,2);
            direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
            //direction = (float)Math.atan2(target.center_y, target.center_x) - (float)Math.atan2(origin.center_y, origin.center_x);

            active_targets.clear();
            active_targets.add(target);
            active_time=10;
        }
    }


}
