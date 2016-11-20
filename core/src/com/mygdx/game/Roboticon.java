package com.mygdx.game;

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
     * Constructor of the class
     *
     * @param ID     An integer uniquely defining the roboticon, starting at 0
     * @param Player A Player object to own the roboticon
     * @param Tile   A Tile object the roboticon is positioned on and therefore belongs to
     */
    public void Roboticon(int ID, Player Player, Tile Tile) {
        RoboticonID = ID;
        this.CurrentTile = Tile;
        this.Owner = Player;
    }

    /**
     * Method to upgrade a single level of the roboticon.
     * <p>
     * The parameter 'Resource' specifies 'Ore', 'Energy' or 'Food' to be upgraded one level.
     * </p>
     *
     * @param Resource
     */
    public void upgrade(String Resource) throws InvalidStringArgumentException {

        if (Resource == "Ore") {
            this.Level[0] += 1;

        } else if (Resource == "Energy") {
            this.Level[1] += 1;

        } else if (Resource == "Food") {
            this.Level[2] += 1;

        } else {
            throw new InvalidStringArgumentException(Resource);
        }

    }

    /**
     * A method to return an array of all possible upgrades available to the roboticon at its current state
     *
     * @return Upgrades
     */
    public Integer[] possibleUpgrades() {
        if (Level[0] >= MaxLevel) {
            Upgrades[0] = Level[0] += 1;
        }
        if (Level[1] >= MaxLevel) {
            Upgrades[1] = Level[1] += 1;
        }
        //if (Level[2] >= MaxLevel) {
        //    Upgrades[2] = Level[2] += 1;
        //}

        return Upgrades;
    }

    /**
     * A method to return the production modifier offered by the roboticon.
     * <p>
     * Contains inherent randomness, not just a 1:1 ratio of level to return each phase of production. The modifier is used outside of
     * this class to multiply the inherent resources located on that tile.
     * </p>
     */
    public Integer[] productionModifier() {
        Integer Modifiers[] = {1}; // Array to return the modifier for resource production, stored [Ore, Energy, Food]
        Integer Max = 50;
        Integer Min = 1;
        Random rand = new Random();
        int n = rand.nextInt(50) + 1;

        return Modifiers;


    }
}