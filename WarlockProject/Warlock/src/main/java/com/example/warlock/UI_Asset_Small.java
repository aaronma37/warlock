package com.example.warlock;

/**
 * Created by aaron on 11/2/16.
 */
public class UI_Asset_Small {

    public float height, width, x,y;
    public int click_code_1, click_code_2, trigger, index;

    public UI_Asset_Small(int i_index, float i_width, float i_height, float i_y, float i_x,int c1, int c2, int i_trigger){
        this.index=i_index;
        this.width=i_width;
        this.height=i_height;
        this.x=i_x;
        this.y=i_y;
        this.click_code_1=c1;
        this.click_code_2=c2;
        this.trigger=i_trigger;
    }

    public void setClick_code_1(int c1){
        this.click_code_1=c1;
    }
    public void setClick_code_2(int c2){
        this.click_code_2=c2;
    }
}
