package com.mygdx.game;
public class College {

  /**
   * The name of the College.
   */
  public String Name;

  /**
   * The custom name that a player can choose for the college.
   */
  private String CustomName;

  /**
   * The effect(bonus) that the college uniquely has.
   */
  //private Effect Effect;
  /**
   * The player is playing as the college.
   */

  private Player Owner;

  /**
   * The description of the College.
   */
  private String Description;



  /**
   * The constructor for the class.
   * @param Name The name of the college.
   * @param Description The description of the college.
   */
  public College(String Name, String Description){
    this.Name = Name;
    this.Description = Description;

  }

  /**
   * Setter for the custom name.
   * @param Name The name that the custom name is to be changed to.
   */
  public void changeCustomName(String Name) {
    this.CustomName = Name;
  }

  /**
   * Assigns a player to the college.
   * @param Player The player that has chosen the college.
   */
  public void assignPlayer( Player Player) {
      this.Owner = Player;
  }

}