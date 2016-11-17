package com.mygdx.game;
public class Roboticon {

  public Integer RoboticonID;

  private Integer OreLevel = 1;

  private Integer FoodLevel = 1;

  private Integer EnergyLevel = 1;

  private Player Owner;

  private Tile CurrentTile;

  public Roboticon(int ID){
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

}