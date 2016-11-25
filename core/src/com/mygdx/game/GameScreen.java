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
    private Table table;

    private TTFont gameFont;
    //Establish menu font

    public GameScreen(Game game) {
        this.game = game;
        //Import current game-state
    }


    @Override
    public void show() {
        stage = new Stage();
        table = new Table();
        //Initialise stage and button-table

        gameFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 24, Color.WHITE);

        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Fill the screen with the table
        //This is bound to change in the future for obvious reasons

        table.add(new Label("This is a label", new Label.LabelStyle(gameFont.font(), Color.WHITE)));

        table.debug();
        stage.addActor(new Image(new Texture("image/TestMap.png")));
        stage.addActor(table);

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
