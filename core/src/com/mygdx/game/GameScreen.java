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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

public class GameScreen implements Screen{

    /**
     * Stores current game-state, enabling transitions between screens
     */
    private Game game;

    /**
     * Variable dictating whether the game is running or paused
     */
    private State state;

    /**
     * Establishes an on-screen stage which can be populated with actors
     */
    private Stage stage;

    /**
     * Establishes the metadata for the interface's left-hand table
     */
    private Table tableLeft;

    /**
     * Establishes the metadata for the interface's right-hand table
     */
    private Table tableRight;

    /**
     * Establishes a secondary stage which will appear when the game is paused
     */
    private Stage pauseStage;

    /**
     * Establishes the visual framework for the pause screen
     */
    private Table pauseTable;

    /**
     * Font which will be adopted by the game-screen's main interface
     */
    private TTFont gameFont;

    /**
     * Font which will be adopted on the pause menu
     */
    private TTFont menuFont;

    /**
     * Font which will be adopted for the logo on the game's pause menu
     */
    private TTFont titleFont;

    /**
     * Holds the image of the in-game map
     */
    private Image map;

    /**
     * Establishes the grid of tiles to be laid over the map
     */
    private Table tileGrid;

    /**
     * Array holding the tiles to be laid over the map
     */
    private Tile[] tiles;

    /**
     * Variable defining the widths (in pixels) of the interface's side-hand tables
     */
    private int tableWidth;

    /**
     * Timer used to dictate the pace and flow of the game
     * This has a visual interface which will be displayed in the top-left corner of the game-screen
     */
    private GameTimer timer;

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

    private Label moneyCounter;
    /**
     * Defines whether or not a tile has been acquired in the current phase of the game
     */

    private boolean tileAcquired;

    /**
     * Label stating the ID of the currently-selected tile
     */
    private Label selectedTileLabel;

    /**
     * Array storing player-data for each participant
     */
    private Player[] players = new Player[3];

    /**
     * Holds the numeric representation of the game's current phase
     */
    private int phase;

    /**
     * Holds the ID of the currently-active player
     */
    private int currentPlayer;

    /**
     * Object defining QOL drawing functions for rectangles and on-screen tables
     * Used in this class to render tooltip regions
     */
    private Drawer drawer;

    /**
     * Holds the data pertaining to the currently-selected tile
     */
    private Tile selectedTile;

    /**
     * Button which allows players to prematurely end their turns
     */
    private TextButton endPhase;

    /**
     * Button which allows players to pause the game
     */
    private TextButton pause;
    //Establish buttons to advance between phases and pause the game

    /**
     * Button which allows players to resume the game from the pause menu
     */
    private TextButton resume;

    /**
     * Button which allows players to claim selected tiles
     */
    private TextButton claim;

    /**
     * Button which allows players to deploy owned roboticons onto selected tiles
     */
    private TextButton deploy;

    /**
     * Establish visual parameters for in-game buttons
     */
    private TextButton.TextButtonStyle gameButtonStyle;
    //Establish visual parameters for in-game buttons

    /**
     * Holds all of the data and the functions of the game's market
     * Also comes bundled with a visual interface which can be rendered on to the game's screen
     */
    private Market market;

    /**
     * The game-screen's initial constructor
     *
     * @param game Variable storing the game's state
     */
    public GameScreen(Game game) {
        this.phase = 1;
        this.currentPlayer = 1;
        this.game = game;
        Player Player1 = new Player(1);
        Player Player2 = new Player(2);
        players[1] = Player1;
        players[2] = Player2;
        //Import current game-state and establish player data
    }

