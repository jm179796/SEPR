package com.duck.game.UI;

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
import java.util.TimerTask;

public class SplashScreen implements Screen {

    private Game game;

    private SpriteBatch batch;
    private Sprite logo;

    public SplashScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        logo = new Sprite(new Texture("logo.png"));
        logo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Create logo sprite and match size to window size
    }

    @Override
    public void render(float delta) {
        Timer timer = new Timer();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //OpenGL nonsense

        batch.begin();
        logo.draw(batch);
        batch.end();
        //Draw splash logo in current batch

        game.setScreen(new MainMenu(game));
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
