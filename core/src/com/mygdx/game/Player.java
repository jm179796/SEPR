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

  private List<Tile> TileList = new ArrayList<>();

  private List<Roboticon> RoboticonList = new ArrayList();

  public Player(Integer PlayerID){
      this.PlayerID = PlayerID;
  }

  public void toggleActive() {
      if(this.Active == true){
          this.Active = false;
      }
      else{
          this.Active = true;
      }
  }

  public void assignCollege( College College) {
    this.College = College;
  }

  public void addRoboticon( Roboticon Roboticon) {
      RoboticonList.add(Roboticon);
  }

  public void assignTile( Tile Tile) {
      TileList.add(Tile);
  }

  public void calculateScore() {
  }

}