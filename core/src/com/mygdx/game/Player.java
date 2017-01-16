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
    
    /**
     * An integer storing the amount of food a player owns.
     */
    private Integer FoodCount = 0;

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


    public int getPlayerID(){
        return PlayerID;
    }
    /**
     * Getter for the tile list of the Player
     * @return TileList A list of the tile objects that the player owns.
     */
    public List<Tile> getTileList(){
        return this.TileList;
    }
    /**
     * Getter for the money attribute of the player
     * @return Money Integer value of the current money of the player
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
     * Getter for OreCount
     * @return this.OreCount the orecount of the player as an integer
     */
    public Integer getOreCount(){
        return this.OreCount;
    }

    /**
     * Getter for EnergyCount
     * @return this.EnergyCount the Energycount of the player as an integer
     */
    public Integer getEnergyCount(){
        return this.EnergyCount;
    }

    /**
     * Getter for FoodCount
     * @return this.FoodCount the Foodcount of the player as an integer
     */
    public Integer getFoodCount(){
        return this.FoodCount;
    }

    /**
     * Setter for Foodcount
     * @param Newcount Integer value that the Foodcount is set to
     */
    public void setFoodCount(Integer Newcount){
        this.FoodCount = Newcount;
    }

    /**
     * Setter for Orecount
     * @param Newcount Integer value that the Orecount is set to
     */
    public void setOreCount(Integer Newcount){
        this.OreCount = Newcount;
    }

    /**
     * Setter for Energycount
     * @param Newcount Integer value that the Energycount is set to
     */
    public void setEnergyCount(Integer Newcount){
        this.EnergyCount = Newcount;
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
     * Increases/decreases the specified resource of the player by the specified amount
     *
     * @param resource The resource that is to be modified.
     * @param amount The amount that the player's resource is to change by. Negative value for a decrease, positive for an increase.
     */

    public void varyResource(String resource, int amount){
        if(resource == "Ore"){
            this.OreCount += amount;
        }
        else if(resource == "Energy"){
            this.EnergyCount += amount;
        }
        else if(resource == "Food"){
            this.FoodCount += amount;
        }

        else if(resource =="Money"){
            this.Money += amount;
        }
        else{
            //exception for unknown value entered
        }
    }

    /**
     * Calculates the score of the player based on the resources that they own.
     */
    public void calculateScore() {
    }

}
