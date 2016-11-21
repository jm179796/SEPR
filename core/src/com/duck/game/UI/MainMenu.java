package com.duck.game.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Joseph on 21/11/2016.
 */
public class MainMenu implements Screen {

    private Game game;

    private SpriteBatch batch;
    private Sprite testimage;

    private Stage stage;
    private Table table;

    public MainMenu(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        testimage = new Sprite(new Texture("SoonTMtest.png"));
        testimage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Create logo sprite and match size to window size

        stage = new Stage();
        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //OpenGL nonsense

        batch.begin();
        testimage.draw(batch);
        batch.end();
        //Draw splash logo in current batch
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
