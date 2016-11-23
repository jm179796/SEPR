package com.mygdx.game;

import java.util.*;

public class Player {
    /**
     * Unique numerical identifier of the player.
     */
    public Integer PlayerID;

    /**
     * An integer storing the amount of ore the player owns.
     */
    private Integer OreCount = 0;

    //private Integer FoodCount = 0;

    /**
     *An integer storing the amount of energy a player owns.
     */
    private Integer EnergyCount = 0;

    /**
     * An integer storing the amount of money a player owns.
     */
    private Integer Money = 0;

    /**
     * A variable determining if the player is currently active. True means that they are active.
     */
    private Boolean Active = false;

    /**
     * The college that the player is playing as.
     */
    private College College;

    /**
     * A list of the tiles that the player owns.
     */
    private List<Tile> TileList = new ArrayList<Tile>();

    /**
     * A list of the roboticons that the player owns.
     */
    private List<Roboticon> RoboticonList = new ArrayList<Roboticon>();

    /**
     * The constructor of the class
     *
     * @param PlayerID The id of the player that is being created.
     */
    public Player(Integer PlayerID) {
        this.PlayerID = PlayerID;
    }

    /**
     * Getter for the money attribute of the player
     * @return Money Intgere value of the current money of the player
     */
    public Integer getMoney(){
        return this.Money;
    }

    /**
     * Setter for the money attribute
     * @param NewMoney Integer value corresponding to the new money value desired
     */
    public void setMoney(Integer NewMoney){
        this.Money = NewMoney;
    }
    /**
     * Toggles the 'active' attribute of the player from True to False or False to True.
     */
    public void toggleActive() {
        if (this.Active == true) {
            this.Active = false;
        } else {
            this.Active = true;
        }
    }

    //add accessors
    /**
     * Changes the college of the player to the one that is specified.
     *
     * @param College The college that the player is being assigned to.
     */
    public void assignCollege(College College) {
        this.College = College;
    }

    /**
     * Adds a roboticon to the list that the player owns.
     *
     * @param Roboticon The roboticon that is to be added
     */
    public void addRoboticon(Roboticon Roboticon) {
        RoboticonList.add(Roboticon);
    }

    /**
     * Adds the specified tile to the players list of tiles.
     *
     * @param Tile The tile that is to be added to the player's tile list.
     */

    public void assignTile(Tile Tile) {
        TileList.add(Tile);
    }

    /**
     * Increases/decreases the money that the player has.
     *
     * @param cost The amount that the player's money is to change by. Negative value for a decrease, positive for an increase.
     */
    public void changeMoney(int cost){
      this.Money += cost;
    }

  //public void varyResource(string resource, int amount){
        //Player.resource += amount;
    //}

    /**
     * Calculates the score of the player based on the resources that they own.
     */
    public void calculateScore() {
    }

}
