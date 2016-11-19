package com.mygdx.game;

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.util.List;
import java.util.*;

/**
 * @author Kieran Hall KJH532
 * @version 1.0
 * @since 1.0
 */
public class Roboticon {
    /**
     * Unique numerical identifier of the roboticon.
     */
    public Integer RoboticonID;
    /**
     * Variable holding which player the roboticon belongs to.
     */
    public Player Owner;
    /**
     * Variable holding what tile the roboticon is stored on.
     */
    public Tile CurrentTile;
    /**
     * Integer variable determining the maximum level of roboticons allowed in the game.
     */
    private Integer MaxLevel = 3;
    /**
     * Array of integers holding the current level of the roboticon, stored as: [Ore, Energy, Food]
     */
    private Integer Level[] = {0, 0, 0};
    /**
     * Upgrade array, holds the possible levels of upgrade for the current robot. Stored as [Ore, Energy, Food]
     */
    private Integer Upgrades[];

    /**
     * Method to set the RoboticonID -- SHOULD BE DONE ON INSTANTIATION
     *
     * @param ID
     */
    public void setID(int ID) {
        RoboticonID = ID;
    }

    /**
     * Method to upgrade a single level of the roboticon.
     * <p>
     * The parameter 'Resource' specifies 'Ore', 'Energy' or 'Food' to be upgraded one level.
     * </p>
     *
     * @param Resource
     */
    public void upgrade(char Resource) {
    }

    /**
     * Method to set the Player the roboticon belongs to -- SHOULD BE DONE ON INSTANCIATION
     *
     * @param Player
     */
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