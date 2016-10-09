package com.example.warlock;

/**
 * Created by aaron on 8/16/16.
 */
public class PhysicalState {

    public int state=0;
    public float time_in_state=0;
    public float force=0;
    public boolean can_move=false;
    public float interrupt_level=0;
    public int knock_back_direction=0;
    public float destination_x=0;
    public float ms=0;
    public boolean castable=false;
    public int spirit_type, spell_type, meta_type;


    //0 ready, 1 busy, 2 knockback
    public PhysicalState(){
    }

    public void setState(int i_state, float val1, float val2, int val3) {
        this.state = i_state;

        if (state==0){
            //idle
            castable=true;
            can_move=true;
        }
        else if (state==1){
            //Casting
            if (val1==0){
                if (val2==0){
                    interrupt_level=1;
                }
            }
            castable=false;
            can_move=false;

        }else if (state==2){
            //Knockback state
            time_in_state=val1;
            force=val2;
            knock_back_direction=val3;
            castable=false;
            can_move=false;

        }else if (state==3){
            //Moving state
            ms=.01f;
            destination_x=val1;
            castable=true;
            can_move=true;

        }else if (state==4){
            //Sprint
            castable=true;
            ms=.03f;
            destination_x=val1;
            can_move=true;

        }else if (state==7){
            //ACTION ANIMATION STATE
            castable=false;

            spirit_type=(int)val2;
            spell_type = (int)val1;
            meta_type=val3;
            can_move=false;

        }

    }
}
