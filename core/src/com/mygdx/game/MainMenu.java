package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Joseph on 21/11/2016.
 */
public class MainMenu implements Screen {

    private Game game;
    //Stores current game-state, enabling transitions between screens

    private Stage stage;
    private Table table;
    private TextButton testButton;
    //Establish menu environment and structure

    public MainMenu(Game game) {
        this.game = game;
    }
    //Import current game-state

    @Override
    public void show() {
        stage = new Stage();
        table = new Table();
        //Initialise stage and button-table

        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Fill the screen with the table
        //This is bound to change in the future for obvious reasons

        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = new BitmapFont(Gdx.files.internal("font/testfontbignoodle.ttf"), false);
        //Set up the format for the buttons on the menu
        //NEED TO CONFIGURE FONT

        testButton = new TextButton("TEST", menuButtonStyle);
        //Initialise test button using defined style

        table.add(testButton);
        table.debug();
        stage.addActor(table);
        //Add test button to table on the interface
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //OpenGL nonsense

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

    }
}
