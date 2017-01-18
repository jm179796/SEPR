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
 * Created by Joseph on 18/01/2017.
 */
public class GameEngine {
    private Game game;

    private GameScreen gameScreen;

    private Player[] players;

    private int currentPlayerID;

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

    private Drawer drawer;

    /**
     * Holds all of the data and the functions of the game's market
     * Also comes bundled with a visual interface which can be rendered on to the game's screen
     */
    private Market market;

    /**
     * Array holding the tiles to be laid over the map
     */
    private Tile[] tiles;

    /**
     * Holds the data pertaining to the currently-selected tile
     */
    private Tile selectedTile;

    /**
     * Variable dictating whether the game is running or paused
     */
    private State state;

    private Integer roboticonIDCounter = 0;

    public GameEngine(Game game, GameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;

        drawer = new Drawer(this.game);

        players = new Player[3];
        currentPlayerID = 1;

        phase = 1;

        timer = new GameTimer(9999, new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 120), Color.WHITE);
        //Set up game timer

        tiles = new Tile[16];
        //Initialise tiles

        for (int i = 0; i < 16; i++) {
            final int fi = i;
            final GameScreen gs = gameScreen;

            tiles[i] = new Tile(this.game, i + 1, 0, 0, 0, false, new Runnable() {
                @Override
                public void run() {
                    gs.selectTile(tiles[fi]);
                    selectedTile = tiles[fi];
                }
            });
        }

        constructMarket();

        state = State.RUN;

        Player Player1 = new Player(1);
        Player Player2 = new Player(2);
        players[1] = Player1;
        players[2] = Player2;
        College Goodricke = new College(1, "The best college");
        College Derwent = new College(2, "It has asbestos");
        players[1].assignCollege(Goodricke);
        players[2].assignCollege(Derwent);

        timer.start();
    }

    /**
     * Advances the game's progress upon call
     */
    public void nextPhase() {
        System.out.print("Player " + currentPlayerID + " | Phase " + phase);
        if(phase == 1){
            if(tileAcquired == true) {
                tileAcquired = false;

                if (currentPlayerID == 1) {
                    switchCurrentPlayer();
                    updateLabels();
                } else {
                    phase = 2;
                    timer.setTime(2, 0);
                    switchCurrentPlayer();
                    updateLabels();
                    drawer.switchTextButton(gameScreen.endTurnButton(), true, Color.WHITE);
                }
            }
        }
        else if(phase == 2){
            if(currentPlayerID == 1){
                switchCurrentPlayer();
                updateLabels();
            }
            else{
                phase = 3;
                timer.setTime(2,0);
                switchCurrentPlayer();
                updateLabels();
                drawer.switchTextButton(gameScreen.deployRoboticonButton(), true, Color.WHITE);
            }
        }
        else if(phase == 3){
            if(currentPlayerID == 1){
                currentPlayerID = 2;
                updateLabels();
            }
            else {
                phase = 4;
                timer.setTime(0, 99999);
                currentPlayerID = 1;
                updateLabels();
                drawer.switchTextButton(gameScreen.deployRoboticonButton(), false, Color.GRAY);
            }
        }
        else if(phase == 4){
            List<Tile> tileList = players[1].getTileList();
            for (Tile Tile : tileList){
                if (tileList.size() > 0){
                    players[1] = Tile.Produce(players[1]);
                }

            }
            List<Tile> tileList2 = players[2].getTileList();
            for (Tile Tile : tileList2){
                if(tileList2.size() > 0){
                    players[2] = Tile.Produce(players[2]);
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
            }
        }

        gameScreen.updatePhaseLabel(phase);

        gameScreen.deselectTile();
    }

    /**
     * Sets the current player to be that which isn't whenever this is called
     */
    public void switchCurrentPlayer() {
        currentPlayerID = 3 - currentPlayerID;

        gameScreen.currentPlayerIcon().setDrawable(new TextureRegionDrawable(new TextureRegion(players[currentPlayerID].getCollege().getLogoTexture())));
        gameScreen.currentPlayerIcon().setSize(64, 64);

    }

    public void updateLabels(){
        gameScreen.setFoodCounterValue(currentPlayer().getFoodCount());
        gameScreen.setEnergyCounterValue(currentPlayer().getEnergyCount());
        gameScreen.setOreCounterValue(currentPlayer().getOreCount());
        gameScreen.setMoneyCounterValue(currentPlayer().getMoney());
        gameScreen.setRoboticonCounterValue(currentPlayer().getRoboticonInventory());
    }

    public void constructMarket() {
        market = new Market(game);

        market.buyRoboticon.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(phase == 2 ) {

                    try {
                        players[currentPlayerID] = market.buyRoboticon(players[currentPlayerID]);
                        updateLabels();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        market.buyOre.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (phase == 5) {

                    try {
                        players[currentPlayerID] = market.buy("ore", 1, players[currentPlayerID]);
                        gameScreen.setOreCounterValue(currentPlayer().getOreCount());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        market.buyFood.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (phase == 5) {

                    try {
                        players[currentPlayerID] = market.buy("food", 1, players[currentPlayerID]);
                        gameScreen.setFoodCounterValue(currentPlayer().getFoodCount());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        market.buyEnergy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (phase == 5) {

                    try {
                        players[currentPlayerID] = market.buy("energy", 1, players[currentPlayerID]);
                        gameScreen.setEnergyCounterValue(currentPlayer().getEnergyCount());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        market.sellEnergy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (phase == 5) {

                    try {
                        players[currentPlayerID] = market.sell("energy", 1, players[currentPlayerID]);
                        gameScreen.setEnergyCounterValue(currentPlayer().getEnergyCount());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        market.sellOre.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (phase == 5) {

                    try {
                        players[currentPlayerID] = market.sell("ore", 1, players[currentPlayerID]);
                        gameScreen.setOreCounterValue(currentPlayer().getOreCount());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        market.sellFood.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (phase == 5) {

                    try {
                        players[currentPlayerID] = market.sell("food", 1, players[currentPlayerID]);
                        gameScreen.setFoodCounterValue(currentPlayer().getFoodCount());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
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
        if (phase == 1) {
            if (selectedTile.isOwned() == false){
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
    }

    public void deployRoboticon(){

        if (phase == 3) {
            if (players[currentPlayerID].getRoboticonInventory() > 0) {

                if (selectedTile.hasRoboticon() == false) {
                    Roboticon Roboticon = new Roboticon(roboticonIDCounter, players[currentPlayerID], selectedTile);
                    players[currentPlayerID].addRoboticon(Roboticon);
                    selectedTile.assignRoboticon(Roboticon);
                    roboticonIDCounter += 1;

                }
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
}
