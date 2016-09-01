package com.example.warlock;

/**
 * Created by aaron on 9/1/16.
 */
public class Image_Info {
    private int mTextureDataHandle;
    public float height, width, x,y;
    public int click_code_1, click_code_2;

    public Image_Info(int link, float i_width, float i_height, float i_y, float i_x,int c1, int c2){
        mTextureDataHandle = link;
        this.width=i_width;
        this.height=i_height;
        this.x=i_x;
        this.y=i_y;
        this.click_code_1=c1;
        this.click_code_2=c2;
    }

    public int returnTextureData(){return mTextureDataHandle;}

    public void setClick_code_1(int c1){
        this.click_code_1=c1;
    }
    public void setClick_code_2(int c2){
        this.click_code_2=c2;
    }


}
