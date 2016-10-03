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

    public int helper_counter=0;

    private float LOCATION_WIDTH=.4f, LOCATION_HEIGHT=.1f, LOCATION_X=0f, LOCATION_Y=-.8f;


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

        { //LOCATIONS
            helper_counter=100;

            text[helper_counter].str = "unknown location";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Tutorial 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Tutorial 2";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Tutorial 3";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Town 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 11";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 12";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Town 21";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Town 22";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 31";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 32";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Fire Temple 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Fire Temple 2";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 41";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 42";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Town 5";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 51";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 52";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "WF-Temple 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 61";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 62";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Light Temple 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Light Temple 2";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Portal";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 71";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 72";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "LW-Temple 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 81";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


            text[helper_counter].str = "Route 82";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Dark Temple 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Dark Temple 2";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Town 3-1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Town 3-2";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


            text[helper_counter].str = "Route 91";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;



            text[helper_counter].str = "Route 101";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;



            text[helper_counter].str = "Route 102";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Vampire Temple 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 111";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 112";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Water Temple 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Water Temple 2";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Town 4";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


            text[helper_counter].str = "Route 121";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


            text[helper_counter].str = "Route 122";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


            text[helper_counter].str = "Witch Temple 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


            text[helper_counter].str = "Route 131";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


            text[helper_counter].str = "LF Temple 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


            text[helper_counter].str = "Route 141";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "LD Temple 1";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;

            text[helper_counter].str = "Route 152";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


            text[helper_counter].str = "Route 151";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


            text[helper_counter].str = "Route 153";
            text[helper_counter].setSize(LOCATION_WIDTH, LOCATION_HEIGHT, LOCATION_X, LOCATION_Y);
            helper_counter++;


        }

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
