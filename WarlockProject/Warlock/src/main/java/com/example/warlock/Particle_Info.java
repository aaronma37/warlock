package com.example.warlock;

/**
 * Created by aaron on 9/26/16.
 */
public class Particle_Info {
    public float x,y,x_vel,y_vel,mass,drag,alpha,decay;
    public int type;

    public Particle_Info(float i_mass, float i_drag, float i_decay){
        x=0;y=0;x_vel=0;y_vel=0;mass=i_mass;drag=i_drag;alpha=1;decay=i_decay;
    }

    public void reset(float i_x, float i_y, float i_x_vel, float i_y_vel, int i_type){
        x=i_x;
        y=i_y;
        x_vel=i_x_vel;
        y_vel=i_y_vel;
        type=i_type;
        alpha=1;
    }

    public void step(){
        x+=x_vel;
        y+=y_vel;
        x_vel-=x_vel*drag/mass;
        y_vel-=y_vel*drag/mass;
        alpha-=decay;
    }


}
