package com.mygdx.game;

import java.util.*;

public class Tile {

  /**
   * The ID number that the tile is uniquely identified by
   */
  public Integer TileID;

  /**
   * An integer storing the amount of Energy resources available on the tile.
   */
  private Integer EnergyCount;

  // private Integer FoodCount;
  /**
   * An integer storing the amount of Ore resources available on the tile
   */
  private Integer OreCount;
  
  /**
   * A modifier influincing how much energy is produced.
   */
  private Integer EnergyModifier;
  
  /**
   * A modifier influincing how much ore is produced.
   */
  private Integer OreModifier;

  /**
   * A boolean signifying whether the tile contains a landmark or not
   */
  private Boolean Landmark;

  /**
   * The player that owns the tile, if it has one.
   */
  private Player Owner;

  /**
   * A list of the roboticons tht have been placed on the tile.
   */
  private List<Roboticon> Roboticon_List = new ArrayList<Roboticon>();

  /**
   * The constructor for the object
   * @param TileID The ID of the generated Tile.
   * @param EnergyCount The amount of energy resources that the tile initially stores.
   * @param OreCount The amount of ore resources that the tile initially stores.
   * @param Landmark A boolean to signify if the tile is to be a landmark or not.
   * @param EnergyModifier The initial energy modifier of the tile.
   * @param OreModifier  The initial ore modifier of the tile.
   */
  public Tile(int TileID, int EnergyCount, int OreCount, int EnergyModifier, int OreModifier, boolean Landmark){
    this.TileID = TileID;
    this.EnergyCount = EnergyCount;
    //this.FoodCount = FoodCount;
    this.EnergyModifier = EnergyModifier;
    this.OreModifier = OreModifier
    this.OreCount = OreCount;
    this.Landmark = Landmark;
  }

  /**
   * Calculates how many resources are produced based on the amount of roboticons present and adds them to the player.
   * @param Player The player that is producing the resources.
   * @return Player The player object after it's resource values have  been modified.
   */
  public Player Produce(Player Player){
    if(Roboticon_List.size() > 0) {
      for (Roboticon Roboticon : Roboticon_List) {
        Integer[] modifiers = Roboticon.productionModifier();
        OreProduce = modifiers[0] * this.OreModifier;
        Player.varyResource("Ore", OreProduce);
        this.OreCount -= OreProduce;
        EnergyProduce = modifiers[1] * this.EnergyModifier;
        Player.varyResource("Energy", EnergyProduce);
        this.EnergyCount -= EnergyProduce;
      }
    }
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
   * Adds a roboticon to the roboticon list.
   * @param Roboticon The roboticon to be added to the list.
   */
  public void assignRoboticon( Roboticon Roboticon) {
      Roboticon_List.add(Roboticon);
  }

  /**
   * Removes the first instance of the roboticon from the list.
   * @param Roboticon The roboticon to be removed.
   */
  public void unassignRoboticon( Roboticon Roboticon) {
      Roboticon_List.remove(Roboticon);
  }

  /**
   * Checks if the inputted tile is adjacent to the tile.
   * @param Tile The tile which is to be checked against.
   */
  public void isAdjacent( Tile Tile) {
  }

}
