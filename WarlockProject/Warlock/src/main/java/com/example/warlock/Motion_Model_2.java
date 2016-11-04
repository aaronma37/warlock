package com.example.warlock;

/**
 * Created by aaron on 11/4/16.
 */
public class Motion_Model_2 {

    public int hinge_index=-1, layer=0;
    public float off_x,off_y, alpha, omega, drag, mass, force, hinge_x,hinge_y, length,x,y;
    public boolean dynamic=false;

    public Motion_Model_2(){
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

    public void update_force(float i_x, float i_y){
        x=i_x;
        y=i_y;
        force=((float)Math.cos(alpha)*(x-hinge_x)+(float)Math.sin(alpha)*(y-hinge_y));
    }


    public void step(){
        omega+=-alpha + force/mass-omega*drag;
        alpha+=omega;
    }



}
