package com.mygdx.game;

/**
 * Created by Joseph on 21/11/2016.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

public class SplashScreen implements Screen {

    /**
     * Stores current game-state, enabling transitions between screens
     */
    private Game game;

    /**
     * Batch that manages the rendering pipeline for all of the images to be displayed on the screen
     */
    private SpriteBatch batch;

    /**
     * The object which will encode the team's logo
     */
    private Sprite logo;

    /**
     * Timer which will temporarily stall the splash screen before it gives way to the main menu
     */
    private Timer timer;

    /**
     * Variable storing the number of seconds over which the splash screen will hang
     */
    private int delay;

    /**
     * Enables keyboard inputs to be registered and bound to functions
     * Used in this context to permit skipping the splash screen by hitting the keyboard or clicking the mouse
     */
    private InputProcessor inputProcessor = new InputProcessor() {
        @Override
        public boolean keyDown(int keycode) {
            timer.stop();
            game.setScreen(new MainMenu(game));
            return false;
            //Skip past the splash screen if the game receives any keyboard input
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            timer.stop();
            game.setScreen(new MainMenu(game));
            return false;
            //Skip past the splash screen if the game receives any mouse input
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    };

    /**
     * The constructor for the class.
     *
     * @param game Variable storing the game's state
     */
    public SplashScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        //Initialise sprite rendering pipeline

        logo = new Sprite(new Texture("image/logo.png"));
        logo.setSize(logo.getWidth() / (float) 2.3, logo.getHeight() / (float) 2.3);
        logo.setCenter(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        //Create logo sprite and re-size/re-position it to fit into game window

        delay = 3;
        //Set the splash-screen's delay

        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new MainMenu(game));
            }
        }, delay);
        //Open the game's main menu when the timer reaches its first interval
        //This interval is set by the value of the "delay" variable

        Gdx.input.setInputProcessor(inputProcessor);
        //Set the splash-screen to detect inputs
        //If a keystroke or a mouse-click is detected, open the menu straight away
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Set splash-screen background colour

        batch.begin();
        logo.draw(batch);
        batch.end();
        //Draw logo texture on screen

        timer.start();
        //Start the delay timer
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
        batch.dispose();
    }
    //Dispose of the splash-screen's data once the main menu opens up
}
