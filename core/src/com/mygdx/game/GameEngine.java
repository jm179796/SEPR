package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.List;

/**
 * @author Duck-Related Team Name in BIG MASSIVE LETTERS
 * @version READ ASSESSMENT 2
 */
public class GameEngine {
    /**
     * Stores current game-state, enabling transitions between screens and external QOL drawing functions
     */
    private Game game;

    /**
     * The game's engine only ever runs while the main in-game interface is showing, so it was designed to manipulate
     * elements (both visual and logical in nature) on that screen
     * It therefore requires access to the public methods in the GameScreen class, so instantiation in this class
     * is a necessity
     */
    private GameScreen gameScreen;

    /**
     * Stores data pertaining to the game's active players
     * For more information, check the "Player" class
     */
    private Player[] players;

    /**
     * Holds the numeric getID of the player who's currently active in the game
     */
    private int currentPlayerID;

    /**
     * Holds the number of the phase that the game is currently in
     * Varies between 1 and 5
     */
    private int phase;

    /**
     * Defines whether or not a tile has been acquired in the current phase of the game
     */
    private boolean tileAcquired;

    /**
     * Timer used to dictate the pace and flow of the game
     * This has a visual interface which will be displayed in the top-left corner of the game-screen
     */
    private GameTimer timer;

    /**
     * Object providing QOL drawing functions to simplify visual construction and rendering tasks
     */
    private Drawer drawer;

    /**
     * Holds all of the data and the functions of the game's market
     * Also comes bundled with a visual interface which can be rendered on to the game's screen
     */
    private Market market;

    /**
     * Array holding the tiles to be laid over the map
     * Note that the tiles' visuals are encoded by the image declared and stored in the GameScreen class (and not here)
     */
    private Tile[] tiles;

    /**
     * Holds the data pertaining to the currently-selected tile
     */
    private Tile selectedTile;

    /**
     * Variable dictating whether the game is running or paused at any given moment
     */
    private State state;

    /**
     * ???
     * Allan please add details
     */
    private Integer roboticonIDCounter = 0;

    /**
     * Constructs the game's engine. Imports the game's state (for direct renderer access) and the data held by the
     * GameScreen which this engine directly controls; then goes on to set up player-data for the game's players,
     * tile-data for the on-screen map and the in-game market for in-play manipulation. In the cases of the latter
     * two tasks, the engine directly interacts with the GameScreen object imported to it (as a parameter of this
     * constructor) so that it can draw the visual interfaces for the game's market and tiles directly to the game's
     * primary interface.
     *
     * @param game Variable storing the game's state
     * @param gameScreen The object encoding the in-game interface which is to be controlled by this engine
     */
    public GameEngine(Game game, GameScreen gameScreen) {
        this.game = game;
        //Import current game-state to access the game's renderer

        this.gameScreen = gameScreen;
        //Bind the engine to the main in-game interface
        //Required to alter the visuals and logic of the interface directly through this engine

        drawer = new Drawer(this.game);
        //Import QOL drawing function

        players = new Player[3];
        currentPlayerID = 1;
        //Set up objects to hold player-data
        //Start the game such that player 1 makes the first move

        phase = 1;
        //Start the game in the first phase (of 5, which recur until all tiles are claimed)

        timer = new GameTimer(9999, new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 120), Color.WHITE);
        //Set up game timer

        tiles = new Tile[16];
        //Initialise data for all 16 tiles on the screen
        //This instantiation does NOT automatically place the tiles on the game's main interface

        for (int i = 0; i < 16; i++) {
            final int fi = i;
            final GameScreen gs = gameScreen;

            tiles[i] = new Tile(this.game, i + 1, 5, 5, 5, false, new Runnable() {
                @Override
                public void run() {
                    gs.selectTile(tiles[fi]);
                    selectedTile = tiles[fi];
                }
            });
        }
        //Configure all 16 tiles with independent yields and landmark data
        //Also assign listeners to them so that they can detect mouse clicks

        market = new Market(game, this);
        //Instantiates the game's market and hands it direct renderer access

        state = State.RUN;
        //Mark the game's current play-state as "running" (IE: not paused)

