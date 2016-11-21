package com.mygdx.game;
public class College {

  public String Name;

  private String CustomName;

  private Effect Effect;

  private Player Owner;

  public void changeCustomName(String Name) {
    this.CustomName = Name;
  }

  public void assignPlayer( Player Player) {
      this.Owner = Player;
  }



}