    /**
     * Executes when the game-screen is loaded up, typically from the point of another screen
     * Serves as an extension of the screen's constructor that primarily constructs visual elements
     */
    @Override
    public void show() {
        drawer = new Drawer(game);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
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
        stage.addActor(map);
        //Initialise and deploy map texture

        constructTileGrid();

        tableWidth = (int) ((Gdx.graphics.getWidth() - map.getWidth()) / 2);
        //Set widths of side-hand tables
        //This will always be 256 for as long as the size of the game's window is fixed
        //The purpose of this variable is to facilitate the later implementation of window resizing

        gameFont.setSize(120);
        timer = new GameTimer(9999, gameFont, Color.WHITE);
        //Set up game timer
        //"Runnable" parameter specifies code to be executed when the timer runs out

        constructLeftTable();
        constructRightTable();
        //Construct and deploy side-hand tables

        constructPauseMenu();
        //Construct pause-menu (and hide it for the moment)

        //drawer.debug(stage);
        //Call this to draw temporary debug lines around all of the actors on the stage

        state = State.RUN;
        //Mark that the game is currently running (and not paused)

        timer.start();
        //Start in-game timer
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

        if (state == State.RUN) {
            drawRectangles();

            stage.act(delta);
            stage.draw();
            //Draw the stage onto the screen

            for (Tile tile : tiles) {
                tile.drawTooltip();
            }
            //If any of the tiles' tooltips are deemed "active", render them to the screen too
        } else if (state == State.PAUSE) {
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

        stage.dispose();
        //Dispose of the stage
    }

    /**
     * Set up the form and contents of the left-hand table so that they can be rendered later
     */
    public void constructLeftTable() {
        tableLeft = new Table();
        //Construct left-hand table

        tableLeft.setBounds(0, 0, tableWidth, Gdx.graphics.getHeight());
        //Set boundaries of left-hand table

        tableLeft.center().top();
        //Shift the table towards the top of the screen

        drawer.addTableRow(tableLeft, timer, 0, 0, 0, 0, 2);
        //Add the timer to the table

        endPhase = new TextButton("End Phase", gameButtonStyle);
        endPhase.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                nextPhase();
            }
        });
        drawer.addTableRow(tableLeft, endPhase, 0, 0, 15, 0, 2);
        //Prepare and add the "End Phase" button to the table

        gameFont.setSize(36);

        drawer.addTableRow(tableLeft, new Label("CURRENT PLAYER", new Label.LabelStyle(gameFont.font(), Color.BLACK)), 0, 0, 10, 0, 2);
        //Window-dressing


        gameFont.setSize(24);
        Table collegeInfo = new Table();
        drawer.addTableRow(collegeInfo, new Label("COL", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 64, 64);
        drawer.addTableRow(collegeInfo, new Label("COLLEGE", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
        drawer.addTableRow(tableLeft, collegeInfo, 5, 0, 0, 15);
        //More window-dressing

        Table resourceCounters = new Table();
        foodCounter = new Label(players[currentPlayer].getFoodCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        energyCounter = new Label(players[currentPlayer].getEnergyCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        oreCounter = new Label(players[currentPlayer].getOreCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        roboticonCounter = new Label("0", new Label.LabelStyle(gameFont.font(), Color.WHITE));
        moneyCounter = new Label(players[currentPlayer].getMoney().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        drawer.addTableRow(resourceCounters, new LabelledElement("Food", gameFont, Color.WHITE, foodCounter, 125));
        drawer.addTableRow(resourceCounters, new LabelledElement("Energy", gameFont, Color.WHITE, energyCounter, 125));
        drawer.addTableRow(resourceCounters, new LabelledElement("Ore", gameFont, Color.WHITE, oreCounter, 125));
        drawer.addTableRow(resourceCounters, new LabelledElement("Roboticons", gameFont, Color.WHITE, roboticonCounter, 125));
        drawer.addTableRow(resourceCounters, new LabelledElement("Money", gameFont, Color.WHITE, moneyCounter, 125));
        tableLeft.add(resourceCounters).size(140, 95);
        //Add resource-counters to the table
        //These will show the current resource stocks for the current player

        pause = new TextButton("Pause Game", gameButtonStyle);
        pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                timer.stop();
                //Stop the game's timer

                Gdx.input.setInputProcessor(pauseStage);
                //Prepare the pause menu to accept user inputs

                state = State.PAUSE;
                //Mark that the game has been paused
            }
        });
        drawer.addTableRow(tableLeft, pause, 138, 0, 0, 0, 2);
        //Prepare and add the pause button to the table

        stage.addActor(tableLeft);
        //Add left-hand table to the stage
    }

    /**
     * Set up the form and contents of the right-hand table so that they can be rendered later
     */
    public void constructRightTable() {
        tableRight = new Table();
        //Construct right-hand table

        tableRight.setBounds((Gdx.graphics.getWidth() / 2) + (map.getWidth() / 2), 0, tableWidth, Gdx.graphics.getHeight());
        //Set boundaries of right-hand table

        tableRight.center().top();
        //Shift the table towards the top of the screen

        gameFont.setSize(40);
        selectedTileLabel = new Label("NO TILE SELECTED", new Label.LabelStyle(gameFont.font(), Color.WHITE));
        selectedTileLabel.setAlignment(Align.center);
        drawer.addTableRow(tableRight, selectedTileLabel, 240, 43, 0, 0, 10, 0, 2);
        drawer.addTableRow(tableRight, new Label("COL", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 64, 64, 0, 0, 0, 0);
        tableRight.add(new Label("ROB", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
        //Add labels for currently selected tile
        //These will change to reflect the IDs, owners and statuses of each tile selected
        //IN THE FUTURE, THIS BLOCK WILL BE AMENDED TO SHOW THE ICONS OF TILES' OWNERS AND ASSIGNED ROBOTICONS

        gameFont.setSize(20);
        drawer.addTableRow(tableRight, new Label("COLLEGE", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 0, 0, 10, 0);
        tableRight.add(new Label("ROBOTICON", new Label.LabelStyle(gameFont.font(), Color.WHITE))).padBottom(10);
        //Even more window-dressing

        gameFont.setSize(28);
        gameButtonStyle.font = gameFont.font();
        claim = new TextButton("Claim", gameButtonStyle);
        deploy = new TextButton("Deploy", gameButtonStyle);
        //Prepare buttons to claim the currently-selected tile and to deploy a roboticon onto it


        claim.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (phase == 1) {

                    if (selectedTile.isOwned() == false) {

                        players[currentPlayer].assignTile(selectedTile);
                        selectedTile.setOwner(players[currentPlayer]);
                        tileAcquired = true;

                    }
                }
            }
        });
        //Functionality of tile-claim button
        drawer.addTableRow(tableRight, claim, 0, 0, 15, 0);
        tableRight.add(deploy).padBottom(15);
        //Add tile claim/deploy buttons to table

        market = new Market(game);
        market.buyOre.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(phase == 5 ) {

                    try {
                        players[currentPlayer] = market.buy("ore", 1, players[currentPlayer]);
                        updateLabels();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        market.buyFood.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(phase == 5 ) {

                    try {
                        players[currentPlayer] = market.buy("food", 1, players[currentPlayer]);
                        updateLabels();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        market.buyEnergy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(phase == 5 ) {

                    try {
                        players[currentPlayer] = market.buy("energy", 1, players[currentPlayer]);
                        updateLabels();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        market.sellEnergy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(phase == 5 ) {

                    try {
                        players[currentPlayer] = market.sell("energy", 1, players[currentPlayer]);
                        updateLabels();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        market.sellOre.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(phase == 5 ) {

                    try {
                        players[currentPlayer] = market.sell("ore", 1, players[currentPlayer]);
                        updateLabels();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        market.sellFood.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(phase == 5 ) {

                    try {
                        players[currentPlayer] = market.sell("food", 1, players[currentPlayer]);
                        updateLabels();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });



        drawer.addTableRow(tableRight, market, 2);
        //Establish market and add market interface to right-hand table

        stage.addActor(tableRight);
        //Add right-hand table to the stage
    }

    /**
     * Set up the game-screen's central tile-grid so that it can be interacted with
     */
    public void constructTileGrid(){
        tileGrid = new Table();
        //Initialise tile-grid

        tiles = new Tile[16];
        //Initialise tile-buttons

        tileGrid.setBounds((Gdx.graphics.getWidth() / 2) - (map.getWidth() / 2), 0, map.getWidth(), map.getHeight());
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                final int fx = x;
                final int fy = y;

                tiles[(y * 4) + x] = new Tile(this.game, (y * 4) + x + 1, 0, 0, 0, false, new Runnable() {
                    @Override
                    public void run() {
                        //drawer.addTableRow(tableLeft, new Label("Tile " + ((fy * 4) + fx + 1) + " was clicked", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
                        selectTile(getTile(tileGrid, fx, fy));
                        selectedTile = getTile(tileGrid, fx, fy);
                    }
                });

                tileGrid.add(tiles[(y * 4) + x]).width(map.getWidth() / 4).height(map.getHeight() / 4);
            }
            tileGrid.row();
        }

        stage.addActor(tileGrid);
    }

    /**
     * Set up the pause menu to allow for the game to be successfully paused
     */
    public void constructPauseMenu() {
        pauseStage = new Stage();
        pauseTable = new Table();
        //Establish stage and interface for pause menu

        titleFont = new TTFont(Gdx.files.internal("font/earthorbiterxtrabold.ttf"), 72);
        menuFont = new TTFont(Gdx.files.internal("font/enterthegrid.ttf"), 36);
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

        resume = new TextButton("Resume", menuButtonStyle);
        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                state = State.RUN;

                Gdx.input.setInputProcessor(stage);

                if (timer.minutes() > 0 || timer.seconds() > 0) {
                    timer.start();
                }
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
        drawer.lineRectangle(Color.WHITE, (int) map.getX(), (int) map.getY(), (int) map.getWidth() + 1, (int) map.getHeight());
        //Draw border around the map

        drawer.filledRectangle(Color.WHITE, 0, (int) timer.getHeight(), tableWidth, 1);
        drawer.filledRectangle(Color.WHITE, 0, (int) (timer.getHeight() + endPhase.getHeight()), tableWidth, 1);
        drawer.borderedRectangle(Color.GRAY, Color.WHITE, 19, (int) (timer.getHeight() + endPhase.getHeight()) + 15, 219, 40);
        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.125)) - 110, 240, 66, 66);
        drawer.filledRectangle(Color.WHITE, 0, Gdx.graphics.getHeight() - 46, tableWidth, 1);
        //Draw lines and rectangles in left-hand table

        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.875)) - 94, 52, 66, 66);
        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.875)) + 26, 52, 66, 66);
        drawer.filledRectangle(Color.WHITE, Gdx.graphics.getWidth() - 256, 190, tableWidth, 1);
        //Draw lines in right-hand table
    }

    /**
     * Allows individual tiles to be accessed straight from the game-screen's tile-grid
     *
     * @param tileGrid The grid of tiles at the core of the game-screen
     * @param x The x-coordinate of the tile to be accessed
     * @param y The y-coordinte of the tile to be accessed
     * @return Tile
     */
    public Tile getTile(Table tileGrid, int x, int y) {
        return (Tile) tileGrid.getChildren().get((y * tileGrid.getColumns()) + x);
    }
    //Returns the tile-type object held in the provided TileGrid at the specified co-ordinates

    /**
     * Allows individual tiles to be accessed straight from the game-screen's tile-grid
     *
     * @param tileGrid The grid of tiles at the core of the game-screen
     * @param i The index of the tile to be accessed
     * @return Tile
     */
    public Tile getTile(Table tileGrid, int i) {
        return (Tile) tileGrid.getChildren().get(i);
    }
    //Returns the tile-type object held in the provided TileGrid at the specified position

    /**
     * The code to be run whenever a particular tile is clicked on
     *
     * @param tile The tile being clicked on
     */
    public void selectTile(Tile tile) {
        selectedTileLabel.setText("Tile " + tile.ID());
    }

    /**
     * Encodes possible game-states
     */
    public enum State {
        RUN,
        PAUSE
    }

    /**
     * Advances the game's progress upon call
     */
    public void nextPhase() {
        System.out.print("player" + currentPlayer);
        System.out.print("phase" + phase);
        if(phase == 1){
            if(tileAcquired == true) {
                tileAcquired = false;

                if (currentPlayer == 1) {
                    currentPlayer = 2;

                } else {
                    phase = 2;
                    timer.setTime(2, 0);
                    currentPlayer = 1;
                }
            }
        }
        else if(phase == 2){
            if(currentPlayer == 1){
                currentPlayer = 2;
            }
            else{
                phase = 3;
                timer.setTime(2,0);
            }

        }
        else if(phase == 3){
            phase = 4;
            timer.setTime(0,99999);
        }
        else if(phase == 4){
            phase = 5;
            timer.setTime(0,99999);
        }
        else if(phase == 5){
            phase = 1;
            timer.setTime(0,99999);
        }

    }
    public void updateLabels(){
        moneyCounter.setText(players[currentPlayer].getMoney().toString());
        foodCounter.setText(players[currentPlayer].getFoodCount().toString());
        oreCounter.setText(players[currentPlayer].getOreCount().toString());
        energyCounter.setText(players[currentPlayer].getEnergyCount().toString());
    }
}