        Player Player1 = new Player(1);
        Player Player2 = new Player(2);
        players[1] = Player1;
        players[2] = Player2;
        College Goodricke = new College(1, "The best college");
        College Derwent = new College(2, "It has asbestos");
        players[1].assignCollege(Goodricke);
        players[2].assignCollege(Derwent);
        //Temporary assignment of player-data for testing purposes

        timer.start();
        //Start the game's timer as soon as the engine loads
        //This works because the engine loads as soon as the game's primary interface loads
    }

    /**
     * Advances the game's progress upon call
     * Acts as a state machine of sorts, moving the game from one phase to another depending on what phase it is
     * currently at when this method if called. If player 1 is the current player in any particular phase, then the
     * phase number remains and control is handed off to the other player: otherwise, control returns to player 1 and
     * the game advances to the next state, implementing any state-specific features as it goes.
     *
     * PHASE 1: Acquisition of Tiles
     * PHASE 2: Acquisition of Roboticons
     * PHASE 3: Placement of Roboticons
     * PHASE 4: Production of Resources by Roboticons
     * PHASE 5: Market Trading
     */
    public void nextPhase() {
        System.out.print("Player " + currentPlayerID + " | Phase " + phase);
        if(phase == 1){
            if(tileAcquired == true) {
                tileAcquired = false;

                if (currentPlayerID == 1) {
                    switchCurrentPlayer();
                } else {
                    phase = 2;
                    timer.setTime(2, 0);
                    switchCurrentPlayer();
                    drawer.switchTextButton(gameScreen.endTurnButton(), true, Color.WHITE);
                }
            }
        }
        else if(phase == 2){
            if(currentPlayerID == 1){
                switchCurrentPlayer();
            }
            else{
                phase = 3;
                timer.setTime(2,0);
                switchCurrentPlayer();
            }
        }
        else if(phase == 3){
            if(currentPlayerID == 1){
                switchCurrentPlayer();
            }
            else {
                phase = 4;
                timer.setTime(0, 99999);
                switchCurrentPlayer();

            }
        }
        else if(phase == 4){
            List<Tile> tileList = players[1].getTileList();
            for (Tile Tile : tileList){

                if (tileList.size() > 0){
                    System.out.print("yes");
                    players[1] = Tile.Produce(players[1]);
                    switchCurrentPlayer();
                }

            }
            List<Tile> tileList2 = players[2].getTileList();
            for (Tile Tile : tileList2){
                if(tileList2.size() > 0){
                    players[2] = Tile.Produce(players[2]);
                    switchCurrentPlayer();
                }

            }

            phase = 5;
            timer.setTime(0,99999);
        }
        else if(phase == 5){
            if (currentPlayerID == 1) {
                switchCurrentPlayer();
            }
            else{
                phase = 1;
                timer.setTime(0,99999);

                drawer.switchTextButton(gameScreen.endTurnButton(), false, Color.GRAY);
                drawer.switchTextButton(gameScreen.claimTileButton(), true, Color.WHITE);
                switchCurrentPlayer();
            }
        }

        gameScreen.updatePhaseLabel(phase);
        //Whenever a phase transition is made, indicate that on-screen by updating the phase label

        gameScreen.deselectTile();
        //Automatically de-select the currently-selected tile after a phase/player switch

        if(checkGameEnd() == true){
            Integer score1 = players[1].calculateScore();
            Integer score2 = players[2].calculateScore();
            if(score1 > score2){
                System.out.print("Player 1 Wins!");
            }
            else{
                System.out.print("Player 2 Wins!");
            }
        }
        //Temporary code for determining the game's winner once all tiles have been acquired
        //Each player should own 8 tiles when this block is executed
    }

    /**
     * Sets the current player to be that which isn't active whenever this is called
     * Updates the in-game interface to reflect the statistics and the identity of the player now controlling it
     */
    private void switchCurrentPlayer() {
        currentPlayerID = 3 - currentPlayerID;
        //3 - 1 = 2
        //3 - 2 = 1
        //This naturally switches the getID of the currently-active player from 1 to 2 and vice-versa

        gameScreen.currentPlayerIcon().setDrawable(new TextureRegionDrawable(new TextureRegion(players[currentPlayerID].getCollege().getLogoTexture())));
        gameScreen.currentPlayerIcon().setSize(64, 64);
        //Find and draw the icon representing the "new" player's associated college

        updateLabels();
        //Display the "new" player's inventory on-screen

    }

    /**
     * Updates the on-screen counters for food, energy, ore, money and Roboticons
     * This is typically called when the active player switches or when a market transaction is made
     */
    public void updateLabels(){
        gameScreen.setFoodCounterValue(currentPlayer().getFoodCount());
        gameScreen.setEnergyCounterValue(currentPlayer().getEnergyCount());
        gameScreen.setOreCounterValue(currentPlayer().getOreCount());
        gameScreen.setMoneyCounterValue(currentPlayer().getMoney());
        gameScreen.setRoboticonCounterValue(currentPlayer().getRoboticonInventory());
    }

    public void pauseGame() {
        timer.stop();
        //Stop the game's timer

        gameScreen.openPauseStage();
        //Prepare the pause menu to accept user inputs

        state = State.PAUSE;
        //Mark that the game has been paused
    }

    public void resumeGame() {
        state = State.RUN;

        gameScreen.openGameStage();

        if (timer.minutes() > 0 || timer.seconds() > 0) {
            timer.start();
        }
    }

    public void claimTile() {
        if (phase == 1 && selectedTile.isOwned() == false) {
            players[currentPlayerID].assignTile(selectedTile);
            //Assign selected tile to current player

            selectedTile.setOwner(players[currentPlayerID]);
            //Set the owner of the currently selected tile to be the current player

            tileAcquired = true;
            //Mark that a tile has been acquired on this turn

            switch (players[currentPlayerID].getCollege().getID()) {
                case (1):
                    //DERWENT
                    selectedTile.setTileBorderColor(Color.BLUE);
                    break;
                case (2):
                    //LANGWITH
                    selectedTile.setTileBorderColor(Color.CHARTREUSE);
                    break;
                case (3):
                    //VANBURGH
                    selectedTile.setTileBorderColor(Color.TEAL);
                    break;
                case (4):
                    //JAMES
                    selectedTile.setTileBorderColor(Color.CYAN);
                    break;
                case (5):
                    //WENTWORTH
                    selectedTile.setTileBorderColor(Color.MAROON);
                    break;
                case (6):
                    //HALIFAX
                    selectedTile.setTileBorderColor(Color.YELLOW);
                    break;
                case (7):
                    //ALCUIN
                    selectedTile.setTileBorderColor(Color.RED);
                    break;
                case (8):
                    //GOODRICKE
                    selectedTile.setTileBorderColor(Color.GREEN);
                    break;
                case (9):
                    //CONSTANTINE
                    selectedTile.setTileBorderColor(Color.PINK);
                    break;
            }
            //Set the colour of the tile's new border based on the college of the player who claimed it

            nextPhase();
            //Advance the game
        }
    }

    public void deployRoboticon(){
        if (phase == 3) {
            if (selectedTile.hasRoboticon() == false) {
                if (players[currentPlayerID].getRoboticonInventory() > 0) {
                    Roboticon Roboticon = new Roboticon(roboticonIDCounter, players[currentPlayerID], selectedTile);
                    players[currentPlayerID].addRoboticon(Roboticon);
                    selectedTile.assignRoboticon(Roboticon);
                    roboticonIDCounter += 1;
                    players[currentPlayerID].decreaseRoboticonInventory();
                    updateLabels();
                }
            } else if(phase == 2 && selectedTile.hasRoboticon()) {
                //selectedTile.getRoboticonStored().upgrade();
            }

        }

    }

    /**
     * Encodes possible game-states
     */
    public enum State {
        RUN,
        PAUSE
    }

    public State state() {
        return state;
    }

    public int phase() {
        return phase;
    }

    public Player[] players() { return players; }

    public Player currentPlayer() { return players[currentPlayerID]; }

    public int currentPlayerID() {
        return currentPlayerID;
    }

    public GameTimer timer() {
        return timer;
    }

    public Tile[] tiles() {
        return tiles;
    }

    public Tile selectedTile() {
        return selectedTile;
    }

    public Market market() {
        return market;
    }

    public boolean checkGameEnd(){
        boolean end = true;
        for(Tile Tile : tiles){
            if(Tile.getOwner().PlayerID == 0){
                end = false;
            }
        }
        return end;
    }

    public void updateCurrentPlayer(Player currentPlayer) {
        players[currentPlayerID] = currentPlayer;
    }
}
