package com.example.warlock;

/**
 * Created by aaron on 11/4/16.
 */
public class Motion_Model_2 {

    public int hinge_index=-1, layer=0;
    public int held_index=-1;
    public int LEFT_ARM=0;
    public float off_x,off_y, alpha, omega, drag, mass, force, hinge_x,hinge_y, length,x,y, printx=0,printy=0;
    public boolean dynamic=false;
    public float lever_x=0, lever_y=0;
    public float PI_CONV=3.14f/180f;
    public int type =0;
    public int ct=0;
    public int ft=0;
    public float alph=1f;

    public Motion_Model_2(){
        type=-1;
        hinge_index=-1;
        off_x=0;
        off_y=0;
        mass=0;
        drag=0;
        length=0;
        omega=0;
        force=0;
        alpha=0;
        hinge_x=0;hinge_y=0;
        x=0;
        y=0;
    }

    public Motion_Model_2(int ind, float i_off_x, float i_off_y, float i_mass, float i_drag, float i_length, int i_layer){
        layer=i_layer;
        dynamic=true;
        hinge_index=ind;
        off_x=i_off_x;
        off_y=i_off_y;
        mass=i_mass;
        drag=i_drag;
        length=i_length;
        omega=0;
        force=0;
        alpha=0;
        hinge_x=0;hinge_y=0;
        x=0;
        y=0;
    }

    public Motion_Model_2(int ind, float i_off_x, float i_off_y, float i_mass, float i_drag, float i_length, int i_layer, int i_held){
        held_index=i_held;
        layer=i_layer;
        dynamic=true;
        hinge_index=ind;
        off_x=i_off_x;
        off_y=i_off_y;
        mass=i_mass;
        drag=i_drag;
        length=i_length;
        omega=0;
        force=0;
        alpha=0;
        hinge_x=0;hinge_y=0;
        x=0;
        y=0;
    }

    public Motion_Model_2(int ind, float i_off_x, float i_off_y, float i_mass, float i_drag, float i_length, int i_layer, int i_held, float i_lever_x, float i_lever_y){
        held_index=i_held;
        layer=i_layer;
        dynamic=true;
        hinge_index=ind;
        off_x=i_off_x;
        off_y=i_off_y;
        mass=i_mass;
        drag=i_drag;
        length=i_length;
        omega=0;
        force=0;
        alpha=0;
        hinge_x=0;hinge_y=0;
        x=0;
        y=0;
        lever_x=i_lever_x;
        lever_y=i_lever_y;
    }

    public Motion_Model_2(int i_type, int ind, float i_lever_x, float i_lever_y){
        type=i_type;
        held_index=0;
        layer=1;
        dynamic=true;
        hinge_index=ind;
        off_x=0;
        off_y=0;
        mass=1;
        drag=1;
        length=0;
        omega=0;
        force=0;
        alpha=0;
        hinge_x=0;hinge_y=0;
        x=0;
        y=0;
        lever_x=i_lever_x;
        lever_y=i_lever_y;
    }

    public Motion_Model_2(int i_type, int ind, float i_lever_x, float i_lever_y, int cur_time, int fin_time){
        type=i_type;
        held_index=0;
        layer=1;
        dynamic=true;
        hinge_index=ind;
        off_x=0;
        off_y=0;
        mass=0;
        drag=0;
        length=0;
        omega=0;
        force=0;
        alpha=0;
        hinge_x=0;hinge_y=0;
        x=0;
        y=0;
        lever_x=i_lever_x;
        lever_y=i_lever_y;
        ct=cur_time;
        ft=fin_time;
    }

    public void set_offsets(float x_, float y_){
        printx=x_;
        printy=y_;
    }

    public void update_force(float i_x, float i_y, float i_alpha){

        //alpha=(float)Math.atan2(((double)(i_x-hinge_x)),((double)(i_y-hinge_y)));
        if (type==0 || type==-1){
            force=((float)Math.cos(alpha*PI_CONV)*(i_x-x)+(float)Math.sin(alpha*PI_CONV)*(i_y-y));
            x=i_x;//+lever_x*(float)Math.cos(alpha*PI_CONV) + lever_y*(float)Math.sin(alpha*PI_CONV);
            y=i_y;//+lever_x*(float)Math.sin(alpha*PI_CONV) + lever_y*(float)Math.cos(alpha*PI_CONV)-lever_y;
        }else if (type==1){
            x=i_x;
            y=i_y;
            alpha=i_alpha;
        }
        if (type == 2) {
            ct++;
            if (ct>ft){

                reset_values(type,i_x,i_y);

            }
        }

    }


    public void step(){
        if (type==0){

            omega+=.01*(-alpha) + force/mass-.05*(omega*drag);
            alpha+=omega;
        }else if (type==2){
            alph=(float)(ft-ct)/ft;
        }

    }

    public void reset_values(int t, float x_, float y_){
        if (t==2){
            x=x_;
            y=y_;
            ct=0;
            alph=1f;
        }
    }



}
