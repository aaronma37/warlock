package com.example.warlock;

/**
 * Created by aaron on 10/30/16.
 */
public class NPC_Action {
    public boolean active=false;
    public int type,val1,val2,val3;
    public int state;
    public boolean init=false;

    public NPC_Action(boolean activate){
        active=activate;
    }


    public NPC_Action(boolean activate, int i_type, int v1, int v2){
        active=activate;
        type=i_type;
        val1=v1;
        val2=v2;
        state=v1;
    }


}
