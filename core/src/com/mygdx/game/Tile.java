package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

/**
 * @author Duck Related Team Name in Big Massive Letters
 * @since Assessment 2
 * @version Assessment 2
 *
 * An executable version of the game can be found at: https://jm179796.github.io/SEPR/DRTN-Assessment2.jar
 * Our website is: https://jm179796.github.io/SEPR/
 */

public class Tile extends Button {

    /**
     * Defines width of the tile's tooltip
     */
    private final int tooltipWidth;
    /**
     * Defines height of the tile's tooltip
     */
    private final int tooltipHeight;
    /**
     * Defines distance (in pixels) from cursor point to the lower-right (or upper-right) corner of the tile's tooltip
     * (on both axes)
     */
    private final int tooltipCursorSpace;
    /**
     * Defines internal padding within the tile's tooltip (in pixels)
     */
    private final int tooltipTextSpace;
    /**
     * Defines the fill-colour for the tile's tooltip
     */
    private final Color tooltipFillColor;
    /**
     * Defines the line-colour for the tile's tooltip
     */
    private final Color tooltipLineColor;
    /**
     * Defines the font of the text inside the tile's tooltip
     */
    private final TTFont tooltipFont;
    /**
     * Holds game-state for the purpose of accessing the game's renderer
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
    private boolean landmark;
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
    /**
     * Object defining QOL drawing functions for rectangles and on-screen tables
     * Used in this class to render tooltip regions
     */
    private Drawer drawer;
    /**
     * Boolean variable that's true whenever the tile's tooltip is visible and false otherwise
     */
    private boolean tooltipActive;
    /**
     * Holds the colour of the border to be drawn over the tile when it is claimed
     */
    private Color tileBorderColor;
    /**
     * Determines the thickness of the tile's border (in pixels)
     */
    private int tileBorderThickness;

    /**
     * Construct's the tile's visual interface and logical underpinnings. Sets the sizes of the tile's associated
     * tooltip and border before setting its resource yields and implementing Listeners to detect when the tile is
     * clicked on (for individual tile selection) and hovered over (to determine when the tile's tooltip should be
     * drawn).
     *
     * @param game Variable storing the game's state
     * @param ID   The tile's distictive getID value
     * @param EnergyCount The multiplier for the production of energy
     * @param OreCount    The multiplier for the production of ore
     * @param FoodCount The multiplier for the production of food
     * @param landmark    A boolean to signify if the tile is to be a landmark or not
     * @param runnable    An object encapsulating a method that can be executed when the tile is clicked on
     */
    public Tile(Game game, int ID, int EnergyCount, int OreCount, int FoodCount, boolean landmark, final Runnable runnable) {
        super(new ButtonStyle());
        //Execute the constructor for the class' parent Button class using default visual parameters

        this.game = game;
        //Import and save the game's state

        this.drawer = new Drawer(this.game);
        //Use the game's state to establish a new Drawer class that can directly interface with (and modify) it

        this.ID = ID;
        //Import and save the tile's assigned getID

        tooltipWidth = 76;
        tooltipHeight = 33;
        tooltipCursorSpace = 3;
        tooltipTextSpace = 3;

        tooltipFillColor = Color.GRAY;
        tooltipLineColor = Color.BLACK;

        tooltipFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 36);
        //Visual parameters of the tile's tooltip

        tooltipActive = false;
        //Initialise boolean variable to track when the tile's tooltip is on-screen

        tileBorderColor = Color.BLACK;
        tileBorderThickness = 3;
        //Initialise the tile's border to default visual parameters

        this.EnergyCount = EnergyCount;
        this.FoodCount = FoodCount;
        this.OreCount = OreCount;
        //Import and save the tile's determined resource yields

        this.landmark = landmark;
        //Import and save the tile's landmark status

