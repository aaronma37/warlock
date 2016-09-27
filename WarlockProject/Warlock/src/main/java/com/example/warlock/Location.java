package com.example.warlock;

/**
 * Created by aaron on 9/27/16.
 */
public class Location {

    public int location_index;
    public int right,left,up,down;

    public Location(int i_location_index, int i_right, int i_left, int i_up, int i_down){
        location_index=i_location_index;
        right=i_right;
        left=i_left;
        up=i_up;
        down=i_down;
    }
}
