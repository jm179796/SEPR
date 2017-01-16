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

    private Game game;
    //Stores current game-state, enabling transitions between screens

    private State state;
    //Variable to dictate whether or not the game is paused

    private Stage stage;
    private Table tableLeft;
    private Table tableRight;
    //Establish stage and side-hand tables

    private Stage pauseStage;
    private Table pauseTable;
    //Establish the stage and table which will appear when the game is paused

    private TTFont gameFont;
    private TTFont menuFont;
    private TTFont titleFont;
    //Establish fonts

    private Image map;
    //Establish in-game map

    private Table tileGrid;
    //Establish grid of buttons over central map

    private Tile[] tiles;
    //Establish invisible buttons to lay over the map's tiles

    private int tableWidth;
    //Establish variable for holding sizes of in-game tables

    private GameTimer timer;
    //Establish game-timer

    private Label foodCounter;
    private Label energyCounter;
    private Label oreCounter;
    private Label roboticonCounter;

    private Label selectedTileLabel;
    //Establish label object to describe tiles selected

    private Player[] players = new Player[3];
    private Market market = new Market();
    private int phase = 1;
    private int currentPlayer = 1;
    //Establish resource-counter labels

    private Drawer drawer;
    //Import standard drawing functions

    private TextButton endPhase;
    private TextButton pause;
    //Establish buttons to advance between phases and pause the game

    private TextButton resume;
    //Establish button to resume the game from the pause menu

    private TextButton claim;
    private TextButton deploy;
    //Establish buttons to claim tiles and deploy roboticons on to them

    TextButton.TextButtonStyle gameButtonStyle;
    //Establish visual parameters for in-game buttons

    public GameScreen(Game game) {
        this.game = game;
        Player Player1 = new Player(1);
        Player Player2 = new Player(2);
        players[1] = Player1;
        players[2] = Player2;
        //Import current game-state
    }

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
        timer = new GameTimer(120, gameFont, Color.WHITE);
        //Set up game timer
        //"Runnable" parameter specifies code to be executed when the timer runs out

        constructLeftTable();
        constructRightTable();
        //Construct and deploy side-hand tables

        constructPauseMenu();
        //Construct pause-menu (and hide it for the moment)

        drawer.debug(stage);
        //Call this to draw temporary debug lines around all of the actors on the stage

        state = State.RUN;

        timer.start();
        //Start in-game timer
    }

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
        } else if (state == State.PAUSE) {
            drawer.filledRectangle(Color.WHITE, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            pauseStage.act(delta);
            pauseStage.draw();
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

    @Override
    public void dispose() {
        gameFont.dispose();
        //Dispose of the core BitmapFont object within this TTFont object

        stage.dispose();
        //Dispose of the stage
    }

    public void constructLeftTable() {
        tableLeft = new Table();
        //Construct left-hand table

        tableLeft.setBounds(0, 0, tableWidth, Gdx.graphics.getHeight());
        //Set boundaries of left-hand table

        tableLeft.center().top();
        //Shift the table towards the top of the screen

        drawer.addTableRow(tableLeft, timer, 0, 0, 0, 0, 2);

        endPhase = new TextButton("End Phase", gameButtonStyle);
        drawer.addTableRow(tableLeft, endPhase, 0, 0, 15, 0, 2);

        gameFont.setSize(36);
        drawer.addTableRow(tableLeft, new Label("CURRENT PLAYER", new Label.LabelStyle(gameFont.font(), Color.BLACK)), 0, 0, 10, 0, 2);

        gameFont.setSize(24);
        Table collegeInfo = new Table();
        drawer.addTableRow(collegeInfo, new Label("COL", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 64, 64);
        drawer.addTableRow(collegeInfo, new Label("COLLEGE", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
        drawer.addTableRow(tableLeft, collegeInfo, 5, 0, 0, 15);

        Table resourceCounters = new Table();
        foodCounter = new Label(players[currentPlayer].getFoodCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        energyCounter = new Label(players[currentPlayer].getEnergyCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        oreCounter = new Label(players[currentPlayer].getOreCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        roboticonCounter = new Label("0", new Label.LabelStyle(gameFont.font(), Color.WHITE));
        drawer.addTableRow(resourceCounters, new LabelledElement("Food", gameFont, Color.WHITE, foodCounter, 125));
        drawer.addTableRow(resourceCounters, new LabelledElement("Energy", gameFont, Color.WHITE, energyCounter, 125));
        drawer.addTableRow(resourceCounters, new LabelledElement("Ore", gameFont, Color.WHITE, oreCounter, 125));
        drawer.addTableRow(resourceCounters, new LabelledElement("Roboticons", gameFont, Color.WHITE, roboticonCounter, 125));
        tableLeft.add(resourceCounters).size(140, 95);

        pause = new TextButton("Pause Game", gameButtonStyle);
        pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                timer.stop();

                Gdx.input.setInputProcessor(pauseStage);

                state = State.PAUSE;
            }
        });
        drawer.addTableRow(tableLeft, pause, 138, 0, 0, 0, 2);

        stage.addActor(tableLeft);
        //Add left-hand table to the stage
    }

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

        gameFont.setSize(20);
        drawer.addTableRow(tableRight, new Label("COLLEGE", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 0, 0, 10, 0);
        tableRight.add(new Label("ROBOTICON", new Label.LabelStyle(gameFont.font(), Color.WHITE))).padBottom(10);

        gameFont.setSize(28);
        gameButtonStyle.font = gameFont.font();
        claim = new TextButton("Claim", gameButtonStyle);
        deploy = new TextButton("Deploy", gameButtonStyle);
        drawer.addTableRow(tableRight, claim);
        tableRight.add(deploy);

        stage.addActor(tableRight);
        //Add right-hand table to the stage
    }

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
                    }
                });

                tileGrid.add(tiles[(y * 4) + x]).width(map.getWidth() / 4).height(map.getHeight() / 4);
            }
            tileGrid.row();
        }

        stage.addActor(tileGrid);
    }

    public void constructPauseMenu() {
        pauseStage = new Stage();
        pauseTable = new Table();

        titleFont = new TTFont(Gdx.files.internal("font/earthorbiterxtrabold.ttf"), 72);
        menuFont = new TTFont(Gdx.files.internal("font/enterthegrid.ttf"), 36);

        pauseTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        drawer.addTableRow(pauseTable, new Label("Sabbaticoup", new Label.LabelStyle(titleFont.font(), Color.BLACK)), 0, 0, 30, 0);

        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = menuFont.font();
        menuButtonStyle.fontColor = Color.BLACK;
        menuButtonStyle.pressedOffsetX = 1;
        menuButtonStyle.pressedOffsetY = -1;

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

        drawer.addTableRow(pauseTable, resume);

        pauseStage.addActor(pauseTable);
    }

    public void drawRectangles() {
        drawer.lineRectangle(Color.WHITE, (int) map.getX(), (int) map.getY(), (int) map.getWidth(), (int) map.getHeight());
        //Draw border around the map

        drawer.filledRectangle(Color.WHITE, 0, (int) timer.getHeight(), tableWidth, 1);
        drawer.filledRectangle(Color.WHITE, 0, (int) (timer.getHeight() + endPhase.getHeight()), tableWidth, 1);
        drawer.borderedRectangle(Color.GRAY, Color.WHITE, 19, (int) (timer.getHeight() + endPhase.getHeight()) + 15, 219, 40);
        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.125)) - 110, 240, 66, 66);
        drawer.filledRectangle(Color.WHITE, 0, Gdx.graphics.getHeight() - 46, tableWidth, 1);
        //Draw lines and rectangles in left-hand table

        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.875)) - 94, 52, 66, 66);
        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.875)) + 26, 52, 66, 66);
        //Draw lines in right-hand table
    }

    public Tile getTile(Table tileGrid, int x, int y) {
        return (Tile) tileGrid.getChildren().get((y * tileGrid.getColumns()) + x);
    }
    //Returns the tile-type object held in the provided TileGrid at the specified co-ordinates

    public Tile getTile(Table tileGrid, int i) {
        return (Tile) tileGrid.getChildren().get(i);
    }
    //Returns the tile-type object held in the provided TileGrid at the specified position

    public void selectTile(Tile tile) {
        selectedTileLabel.setText("Tile " + tile.ID());
    }

    public enum State {
        RUN,
        PAUSE
    }
}