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
    public Hitbox hitbox;
    public float location_x;
    public float location_y;
    public float direction;
    public List<Person> active_targets = new ArrayList<>();
    public Projectile(){

    }

    public Projectile(float init_cast_location_x,float init_cast_location_y, float init_speed, float init_damage, int init_type, int init_debuff, float init_persistance, Hitbox init_hitbox, float init_direction){
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
    }

    public void step(){
        location_x=location_x+speed*(float)Math.cos(direction);
        location_y=location_x+speed*(float)Math.cos(direction);
    }

    public void on_hit(){
        active=false;
    }

    public void addTarget(Person target){
        active_targets.add(target);
    }



}
