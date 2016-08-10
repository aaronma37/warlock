package com.example.warlock;

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
    public float hitbox_x;
    public float hitbox_y;
    public float location_x;
    public float location_y;
    public float direction;
    public Projectile(){

    }

    public Projectile(float init_cast_location_x,float init_cast_location_y, float init_speed, float init_damage, int init_type, int init_debuff, float init_persistance, float init_hitbox_x, float init_hitbox_y, float init_direction){
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
        hitbox_x=init_hitbox_x;
        hitbox_y=init_hitbox_y;
        direction=init_direction;
    }

    public void step(){
        location_x=location_x+speed*(float)Math.cos(direction);
        location_y=location_x+speed*(float)Math.cos(direction);
    }

    public void on_hit(){
        active=false;
    }



}
