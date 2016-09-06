package com.example.warlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 8/23/16.
 */
public class Spirit {

    private final int META_SIZE=3;
    private final int ACTION_SPACE_SIZE=15;
    private final int SUPPORT_SPACE_SIZE=15;
    private final int DEFENSE_SPACE_SIZE=15;

    private final int OBSERVATION_META_SIZE=15;
    private final int OBSERVATION_ACTION_SIZE=15;
    private final int OBSERVATION_SUPPORT_SIZE=15;
    private final int OBSERVATION_DEFENSE_SIZE=15;

    public List<Integer> available_offensive_action_space = new ArrayList<>();
    public List<Integer> available_support_action_space = new ArrayList<>();
    public List<Integer> available_defensive_action_space = new ArrayList<>();

    public action_space_action meta_a[] = new action_space_action[META_SIZE];

    public action_space_action off_a[] = new action_space_action[ACTION_SPACE_SIZE];
    public action_space_action sup_a[] = new action_space_action[ACTION_SPACE_SIZE];
    public action_space_action def_a[] = new action_space_action[ACTION_SPACE_SIZE];

    public int chosen_move[]= new int[4];
    public float action_choose_index[]= new float[ACTION_SPACE_SIZE];
    public Offensive_Physical_Actions action = new Offensive_Physical_Actions();
    public int attribute=0;
    public int identity;


    public int max_sum=0, temp_sum=0;

    public Spirit(int init_identity){
        this.identity=init_identity;
        for (int k=0;k<META_SIZE;k++){meta_a[k]=new action_space_action(k);}

        for (int k=0;k<ACTION_SPACE_SIZE;k++){
            off_a[k]=new action_space_action(k);
            sup_a[k]=new action_space_action(k);
            def_a[k]=new action_space_action(k);
        }

    }


    public int[] choose(int meta_o[], int target_o[], int off_o[], Person target, Person origin){
        //choose meta
        chosen_move[3]=this.identity;

        reset_action_choose_index();
        int meta_decision=0;
        int action_decision=0;

        max_sum=0;
        for (int i=0; i< META_SIZE; i++){
            temp_sum=0;
            for (int j=0;j<10;j++){
                temp_sum+=meta_a[i].o[j]*meta_o[j];
            }
            if (temp_sum>max_sum){
                max_sum=temp_sum;
                meta_decision=i;
            }
        }
        chosen_move[0]=meta_decision;

        if (meta_decision == 0){
            max_sum=0;
            for (int i =0;i<ACTION_SPACE_SIZE;i++){
                //if (checkFeasibility(off_a[i].index, target, available_offensive_action_space)){
                temp_sum=0;
                if (checkFeasibility(0,i,target, origin,available_offensive_action_space)){

                    for (int j=0;j<15;j++){
                        if (off_a[i].o[j]/off_a[i].c[j]>5){
                            temp_sum+=off_a[i].o[j]*off_o[j];
                        }{
                            temp_sum+=1;
                        }
                    }
                    //System.out.println(this.name+"casts attack: " + action_decision + "score: " + temp_sum);


                    if (temp_sum>max_sum){
                        max_sum=temp_sum;
                        action_decision=i;
                    }

                }
                if(i==0){
                    action_choose_index[i]=temp_sum;
                }else{
                    action_choose_index[i]=action_choose_index[i-1]+temp_sum;
                }
            }

            action_decision=(int)Math.floor(Math.random()*action_choose_index[ACTION_SPACE_SIZE-1]);
            for(int i=0;i<ACTION_SPACE_SIZE;i++){
                if (action_decision<action_choose_index[i]){
                    action_decision=i;
                    break;
                }
            }
            System.out.println(origin.name+"casts attack: " + action_decision + "score: " + action_choose_index[ACTION_SPACE_SIZE-1]);

        }else if (meta_decision==1){

        }
        chosen_move[1]=0;
        chosen_move[2]=action_decision;

        return chosen_move;
    }

    public void reward_spirit(int reward_type, int reward_spell[]){
        System.out.println("reward sent: " + reward_type);
        if (reward_type>0){
            for(int i=0;i<OBSERVATION_ACTION_SIZE;i++){
                if (off_a[reward_spell[2]].o[i]<100){
                    off_a[reward_spell[2]].o[i]+=1;
                }

                if (off_a[reward_spell[2]].c[i]<2){
                    off_a[reward_spell[2]].c[i]=off_a[reward_spell[2]].c[i]/2;
                }else{
                    off_a[reward_spell[2]].c[i]-=1;
                }
            }
        }else{
            for(int i=0;i<OBSERVATION_ACTION_SIZE;i++){
                if (off_a[reward_spell[2]].c[i]<100){
                    off_a[reward_spell[2]].c[i]+=1;
                }

                if (off_a[reward_spell[2]].o[i]<2){
                    off_a[reward_spell[2]].o[i]=off_a[reward_spell[2]].c[i]/2;
                }else{
                    off_a[reward_spell[2]].o[i]-=1;
                }
            }
        }
    }

    private void reset_action_choose_index(){
        for (int i=0;i<ACTION_SPACE_SIZE;i++){
            action_choose_index[i]=0;
        }
        for (int i=0;i<3;i++){
            chosen_move[i]=0;
        }
    }

    public void setAvailableOffensiveActionSpace(){
        available_offensive_action_space.clear();
        for (int i=0;i<ACTION_SPACE_SIZE;i++){
            if(checkIndex(i,0,attribute)){
                available_offensive_action_space.add(i);
            }
        }
    }

    public boolean checkFeasibility(int meta_type, int spell_type, Person target, Person origin, List<Integer> action_list){

        if (!action_list.contains(spell_type)){
            return false;
        }

        if (!action.returnFeasibility(origin,target,meta_type,spell_type)){
            return false;
        }

        return true;
    }

    public boolean checkIndex(int spell_type, int meta_type, int att){

        if (spell_type==1){
            if (att>=spell_type){return true;}
        }else if(spell_type==2){
            if (att>=spell_type){return true;}

        }else if(spell_type==3){
            if (att>=spell_type){return true;}

        }else if(spell_type==4){
            if (att>=spell_type){return true;}

        }else if(spell_type==5){
            if (att>=spell_type){return true;}

        }else if(spell_type==6){
            if (att>=spell_type){return true;}

        }else if(spell_type==7){
            if (att>=spell_type){return true;}

        }else if(spell_type==8){
            if (att>=spell_type){return true;}

        }else if(spell_type==9){
            if (att>=spell_type){return true;}

        }else if(spell_type==10){
            if (att>=spell_type){return true;}

        }else if(spell_type==11){
            if (att>=spell_type%10){return true;}

        }else if(spell_type==12){
            if (att>=spell_type%10){return true;}

        }else if(spell_type==13){
            if (att>=spell_type%10){return true;}

        }else if(spell_type==14){
            if (att>=spell_type%10){return true;}

        }else if(spell_type==15){
            if (att>=spell_type%10){return true;}

        }
        return false;
    }
}
