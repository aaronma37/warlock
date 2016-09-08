package com.example.warlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 9/4/16.
 */
public class Text_Collection {

    public Hard_Text text[] = new Hard_Text[500];
    public List<Hard_Text> active_text = new ArrayList<>();
    public Hard_Text active_area_text[] = new Hard_Text[10];

    public Text_Collection(){

        for (int i=0;i<500;i++){
            text[i] = new Hard_Text("default",0,.1f,.1f,0,0);
        }

        for(int i=0;i<10;i++){
            active_area_text[i] = new Hard_Text("",0,0,0,0,0);
        }

        text[0].str="Tap to begin";
        text[0].setSize(.4f,.1f,0,0);

        text[10].str="No meta";
        text[10].setSize(.4f,.1f,.5f,-.8f);

        text[11].str="No action";
        text[11].setSize(.4f,.1f,0f,-.8f);

        active_area_text[0].str="No Meta";
        active_area_text[0].setSize(.3f,.1f,.7f,-.6f);
        active_area_text[0].set_font_size(.8f);

        active_area_text[1].str="No action";
        active_area_text[1].setSize(.3f,.1f,0f,-.6f);
        active_area_text[1].set_font_size(.8f);

    }

    public void add_to_active_text(int i){
        if (!active_text.contains(text[i])){
            active_text.add(text[i]);
        }
    }

    public String meta_to_text(int i){
        if (i==0){
            return "Offensive";
        }else if (i==1){
            return "Support";
        }else{
            return "Defensive";
        }
    }

    public void set_meta_text(int i){
        if (i==0){
            active_area_text[0].str="Offense";
        }else if (i==1){
            active_area_text[0].str="Support";
        }else{
            active_area_text[0].str="Defense";
        }
    }

    public void set_action_text(String str){
            active_area_text[1].str=str;

    }

    public void remove_to_active_text(int i){
        active_text.remove(text[i]);
    }

}
