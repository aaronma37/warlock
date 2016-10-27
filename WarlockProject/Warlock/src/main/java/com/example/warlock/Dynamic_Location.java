package com.example.warlock;

/**
 * Created by aaron on 10/4/16.
 */
public class Dynamic_Location {
    public float x,y,xr,yr,zr;
    public float og_x,og_y;
    public int type, size_index;
    public float width;
    public float vx,vy,vz;

    public Dynamic_Location(int i_type, float i_width, int i_size_index){
        width=i_width;
        type=i_type;
        size_index=i_size_index;

        if (type==0){
            x=(float)Math.random()*width*2-width;
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
            x=(float)Math.random()*width*2-width;
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

            x=(float)Math.random()*width*2-width;
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

    public void reset(){
        if (type==0){
            x=(float)Math.random()*(width*2)-width;
            y=1f;
        }else if (type==1 || type==2){
            x=width;
            y=1f-(float)Math.random()*(.4f);
        }



    }



}
