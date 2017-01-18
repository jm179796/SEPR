package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.Texture;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.awt.*;

public class College {

  /**
   * The numeric representation of the college
   *
   * DERWENT: 1
   * LANGWITH: 2
   * VANBURGH: 3
   * JAMES: 4
   * WENTWORTH: 5
   * HALIFAX: 6
   * ALCUIN: 7
   * GOODRICKE: 8
   * CONSTANTINE: 9
   */
  private int ID;

  /**
   * The name of the College.
   */
  private String Name;

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
   * The symbol of the college
   */
  private Image logo;

  /**
   * The constructor for the class.
   * @param ID The ID of the college.
   * @param Description The description of the college.
   */
  public College(int ID, String Description){
    if (ID < 1 || ID > 9) {
      throw new ValueException("Invalid College ID Given (Must be between 1 and 9)");
    }

    this.ID = ID;

    switch (this.ID) {
      case (1):
        this.Name = "Derwent";
        this.logo = new Image(new Texture("image/Derwent.png"));
        break;
      case (2):
        this.Name = "Langwith";
        this.logo = new Image(new Texture("image/Langwith.png"));
        break;
      case (3):
        this.Name = "Vanburgh";
        this.logo = new Image(new Texture("image/Vanburgh.png"));
        break;
      case (4):
        this.Name = "James";
        this.logo = new Image(new Texture("image/James.png"));
        break;
      case (5):
        this.Name = "Wentworth";
        this.logo = new Image(new Texture("image/Wentworth.png"));
        break;
      case (6):
        this.Name = "Halifax";
        this.logo = new Image(new Texture("image/Halifax.png"));
        break;
      case (7):
        this.Name = "Alcuin";
        this.logo = new Image(new Texture("image/Alcuin.png"));
        break;
      case (8):
        this.Name = "Goodricke";
        this.logo = new Image(new Texture("image/Goodricke.png"));
        break;
      case (9):
        this.Name = "Constantine";
        this.logo = new Image(new Texture("image/Constantine.png"));
        break;
    }

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

  public String getName() {
    return this.Name;
  }

  public int getID() {
    return this.ID;
  }

  public Image getLogo() {
    return this.logo;
  }

  public Texture getLogoTexture() {
    switch (this.ID) {
      case (1):
        return new Texture("image/Derwent.png");
      break;
      case (2):
        return new Texture("image/Langwith.png");
      break;
      case (3):
        return new Texture("image/Vanburgh.png");
      break;
      case (4):
        return new Texture("image/James.png");
      break;
      case (5):
        return new Texture("image/Wentworth.png");
      break;
      case (6):
        return new Texture("image/Halifax.png");
      break;
      case (7):
        return new Texture("image/Alcuin.png");
      break;
      case (8):
        return new Texture("image/Goodricke.png");
      break;
      case (9):
        return new Texture("image/Constantine.png");
      break;
      default:
        return new Texture();
        break;
    }

  }
}