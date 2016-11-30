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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Drawer;

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

    private Button[] tileButtons;
    //Establish invisible buttons to lay over the map's tiles

    private int tableWidth;
    //Establish variable for holding sizes of in-game tables

    private GameTimer timer;
    //Establish game-timer

    private Label foodCounter;
    private Label waterCounter;
    private Label oreCounter;
    //Establish resource-counter labels

    private Drawer drawer;
    //Import standard drawing functions

    public GameScreen(Game game) {
        this.game = game;
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

        tileGrid = new Table();
        //Initialise tile-grid

        tileButtons = new Button[16];
        //Initialise tile-buttons

        tileGrid.setBounds((Gdx.graphics.getWidth() / 2) - (map.getWidth() / 2), 0, map.getWidth(), map.getHeight());
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                tileButtons[(y * 4) + x] = new Button(new Button.ButtonStyle());
                tileButtons[(y * 4) + x].addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        tileClick();
                    }
                });
                tileGrid.add(tileButtons[(y * 4) + x]).width(map.getWidth() / 4).height(map.getHeight() / 4);
            }
            tileGrid.row();
        }
        stage.addActor(tileGrid);
        //Populate tile-grid with invisible buttons and deploy it on to the stage

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
        drawer.filledRectangle(Color.WHITE, 0, (int) (timer.getHeight() + foodCounter.getHeight() + waterCounter.getHeight() + oreCounter.getHeight() + 20), tableWidth, 1);
        //Draw lines in left-hand table
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
        //batch.dispose();
        //game.dispose();
    }

    public void tileClick() {
        tableLeft.row();
        tableLeft.add(new Label("A tile was clicked", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
    }
    //NEED TO ADD AND IMPLEMENT INDEX PARAMETER TO ALLOW FOR TILES TO BE DIFFERENTIATED

    public void constructLeftTable() {
        tableLeft = new Table();
        //Construct left-hand table

        tableLeft.setBounds(0, 0, tableWidth, Gdx.graphics.getHeight());
        //Set boundaries of left-hand table

        tableLeft.center().top();
        //Shift the table towards the top of the screen

        tableLeft.add(timer).top();

        gameFont.setSize(24);
        foodCounter = new Label("Test", new Label.LabelStyle(gameFont.font(), Color.WHITE));
        waterCounter = new Label("Test", new Label.LabelStyle(gameFont.font(), Color.WHITE));
        oreCounter = new Label("Test", new Label.LabelStyle(gameFont.font(), Color.WHITE));

        drawer.addTableRow(tableLeft, new LabelledElement("Food", gameFont, Color.WHITE, foodCounter, 175), 10, 0, 0, 0);
        drawer.addTableRow(tableLeft, new LabelledElement("Water", gameFont, Color.WHITE, waterCounter, 175));
        drawer.addTableRow(tableLeft, new LabelledElement("Ore", gameFont, Color.WHITE, oreCounter, 175));

        drawer.addTableRow(tableLeft, new Label("Roboticon Shop Area", new Label.LabelStyle(gameFont.font(), Color.WHITE)), 20, 0, 0, 0);

        stage.addActor(tableLeft);
        //Add left-hand table to the stage
    }

    public void constructRightTable() {
        tableRight = new Table();
        //Construct right-hand table

        tableRight.setBounds((Gdx.graphics.getWidth() / 2) + (map.getWidth() / 2), 0, tableWidth, Gdx.graphics.getHeight());
        //Set boundaries of right-hand table

        gameFont.setSize(24);
        tableRight.add(new Label("This is the right-hand table", new Label.LabelStyle(gameFont.font(), Color.WHITE)));

        stage.addActor(tableRight);
        //Add right-hand table to the stage
    }
}
