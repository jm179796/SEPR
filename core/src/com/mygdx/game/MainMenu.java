package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by Joseph on 21/11/2016.
 */
public class MainMenu implements Screen {

    private Game game;
    //Stores current game-state, enabling transitions between screens

    private Stage stage;
    private Table table;
    private TextButton[] buttons = new TextButton[3];
    //Establish menu environment and structure

    private TTFont menuFont;
    private TTFont titleFont;
    private TTFont tempFont;
    //Establish menu font

    public MainMenu(Game game) {
        this.game = game;
    }
    //Import current game-state

    private Drawer drawer;
    //Import standard drawing functions

    private SpriteBatch batch;
    private Sprite background;
    //Declare background image sprite and render-batch in which to put it

    @Override
    public void show() {
        drawer = new Drawer(game);

        batch = new SpriteBatch();
        //Initialise sprite-batch

        stage = new Stage();
        table = new Table();
        //Initialise stage and button-table

        titleFont = new TTFont(Gdx.files.internal("font/earthorbiterxtrabold.ttf"), 120, 2, Color.BLACK, false);
        menuFont = new TTFont(Gdx.files.internal("font/enterthegrid.ttf"), 36, 2, Color.BLACK, false);
        tempFont = new TTFont(Gdx.files.internal("font/earthorbiter.ttf"), 24, 2, Color.BLACK, false);
        //Initialise menu font

        Gdx.input.setInputProcessor(stage);
        //Set the stage up to accept user inputs

        background = new Sprite(new Texture("image/MenuBG.png"));
        background.setSize(background.getWidth(), background.getHeight());
        background.setCenter(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        //Create logo sprite and re-size/re-position it to fit into game window

        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Fill the screen with the table
        //This is bound to change in the future for obvious reasons

        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = menuFont.font();
        menuButtonStyle.fontColor = Color.WHITE;
        menuButtonStyle.pressedOffsetX = 1;
        menuButtonStyle.pressedOffsetY = -1;
        //Set up the format for the buttons on the menu
        //STILL NEED TO SORT OUT BUTTON ANIMATIONS

        buttons[0] = new TextButton("Start Game", menuButtonStyle);
        buttons[0].addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        buttons[1] = new TextButton("How to Play", menuButtonStyle);
        buttons[2] = new TextButton("Leaderboard", menuButtonStyle);
        //Initialise menu buttons using defined style

        //ADD TITLE BAR
        drawer.addTableRow(table, new Label("Sabbaticoup", new Label.LabelStyle(titleFont.font(), Color.WHITE)), 0, 0, 0, 0);
        drawer.addTableRow(table, new Label("(Title TBC)", new Label.LabelStyle(tempFont.font(), Color.WHITE)), 0, 0, 50, 0);

        //ADD BUTTONS
        for (int i = 0; i < buttons.length; i++) {
            drawer.addTableRow(table, buttons[i]);
        }

        //FINALISE TABLE
        stage.addActor(table);

        drawer.debug(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //OpenGL nonsense
        //First instruction sets background colour

        batch.begin();
        background.draw(batch);
        batch.end();

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
        menuFont.dispose();

        stage.dispose();
    }
}
