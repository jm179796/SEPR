package com.mygdx.game;

import java.util.*;

public class Tile {

  public Integer TileID;

  private Integer EnergyCount;

  // private Integer FoodCount;

  private Integer OreCount;

  private Boolean Landmark;

  private Player Owner;

  private List<Roboticon> Roboticon_List = new ArrayList<Roboticon>();

  public Tile(int TileID, int EnergyCount, int FoodCount, int OreCount, boolean Landmark){
    this.TileID = TileID;
    this.EnergyCount = EnergyCount;
    //this.FoodCount = FoodCount;
    this.OreCount = OreCount;
      this.Landmark = Landmark;
  }

  public void varyResource(String Resource, int quantity) {
  }

  public void setOwner( Player Owner) {
      this.Owner = Owner;
  }

  public void assignRoboticon( Roboticon Roboticon) {
      Roboticon_List.add(Roboticon);
  }

  public void unassignRoboticon( Roboticon Roboticon) {
      Roboticon_List.remove(Roboticon);
  }

  public void isAdjacent( Tile Tile) {
  }

}
