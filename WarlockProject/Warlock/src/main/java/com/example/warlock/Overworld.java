package com.example.warlock;

/**
 * Created by aaron on 9/27/16.
 */
public class Overworld {
    public Location location[] = new Location[50];

    public Overworld(){
        //right left up down
        location[0]= new Location(0,0,0,0,0);
        //TUTORIAL AREA 1
        location[1]= new Location(1,2,0,0,0);
        //TUTORIAL AREA 2
        location[2]= new Location(2,3,1,0,0);
        //TUTORIAL AREA 3
        location[3]= new Location(3,4,2,0,0);
        //TOWN 1
        location[4]= new Location(4,5,3,0,0);
        //ROUTE 1 - 1
        location[5]= new Location(5,6,4,0,0);
        //ROUTE 1 - 2
        location[6]= new Location(6,7,5,9,0);
        //TOWN 2 - 1
        location[7]= new Location(7,8,0,0,19);
        //TOWN 2 - 2
        location[8]= new Location(8,9,0,27,37);
        //ROUTE 3 - 1
        location[9]= new Location(9,0,0,10,6);
        //ROUTE 3 - 2
        location[10]= new Location(10,0,0,11,9);
        //FIRE TEMPLE 1 ENTRANCE
        location[11]= new Location(11,0,0,12,10);
        //FIRE TEMPLE 2 ENTRANCE
        location[12]= new Location(12,33,13,0,11);
        //ROUTE 4 - 1
        location[13]= new Location(13,12,0,14,0);
        //ROUTE 4 - 2
        location[14]= new Location(14,0,0,15,13);
        //TOWN 5 - 1
        location[15]= new Location(15,16,0,0,14);
        //ROUTE 5 - 1
        location[16]= new Location(16,17,15,0,0);
        //ROUTE 5 - 2
        location[17]= new Location(17,18,16,0,0);
        //TIME TEMPLE 1 ENTRANCE
        location[18]= new Location(18,0,17,0,0);
        //ROUTE 6 - 1
        location[19]= new Location(19,0,0,7,20);
        //ROUTE 6 - 2
        location[20]= new Location(20,0,0,19,21);
        //LIGHT TEMPLE 1 ENTRANCE
        location[21]= new Location(21,0,0,20,0);
        //LIGHT TEMPLE 2 ENTRANCE
        location[22]= new Location(22,24,23,0,47);
        //PORTAL
        location[23]= new Location(23,22,0,0,0);
        //ROUTE 7 - 1
        location[24]= new Location(24,22,0,0,25);
        //ROUTE 7 - 2
        location[25]= new Location(25,26,0,24,0);
        //NATURE TEMPLE 1 ENTRANCE
        location[26]= new Location(26,0,25,0,0);
        //ROUTE 8 - 1
        location[27]= new Location(27,28,0,0,8);
        //ROUTE 8 - 2
        location[28]= new Location(28,29,27,0,0);
        //DARK TEMPLE 1 ENTRANCE
        location[29]= new Location(29,0,28,0,0);
        //DARK TEMPLE 2 ENTRANCE
        location[30]= new Location(30,0,0,31,41);
        //TOWN 3 - 1
        location[31]= new Location(31,0,0,32,30);
        //TOWN 3 - 2
        location[32]= new Location(32,0,33,0,31);
        //ROUTE 9 - 1
        location[33]= new Location(33,32,12,0,0);
        //ROUTE 101
        location[34]= new Location(34,0,0,35,32);
        //ROUTE 102
        location[35]= new Location(35,0,0,36,34);
        //VAMPIRE TEMPLE 1 ENTRANCE
        location[36]= new Location(36,0,0,0,35);
        //ROUTE 111
        location[37]= new Location(37,38,0,8,0);
        //ROUTE 112
        location[38]= new Location(38,39,37,0,0);
        //WATER TEMPLE 1 ENTRANCE
        location[39]= new Location(39,0,38,0,0);
        //WATER TEMPLE 2 ENTRANCE
        location[40]= new Location(40,0,0,41,0);
        //TOWN 4
        location[41]= new Location(41,42,0,30,40);
        //ROUTE 121
        location[42]= new Location(42,43,41,0,0);
        //ROUTE 122
        location[43]= new Location(43,44,42,0,0);
        //WITCH TEMPLE 1
        location[44]= new Location(44,0,43,0,0);
        //ROUTE 131
        location[45]= new Location(45,23,46,0,0);
        //ARCANE TEMPLE 1 ENTRANCE
        location[46]= new Location(46,0,45,0,0);
        //ROUTE 141
        location[47]= new Location(47,48,0,22,0);
        //NECRO TEMPLE 1
        location[48]= new Location(48,49,47,0,0);
        //ROUTE 152
        location[49]= new Location(49,0,48,50,0);
        //ROUTE 151
        location[50]= new Location(50,0,0,51,49);
        //ROUTE 153
        location[51]= new Location(51,41,0,0,50);

























    }
}
