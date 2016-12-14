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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class GameScreen implements Screen{

    private Game game;
    //Stores current game-state, enabling transitions between screens

    private Stage stage;
    private Table tableLeft;
    private Table tableRight;
    //Establish stage and side-hand tables

    private TTFont gameFont;
    //Establish font

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

    private Player[] Players = new Player[3];
    private Market Market = new Market();
    private int phase = 1;
    private int currentPlayer = 1;
    //Establish resource-counter labels

    private Drawer drawer;
    //Import standard drawing functions

    public GameScreen(Game game) {
        this.game = game;
        Player Player1 = new Player(1);
        Player Player2 = new Player(2);
        Players[1] = Player1;
        Players[2] = Player2;
        //Import current game-state
    }


    @Override
    public void show() {
        drawer = new Drawer(game);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        //Prepare the local stage and set it up to accept inputs

        gameFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"));
        //Set fonts for game interface

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

        //drawer.debug(stage);
        //Call this to draw temporary debug lines around all of the actors on the stage

        timer.start();
        //Start in-game timer
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //OpenGL nonsense
        //First instruction sets background colour

        stage.act(delta);
        stage.draw();
        //Draw the stage onto the screen

        drawer.lineRectangle(Color.WHITE, (int) map.getX(), (int) map.getY(), (int) map.getWidth(), (int) map.getHeight());
        //Draw border around the map

        drawer.filledRectangle(Color.WHITE, 0, (int) (timer.getHeight()), tableWidth, 1);
        drawer.filledRectangle(Color.WHITE, 0, (int) (timer.getHeight() + foodCounter.getHeight() + energyCounter.getHeight() + oreCounter.getHeight()) + 30, tableWidth, 1);
        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.125)) - 110, ((int) (timer.getHeight())) + 9, 66, 66);
        //Draw lines in left-hand table

        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.875)) - 82, 52, 66, 66);
        drawer.lineRectangle(Color.WHITE, ((int) (Gdx.graphics.getWidth() * 0.875)) + 15, 52, 66, 66);
        //Draw lines in right-hand table

        for (Tile tile : tiles) {
            tile.drawTooltip();
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

        drawer.addTableRow(tableLeft, timer, 0, 0, 10, 0, 2);

        gameFont.setSize(24);
        foodCounter = new Label(Players[currentPlayer].getFoodCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        energyCounter = new Label(Players[currentPlayer].getEnergyCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));
        oreCounter = new Label(Players[currentPlayer].getOreCount().toString(), new Label.LabelStyle(gameFont.font(), Color.WHITE));

        Table collegeInfo = new Table();
        drawer.addTableRow(collegeInfo, new Label("COL", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 64, 64);
        drawer.addTableRow(collegeInfo, new Label("COLLEGE", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
        drawer.addTableRow(tableLeft, collegeInfo, 0, 0, 0, 15);

        Table resourceCounters = new Table();
        drawer.addTableRow(resourceCounters, new LabelledElement("Food", gameFont, Color.WHITE, foodCounter, 125));
        drawer.addTableRow(resourceCounters, new LabelledElement("Energy", gameFont, Color.WHITE, energyCounter, 125));
        drawer.addTableRow(resourceCounters, new LabelledElement("Ore", gameFont, Color.WHITE, oreCounter, 125));
        tableLeft.add(resourceCounters).size(140, 80).padBottom(3);

        drawer.addTableRow(tableLeft, new Label("Roboticon Shop Area", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 15, 0, 0, 0, 2);

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
        drawer.addTableRow(tableRight, new Label("NO TILE SELECTED", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 0, 0, 10, 0, 2);
        drawer.addTableRow(tableRight, new Label("COL", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 64, 64, 0, 0, 0, 0);
        tableRight.add(new Label("ROB", new Label.LabelStyle(gameFont.font(), Color.WHITE)));

        gameFont.setSize(24);
        drawer.addTableRow(tableRight, new Label("OWNED BY", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
        tableRight.add(new Label("ROBOTICON", new Label.LabelStyle(gameFont.font(), Color.WHITE)));

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

                    }
                });

                tileGrid.add(tiles[(y * 4) + x]).width(map.getWidth() / 4).height(map.getHeight() / 4);
            }
            tileGrid.row();
        }

        stage.addActor(tileGrid);
    }

    public Tile getTile(Table tileGrid, int x, int y) {
        return (Tile) tileGrid.getChildren().get((y * tileGrid.getColumns()) + x);
    }
    //Returns the tile-type object held in the provided TileGrid at the specified co-ordinates

    public Tile getTile(Table tileGrid, int i) {
        return (Tile) tileGrid.getChildren().get(i);
    }
    //Returns the tile-type object held in the provided TileGrid at the specified position
}
