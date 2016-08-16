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
    public Person target;
    public int spell_type;
    public int meta_type;
    public float block=0;
    public Person origin;
    public boolean move_flag=false;
    public boolean projectile_flag=false;
    public boolean facing_flag=false;
    public int facing_direction=1;
    public float move_speed;
    public float move_direction;


    public Offensive_Physical_Actions(){

    }

    public Offensive_Physical_Actions(float init_cast_time, int init_animation, Projectile init_projectile, Person init_target){
        total_cast_time=init_cast_time;
        spell_type=0;
        animation=init_animation;
        //projectile = new Projectile();
        projectile=init_projectile;
        target=init_target;
        cast_time=0;
        active=true;
    }

    public void step(){
        cast_time=cast_time+1;
        if (cast_time>total_cast_time){
            if (meta_type==0){
                //OFF META TYPE
                if (spell_type==0){
                    //FIREBALL
                    make_active=true;
                    active=false;
                    return;
                }else if (spell_type==1){
                    //APPROACH
                    move_speed=.005f;

                    if (origin.center_x-target.center_x>0){
                        move_direction=-1;
                    }else{
                        move_direction=1;
                    }
                    make_active=true;
                    active=false;
                }else if (spell_type==2){
                    make_active=true;
                    active=false;
                }
            } else if (meta_type==1){
                if (spell_type==0){
                    make_active=true;
                    active=false;
                    block=0;
                    return;
                }
            }

            setFacingDirection();

            make_active=true;
            active=false;
        }
    }

    public void setFacingDirection(){
        if (facing_flag){
            if (origin.center_x-target.center_x>0){
                facing_direction=-1;
            }
        }
    }

    public void set(int init_meta_type, int init_spell_type, Projectile init_projectile, Person init_target, Person init_origin){
        animation=0;
        projectile=init_projectile;
        target=init_target;
        origin=init_origin;
        cast_time=0;
        projectile_flag=false;
        move_flag=false;
        facing_flag=true;

        spell_type=init_spell_type;
        meta_type=init_meta_type;

        if (meta_type==0){
            //OFF META TYPE
            if (spell_type==0){
                //FIREBALL
                projectile_flag=true;
                total_cast_time=100f;
                active=true;
            }else if (spell_type==1){
                //APPROACH
                move_flag=true;
                total_cast_time=10f;
                active=true;
            }else if (spell_type==2){
                total_cast_time=30f;
                active=true;
                projectile_flag=true;
            }
        }else if (meta_type==1){
            //DEF META TYPE
            if (spell_type==0){
                total_cast_time=30f;
                block = 5;
                active=true;
            }
        }


    }

    public void reset(){
        active=false;
        make_active=false;
        cast_time=0;
    }

}
