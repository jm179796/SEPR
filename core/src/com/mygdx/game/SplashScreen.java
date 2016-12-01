package com.mygdx.game;

/**
 * Created by Joseph on 21/11/2016.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Timer;

public class SplashScreen implements Screen {

    private Game game;
    //Stores current game-state, enabling transitions between screens

    private SpriteBatch batch;
    private Sprite logo;
    //Declare logo sprite and render-batch in which to put it

    public SplashScreen(Game game) {
        this.game = game;
    }
    //Import current game-state

    @Override
    public void show() {
        batch = new SpriteBatch();
        //Initialise sprite-batch

        logo = new Sprite(new Texture("image/logo.png"));
        logo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Create logo sprite and match logo size to window size
    }

    @Override
    public void render(float delta) {
        //Timer timer = new Timer();
        //Establish timer
        //TIMER IS BROKEN RIGHT NOW SO THIS HAS BEEN DISABLED FOR THE MOMENT

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //This pile of red tape needs to be here for some reason

        batch.begin();
        logo.draw(batch);
        batch.end();
        //Draw splash logo in current batch

        //DELAY CODE WILL GO HERE...
        //...WHEN I WORK OUT HOW TO IMPLEMENT THE TIMER PROPERLY

        game.setScreen(new MainMenu(game));
        //Switch to main menu after delay
    }

    //About the stuff below...
    //https://img.ifcdn.com/images/8e02c24ac8376c15c2533ccef3d8f9d1569316499c080d418c83a93f861eb494_1.gif
    //It needs to be here, though, else this class can't correctly store game-states

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
