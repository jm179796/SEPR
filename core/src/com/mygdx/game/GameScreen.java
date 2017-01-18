package com.mygdx.game;

/**
 * Created by Nico on 23/11/2016.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class GameScreen implements Screen{

    /**
     * Stores current game-state, enabling transitions between screens
     */
    private Game game;

    /**
     * Engine class that handles all of the game's logical processing
     */
    private GameEngine engine;

    /**
     * Establishes an on-screen stage which can be populated with actors
     */
    private Stage gameStage;

    /**
     * Establishes a secondary stage which will appear when the game is paused
     */
    private Stage pauseStage;

    /**
     * Font which will be adopted by the game-screen's main interface
     */
    private TTFont gameFont;

    /**
     * Holds the image of the in-game map
     */
    private Image map;

    /**
     * Establishes the grid of tiles to be laid over the map
     */
    private Table tileGrid;

    /**
     * Label identifying the current phase number
     */
    private Label phaseLabel;

    /**
     * Label counter providing a visual representation of the current player's food stocks
     */
    private Label foodCounter;

    /**
     * Label counter providing a visual representation of the current player's energy stocks
     */
    private Label energyCounter;

    /**
     * Label counter providing a visual representation of the current player's ore stocks
     */
    private Label oreCounter;

    /**
     * Label counter providing a visual representation of the current player's roboticon stocks
     */
    private Label roboticonCounter;

    /**
     * Label counter providing a visual representation of the current player's money
     */
    private Label moneyCounter;

    /**
     * Label stating the ID of the currently-selected tile
     */
    private Label selectedTileLabel;

    /**
     * Object defining QOL drawing functions for rectangles and on-screen tables
     * Used in this class to render tooltip regions
     */
    private Drawer drawer;

    /**
     * Button that, when clicked, ends the current turn for the current player prematurely
     */
    private TextButton endTurnButton;

    /**
     * Button which can be clicked on to pause the game
     */
    private TextButton pauseButton;

    /**
     * Button which allows players to claim selected tiles
     */
    private TextButton claimTileButton;

    /**
     * Button which allows players to deploy owned roboticons onto selected tiles
     */
    private TextButton deployRoboticonButton;

    /**
     * Establish visual parameters for in-game buttons
     */
    private TextButton.TextButtonStyle gameButtonStyle;

    /**
     * Icon representing the currently-active player's chosen college
     */
    private Image currentPlayerIcon;

    /**
     * Icon representing the player who owns the currently-selected tile
     */
    private Image selectedTileOwnerIcon;

    /**
     * The game-screen's initial constructor
     *
     * @param game Variable storing the game's state
     */
    public GameScreen(Game game) {
        this.game = game;
        //Import current game-state

        engine = new GameEngine(game, this);
        //Start game engine up
    }

    /**
     * Executes when the game-screen is loaded up, typically from the point of another screen
     * Serves as an extension of the screen's constructor that primarily constructs visual elements
     */
    @Override
    public void show() {
        drawer = new Drawer(game);

        gameStage = new Stage();
        Gdx.input.setInputProcessor(gameStage);
        //Prepare the local stage and set it up to accept inputs

        gameFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 36);
        //Set fonts for game interface

        gameButtonStyle = new TextButton.TextButtonStyle();
        gameButtonStyle.font = gameFont.font();
        gameButtonStyle.fontColor = Color.WHITE;
        gameButtonStyle.pressedOffsetX = 1;
        gameButtonStyle.pressedOffsetY = -1;
        //Set up visual parameters for buttons

        map = new Image(new Texture("image/TestMap.png"));
        map.setPosition((Gdx.graphics.getWidth() / 2) - (map.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (map.getHeight() / 2));
        gameStage.addActor(map);
        //Initialise and deploy map texture

        constructTileGrid();

        constructButtons();
        //Set up the buttons to be placed down onto the interface

        constructLeftTable();
        constructRightTable();
        //Construct and deploy side-hand tables

        constructPauseMenu();
        //Construct pause-menu (and hide it for the moment)

        //drawer.debug(gameStage);
        //Call this to draw temporary debug lines around all of the actors on the stage
    }

    /**
     * Renders all visual elements (set up in the [show()] subroutine and all of its subsiduaries) to the game's window
     * This is called to prepare each and every frame that the game deploys
     *
     * @param delta
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //OpenGL nonsense
        //First instruction sets background colour

        if (engine.state() == GameEngine.State.RUN) {
            drawRectangles();

            gameStage.act(delta);
            gameStage.draw();
            //Draw the stage onto the screen

            for (Tile tile : engine.tiles()) {
                tile.drawTooltip();
                tile.drawBorder();
            }
            //If any of the tiles' tooltips are deemed "active", render them to the screen too
        } else if (engine.state() == GameEngine.State.PAUSE) {
            drawer.filledRectangle(Color.WHITE, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            //If the game is paused, render a white background...

            pauseStage.act(delta);
            pauseStage.draw();
            //...followed by the menu itself
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    /**
     * Disposes of all visual data used to construct previous frames
     * This is called after each frame is rendered, and remains necessary to prevent memory leaks
     */
    @Override
    public void dispose() {
        gameFont.dispose();
        //Dispose of the core BitmapFont object within this TTFont object

        gameStage.dispose();
        //Dispose of the stage
    }

    /**
     * Set up the buttons to be placed onto the interface later
     */
    public void constructButtons() {
        /**
         * Button that, when clicked, ends the current turn for the current player prematurely
         */
        endTurnButton = new TextButton("END TURN", gameButtonStyle);
        endTurnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                engine.nextPhase();
            }
        });
        drawer.switchTextButton(endTurnButton, false, Color.GRAY);
        //Turn off the "END TURN" button right away to force players into selecting tiles

        /**
         * Button which can be clicked on to pause the game
         */
        pauseButton = new TextButton("Pause Game", gameButtonStyle);
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                engine.pauseGame();
            }
        });

        gameFont.setSize(30);
        gameButtonStyle.font = gameFont.font();

        /**
         * Button which allows players to claim selected tiles
         */
        claimTileButton = new TextButton("CLAIM", gameButtonStyle);
        claimTileButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               engine.claimTile();
            }
        });

        deployRoboticonButton = new TextButton("DEPLOY", gameButtonStyle);
    }

    /**
     * Set up the form and contents of the left-hand table so that they can be rendered later
     */
    public void constructLeftTable() {
        /**
         * Establishes the metadata for the interface's left-hand table
         */
        Table tableLeft = new Table();
        //Construct left-hand table

        tableLeft.setBounds(0, 0, 256, Gdx.graphics.getHeight());
        //Set boundaries of left-hand table

        tableLeft.center().top();
        //Shift the table towards the top of the screen

        tableLeft.add(engine.timer()).colspan(2);
        //Add the timer to the table

        gameFont.setSize(36);
        Table phaseTable = new Table();
        phaseLabel = new Label("PHASE 1", new Label.LabelStyle(gameFont.font(), Color.WHITE));
        phaseTable.add(phaseLabel).width(110);
        phaseTable.add(endTurnButton);
        drawer.addTableRow(tableLeft, phaseTable, 0, 0, 15, 0, 2);
        //Prepare and add the "End Phase" button to the table

        drawer.addTableRow(tableLeft, new Label("CURRENT PLAYER", new Label.LabelStyle(gameFont.font(), Color.BLACK)), 0, 0, 10, 0, 2);
        //Window-dressing: adds "CURRENT PLAYER" label

        gameFont.setSize(24);
        Table collegeInfo = new Table();
        currentPlayerIcon = engine.currentPlayer().getCollege().getLogo();
        drawer.addTableRow(collegeInfo, currentPlayerIcon, 64, 64);
        drawer.addTableRow(collegeInfo, new Label("COLLEGE", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
        drawer.addTableRow(tableLeft, collegeInfo, 5, 0, 0, 15);
        //Prepare icon region to show the icon of the college which is currently active

        Table resourceCounters = new Table();
        foodCounter = new Label(engine.currentPlayer().getFoodCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        energyCounter = new Label(engine.currentPlayer().getEnergyCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        oreCounter = new Label(engine.currentPlayer().getOreCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        roboticonCounter = new Label(engine.currentPlayer().getRoboticonCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        moneyCounter = new Label(engine.currentPlayer().getMoney().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        drawer.addTableRow(resourceCounters, new LabelledElement("Food", gameFont, Color.WHITE, foodCounter, 120, 40));
        drawer.addTableRow(resourceCounters, new LabelledElement("Energy", gameFont, Color.WHITE, energyCounter, 120, 40));
        drawer.addTableRow(resourceCounters, new LabelledElement("Ore", gameFont, Color.WHITE, oreCounter, 120, 40));
        drawer.addTableRow(resourceCounters, new LabelledElement("Roboticons", gameFont, Color.WHITE, roboticonCounter, 120, 40));
        drawer.addTableRow(resourceCounters, new LabelledElement("Money", gameFont, Color.WHITE, moneyCounter, 120, 40));
        tableLeft.add(resourceCounters).size(160, 120);
        //Add resource-counters to the table
        //These will show the current resource stocks for the current player

        drawer.addTableRow(tableLeft, pauseButton, 113, 0, 0, 0, 2);
        //Prepare and add the pause button to the bottom of the table

        gameStage.addActor(tableLeft);
        //Add left-hand table to the stage
    }

    /**
     * Set up the form and contents of the right-hand table so that they can be rendered later
     */
    public void constructRightTable() {
        /**
         * Establishes the metadata for the interface's right-hand table
         */
        Table tableRight = new Table();

        tableRight.setBounds((Gdx.graphics.getWidth() / 2) + (map.getWidth() / 2), 0, 256, Gdx.graphics.getHeight());
        //Set boundaries of right-hand table

        tableRight.center().top();
        //Shift the table towards the top of the screen

        gameFont.setSize(40);
        selectedTileLabel = new Label("NO TILE SELECTED", new Label.LabelStyle(gameFont.font(), Color.WHITE));
        selectedTileLabel.setAlignment(Align.center);
        drawer.addTableRow(tableRight, selectedTileLabel, 240, 43, 0, 0, 10, 0, 2);
        //Set up and deploy label to identify tiles when they're clicked on

        selectedTileOwnerIcon = new Image();
        selectedTileOwnerIcon.setVisible(false);
        drawer.addTableRow(tableRight, selectedTileOwnerIcon, 64, 64);
        tableRight.add(new Label("ROB", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
        //Instantiate and deploy icons to represent tiles' owners and Roboticons

        gameFont.setSize(20);
        drawer.addTableRow(tableRight, new Label("COLLEGE", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 0, 0, 10, 0);
        tableRight.add(new Label("ROBOTICON", new Label.LabelStyle(gameFont.font(), Color.WHITE))).padBottom(10);
        //Even more window-dressing

        drawer.switchTextButton(claimTileButton, false, Color.GRAY);
        drawer.switchTextButton(deployRoboticonButton, false, Color.GRAY);
        //Disable the claim and deploy button until a tile is selected under the appropriate conditions

        drawer.addTableRow(tableRight, claimTileButton, 0, 0, 15, 0);
        tableRight.add(deployRoboticonButton).padBottom(15);
        //Add tile claim/deploy buttons to interface

        drawer.addTableRow(tableRight, engine.market(), 2);
        //Establish market and add market interface to right-hand table

        gameStage.addActor(tableRight);
        //Add right-hand table to the stage
    }

    /**
     * Set up the game-screen's central tile-grid so that it can be interacted with
     */
    public void constructTileGrid(){
        tileGrid = new Table();
        //Initialise tile-grid

        tileGrid.setBounds((Gdx.graphics.getWidth() / 2) - (map.getWidth() / 2), 0, map.getWidth(), map.getHeight());
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                tileGrid.add(engine.tiles()[(y * 4) + x]).width(map.getWidth() / 4).height(map.getHeight() / 4);
            }
            tileGrid.row();
        }

        gameStage.addActor(tileGrid);
    }

    /**
     * Set up the pause menu to allow for the game to be successfully paused
     */
    public void constructPauseMenu() {

        /**
         * Establishes the visual framework for the pause screen
         */
        Table pauseTable = new Table();
        pauseStage = new Stage();
        //Establish stage and interface for pause menu

        TTFont titleFont = new TTFont(Gdx.files.internal("font/earthorbiterxtrabold.ttf"), 72);
        TTFont menuFont = new TTFont(Gdx.files.internal("font/enterthegrid.ttf"), 36);
        //Prepare fonts for the logo and the options on the pause menu

        pauseTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Centre the pause menu's contents

        drawer.addTableRow(pauseTable, new Label("Sabbaticoup", new Label.LabelStyle(titleFont.font(), Color.BLACK)), 0, 0, 30, 0);
        //Add the game's logo to the pause menu

        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = menuFont.font();
        menuButtonStyle.fontColor = Color.BLACK;
        menuButtonStyle.pressedOffsetX = 1;
        menuButtonStyle.pressedOffsetY = -1;
        //Establish the visual style of the pause menu's buttons

        /**
         * Button which allows players to resume the game from the pause menu
         */
        TextButton resume = new TextButton("Resume", menuButtonStyle);
        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                engine.resumeGame();
            }
        });
        //Establish and prepare a button to resume the game from the pause menu

        drawer.addTableRow(pauseTable, resume);
        //Add the resume button to the pause menu's interface

        pauseStage.addActor(pauseTable);
        //Prepare the pause menu's interface to be shown on the screen when the game is paused
    }

    /**
     * Draw auxiliary rectangles to provide window-dressing for the interface
     */
    public void drawRectangles() {
        drawer.lineRectangle(Color.WHITE, (int) map.getX(), (int) map.getY(), (int) map.getWidth() + 1, (int) map.getHeight(), 1);
        //Draw border around the map

        drawer.filledRectangle(Color.WHITE, 0, (int) engine.timer().getHeight(), 256, 1);
        drawer.filledRectangle(Color.WHITE, 0, (int) (engine.timer().getHeight() + endTurnButton.getHeight()), 256, 1);
        drawer.borderedRectangle(Color.GRAY, Color.WHITE, 19, (int) (engine.timer().getHeight() + endTurnButton.getHeight()) + 15, 219, 40, 1);
        //drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.125)) - 110, 240, 66, 66);
        drawer.filledRectangle(Color.WHITE, 0, Gdx.graphics.getHeight() - 46, 256, 1);
        //Draw lines and rectangles in left-hand table

        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.875)) - 94, 52, 66, 66, 1);
        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.875)) + 26, 52, 66, 66, 1);
        drawer.filledRectangle(Color.WHITE, Gdx.graphics.getWidth() - 256, 190, 256, 1);
        //Draw lines in right-hand table
    }

    public void setFoodCounterValue(int value) {
        foodCounter.setText((String.valueOf(value)));
    }

    public void setEnergyCounterValue(int value) {
        energyCounter.setText((String.valueOf(value)));
    }

    public void setOreCounterValue(int value) {
        oreCounter.setText((String.valueOf(value)));
    }

    public void setRoboticonCounterValue(int value) {
        roboticonCounter.setText((String.valueOf(value)));
    }

    public void setMoneyCounterValue(int value) {
        moneyCounter.setText((String.valueOf(value)));
    }

    public void updateSelectedTileLabel(int value) {
        if (value < 1 || value > 16) {
            selectedTileLabel.setText("NO TILE SELECTED");
        } else {
            selectedTileLabel.setText("TILE " + value);
        }
    }

    public void updateSelectedTileLabel(Tile tile) {
        selectedTileLabel.setText("TILE " + tile.ID());
    }

    public void updatePhaseLabel(int value) {
        phaseLabel.setText("PHASE " + value);
    }

    public TextButton claimTileButton() {
        return claimTileButton;
    }

    public TextButton endTurnButton() {
        return endTurnButton;
    }

    public Image currentPlayerIcon() {
        return currentPlayerIcon;
    }

    public Image selectedTileOwnerIcon() {
        return selectedTileOwnerIcon;
    }

    public void openGameStage() {
        Gdx.input.setInputProcessor(gameStage);
    }

    public void openPauseStage() {
        Gdx.input.setInputProcessor(pauseStage);
    }

    /**
     * The code to be run whenever a particular tile is clicked on
     *
     * @param tile The tile being clicked on
     */
    public void selectTile(Tile tile) {
        selectedTileLabel.setText("TILE " + tile.ID());

        if (engine.phase() == 1 && !tile.isOwned()) {
            drawer.switchTextButton(claimTileButton, true, Color.WHITE);
        } else if (engine.phase() == 3 && engine.currentPlayer().getRoboticonCount() > 0) {
            drawer.switchTextButton(deployRoboticonButton, true, Color.WHITE);
        }

        if (tile.isOwned()) {
            selectedTileOwnerIcon.setVisible(true);
            selectedTileOwnerIcon.setDrawable(new TextureRegionDrawable(new TextureRegion(tile.getOwner().getCollege().getLogoTexture())));
            selectedTileOwnerIcon.setSize(64, 64);
        } else {
            selectedTileOwnerIcon.setVisible(false);
        }
    }

    /**
     * Run this to deselect the currently selected tile
     */
    public void deselectTile() {
        drawer.switchTextButton(claimTileButton, false, Color.GRAY);
        drawer.switchTextButton(deployRoboticonButton, false, Color.GRAY);

        selectedTileOwnerIcon().setVisible(false);

        updateSelectedTileLabel(0);
    }
}