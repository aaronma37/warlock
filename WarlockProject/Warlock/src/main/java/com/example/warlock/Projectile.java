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
    public float knock_back_time;
    public float knock_back_force;
    public float direction;
    public List<Person> active_targets = new ArrayList<>();
    public int active_time;
    public int distance_from_target=2;
    public int time_active=0;
    public int interrupt_level=0;
    public int knockback_direction=0;
    public int spell_type=0;

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
        spell_type=0;
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

    public void setSpell(Person target, Person origin, int init_spell_type){
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

        if (init_spell_type==0){

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
        }else if (init_spell_type==16){
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
        }else if (init_spell_type==17){
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
        }else if (init_spell_type==18){
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
        }else if (init_spell_type==19){
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
        }else if (init_spell_type==20){
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
        }else if (init_spell_type==21){
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
        }else if (init_spell_type==22){
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
        }else if (init_spell_type==23){
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
        }else if (init_spell_type==24){
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
        }else if (init_spell_type==25){
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
        }else if (init_spell_type==26){
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
        }else if (init_spell_type==27){
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
        }else if (init_spell_type==28){
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
        }else if (init_spell_type==29){
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
        }else if (init_spell_type==30){
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
        }else if (init_spell_type==31){
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
        }else if (init_spell_type==32){
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
        }else if (init_spell_type==33){
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
        }else if (init_spell_type==34){
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
        }else if (init_spell_type==35){
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
        }else if (init_spell_type==36){
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
        }else if (init_spell_type==37){
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
        }else if (init_spell_type==38){
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
        }else if (init_spell_type==39){
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
        }else if (init_spell_type==40){
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
        }else if (init_spell_type==41){
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
        }else if (init_spell_type==42){
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
        }else if (init_spell_type==43){
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
        }else if (init_spell_type==44){
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
        }else if (init_spell_type==45){
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
        }else if (init_spell_type==46){
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
        }else if (init_spell_type==47){
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
        }else if (init_spell_type==48){
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
        }else if (init_spell_type==49){
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
        }else if (init_spell_type==50){
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
        }else if (init_spell_type==51){
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
        }else if (init_spell_type==52){
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
        }else if (init_spell_type==53){
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
        }else if (init_spell_type==54){
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
        }else if (init_spell_type==55){
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
        }else if (init_spell_type==56){
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
        }else if (init_spell_type==57){
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
        }else if (init_spell_type==58){
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
        }else if (init_spell_type==59){
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
        }else if (init_spell_type==60){
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
        }else if (init_spell_type==61){
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
        }else if (init_spell_type==62){
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
        }else if (init_spell_type==63){
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
        }else if (init_spell_type==64){
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
        }else if (init_spell_type==65){
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
        }else if (init_spell_type==66){
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
        }else if (init_spell_type==67){
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
        }else if (init_spell_type==68){
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
        }else if (init_spell_type==69){
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
        }else if (init_spell_type==70){
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
        }else if (init_spell_type==71){
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
        }else if (init_spell_type==72){
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
        }else if (init_spell_type==73){
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
        }else if (init_spell_type==74){
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
        }else if (init_spell_type==75){
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
        }else if (init_spell_type==76){
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
        }else if (init_spell_type==77){
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
        }else if (init_spell_type==78){
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
        }else if (init_spell_type==79){
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
        }else if (init_spell_type==80){
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
        }else if (init_spell_type==81){
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
        }else if (init_spell_type==82){
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
        }else if (init_spell_type==83){
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
        }else if (init_spell_type==84){
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
        }else if (init_spell_type==85){
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
        }else if (init_spell_type==86){
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
        }else if (init_spell_type==87){
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
        }else if (init_spell_type==88){
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
        }else if (init_spell_type==89){
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
        }else if (init_spell_type==90){
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
        }else if (init_spell_type==91){
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
        }else if (init_spell_type==92){
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
        }else if (init_spell_type==93){
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
        }else if (init_spell_type==94){
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
        }else if (init_spell_type==95){
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
        }else if (init_spell_type==96){
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
        }else if (init_spell_type==97){
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
        }else if (init_spell_type==98){
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
        }else if (init_spell_type==99){
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
        }else if (init_spell_type==100){
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
        }else if (init_spell_type==101){
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
        }else if (init_spell_type==102){
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
        }else if (init_spell_type==103){
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
        }else if (init_spell_type==104){
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
        }else if (init_spell_type==105){
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
        }else if (init_spell_type==106){
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
        }else if (init_spell_type==107){
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
        }else if (init_spell_type==108){
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
        }else if (init_spell_type==109){
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
        }else if (init_spell_type==110){
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
        }else if (init_spell_type==111){
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
        }else if (init_spell_type==112){
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
        }else if (init_spell_type==113){
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
        }else if (init_spell_type==114){
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
        }else if (init_spell_type==115){
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
        }else if (init_spell_type==116){
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
        }else if (init_spell_type==117){
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
        }else if (init_spell_type==118){
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
        }else if (init_spell_type==119){
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
        }else if (init_spell_type==120){
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
        }else if (init_spell_type==121){
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
        }else if (init_spell_type==122){
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
        }else if (init_spell_type==123){
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
        }else if (init_spell_type==124){
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
        }else if (init_spell_type==125){
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
        }else if (init_spell_type==126){
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
        }else if (init_spell_type==127){
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
        }else if (init_spell_type==128){
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
        }else if (init_spell_type==129){
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
        }else if (init_spell_type==130){
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
        }else if (init_spell_type==131){
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
        }else if (init_spell_type==132){
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
        }else if (init_spell_type==133){
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
        }else if (init_spell_type==134){
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
        }else if (init_spell_type==135){
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
        }else if (init_spell_type==136){
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
        }else if (init_spell_type==137){
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
        }else if (init_spell_type==138){
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
        }else if (init_spell_type==139){
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
        }else if (init_spell_type==140){
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
        }else if (init_spell_type==141){
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
        }else if (init_spell_type==142){
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
        }else if (init_spell_type==143){
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
        }else if (init_spell_type==144){
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
        }else if (init_spell_type==145){
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
        }else if (init_spell_type==146){
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
        }else if (init_spell_type==147){
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
        }else if (init_spell_type==148){
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
        }else if (init_spell_type==149){
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
        }else if (init_spell_type==150){
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
