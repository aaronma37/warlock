package com.example.warlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 9/4/16.
 */
public class Text_Collection {

    public Hard_Text text[] = new Hard_Text[500];
    public List<Hard_Text> active_text = new ArrayList<>();

    public Text_Collection(){

        for (int i=0;i<500;i++){
            text[i] = new Hard_Text("default",0,.1f,.1f,0,0);
        }

        text[0].str="Tap to begin";
        text[0].setSize(.4f,.1f,0,0);
    }

    public void add_to_active_text(int i){
        if (!active_text.contains(text[i])){
            active_text.add(text[i]);
        }

    }

    public void remove_to_active_text(int i){
        active_text.remove(text[i]);
    }

}
