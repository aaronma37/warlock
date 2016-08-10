package com.example.warlock;

/**
 * Created by aaron on 8/9/16.
 */
public class Offensive_Physical_Actions {

    // cast time, animation, projectile information, damage, debuffs
    public float cast_time;
    public float total_cast_time;
    public int animation;
    public Projectile projectile;
    public boolean make_active=false;
    public boolean  active=false;

    public Offensive_Physical_Actions(){

    }

    public Offensive_Physical_Actions(float init_cast_time, int init_animation, Projectile init_projectile){
        total_cast_time=init_cast_time;
        animation=init_animation;
        projectile=init_projectile;
        cast_time=0;
    }

    public void step(){
        cast_time=cast_time+1;
        if (cast_time>total_cast_time){
            make_active=true;
        }
    }
}
