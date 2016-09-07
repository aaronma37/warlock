package com.example.warlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 9/6/16.
 */
public class Wardrobe {

    private static int NUMBER_OF_EQUIPMENT=100;
    private static int NUMBER_OF_EQUIPPED=3;
    private static int NUMBER_OF_INVENTORY=50;

    public Equipment equipment[] = new Equipment[NUMBER_OF_EQUIPMENT];
    public Equipment currently_equipped[] = new Equipment[NUMBER_OF_EQUIPPED];
    public List<Equipment> inventory = new ArrayList<>();

    public Wardrobe(){

        for (int i =0;i<NUMBER_OF_EQUIPMENT;i++){
            equipment[i] = new Equipment();
        }
        for (int i =0;i<NUMBER_OF_EQUIPPED;i++){
            currently_equipped[i] = new Equipment();
            currently_equipped[i] = equipment[0];

        }


        //
        equipment[1].attributes[0]=1;
        equipment[1].attributes[1]=1;
        equipment[1].location=0;

        //
        //
        equipment[2].attributes[0]=1;
        equipment[2].attributes[1]=1;
        equipment[1].location=1;

        //
        //
        equipment[3].attributes[0]=1;
        equipment[3].attributes[1]=1;
        equipment[1].location=2;

        //
        //
        equipment[4].attributes[0]=1;
        equipment[4].attributes[1]=1;

        //
        //
        equipment[5].attributes[0]=1;
        equipment[5].attributes[1]=1;

        //
        //
        equipment[6].attributes[0]=1;
        equipment[6].attributes[1]=1;

        //


    }


    public void equip(Equipment eq){
            currently_equipped[eq.location]=eq;
    }

    public void add_to_inventory(Equipment eq){
        inventory.add(eq);
    }

    public void remove_from_inventory(Equipment eq){
        inventory.remove(eq);
    }

}
