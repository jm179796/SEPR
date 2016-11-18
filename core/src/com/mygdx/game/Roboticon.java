package com.mygdx.game;

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.util.List;
import java.util.*;

import java.sql.Array;

public class Roboticon {

    public Integer RoboticonID;

    private Integer MaxLevel = 3; //Variable to determine the maximum upgrade level of the robot

    private Integer Level[] = {0,0,0}; //Variable to hold the current levels of the robot, stored [Ore, Energy, Food]

    private Integer Upgrades[];
    // Upgrade array, returns the possible levels of upgrade for the current robot. Stored as [Ore, Energy, Food]

    public Player Owner;

    public Tile CurrentTile;

    public Roboticon(int ID) {
        RoboticonID = ID;
    }

    public void varyLevel() {
    }

    public void setOwner(Player Player) {
        this.Owner = Player;
    }

    public void setCurrentTile(Tile Tile) {
        this.CurrentTile = Tile;
    }

    public void possibleUpgrades() {
        if (Level[0] >= MaxLevel) {
            Upgrades[0] = Level[0] += 1;
        }
        if (Level[1] >= MaxLevel) {
            Upgrades[1] = Level[1] += 1;
        }
        //if (Level[2] >= MaxLevel) {
        //    Upgrades[2] = Level[2] += 1;
        //}
    }

    public void productionModifier() {
        Integer Modifiers[]; // Array to return the modifier for resource production, stored [Ore, Energy, Food]
        Integer Max = 50; Integer Min = 1;
        Random rand = new Random();
        int  n = rand.nextInt(50) + 1;

//50 is the maximum and the 1 is our minimum


    }
}