package com.example.warlock;

import android.content.Context;

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
    public Box box;
    public float location_x;
    public float location_y;
    public float knock_back_time;
    public float knock_back_force;
    public float knockback_y;
    public float direction;
    public List<Person> active_targets = new ArrayList<>();
    public Person owner;
    public int active_time;
    public int distance_from_target=2;
    public int time_active=0;
    public int interrupt_level=0;
    public int knockback_direction=0;
    public int spell_type=0;
    public Context myContext;
    public boolean projectile_interruptable=false;

    public Projectile_Skeleton skeleton;

    public Projectile(Context context){
        active=false;
        skeleton= new Projectile_Skeleton();
        myContext=context;
        box= new Box(0,0,0,0,0);
    }

    public void reset(){active=false; time_active=0;}

    public Projectile(float init_cast_location_x,float init_cast_location_y, float init_speed, float init_damage, int init_type, int init_debuff, float init_persistance, Hitbox init_hitbox, float init_direction, int init_active_time,Context context){
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
        spell_type=0;
        skeleton = new Projectile_Skeleton();
        myContext=context;
        owner = new Person("dummy",0,0,myContext);
    }

    public void step() {

        skeleton.step(owner.center_x,owner.center_y,owner.facing_direction);

        if (!skeleton.active){
            active=false;
        }

        location_x=skeleton.x+skeleton.origin_x;
        location_y=skeleton.y+skeleton.origin_y;
        reset_box();


        if (Math.abs(location_x - active_targets.get(0).center_x) > .5f) {
            distance_from_target = 2;
        } else if (Math.abs(location_x - active_targets.get(0).center_x) > .35f) {
            distance_from_target = 1;
        } else {
            distance_from_target = 0;
        }
    }

    public void reset_box(){
        box.x=location_x;
        box.y=location_y;
        box.width=skeleton.width;
        box.height=skeleton.height;
    }

    public boolean ask_for_particle(){
        if (type==0) {
            if (Math.random()>.05f){
                return true;
            }
        }
        return false;
    }

    public void on_hit(){
        if (spell_type==1){
            return;
        }else{
            active=false;
        }
    }

    public void addTarget(Person target){
        active_targets.add(target);
    }


    //MAGICAL FIRE
    public void SET_TYPE_fire_ball(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        projectile_interruptable=false;
        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=5;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;
        knock_back_force=.01f;
        knock_back_time=15;

        interrupt_level=1;
    }

    public void SET_TYPE_fire_spray(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        projectile_interruptable=false;
        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void SET_TYPE_fire_blast(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x+origin.facing_direction*.2f;
        cast_location_y=origin.center_y;
        location_x=cast_location_x;
        location_y=cast_location_y;
        projectile_interruptable=false;
        speed=0f;
        damage=7;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(3,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        //direction = (float)Math.atan2(target.center_y, target.center_x) - (float)Math.atan2(origin.center_y, origin.center_x);
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=10;

        knock_back_force=.01f;
        knock_back_time=15;

        interrupt_level=1;
    }

    public void SET_TYPE_light_strike_array(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        location_x=cast_location_x;
        location_y=cast_location_y;
        projectile_interruptable=false;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void SET_TYPE_sun_strike(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        location_x=cast_location_x;
        location_y=cast_location_y;
        projectile_interruptable=false;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void SET_TYPE_flame_guard(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        projectile_interruptable=false;
        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void SET_TYPE_flame_wall(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        projectile_interruptable=false;
        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void SET_TYPE_imbue(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        projectile_interruptable=false;
        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }


    //PHYSICAL FIRE
    public void SET_TYPE_palm_blast(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        projectile_interruptable=true;
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void SET_TYPE_axe_attack(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        projectile_interruptable=true;
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void SET_TYPE_spinning_axe(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        projectile_interruptable=true;

        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void SET_TYPE_dragon_kick(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        projectile_interruptable=true;

        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void SET_TYPE_dragon_punch(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        projectile_interruptable=true;

        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void SET_TYPE_dragon_wheel(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        cast_location_x=origin.center_x;
        cast_location_y=origin.center_y;
        projectile_interruptable=true;

        location_x=cast_location_x;
        location_y=cast_location_y;
        speed=.025f;
        damage=1f;
        type=0;
        debuff=0;
        persistance=0;
        hitbox.set(2,2);
        direction=(float)Math.atan2((target.center_y-origin.center_y),(target.center_x-origin.center_x));
        if (target.center_x-origin.center_x>0){
            knockback_direction=1;
        }else{
            knockback_direction=-1;
        }
        active_targets.clear();
        active_targets.add(target);
        active_time=150;

        knock_back_force=.01f;
        knock_back_time=10;

        interrupt_level=1;
    }

    public void setSpell(Person target, Person origin, int init_spell_type, int init_spirit_type, int init_meta_type){
        skeleton.activate(origin.center_x, origin.center_y, origin.facing_direction, init_spell_type, init_spirit_type, init_meta_type);
        owner=origin;
        knockback_y=.05f;
        active=true;
        spell_type=init_spell_type;

        //FIRE
        if (init_spell_type < 11){
            type=1;
        }
        else if (init_spell_type < 21){
            type=2;
        }
        else if (init_spell_type < 31){
            type=3;
        }
        else if (init_spell_type < 41){
            type=4;
        }
        else if (init_spell_type < 51){
            type=5;
        }
        else if (init_spell_type < 61){
            type=6;
        }
        else if (init_spell_type < 71){
            type=7;
        }
        else if (init_spell_type < 81){
            type=8;
        }
        else if (init_spell_type < 91){
            type=9;
        }
        else if (init_spell_type < 101){
            type=10;
        }
        else if (init_spell_type < 111){
            type=11;
        }
        else if (init_spell_type < 121){
            type=12;
        }
        else if (init_spell_type < 131){
            type=13;
        }
        else if (init_spell_type < 141){
            type=14;
        }
        else if (init_spell_type < 151){
            type=15;
        }


        if (init_spirit_type==0){
            if (init_meta_type==0){
                if (init_spell_type==0){
                    SET_TYPE_fire_ball(target,origin,init_spell_type,init_spirit_type,init_meta_type);
                }else if (init_spell_type==1){
                    SET_TYPE_fire_spray(target,origin,init_spell_type,init_spirit_type,init_meta_type);
                }
                else if (init_spell_type==2){
                    SET_TYPE_fire_blast(target, origin, init_spell_type, init_spirit_type, init_meta_type);
                }else if (init_spell_type==3){
                    SET_TYPE_light_strike_array(target, origin, init_spell_type, init_spirit_type, init_meta_type);
                }else if (init_spell_type==4){
                    SET_TYPE_sun_strike(target, origin, init_spell_type, init_spirit_type, init_meta_type);
                }else if (init_spell_type==5){
                    SET_TYPE_flame_guard(target, origin, init_spell_type, init_spirit_type, init_meta_type);
                }else if (init_spell_type==6){
                    SET_TYPE_flame_wall(target, origin, init_spell_type, init_spirit_type, init_meta_type);
                }else if (init_spell_type==7){
                    SET_TYPE_imbue(target, origin, init_spell_type, init_spirit_type, init_meta_type);
                }else if (init_spell_type==8){

                }else if (init_spell_type==9){

                }else if (init_spell_type==10){

                }
            }else if (init_meta_type==1){
                if (init_spell_type==0){
                    SET_TYPE_palm_blast(target,origin,init_spell_type,init_spirit_type,init_meta_type);
                }else if (init_spell_type==1){
                    SET_TYPE_axe_attack(target,origin,init_spell_type,init_spirit_type,init_meta_type);
                }else if (init_spell_type==2){
                    SET_TYPE_spinning_axe(target, origin, init_spell_type, init_spirit_type, init_meta_type);
                }else if (init_spell_type==3){
                    SET_TYPE_dragon_kick(target, origin, init_spell_type, init_spirit_type, init_meta_type);
                }else if (init_spell_type==4){
                    SET_TYPE_dragon_punch(target, origin, init_spell_type, init_spirit_type, init_meta_type);
                }else if (init_spell_type==5){
                    SET_TYPE_dragon_wheel(target, origin, init_spell_type, init_spirit_type, init_meta_type);
                }else if (init_spell_type==6){

                }else if (init_spell_type==7){

                }else if (init_spell_type==8){

                }else if (init_spell_type==9){

                }else if (init_spell_type==10){

                }
            }

        }else if (init_spirit_type==1){
            if (init_spell_type==0){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }else if (init_spell_type==1){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }
            else if (init_spell_type==2){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==3){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==4){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==5){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==6){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==7){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==8){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==9){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==10){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==11){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }else if (init_spell_type==12){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==13){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==14){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==15){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }
        }else if (init_spirit_type==2){
            if (init_spell_type==0){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }else if (init_spell_type==1){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }
            else if (init_spell_type==2){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==3){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==4){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==5){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==6){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==7){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==8){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==9){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==10){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==11){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }else if (init_spell_type==12){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==13){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==14){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==15){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }
        }else if (init_spirit_type==3){
            if (init_spell_type==0){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }else if (init_spell_type==1){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }
            else if (init_spell_type==2){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==3){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==4){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==5){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==6){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==7){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==8){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==9){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==10){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==11){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }else if (init_spell_type==12){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==13){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==14){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==15){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }
        }else if (init_spirit_type==4){
            if (init_spell_type==0){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }else if (init_spell_type==1){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }
            else if (init_spell_type==2){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==3){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==4){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==5){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==6){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==7){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==8){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==9){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==10){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==11){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=150;

                knock_back_force=.01f;
                knock_back_time=10;

                interrupt_level=1;
            }else if (init_spell_type==12){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==13){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==14){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }else if (init_spell_type==15){
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
                if (target.center_x-origin.center_x>0){
                    knockback_direction=1;
                }else{
                    knockback_direction=-1;
                }
                active_targets.clear();
                active_targets.add(target);
                active_time=10;

                knock_back_force=.01f;
                knock_back_time=15;

                interrupt_level=1;
            }
        }


    }


}
