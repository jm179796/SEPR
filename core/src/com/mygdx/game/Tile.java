package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.lang.*;

public class Tile extends Button {

  /**
   * The ID number that the tile is uniquely identified by
   */
  public int TileID;

  /**
   * A modifier influencing how much energy is produced.
   */
  private int EnergyCount;

  // private Integer FoodCount;
  /**
   * A modifier influencing how much ore is produced.
   */
  private int OreCount;
  
  /**
   * A modifier influencing how much ore is produced.
   */
  private Boolean Landmark;

  /**
   * The player that owns the tile, if it has one.
   */
  private Player Owner;

  /**
   * The x and y coordinates of the tile on the map.
   */
  private int[] Coordinates;

  /**
   * The roboticon that has been placed on the tile.
   */
  private Roboticon roboticonStored;
  
  /**
   * Object holding executable method that can be assigned to the tile
   */
  private Runnable runnable;

  /**
   * The constructor for the object
   * @param TileID The ID of the generated Tile.
   * @param EnergyCount The multiplier for the production of energy.
   * @param OreCount The multiplier for the production of ore.
   * @param Landmark A boolean to signify if the tile is to be a landmark or not.
   * @param runnable An object encapsulating a method that can be executed when the tile is clicked on
   */
  public Tile(int TileID, int EnergyCount, int OreCount, boolean Landmark, final Runnable runnable){
    super(new ButtonStyle());
	
	this.TileID = TileID;
    this.EnergyCount = EnergyCount;
    //this.FoodCount = FoodCount;
    this.OreCount = OreCount;
    this.Landmark = Landmark;
    this.Coordinates = Coordinates;
	this.runnable = runnable;
	
	addListener(new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            runnable.run();
        }
    });
  }

  /**
   * Getter for the coordinates of the tile.
   * @return The coordinates of the tile stored as an array.
   */
  public int[] getCoordinates(){
    return this.Coordinates;
  }

  /**
   * Calculates how many resources are produced based on the amount of roboticons present and adds them to the player.
   * @param Player The player that is producing the resources.
   * @return Player The player object after it's resource values have  been modified.
   */
  public Player Produce(Player Player){
	Integer[] modifiers = this.roboticonStored.productionModifier();
    Integer OreProduce = modifiers[0] * this.OreCount;
    Player.varyResource("Ore", OreProduce);
    this.OreCount -= OreProduce;
    Integer EnergyProduce = modifiers[1] * this.EnergyCount;
    Player.varyResource("Energy", EnergyProduce);
    this.EnergyCount -= EnergyProduce;
    return Player;
  }

  /**
   * Sets a certain resource count to the specified amount.
   * @param Resource The resource that is to be changed
   * @param quantity The amount that it is to be set to.
   */
  public void setResource(String Resource, int quantity) {
  }

  /**
   * Changes the owner of the tile to the one specified
   * @param Owner The new owner.
   */
  public void setOwner( Player Owner) {
      this.Owner = Owner;
  }

  /**
   * Setter for the ore count of the tile.
   * @param Count What the count is to be changed to.
   */
  public void changeOreCount(int Count){
    this.OreCount = Count;
  }

  /**
   * Setter for the ore count of the tile.
   * @param Count What the count is to be changed to.
   */
  public void changeEnergyCount(int Count){
    this.EnergyCount = Count;
  }
  /**
   * Adds a roboticon to the roboticon list.
   * @param Roboticon The roboticon to be added to the list.
   */
  public void assignRoboticon( Roboticon Roboticon) {
      roboticonStored = Roboticon;
  }

  /**
   * Removes the first instance of the roboticon from the list.
   * @param Roboticon The roboticon to be removed.
   */
  public void unassignRoboticon( Roboticon Roboticon) {
      roboticonStored = null;
  }

  /**
   * Checks if the inputted tile is adjacent to the tile by checking if either the x or the y coordinate has a distance greater than 1. If so then it is adjacent.
   * @param Tile The tile which is to be checked against.
   * @return adjacent A boolean signifying whether the tiles are adjacent or not.
   */
  public boolean isAdjacent( Tile Tile) {
    boolean adjacent = false;
    if (Tile.getCoordinates()[0] - this.getCoordinates()[0] <= 1 && Tile.getCoordinates()[0] - this.getCoordinates()[0] >= -1) {


      if (Tile.getCoordinates()[1] - this.getCoordinates()[1] <= 1 && Tile.getCoordinates()[1] - this.getCoordinates()[1] >= -1) {
        adjacent = true;
      }
    }

    return adjacent;
  }
  
  /**
   * Returns the tile's associated function
   */
  public Runnable getFunction() {
        return runnable;
    }

  /**
   * Runs the tile's associated function
   */
  public void runFunction() {
        runnable.run();
    }
}
