package com.example.warlock;

import android.content.Context;

/**
 * Created by aaron on 9/27/16.
 */
public class Environment_Data {

    public Location_Data active_location;
    public Location current_location;
    public Context myContext;
    public Overworld overworld;

    public Environment_Data(Context context, int default_loc_index){
        myContext = context;

        overworld = new Overworld();
        current_location=overworld.location[default_loc_index];
        active_location = new Location_Data(myContext, overworld.location[default_loc_index]);

    }

    public void update_active_location(int arrow_push_index){
        if (arrow_push_index==0){
            if (current_location.right!=0){
                current_location=overworld.location[current_location.right];
                active_location = new Location_Data(myContext, current_location);
                return;
            }
        }
        if (arrow_push_index==1){
            if (current_location.right!=0){
                current_location=overworld.location[current_location.left];
                active_location = new Location_Data(myContext, current_location);
                return;
            }
        }
        if (arrow_push_index==2){
            if (current_location.right!=0){
                current_location=overworld.location[current_location.up];
                active_location = new Location_Data(myContext, current_location);
                return;
            }
        }
        if (arrow_push_index==3){
            if (current_location.right!=0){
                current_location=overworld.location[current_location.down];
                active_location = new Location_Data(myContext, current_location);
                return;
            }
        }
    }

}
