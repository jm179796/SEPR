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
import javafx.scene.control.Tab;

public class GameScreen implements Screen{

    private Game game; //Stores current game-state, enabling transitions between screens
    //private SpriteBatch batch;
    //private Sprite map; //Declare map sprite and render-batch in which to put it

    private Stage stage;
    private Table tableLeft;
    private Table tableRight;

    private TTFont gameFont;
    //Establish menu font

    private Image map;

    private float tableWidth;

    private GameTimer timer;

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

        timer = new GameTimer(2, 0, gameFont);

        tableLeft.setBounds(0, 0, tableWidth, Gdx.graphics.getHeight());
        tableRight.setBounds(768, 0, tableWidth, Gdx.graphics.getHeight());
        //Set table boundaries

        tableLeft.add(timer);
        tableRight.add(new Label("This is the right-hand table", new Label.LabelStyle(gameFont.font(), Color.WHITE)));

        tableLeft.debug();
        tableRight.debug();
        stage.addActor(map);
        stage.addActor(tableLeft);
        stage.addActor(tableRight);

        //batch = new SpriteBatch();
        //Initialise sprite-batch

        //map = new Sprite(new Texture("image/TestMap.png"));
        //map.setSize(x,y); //sets window to size x,y

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //OpenGL nonsense
        //First instruction sets background colour

        //batch.begin();
        //map.draw(batch);
        //batch.end();
        //Draw map in batch

        stage.act(delta);
        stage.draw();
        //Draw the stage onto the screen

        timer.start();
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
