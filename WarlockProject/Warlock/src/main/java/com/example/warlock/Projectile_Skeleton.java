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
    public int spell_number;
    public int magic;
    public int spirit;
    public int dir;
    public int count;
    public boolean active;
    public Box box;

    public List<List<List<List<Projectile_Keyframe>>>> animation = new ArrayList<List<List<List<Projectile_Keyframe>>>>();

    public Projectile_Skeleton(){
        set_keyframes();
        count=0;
        x=0;
        y=0;
        width=0;
        height=0;
        box=new Box(0,0,0,0,0);
        reset_box();
    }

    public void set_keyframes(){
        // float ix,float iy,    float iwidth, float iheight,    int b, int e


        //fire
        List<List<List<Projectile_Keyframe>>> fire = new ArrayList<List<List<Projectile_Keyframe>>>();


            //magic fire
            List<List<Projectile_Keyframe>> magical_fire = new ArrayList<List<Projectile_Keyframe>>();



                List<Projectile_Keyframe> unnamed = new ArrayList<>();
                unnamed.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                unnamed.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                unnamed.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                List<Projectile_Keyframe> fireball = new ArrayList<>();
                fireball.add(new Projectile_Keyframe(0, 0,  .1f,.1f,    0,30));
                fireball.add(new Projectile_Keyframe(1f,0,  .1f,.1f,    30,30));
                fireball.add(new Projectile_Keyframe(1f,0,  .1f,.1f,    30,30));

                magical_fire.add(fireball);


                List<Projectile_Keyframe> firespray = new ArrayList<>();
                firespray.add(new Projectile_Keyframe(.1f, 0,  .1f,.1f,    0,100));
                firespray.add(new Projectile_Keyframe(.1f,0,  .1f,.1f,    100,100));
                firespray.add(new Projectile_Keyframe(.1f,0,  .1f,.1f,    100,100));

                magical_fire.add(firespray);

                List<Projectile_Keyframe> fireblast = new ArrayList<>();
                fireblast.add(new Projectile_Keyframe(0, 0,  .5f,.1f,    0,10));
                fireblast.add(new Projectile_Keyframe(0,0,  .5f,.1f,    10,10));
                fireblast.add(new Projectile_Keyframe(0,0,  .5f,.1f,    10,10));

                magical_fire.add(fireblast);

                List<Projectile_Keyframe> lsa = new ArrayList<>();
                lsa.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                lsa.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                lsa.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                magical_fire.add(lsa);

                List<Projectile_Keyframe> sunstrike = new ArrayList<>();
                sunstrike.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                sunstrike.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                sunstrike.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                magical_fire.add(sunstrike);

                List<Projectile_Keyframe> flameguard = new ArrayList<>();
                flameguard.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                flameguard.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                flameguard.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                magical_fire.add(flameguard);

                List<Projectile_Keyframe> flamewall = new ArrayList<>();
                flamewall.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                flamewall.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                flamewall.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                magical_fire.add(flamewall);

                List<Projectile_Keyframe> imbue = new ArrayList<>();
                imbue.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                imbue.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                imbue.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                magical_fire.add(imbue);

                magical_fire.add(unnamed);
                magical_fire.add(unnamed);

            fire.add(magical_fire);

            //physical fire
            List<List<Projectile_Keyframe>> physical_fire = new ArrayList<List<Projectile_Keyframe>>();


                List<Projectile_Keyframe> palmblast = new ArrayList<>();
                palmblast.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                palmblast.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                palmblast.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                physical_fire.add(palmblast);

                List<Projectile_Keyframe> axeattack = new ArrayList<>();
                axeattack.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                axeattack.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                axeattack.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                physical_fire.add(axeattack);

                List<Projectile_Keyframe> spinningaxe = new ArrayList<>();
                spinningaxe.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                spinningaxe.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                spinningaxe.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                physical_fire.add(spinningaxe);

                List<Projectile_Keyframe> dragonkick = new ArrayList<>();
                dragonkick.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                dragonkick.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                dragonkick.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                physical_fire.add(dragonkick);

                List<Projectile_Keyframe> dragonpunch = new ArrayList<>();
                dragonpunch.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                dragonpunch.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                dragonpunch.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                physical_fire.add(dragonpunch);

                List<Projectile_Keyframe> dragonwheel = new ArrayList<>();
                dragonwheel.add(new Projectile_Keyframe(0, 0,  .5f,.3f,    0,10));
                dragonwheel.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));
                dragonwheel.add(new Projectile_Keyframe(0,0,  .5f,.3f,    10,10));

                physical_fire.add(dragonwheel);
                physical_fire.add(unnamed);
                physical_fire.add(unnamed);
                physical_fire.add(unnamed);
                physical_fire.add(unnamed);

            fire.add(physical_fire);

        animation.add(fire);

        //light
        List<List<List<Projectile_Keyframe>>> light = new ArrayList<List<List<Projectile_Keyframe>>>();
            //magic light
            List<List<Projectile_Keyframe>> magical_light = new ArrayList<List<Projectile_Keyframe>>();

                magical_light.add(unnamed);
                magical_light.add(unnamed);
                magical_light.add(unnamed);
                magical_light.add(unnamed);
                magical_light.add(unnamed);
                magical_light.add(unnamed);
                magical_light.add(unnamed);
                magical_light.add(unnamed);
                magical_light.add(unnamed);
                magical_light.add(unnamed);

        light.add(magical_light);

            //physical light
            List<List<Projectile_Keyframe>> physical_light = new ArrayList<List<Projectile_Keyframe>>();

                physical_light.add(unnamed);
            physical_light.add(unnamed);
            physical_light.add(unnamed);
            physical_light.add(unnamed);
            physical_light.add(unnamed);
            physical_light.add(unnamed);
            physical_light.add(unnamed);
            physical_light.add(unnamed);
            physical_light.add(unnamed);
            physical_light.add(unnamed);

            light.add(physical_light);

        animation.add(light);

        //dark
        List<List<List<Projectile_Keyframe>>> dark = new ArrayList<List<List<Projectile_Keyframe>>>();
                //magic dark
                List<List<Projectile_Keyframe>> magical_dark = new ArrayList<List<Projectile_Keyframe>>();

                magical_dark.add(unnamed);
                magical_dark.add(unnamed);
                magical_dark.add(unnamed);
                magical_dark.add(unnamed);
                magical_dark.add(unnamed);
                magical_dark.add(unnamed);
                magical_dark.add(unnamed);
                magical_dark.add(unnamed);
                magical_dark.add(unnamed);


        dark.add(magical_dark);

                //physical dark
                List<List<Projectile_Keyframe>> physical_dark = new ArrayList<List<Projectile_Keyframe>>();

                physical_dark.add(unnamed);
            physical_dark.add(unnamed);
            physical_dark.add(unnamed);
            physical_dark.add(unnamed);
            physical_dark.add(unnamed);
            physical_dark.add(unnamed);
            physical_dark.add(unnamed);
            physical_dark.add(unnamed);
            physical_dark.add(unnamed);
            physical_dark.add(unnamed);
            physical_dark.add(unnamed);


        dark.add(physical_dark);

        animation.add(dark);

        //water
        List<List<List<Projectile_Keyframe>>> water = new ArrayList<List<List<Projectile_Keyframe>>>();
                //magic light
                List<List<Projectile_Keyframe>> magical_water= new ArrayList<List<Projectile_Keyframe>>();

                magical_water.add(unnamed);
                magical_water.add(unnamed);
                magical_water.add(unnamed);
                magical_water.add(unnamed);
                magical_water.add(unnamed);
                magical_water.add(unnamed);
                magical_water.add(unnamed);
                magical_water.add(unnamed);
                magical_water.add(unnamed);

            water.add(magical_water);

                //physical fire
                List<List<Projectile_Keyframe>> physical_water = new ArrayList<List<Projectile_Keyframe>>();

                physical_water.add(unnamed);
                physical_water.add(unnamed);
                physical_water.add(unnamed);
                physical_water.add(unnamed);
                physical_water.add(unnamed);
                physical_water.add(unnamed);
                physical_water.add(unnamed);
                physical_water.add(unnamed);
                physical_water.add(unnamed);
                physical_water.add(unnamed);
                physical_water.add(unnamed);

            water.add(physical_water);

        animation.add(water);


    }

    public void activate(float ix, float iy, int d, int spell_index, int spirit_type, int magical_type){

        //spirit type, magical type, spell number


        magic=magical_type;
        spirit=spirit_type;
        spell_number=spell_index;
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

        if (spell_number==1 && spirit==0 && magic==1){
            origin_x=ix;
            origin_y=iy;
            dir=d;
        }

        if (active){
            count++;
            if (count>animation.get(spirit).get(magic).get(spell_number).get(animation.get(spirit).get(magic).get(spell_number).size()-1).end){
                active=false;
            }

            for (int i=0;i<animation.get(spirit).get(magic).get(spell_number).size();i++){
                if(count<animation.get(spirit).get(magic).get(spell_number).get(i).end){
                    update_projectile(dir, animation.get(spirit).get(magic).get(spell_number).get(i),animation.get(spirit).get(magic).get(spell_number).get(i+1), (1f-(count-animation.get(spirit).get(magic).get(spell_number).get(i).begin)/(animation.get(spirit).get(magic).get(spell_number).get(i).end-animation.get(spirit).get(magic).get(spell_number).get(i).begin)));
                    reset_box();
                    break;
                }

            }
        }
    }

    public void reset_box(){
        box.x=x;
        box.y=y;
        box.width=width;
        box.height=height;
    }

    public void update_projectile(int dir, Projectile_Keyframe anim_1, Projectile_Keyframe anim_2, float rng){
        x= dir*(anim_2.x+rng*(anim_1.x-anim_2.x));
        y= (anim_2.y+rng*(anim_1.y-anim_2.y));
        width= (anim_2.width+rng*(anim_1.width-anim_2.width));
        height= (anim_2.height+rng*(anim_1.height-anim_2.height));
    }



}
