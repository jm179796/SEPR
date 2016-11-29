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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import javafx.scene.control.Tab;

public class GameScreen implements Screen{

    private Game game;
    //Stores current game-state, enabling transitions between screens

    private Stage stage;
    private Table tableLeft;
    private Table tableRight;
    //Establish stage and side-hand tables

    private Table buttonGrid;

    private TTFont gameFont;
    //Establish menu font

    private Image map;
    //Establish in-game map

    private float tableWidth;
    //Establish variable for holding sizes of in-game tables

    private GameTimer timer;
    //Establish game-timer

    public GameScreen(Game game) {
        this.game = game;
        //Import current game-state
    }


    @Override
    public void show() {
        stage = new Stage();
        tableLeft = new Table();
        tableRight = new Table();
        //Initialise stage and button-table

        gameFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 24, Color.WHITE);
        //Set font for game interface

        map = new Image(new Texture("image/TestMap.png"));
        map.setPosition((Gdx.graphics.getWidth() / 2) - (map.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (map.getHeight() / 2));
        //Initialise map texture

        tableWidth = (Gdx.graphics.getWidth() - map.getWidth()) / 2;
        //Set widths of side-hand tables
        //This will always be 256 for as long as the size of the game's window is fixed
        //The purpose of this variable is to facilitate the later implementation of window resizing

        timer = new GameTimer(120, gameFont, new Runnable() {
            @Override
            public void run() {
                tableLeft.row();
                tableLeft.add(new Label("This pops up when the timer runs out", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
            }
        });
        //Set up game timer

        tableLeft.setBounds(0, 0, tableWidth, Gdx.graphics.getHeight());
        tableRight.setBounds((Gdx.graphics.getWidth() / 2) + (map.getWidth() / 2), 0, tableWidth, Gdx.graphics.getHeight());
        //Set table boundaries

        tableLeft.add(timer);
        tableRight.add(new Label("This is the right-hand table", new Label.LabelStyle(gameFont.font(), Color.WHITE)));
        //Add timer and test-label to side-hand tables

        tableLeft.debug();
        tableRight.debug();
        //Render table boundaries for testing purposes
        stage.addActor(map);
        stage.addActor(tableLeft);
        stage.addActor(tableRight);
        //Add map and tables to game screen

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




}
