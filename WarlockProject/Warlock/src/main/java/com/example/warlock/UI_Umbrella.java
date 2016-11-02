package com.example.warlock;

import android.content.ClipData;
import android.content.Context;
import android.opengl.Matrix;

import org.w3c.dom.Text;

/**
 * Created by aaron on 10/30/16.
 */
public class UI_Umbrella {

    public UI_Graphics ui_graphics[] = new UI_Graphics[10];
    public UI_Graphics pause_graphics[] = new UI_Graphics[10];
    public Items items;
    public int INVENTORY_SIZE=10;
    public float item_x[] = new float[INVENTORY_SIZE];
    public float item_y[] = new float[INVENTORY_SIZE];

    public Context myContext;

    public UI_Umbrella(Context context){
        myContext=context;
        items=new Items(myContext);

        for (int i=0;i<10;i++){
            ui_graphics[i] = new UI_Graphics(myContext,i);
            pause_graphics[i]=new UI_Graphics(myContext,i+10);
        }

        for (int i=0;i<INVENTORY_SIZE;i++){
            item_x[i]=pause_graphics[0].PAUSE_MENU_ITEM_PORTRAIT_X-pause_graphics[0].PAUSE_MENU_ITEM_X_DELTA*(i%4);
            item_y[i]=pause_graphics[0].PAUSE_MENU_ITEM_PORTRAIT_Y-pause_graphics[0].PAUSE_MENU_ITEM_Y_DELTA*(i/4);
        }
    }

    public void update_ui_image(int k, int i, Person player){
        if (ui_graphics[k].images[i].trigger==0){
            return;
        }else if (ui_graphics[k].images[i].trigger==1){
            //Button press
            return;
        }else if (ui_graphics[k].images[i].trigger==2){
            //HP
            ui_graphics[k].images[i].width = player.health/450f;
            return;
        }
    }

    public void select_item(int i,Text_Collection pause_text_collection){
        items.selected_item.index=items.inventory[i].index;

        pause_text_collection.text[21].str=Float.toString(items.item_info[items.selected_item.index].attributes[0]);
        pause_text_collection.text[23].str=Float.toString(items.item_info[items.selected_item.index].attributes[1]);
        pause_text_collection.text[25].str=Float.toString(items.item_info[items.selected_item.index].attributes[2]);
        pause_text_collection.text[27].str=Float.toString(items.item_info[items.selected_item.index].attributes[3]);

    }

    public void equip_selected(){
        items.equip(items.selected_item.index);
    }

    public void draw_image(float[] S, float[] M, float[] Z, float x, float y, float width, float height, Image_Info img){
        Matrix.multiplyMM(S, 0, M, 0, Z, 0);
        Matrix.translateM(S, 0,x, y, 1f);
        Matrix.scaleM(S, 0, width, height,1f);
        ui_graphics[0].Draw2(S, img.returnTextureData());
    }



    public void draw_ui(int game_state, int pause_state, float[] S, float[] M, float[] Z, Person player, Text_Collection text_collection, Text_Collection pause_text_collection, Global_Assets assets){

        if (pause_state==0){

            for (int i=0;i<ui_graphics[game_state].assets_list.size();i++){
                //update_ui_image(game_state,i,player);
                draw_image(S,M,Z,ui_graphics[game_state].assets_list.get(i).x,ui_graphics[game_state].assets_list.get(i).y,ui_graphics[game_state].assets_list.get(i).width, ui_graphics[game_state].assets_list.get(i).height,assets.ui_assets.assets.get(ui_graphics[game_state].assets_list.get(i).index));
            }


/*            for (int i=0;i<ui_graphics[game_state].number_of_images;i++){

                update_ui_image(game_state,i,player);

                Matrix.multiplyMM(S, 0, M, 0, Z, 0);
                Matrix.translateM(S, 0, ui_graphics[game_state].images[i].x, ui_graphics[game_state].images[i].y, 1f);
                Matrix.scaleM(S, 0, ui_graphics[game_state].images[i].width,ui_graphics[game_state].images[i].height,1f);
                ui_graphics[game_state].Draw(S, false, i);

            }*/
            text_collection.draw_text(S,M,Z);

        }else{

            for (int i=0;i<pause_graphics[game_state].assets_list.size();i++){
                //update_ui_image(game_state,i,player);
                draw_image(S,M,Z,pause_graphics[game_state].assets_list.get(i).x,pause_graphics[game_state].assets_list.get(i).y,pause_graphics[game_state].assets_list.get(i).width, pause_graphics[game_state].assets_list.get(i).height,assets.ui_assets.assets.get(pause_graphics[game_state].assets_list.get(i).index));
            }

/*            for (int i=0;i<pause_graphics[pause_state].number_of_images;i++){
                Matrix.multiplyMM(S, 0, M, 0, Z, 0);
                Matrix.translateM(S, 0, pause_graphics[pause_state].images[i].x, pause_graphics[pause_state].images[i].y, 1f);
                Matrix.scaleM(S, 0, pause_graphics[pause_state].images[i].width,pause_graphics[pause_state].images[i].height,1f);
                pause_graphics[pause_state].Draw(S, false, i);
            }*/

            if (pause_graphics[pause_state].char_loadout==1){
                player.DrawSelf_fixed_location(S,M,Z,pause_graphics[0].PAUSE_MENU_PLAYER_LOADOUT_X,pause_graphics[0].PAUSE_MENU_PLAYER_LOADOUT_Y, assets);
            }
            if (pause_graphics[pause_state].num_show_items>0){
                for (int i=0;i<pause_graphics[pause_state].num_show_items;i++){
                    items.draw_portrait(S, M, Z, item_x[i], item_y[i],pause_graphics[0].PAUSE_MENU_ITEM_PORTRAIT_WIDTH,1f,items.item_info[items.inventory[i].index]);
                }
            }
            if (pause_graphics[pause_state].item_loadout){
                items.draw_portrait(S,M,Z,pause_graphics[0].PAUSE_MENU_ITEM_LOADOUT_X,pause_graphics[0].PAUSE_MENU_ITEM_LOADOUT_Y,pause_graphics[0].PAUSE_MENU_ITEM_LOADOUT_WIDTH,1f,items.item_info[items.selected_item.index]);
            }
            pause_text_collection.draw_text(S,M,Z);

        }
    }
}
