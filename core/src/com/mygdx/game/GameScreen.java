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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreen implements Screen{

    private Game game;
    //Stores current game-state, enabling transitions between screens

    private Stage stage;
    private Table tableLeft;
    private Table tableRight;
    //Establish stage and side-hand tables

    private Table buttonGrid;
    //Establish grid of buttons over central map

    private TTFont gameFont;
    //Establish menu font

    private Image map;
    //Establish in-game map

    private float tableWidth;
    //Establish variable for holding sizes of in-game tables

    private GameTimer timer;
    //Establish game-timer

    private Button[] tileButtons;
    //Establish invisible buttons to lay over the map's tiles

    public GameScreen(Game game) {
        this.game = game;
        //Import current game-state
    }


    @Override
    public void show() {
        stage = new Stage();
        tableLeft = new Table();
        tableRight = new Table();
        buttonGrid = new Table();
        //Initialise stage, side-tables and button-grid
        Gdx.input.setInputProcessor(stage);
        //Prepares the stage to accept user inputs
        gameFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 24, Color.WHITE);
        //Set font for game interface

        map = new Image(new Texture("image/TestMap.png"));
        map.setPosition((Gdx.graphics.getWidth() / 2) - (map.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (map.getHeight() / 2));
        //Initialise map texture

        tileButtons = new Button[16];
        //Initialise tile-buttons

        tableWidth = (Gdx.graphics.getWidth() - map.getWidth()) / 2;
        //Set widths of side-hand tables
        //This will always be 256 for as long as the size of the game's window is fixed
        //The purpose of this variable is to facilitate the later implementation of window resizing

        tableLeft.setBounds(0, 0, tableWidth, Gdx.graphics.getHeight());
        tableRight.setBounds((Gdx.graphics.getWidth() / 2) + (map.getWidth() / 2), 0, tableWidth, Gdx.graphics.getHeight());
        //Set side-table boundaries

        tableRight.add(new Label("This is the right-hand table", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
        //Add timer and test-label to side-hand tables

        buttonGrid.setBounds((Gdx.graphics.getWidth() / 2) - (map.getWidth() / 2), 0, map.getWidth(), map.getHeight());
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                tileButtons[(y * 4) + x] = new Button(new Button.ButtonStyle());
                tileButtons[(y * 4) + x].addListener(new ClickListener() {
                    public void clicked(InputEvent event) {
                tileButtons[(y * 4) + x].addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        tileClick();
                    }
                });
                buttonGrid.add(tileButtons[(y * 4) + x]).width(map.getWidth() / 4).height(map.getHeight() / 4);
            }
            buttonGrid.row();
        }}}}
        //Set up button-grid over the map and populate it with invisible buttons

        timer = new GameTimer(120, gameFont, new Runnable() {
            @Override
            public void run() {
                tableLeft.row();
                tableLeft.add(new Label("This pops up when the timer runs out", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
            }
        });
        tableLeft.add(timer);
        //Set up game timer
        //"Runnable" parameter specifies code to be executed when the timer runs out

        tableLeft.debug();
        tableRight.debug();
        buttonGrid.debug();
        //Render table boundaries for testing purposes
        stage.addActor(map);
        stage.addActor(tableLeft);
        stage.addActor(tableRight);
        stage.addActor(buttonGrid);
        //Add map, side-tables and button-grid to game-screen

        //timer.start();
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

}
