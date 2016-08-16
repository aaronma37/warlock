package com.example.warlock;

/**
 * Created by aaron on 8/15/16.
 */
public class RlData {
    public MainActivity.Meta_Offense_Physical offense_physical = new MainActivity.Meta_Offense_Physical("Offense Physical");
    public MainActivity.Meta_Defense_Physical defense_physical  = new MainActivity.Meta_Defense_Physical("Defense Physical");
    public MainActivity.Meta_Support_Physical support_physical  = new MainActivity.Meta_Support_Physical("Support Physical");
    public MainActivity.Meta_Offense_Cast offense_cast = new MainActivity.Meta_Offense_Cast("Offense Cast");
    public MainActivity.Meta_Defense_Cast defense_cast = new MainActivity.Meta_Defense_Cast("Defense Cast");
    public MainActivity.Meta_Support_Cast support_cast = new MainActivity.Meta_Support_Cast("Support Cast");
    public MainActivity.Meta_Decision meta_decision = new MainActivity.Meta_Decision("Meta_choices");

    public RlData(){
    }


}

