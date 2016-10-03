package com.example.warlock;

import android.content.Context;

/**
 * Created by aaron on 9/27/16.
 */
public class Environment_Data {

    private int NUM_LOCATIONS=5;

   //public Location_Data active_location;
    public Location current_location;
    public Context myContext;
    public Overworld overworld;


    public Location_Data all_locations[] = new Location_Data[NUM_LOCATIONS];


    public Environment_Data(Context context, int default_loc_index){
        myContext = context;

        overworld = new Overworld();
        current_location=overworld.location[default_loc_index];
        //active_location = new Location_Data(myContext, overworld.location[default_loc_index]);

        for (int i=0;i<NUM_LOCATIONS;i++){
            all_locations[i]=new Location_Data(myContext, overworld.location[i]);
        }


    }

    public int update_active_location(int arrow_push_index){


        if (current_location.neighbor_index[arrow_push_index]!=0){
            System.out.println("Call for: " + current_location.neighbor_index[arrow_push_index]);

            current_location=overworld.location[current_location.neighbor_index[arrow_push_index]];
            System.out.println("moving to: " + current_location.location_index);

            //active_location = new Location_Data(myContext, current_location);

            return current_location.location_index;
        }

        return 0;

    }

}
