package com.example.warlock;

/**
 * Created by aaron on 11/4/16.
 */
public class Motion_Model_2 implements Cloneable{

    public int hinge_index=-1, layer=0;
    public int held_index=-1;
    public int LEFT_ARM=0;
    public float off_x,off_y, alpha, omega, drag, mass, force, hinge_x,hinge_y, length,x,y, printx=0,printy=0;
    public boolean dynamic=false;
    public float lever_x=0, lever_y=0;
    public float PI_CONV=3.14f/180f;
    public int type =0;
    public float ct=0;
    public float ft=0;
    public float alph=1f;

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

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
        this.x=0;
        this.y=0;
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
        this.x=0;
        this.y=0;
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
        this.x=0;
        this.y=0;
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
        this.x=0;
        this.y=0;
        lever_x=i_lever_x;
        lever_y=i_lever_y;
    }

    public Motion_Model_2(int i_type, int ind, float i_lever_x, float i_lever_y, float cur_time, float fin_time){
        this.type=i_type;
        held_index=0;
        layer=1;
        dynamic=true;
        hinge_index=ind;
        off_x=0;
        off_y=0;
        mass=1;
        drag=1;
        length=1;
        omega=0;
        force=0;
        alpha=0;
        hinge_x=0;hinge_y=0;
        this.x=0;
        this.y=0;
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
            this.x=i_x;//+lever_x*(float)Math.cos(alpha*PI_CONV) + lever_y*(float)Math.sin(alpha*PI_CONV);
            this.y=i_y;//+lever_x*(float)Math.sin(alpha*PI_CONV) + lever_y*(float)Math.cos(alpha*PI_CONV)-lever_y;
        }else if (type==1){
            this.x=i_x;
            this.y=i_y;
            alpha=i_alpha;
        }else{
/*            this.x=i_x+ct*.01f;
            this.y=i_y+ct*.01f;*/
            x=x+.005f;
            y=y+.005f;
            alpha=i_alpha;
        }

        if (type == 2) {
/*            this.x=x+.001f;
            this.y=y+.001f;
            alpha=i_alpha;*/

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
            alph=(ft-ct)/ft;
        }

    }

    public void reset_values(int t, float x_, float y_){
        if (t==2){
            this.x=x_;
            this.y=y_;
            ct=0;
            alph=1f;
        }
    }



}
