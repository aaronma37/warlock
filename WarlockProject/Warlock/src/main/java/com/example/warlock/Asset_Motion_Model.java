package com.example.warlock;

/**
 * Created by aaron on 11/1/16.
 */
public class Asset_Motion_Model {

    public float vel_x;
    public float force;
    public float alpha;
    public float a1,a2,a3,a4,a5;
    public float mass;
    public float drag;



    public Asset_Motion_Model(float a_1, float a_2, float a_3, float a_4, float a_5, float i_mass, float i_drag){
        a1=a_1;a2=a_2;a3=a_3;a4=a_4;a5=a_5;
        force=0;
        mass=i_mass;
        drag=i_drag;
    }


    public void add_force(float i_force, int dir){
        force-=dir*i_force;
    }

    public void step(Person_Graphics_Asset_Asset asset){





        vel_x=vel_x+.2f*(0-alpha)-force/mass-vel_x*drag;
        alpha=alpha+vel_x;

        if (alpha>1f){
            alpha=1f;
        }else if (alpha <-1f){
            alpha=-1f;
        }

        for (int i = 0;i<5;i++){
            asset.spriteCoords[00+i*2]=(i*.5f-1f)+alpha*a1;
            asset.spriteCoords[10+i*2]=(i*.5f-1f)+alpha*a2;
            asset.spriteCoords[20+i*2]=(i*.5f-1f)+alpha*a3;
            asset.spriteCoords[30+i*2]=(i*.5f-1f)+alpha*a4;
            asset.spriteCoords[40+i*2]=(i*.5f-1f)+alpha*a5;
        }

        force=0;

        asset.vertexBuffer.put(asset.spriteCoords);
        asset.vertexBuffer.position(0);

    }
}