        this.runnable = runnable;
        this.Owner = new Player(0);
        //Establish the function that the tile should execute when interacted with
        //Currently, "interacting" with the tile means clicking on it

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                runnable.run();
            }
        });
        //Set up the tile to run the provided Runnable function when it's interacted with

        addListener(new ClickListener() {
            /**
             * Determines whether or not the cursor is hovering over the tile at the current time
             */
            Boolean mouseOver = false;
            //Initialise the tile's hover status

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
                mouseOver = true;
                //When a cursor hovers over the tile, register its presence by switching this boolean variable

                /**
                 * Temporary timer measuring the time since the cursor previously started hovering over the tile
                 */
                Timer timer = new Timer();
                //Establish a temporary timer to measure how long the mouse stays over the tile for

                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        if (mouseOver == true) {
                            tooltipActive = true;
                        }
                    }
                }, (float) 0.5);
                //If the cursor stays over the tile for one half of a second, allow for the tile's tooltip to be
                //drawn by the drawTooltip() function

                timer.start();
                //Start the tile's hovering timer as soon as the cursor enters its domain
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
                mouseOver = false;

                tooltipActive = false;
                //Once the cursor leaves the tile's domain, prevent the render from drawing its tooltip
            }
        });
        //Set up mouse-hovering detection over the tiles
        //This is used to trigger the appearance of tooltips after the cursor hovers over the tile for a short period
    }

    /**
     * Calculates how many resources are produced based on the amount of roboticons present and adds them to the player.
     *
     * @param player The player that is producing the resources.
     * @return Player The player object after it's resource values have been modified.
     */
    public Player Produce(Player player) {
        if (roboticonStored != null) {
            Integer[] modifiers = this.roboticonStored.productionModifier();
            Integer OreProduce = modifiers[0] * this.OreCount;
            player.varyResource("Ore", OreProduce);
            //Add the tile's ore yields to the player's resource-counters

            Integer EnergyProduce = modifiers[1] * this.EnergyCount;
            player.varyResource("Energy", EnergyProduce);
            //Add the tile's energy yields to the player's resource-counters

            Integer FoodProduce = modifiers[2] * this.FoodCount;
            player.varyResource("Food", FoodProduce);
            //Add the tile's food yields to the player's resource-counters
        }
        return player;
    }

    /**
     * Sets a certain resource count to the specified amount.
     *
     * @param Resource The resource that is to be changed
     * @param quantity The amount that it is to be set to.
     */
    public void setResource(String Resource, int quantity) {
        //Nothing here?
    }

    /**
     * Returns the class of the player who owns the tile
     *
     * @return Player The tile's owner
     */
    public Player getOwner() {
        return this.Owner;
    }

    /**
     * Changes the owner of the tile to the one specified
     *
     * @param Owner The new owner.
     */
    public void setOwner(Player Owner) {
        this.Owner = Owner;
    }

    /**
     * Setter for the ore count of the tile.
     *
     * @param Count What the count is to be changed to.
     */
    public void changeOreCount(int Count) {
        this.OreCount = Count;
    }

    /**
     * Setter for the ore count of the tile.
     *
     * @param Count What the count is to be changed to.
     */
    public void changeEnergyCount(int Count) {
        this.EnergyCount = Count;
    }

    /**
     * Adds a Roboticon to the roboticon list.
     * @param roboticon The roboticon to be added to the list.
     */
    public void assignRoboticon( Roboticon roboticon) {
        this.roboticonStored = roboticon;
    }

    /**
     * Removes the first instance of the roboticon from the list.
     *
     * @param Roboticon The roboticon to be removed.
     */
    public void unassignRoboticon(Roboticon Roboticon) {
        roboticonStored = null;
    }

    /**
     * Returns the tile's associated function
     *
     * @return Runnable The function that the tile executes when interacted with
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

    /**
     * Draws the tile's tooltip on the game's stage
     * Specifically draws the tooltip region in the space to the top- or bottom-left of the cursor's position
     * (depending on how high up the cursor is in the game's window) before drawing textual and visual contents inside
     * that region
     *
     * This must be called during the construction of each frame in which the tooltip is to be shown
     */
    public void drawTooltip() {
        if (tooltipActive == true) {
            if (Gdx.input.getY() < tooltipHeight) {
                drawer.borderedRectangle(tooltipFillColor, tooltipLineColor, Gdx.input.getX() - tooltipWidth - tooltipCursorSpace, Gdx.input.getY() + tooltipCursorSpace, tooltipWidth, tooltipHeight, 1);
                //Draw the tooltip's main space onto the screen in the region to the top-left of the cursor
                drawer.text("Tile " + this.ID, tooltipFont, Gdx.input.getX() - tooltipWidth - tooltipCursorSpace + tooltipTextSpace, Gdx.input.getY() + tooltipCursorSpace + tooltipTextSpace);
                //Draw an identification label in that space
            } else {
                drawer.borderedRectangle(tooltipFillColor, tooltipLineColor, Gdx.input.getX() - tooltipWidth - tooltipCursorSpace, Gdx.input.getY() - tooltipHeight - tooltipCursorSpace, tooltipWidth, tooltipHeight, 1);
                drawer.text("Tile " + this.ID, tooltipFont, Gdx.input.getX() - tooltipWidth - tooltipCursorSpace + tooltipTextSpace, Gdx.input.getY() - tooltipHeight - tooltipCursorSpace + tooltipTextSpace);
                //Do the same thing, but in the region to the bottom-left of the cursor if the cursor is near the
                //top of the game's window
            }
        }
    }

    /**
     * Draws the tile's coloured border on the game's stage
     * This must be called during the construction of each frame in which the border is to be shown
     * Note that the border must only be shown if the tile is owned by someone
     */
    public void drawBorder() {
        if (isOwned()) {
            drawer.lineRectangle(tileBorderColor, (128 * ((getID() - 1) % 4)) + 260, (128 * ((getID() - 1) / 4)) + 3, (int) (this.getWidth() - 5), (int) (this.getHeight() - 4), tileBorderThickness);
        }
    }

    /**
     * Returns the tile's associated getID value
     *
     * @return Integer The tile's associated getID value
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Returns a boolean value that's true if/when the tile is owned by a player and false otherwise
     *
     * @return Boolean The ownership status of the tile
     */
    public boolean isOwned() {
        return Owner.getPlayerID() != 0;
    }

    /**
     * Sets the colour of the tile's border
     * This must only be called if and when a player acquires the tile
     *
     * @param color The new colour of the tile's border
     */
    public void setTileBorderColor(Color color) {
        tileBorderColor = color;
    }

    /**
     * Returns the colour of the tile's border
     *
     * @return Color The current colour of the tile's border
     */
    public Color tileBorderColor() {
        return tileBorderColor;
    }

    /**
     * Returns a value that's true if a Roboticon is assigned to the tile, and false otherwise
     *
     * @return Boolean The presence of a Roboticon on the tile
     */
    public boolean hasRoboticon(){
        return this.roboticonStored != null;

    }

    /**
     * Retuns the Roboticon assigned to this tile
     *
     * @return Roboticon The Roboticon assigned to this tile
     */
    public Roboticon getRoboticonStored(){
        return this.roboticonStored;
    }


}
