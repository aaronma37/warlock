package com.example.warlock;

/**
 * Created by aaron on 8/15/16.
 */
public class action_space_action {

    public float o[] = new float[10];
    public boolean feasible = true;
    public int index;
    public int cool_down_timer=0;

    public action_space_action(int ind){
        index=ind;

        for (int i=0;i<10;i++){
            o[i]=1;
        }
    }

    public void reset(){
        cool_down_timer=0;
    }



}