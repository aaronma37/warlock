package com.example.warlock;

import android.content.Context;

/**
 * Created by aaron on 11/1/16.
 */
public class Global_Assets {

    private int NUM_EQUIPMENT_BODY=5;
    private int NUM_EQUIPMENT_LEGS=1;
    private int NUM_EQUIPMENT_FACE=1;
    private int NUM_EQUIPMENT_HAIR=3;
    private int NUM_EQUIPMENT_EYES=1;

    private int NUM_SLOTS=5;

    public Context myContext;

    public Equipment_Assets_Body equipment_assets_body[] = new Equipment_Assets_Body[NUM_EQUIPMENT_BODY];
    public Equipment_Assets_Legs equipment_assets_legs[] = new Equipment_Assets_Legs[NUM_EQUIPMENT_LEGS];
    public Equipment_Assets_Face equipment_assets_Face[] = new Equipment_Assets_Face[NUM_EQUIPMENT_FACE];
    public Equipment_Assets_Hair equipment_assets_Hair[] = new Equipment_Assets_Hair[NUM_EQUIPMENT_HAIR];
    public Equipment_Assets_Eyes equipment_assets_Eyes[] = new Equipment_Assets_Eyes[NUM_EQUIPMENT_EYES];
    public UI_Assets ui_assets;
    public Equipment_Assets_Weapon weapon_assets;
    public Location_Assets location_assets;



    public Global_Assets(Context context){

        myContext=context;

        ui_assets= new UI_Assets(myContext);
        location_assets = new Location_Assets(myContext);
        weapon_assets = new Equipment_Assets_Weapon(myContext);

        for (int i =0 ;i<NUM_EQUIPMENT_BODY;i++){
            equipment_assets_body[i] = new Equipment_Assets_Body(myContext,i);
        }

        for (int i =0 ;i<NUM_EQUIPMENT_LEGS;i++){
            equipment_assets_legs[i] = new Equipment_Assets_Legs(myContext,i);
        }

        for (int i =0 ;i<NUM_EQUIPMENT_FACE;i++){
            equipment_assets_Face[i] = new Equipment_Assets_Face(myContext,i);
        }

        for (int i =0 ;i<NUM_EQUIPMENT_HAIR;i++){
            equipment_assets_Hair[i] = new Equipment_Assets_Hair(myContext,i,equipment_assets_Face[0].face.x_off,equipment_assets_Face[0].face.y_off);
        }

        for (int i =0 ;i<NUM_EQUIPMENT_EYES;i++){
            equipment_assets_Eyes[i] = new Equipment_Assets_Eyes(myContext,i);
        }
    }
}
