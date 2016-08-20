package com.example.warlock;

import java.util.List;

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
    public float cool_down;


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

    public void step(Projectile projectile_swap[], List<Projectile> active_projectiles){
        cast_time=cast_time+1;
        if (cast_time>total_cast_time){
            //finished casting
            cast(projectile_swap, active_projectiles);
        }else{
            //casting
            casting();
        }



    }


    public void casting(){
        if (meta_type==0){
            //OFF META TYPE
            if (spell_type==0){
                //FIREBALL
                setFacingDirection();
            }else if (spell_type==1){
                //APPROACH
                setFacingDirection();
                origin.motion(move_speed,move_direction);
            }else if (spell_type==2){
            }
        } else if (meta_type==1){
            if (spell_type==0){
                //Block
            }else if(spell_type==1){
                //Teleport
            }
        }
    }


    public void cast(Projectile projectile_swap[], List<Projectile> active_projectiles){
        if (meta_type==0){
            //OFF META TYPE
            if (spell_type==0){
                //FIREBALL
                setFacingDirection();
                projectile_swap[active_projectiles.size()].reset();
                projectile_swap[active_projectiles.size()].setSpell(target,origin,spell_type);
                active_projectiles.add(projectile_swap[active_projectiles.size()]);

            }else if (spell_type==1){
                //APPROACH
                setFacingDirection();
                origin.motion(move_speed,move_direction);
            }else if (spell_type==2){
                projectile_swap[active_projectiles.size()].reset();
                projectile_swap[active_projectiles.size()].setSpell(target,origin,spell_type);
                active_projectiles.add(projectile_swap[active_projectiles.size()]);
            }
        } else if (meta_type==1){
            if (spell_type==0){
                //Block
                block=0;
            }else if(spell_type==1){
                //Teleport
                if (origin.center_x<0){
                    origin.center_x=1.2f;
                }else{
                    origin.center_x=-1.2f;
                }
            }
        }
        active=false;
        origin.state.setState(0,0,0,0);
    }




    public void setFacingDirection(){
        if (facing_flag){
            if (origin.center_x-target.center_x>0){
                origin.facing_direction=-1;
            }else{
                origin.facing_direction=1;
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
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==1){
                //APPROACH
                move_speed=.01f;

                if (origin.center_x-target.center_x>0){
                    move_direction=-1;
                }else{
                    move_direction=1;
                }

                move_flag=true;
                total_cast_time=10f;
                cool_down=0;
                active=true;
            }else if (spell_type==2){
                total_cast_time=30f;
                active=true;
                cool_down=0;
                projectile_flag=true;
            }
        }else if (meta_type==1){
            //DEF META TYPE
            if (spell_type==0){
                total_cast_time=30f;
                block = 5;
                cool_down=15;
                active=true;
            }else if (spell_type==1){
                total_cast_time=15f;
                cool_down=150;
                active=true;
            }
        }
    }

    public void reset(){
        active=false;
        make_active=false;
        cast_time=0;
    }

    public boolean returnFeasibility(Person origin, Person target, int init_meta_type, int init_spell_type){
        if (init_meta_type==0){
            if (init_spell_type==0){
                return true;
            }else if (init_spell_type==1){
                return true;
            }else if (init_spell_type==2){
                if (Math.abs(target.center_x-origin.center_x)>.25f){
                    return false;
                }
            }
        }else if (init_meta_type==1){
            return true;
        }
        return true;
    }


}
