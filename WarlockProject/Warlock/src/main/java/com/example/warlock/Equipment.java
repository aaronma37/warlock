package com.example.warlock;

/**
 * Created by aaron on 9/6/16.
 */
public class Equipment {
    private static int NUMBER_OF_ATTRIBUTES=5;

    public float attributes[] = new float[NUMBER_OF_ATTRIBUTES];
    public int location;

    public Equipment(){
        for (int i=0;i<NUMBER_OF_ATTRIBUTES;i++){
            attributes[i]=0;
        }
        location=0;
    }
}
