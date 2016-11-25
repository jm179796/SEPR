package com.mygdx.game;

/**
 * Created by Nico on 23/11/2016.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen{

    private Game game; //Stores current game-state, enabling transitions between screens
    private SpriteBatch batch;
    private Sprite map; //Declare map sprite and render-batch in which to put it

    public GameScreen(Game game) {
        this.game = game;
        //Import current game-state
    }


    @Override
    public void show() {

        batch = new SpriteBatch();
        //Initialise sprite-batch

        map = new Sprite(new Texture("TestMap.png"));
        //map.setSize(x,y); //sets window to size x,y

    }

    @Override
    public void render(float delta) {

        batch.begin();
        map.draw(batch);
        batch.end();
        //Draw map in batch
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
        batch.dispose();
        game.dispose();
    }




}
