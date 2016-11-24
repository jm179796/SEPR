<<<<<<< HEAD
package com.mygdx.game;

import java.util.*;

public class Player {

    public Integer PlayerID;

    private Integer OreCount = 0;

    private Integer FoodCount = 0;

    private Integer EnergyCount = 0;

    private Integer Money = 0;

    private Boolean Active;

    private College College;

    private List<Tile> TileList = new ArrayList<Tile>();

    private List<Roboticon> RoboticonList = new ArrayList<Roboticon>();

    public Player(Integer PlayerID) {
        this.PlayerID = PlayerID;
    }

    public void toggleActive() {
        if (this.Active == true) {
            this.Active = false;
        } else {
            this.Active = true;
        }
    }

    public void assignCollege(College College) {
        this.College = College;
    }

    public void addRoboticon(Roboticon Roboticon) {
        RoboticonList.add(Roboticon);
    }

    public void assignTile(Tile Tile) {
        TileList.add(Tile);
    }

  public void calculateScore() {
  }
  public void changeMoney(int cost){
      this.Money += cost;
  }
  //public void varyResource(string resource, int amount){
      //Player.resource += amount;
  //}


}
=======
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
>>>>>>> refs/remotes/origin/master
