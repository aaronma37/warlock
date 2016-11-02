package com.example.warlock;


/**
 * Created by aaron on 11/2/16.
 */
public class Location_Assets_Small {

    public int index,type;
    public float AR,size,x,y,distance;
    public float location_width,size_index;
    public boolean dynamic=false;

    public float xr,yr,zr;
    public float og_x,og_y;
    public float vx,vy,vz;

    public Location_Assets_Small(int i_index, float i_AR, float i_size, float i_x, float i_y, float i_distance, boolean i_dynamic, int i_type, float loc_width) {
        index=i_index;
        AR = i_AR;
        size = i_size;
        x = i_x;
        y = i_y;
        distance = i_distance;
        type=i_type;
        location_width=loc_width;
        dynamic=i_dynamic;

        if (!dynamic){
            location_width=0;
            size_index=0;
        }else{
            init_dynamics(i_type);
        }
    }

    public void init_dynamics(int k){
        if (k==0){
            x=(float)Math.random()*location_width*2-location_width;
            og_x=(float)Math.random();
            og_y=(float)Math.random();
            y=(float)Math.random()*2-1;
            xr=(float)Math.random()*360;
            yr=(float)Math.random()*360;
            zr=(float)Math.random()*360;

            vx=(float)Math.random()*30-15;
            vy=(float)Math.random()*30-15;
            vz=(float)Math.random()*30-15;
        }else if (type==1 || type==2){
            x=(float)Math.random()*location_width*2-location_width;
            og_x=(float)Math.random();
            og_y=(float)Math.random();
            y=1f-(float)Math.random()*.4f;
            xr=0;
            yr=0;
            zr=0;

            vx=(float)Math.random()*30-15;
            vy=0;
            vz=0;
        }else{

            x=(float)Math.random()*location_width*2-location_width;
            og_x=(float)Math.random();
            og_y=(float)Math.random();
            y=(float)Math.random()*2-1;
            xr=(float)Math.random()*360;
            yr=(float)Math.random()*360;
            zr=(float)Math.random()*360;

            vx=(float)Math.random()*30-15;
            vy=(float)Math.random()*30-15;
            vz=(float)Math.random()*30-15;
        }
    }

    public void step(){
        if(dynamic){
            if (type==0){
                x+=.01f;
                y-=.01f;
                xr+=vx;
                yr+=vy;
                zr+=vz;
                if (y<-1f){
                    reset();
                }
            }else if (type==1 || type==2){
                x-=.003f;
                y-=0;
                if (x<-2f){
                    reset();
                }
            }
        }
    }

    public void reset(){
        if (dynamic){
            if (type==0){
                x=(float)Math.random()*(location_width*2)-location_width;
                y=1f;
            }else if (type==1 || type==2){
                x=location_width;
                y=1f-(float)Math.random()*(.4f);
            }

        }
    }
}
