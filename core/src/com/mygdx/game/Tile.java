package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

public class Tile extends Button {

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
     * Table holding the icons to be rendered in the tile's tooltip
     */
    private Table tooltipIcons;

    private Image tileOwnerIcon;

    private Image tileRoboticonIcon;

    /**
     * The constructor for the object
     *
     * @param game Variable storing the game's state
     * @param ID   The tile's distictive ID value
     * @param EnergyCount The multiplier for the production of energy
     * @param OreCount    The multiplier for the production of ore
     * @param landmark    A boolean to signify if the tile is to be a landmark or not
     * @param runnable    An object encapsulating a method that can be executed when the tile is clicked on
     */
    public Tile(Game game, int ID, int EnergyCount, int OreCount, int FoodCount, boolean landmark, final Runnable runnable) {
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

        tooltipActive = false;

        tooltipIcons = new Table();
        tileOwnerIcon = new Image();
        tileRoboticonIcon = new Image();
        drawer.addTableRow(tooltipIcons, tileOwnerIcon, 64, 60);
        tooltipIcons.add(tileRoboticonIcon).size(60, 60);

        tileBorderColor = Color.BLACK;
        tileBorderThickness = 3;

        this.EnergyCount = EnergyCount;
        this.FoodCount = FoodCount;
        this.OreCount = OreCount;

        this.landmark = landmark;

        this.runnable = runnable;
        this.Owner = new Player(0);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                runnable.run();
            }
        });

        addListener(new ClickListener() {
            Boolean mouseOver = false;

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
                mouseOver = true;

                Timer timer = new Timer();

                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        if (mouseOver == true) {
                            tooltipActive = true;
                        }
                    }
                }, (float) 0.5);

                timer.start();
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
                mouseOver = false;

                tooltipActive = false;
            }
        });
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

            Integer EnergyProduce = modifiers[1] * this.EnergyCount;
            player.varyResource("Energy", EnergyProduce);

            Integer FoodProduce = modifiers[2] * this.FoodCount;
            player.varyResource("Food", FoodProduce);
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
    }

    /**
     * Changes the owner of the tile to the one specified
     *
     * @param Owner The new owner.
     */
    public void setOwner(Player Owner) {
        this.Owner = Owner;

        tileOwnerIcon = Owner.getCollege().getLogo();
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
     * This must be called during the construction of each frame in which the tooltip is to be shown
     */
    public void drawTooltip() {
        if (tooltipActive == true) {
            if (Gdx.input.getY() < tooltipHeight) {
                drawer.borderedRectangle(tooltipFillColor, tooltipLineColor, Gdx.input.getX() - tooltipWidth - tooltipCursorSpace, Gdx.input.getY() + tooltipCursorSpace, tooltipWidth, tooltipHeight, 1);
                drawer.text("Tile " + this.ID, tooltipFont, Gdx.input.getX() - tooltipWidth - tooltipCursorSpace + tooltipTextSpace, Gdx.input.getY() + tooltipCursorSpace + tooltipTextSpace);
            } else {
                drawer.borderedRectangle(tooltipFillColor, tooltipLineColor, Gdx.input.getX() - tooltipWidth - tooltipCursorSpace, Gdx.input.getY() - tooltipHeight - tooltipCursorSpace, tooltipWidth, tooltipHeight, 1);
                drawer.text("Tile " + this.ID, tooltipFont, Gdx.input.getX() - tooltipWidth - tooltipCursorSpace + tooltipTextSpace, Gdx.input.getY() - tooltipHeight - tooltipCursorSpace + tooltipTextSpace);
            }
        }
    }

    public void drawBorder() {
        if (isOwned()) {
            drawer.lineRectangle(tileBorderColor, (128 * ((ID() - 1) % 4)) + 260, (128 * ((ID() - 1) / 4)) + 3, (int) (this.getWidth() - 5), (int) (this.getHeight() - 4), tileBorderThickness);
        }
    }

    /**
     * Returns the tile's associated ID value
     *
     * @return Integer The tile's associated ID value
     */
    public int ID() {
        return this.ID;
    }

    /**
     * Returns a boolean value that's true if/when the tile is owned by a player and false otherwise
     *
     * @return Boolean The tile's ownership state
     */
    public boolean isOwned() {
        if(Owner.getPlayerID() != 0 ){
            return true;
        }
        else {
            return false;
        }
    }

    public void setTileBorderColor(Color color) {
        tileBorderColor = color;
    }

    public Color tileBorderColor() {
        return tileBorderColor;
    }

    public boolean hasRoboticon(){
        if(this.roboticonStored != null){
            return true;
        }
        else{
            return false;
        }

    }

    public Roboticon getRoboticonStored(){
        return this.roboticonStored;
    }


}
