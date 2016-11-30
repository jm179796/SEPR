package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Nico on 25/11/2016.
 */
public class CollegeScreen implements Screen {


    private Game game; //Stores current game-state, enabling transitions between screens
    private Stage stage;
    private Table tableCentre;
    private TTFont CollegeFont; //Establish College Menu font
    private TextButton[] buttons = new TextButton[5]; //Establish menu environment and structure

    public CollegeScreen(Game game) {
        this.game = game;
        //Import current game-state
    }

    @Override
    public void show() {
        stage = new Stage();
        tableCentre = new Table();
        //Initialise stage and button-table

        CollegeFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 36, Color.BLACK);
        //Initialise menu font

        Gdx.input.setInputProcessor(stage);
        //Set the stage up to access user inputs

        tableCentre.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Fill the screen with the table
        //This is bound to change in the future for obvious reasons

        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = CollegeFont.font();
        menuButtonStyle.pressedOffsetX = 1;
        menuButtonStyle.pressedOffsetY = -1;
        //Set up the format for the buttons on the menu

        buttons[0] = new TextButton("Alcuin", menuButtonStyle); //Initialise College buttons using defined style
        buttons[1] = new TextButton("Derwent", menuButtonStyle);
        buttons[2] = new TextButton("James", menuButtonStyle);
        buttons[3] = new TextButton("Vanbrugh", menuButtonStyle);
        buttons[4] = new TextButton("Wentworth", menuButtonStyle);

        //ADD TITLE BAR
        CollegeFont.setSize(46);
        Label title = new Label("College Selection Screen\n" , new Label.LabelStyle(CollegeFont.font(), Color.BLACK));
        tableCentre.add(title);

        //ADD BUTTONS
        for (int i = 0; i < buttons.length; i++) {
            tableCentre.row();
            tableCentre.add(buttons[i]);
        }

        //FINALISE TABLE
        tableCentre.debug();
        stage.addActor(tableCentre);
        //"Debug" instruction renders temporary borders around elements


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //OpenGL Code
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

    }
}