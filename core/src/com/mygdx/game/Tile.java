package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Tile extends Button {

  private final int tooltipWidth;
  private final int tooltipHeight;
  private final int tooltipCursorSpace;
  private final int tooltipTextSpace;
  private final Color tooltipFillColor;
  private final Color tooltipLineColor;
  private final TTFont tooltipFont;
  /**
     * Holds game-state
     */
  private Game game;
  /**
     * Uniquely identifies the tile
     */
    private int ID;
  /**
   * A modifier influencing how much energy is produced.
   */
  private int EnergyCount;
  /**
   * A modifier influencing how much food is produced.
   */
  private Integer FoodCount;
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
   * The roboticon that has been placed on the tile.
   */
  private Roboticon roboticonStored;
  /**
   * Object holding executable method that can be assigned to the tile
   */
  private Runnable runnable;
  private Drawer drawer;
  private boolean mouseOver;
  private boolean acquire = false;
  private int xCor;
  private int yCor;
  /**
   * The constructor for the object
   //* @param TileID The ID of the generated Tile.
   * @param EnergyCount The multiplier for the production of energy.
   * @param OreCount The multiplier for the production of ore.
   * @param Landmark A boolean to signify if the tile is to be a landmark or not.
   * @param runnable An object encapsulating a method that can be executed when the tile is clicked on
   */
  public Tile(Game game, int ID, int xCor, int yCor, int EnergyCount, int OreCount, int FoodCount, boolean Landmark, final Runnable runnable){
    super(new ButtonStyle());

    this.game = game;

    this.drawer = new Drawer(this.game);

      this.ID = ID;

    tooltipWidth = 100;
    tooltipHeight = 50;
    tooltipCursorSpace = 3;
      tooltipTextSpace = 3;

    tooltipFillColor = Color.GRAY;
    tooltipLineColor = Color.BLACK;

    tooltipFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 24);

    mouseOver = false;
    this.xCor = xCor;
    this.yCor = yCor;
    this.EnergyCount = EnergyCount;
    this.FoodCount = FoodCount;
    this.OreCount = OreCount;
    this.Landmark = Landmark;

	this.runnable = runnable;

	addListener(new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            runnable.run();
        }
    });

    addListener(new ClickListener() {
        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
          mouseOver = true;
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
          mouseOver = false;
        }
    });
  }

  /**
   * Calculates how many resources are produced based on the amount of roboticons present and adds them to the player.
   * @param Player The player that is producing the resources.
   * @return Player The player object after it's resource values have  been modified.
   */
  public Player Produce(Player Player) {
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
  public void toggleAcquire(){
    if(this.acquire){
      this.acquire = false;

    }
    else{
      this.acquire = true;
    }
  }

    public void drawTooltip() {
      if (mouseOver) {
        drawer.borderedRectangle(tooltipFillColor, tooltipLineColor, Gdx.input.getX() - tooltipWidth - tooltipCursorSpace, Gdx.input.getY() - tooltipHeight - tooltipCursorSpace, tooltipWidth, tooltipHeight);
        drawer.text("Tile " + this.ID, tooltipFont, Gdx.input.getX() - tooltipWidth - tooltipCursorSpace + tooltipTextSpace, Gdx.input.getY() - tooltipHeight - tooltipCursorSpace + tooltipTextSpace);
      }
    }
  public void confirmAcquire() {
    if (acquire) {
      drawer.filledRectangle(Color.BLACK, xCor, yCor, 128, 128);
      drawer.text("Acquire this tile?", tooltipFont, xCor, yCor + 64);
    }
  }
    public int ID() {
        return this.ID;
    }
}
