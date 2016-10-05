package com.example.warlock;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 9/19/16.
 */
public class Projectile_Skeleton {

    public float x,y, origin_x, origin_y;
    public float width, height;
    public int type;
    public int dir;
    public int count;
    public boolean active;

    public List<List<Projectile_Keyframe>> animation = new ArrayList<List<Projectile_Keyframe>>();

    public Projectile_Skeleton(){
        set_keyframes();
        count=0;
        x=0;
        y=0;
        width=0;
        height=0;
    }

    public void set_keyframes(){
        // float ix,float iy,    float iwidth, float iheight,    int b, int e

        List<Projectile_Keyframe> fireball = new ArrayList<>();
        fireball.add(new Projectile_Keyframe(0, 0,  .1f,.1f,    0,30));
        fireball.add(new Projectile_Keyframe(1f,0,  .1f,.1f,    30,30));
        fireball.add(new Projectile_Keyframe(1f,0,  .1f,.1f,    30,30));

        animation.add(fireball);


        List<Projectile_Keyframe> firespray = new ArrayList<>();
        firespray.add(new Projectile_Keyframe(.1f, 0,  .1f,.1f,    0,100));
        firespray.add(new Projectile_Keyframe(.1f,0,  .1f,.1f,    100,100));
        firespray.add(new Projectile_Keyframe(.1f,0,  .1f,.1f,    100,100));

        animation.add(firespray);

    }

    public void activate(float ix, float iy, int d, int index){
        type=index;
        dir = d;
        origin_x=ix;
        origin_y=iy;
        active=true;
        x=0;
        y=0;
        width=0;
        height=0;
        count=0;
    }

    public void step(float ix, float iy, int d){

        if (type==1){
            origin_x=ix;
            origin_y=iy;
            dir=d;
        }

        if (active){
            count++;

            if (count>animation.get(type).get(animation.get(type).size()-1).end){
                active=false;
            }

            for (int i=0;i<animation.get(type).size();i++){
                if(count<animation.get(type).get(i).end){
                    update_projectile(dir, animation.get(type).get(i),animation.get(type).get(i+1), (1f-(count-animation.get(type).get(i).begin)/(animation.get(type).get(i).end-animation.get(type).get(i).begin)));
                    break;
                }

            }
        }
    }

    public void update_projectile(int dir, Projectile_Keyframe anim_1, Projectile_Keyframe anim_2, float rng){
        x= dir*(anim_2.x+rng*(anim_1.x-anim_2.x));
        y= dir*(anim_2.y+rng*(anim_1.y-anim_2.y));
        width= dir*(anim_2.width+rng*(anim_1.width-anim_2.width));
        height= dir*(anim_2.height+rng*(anim_1.height-anim_2.height));
    }



}
