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
    public int attribute_requirements[]= new int[5];

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
        for (int i=0;i<5;i++){
            attribute_requirements[i]=0;
        }
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
                //Approach
                setFacingDirection();
                origin.motion(move_speed,move_direction);
            }else if (spell_type==1){
                //APPROACH
                setFacingDirection();
            }else if (spell_type==2){
            }
        } else if (meta_type==1){
            if (spell_type==0) {
            }
                //Block
        }
    }


    public void cast(Projectile projectile_swap[], List<Projectile> active_projectiles){
        //Define what happens at cast point

        if (meta_type==0){
            //OFF META TYPE
            if (spell_type==0){
                //APPROACH
                setFacingDirection();
                origin.motion(move_speed,move_direction);
            }else if (spell_type==1){
            //FIREBALL
            setFacingDirection();
            projectile_swap[active_projectiles.size()].reset();
            projectile_swap[active_projectiles.size()].setSpell(target,origin,spell_type);
            active_projectiles.add(projectile_swap[active_projectiles.size()]);
            }else if (spell_type==2){
                projectile_swap[active_projectiles.size()].reset();
                projectile_swap[active_projectiles.size()].setSpell(target,origin,spell_type);
                active_projectiles.add(projectile_swap[active_projectiles.size()]);
            }else{
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
        //Define what happens when spell is defined
        //cast time/target/

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

            }else if (spell_type==1){
                //FIREBALL
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==2){
                //FIREBALL  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==3){
                //flamethrower /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==4){
                //FIRESTORM  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==5){
                //immolate  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==6){
                //immolate  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            else if (spell_type==7){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            else if (spell_type==8){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            else if (spell_type==9){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==10){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==11){
                //icebolt  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }
            else if (spell_type==12){
                //icebolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==13){
                //mist  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==14){
                //bubble beam  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==15){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==16){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==17){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==18){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==19){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==20){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //NATURE
            else if (spell_type==21){
                //earthbolt  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==22){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==23){
                //mist /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==24){
                //bubblebeam/0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==25){
                //blank /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==26){
                //0  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==27){
                //0  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==28){
                //0  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==29){
                //0  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==30){
                //0 SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //LIGHT
            else if (spell_type==31){
                //lightbolt  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==32){
                //lightbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==33){
                //charm /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==34){
                //light /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==35){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==36){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==37){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==38){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==39){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==40){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //DARK
            else if (spell_type==41){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==42){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==43){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==44){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==45){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==46){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==47){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==48){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==49){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==50){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }
            //FIREWATER
            else if (spell_type==51){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;

            }else if (spell_type==52){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==53){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==54){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==55){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==56){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==57){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==58){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==59){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==60){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //FIRENATURE
            else if (spell_type==61){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==62){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==63){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==64){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==65){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==66){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==67){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==68){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==69){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==70){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //FIRELIGHT
            else if (spell_type==71){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==72){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==73){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==74){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==75){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==76){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==77){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==78){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==79){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==80){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //FIREDARK
            else if (spell_type==81){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==82){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==83){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==84){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==85){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==86){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==87){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==88){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==89){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==90){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //WATERNATURE
            else if (spell_type==91){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==92){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==93){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==94){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==95){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==96){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==97){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==98){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==99){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==100){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //WATERLIGHT
            else if (spell_type==101){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==102){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==103){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==104){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==105){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==106){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==107){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==108){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==109){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==110){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //WATERDARK
            else if (spell_type==111){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==112){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==113){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==114){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==115){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==116){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==117){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==118){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==119){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==120){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //NATURELIGHT
            else if (spell_type==121){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==122){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==123){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==124){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==125){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==126){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==127){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==128){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==129){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==130){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //NATUREDARK
            else if (spell_type==131){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==132){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==133){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==134){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==135){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==136){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==137){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==138){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==139){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==140){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //LIGHTDARK
            else if (spell_type==141){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==142){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==143){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==144){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==145){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==146){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==147){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==148){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==149){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==150){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }



            /////////////////////////////////////////////////////////
        }else if (meta_type==1){
            //SUPPORT META TYPE
            if (spell_type==0){
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

            }else if (spell_type==1){
                //FIREBALL
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==2){
                //FIREBALL  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==3){
                //flamethrower /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==4){
                //FIRESTORM  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==5){
                //immolate  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==6){
                //immolate  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            else if (spell_type==7){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            else if (spell_type==8){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            else if (spell_type==9){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==10){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==11){
                //icebolt  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            else if (spell_type==12){
                //icebolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==13){
                //mist  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==14){
                //bubble beam  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==15){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==16){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==17){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==18){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==19){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==20){
                //blank  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //NATURE
            else if (spell_type==21){
                //earthbolt  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==22){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==23){
                //mist /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==24){
                //bubblebeam/0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==25){
                //blank /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==26){
                //0  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==27){
                //0  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==28){
                //0  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==29){
                //0  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==30){
                //0 SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //LIGHT
            else if (spell_type==31){
                //lightbolt  /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==32){
                //lightbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==33){
                //charm /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==34){
                //light /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==35){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==36){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==37){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==38){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==39){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==40){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //DARK
            else if (spell_type==41){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==42){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==43){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==44){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==45){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==46){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==47){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==48){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==49){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==50){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //FIREWATER
            else if (spell_type==51){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==52){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==53){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==54){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==55){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==56){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==57){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==58){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==59){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==60){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //FIRENATURE
            else if (spell_type==61){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==62){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==63){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==64){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==65){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==66){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==67){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==68){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==69){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==70){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //FIRELIGHT
            else if (spell_type==71){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==72){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==73){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==74){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==75){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==76){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==77){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==78){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==79){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==80){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //FIREDARK
            else if (spell_type==81){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==82){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==83){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==84){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==85){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==86){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==87){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==88){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==89){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==90){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //WATERNATURE
            else if (spell_type==91){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==92){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==93){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==94){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==95){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==96){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==97){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==98){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==99){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==100){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //WATERLIGHT
            else if (spell_type==101){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==102){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==103){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==104){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==105){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==106){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==107){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==108){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==109){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==110){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //WATERDARK
            else if (spell_type==111){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==112){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==113){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==114){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==115){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==116){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==117){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==118){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==119){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==120){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //NATURELIGHT
            else if (spell_type==121){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==122){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==123){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==124){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==125){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==126){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==127){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==128){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==129){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==130){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //NATUREDARK
            else if (spell_type==131){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==132){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==133){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==134){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==135){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==136){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==137){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==138){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==139){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==140){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }
            //LIGHTDARK
            else if (spell_type==141){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==142){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==143){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==144){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==145){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==146){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==147){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==148){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==149){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
                active=true;
            }else if (spell_type==150){
                //earthbolt SPAM /0
                projectile_flag=true;
                total_cast_time=45f;
                cool_down=0;
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
