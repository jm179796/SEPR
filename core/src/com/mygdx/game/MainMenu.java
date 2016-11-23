package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

        FreeTypeFontGenerator TTFGenerator;
        FreeTypeFontGenerator.FreeTypeFontParameter TTFStyle = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //Set up a font-generator and a style configuration for it to work from
        //This will serve to convert vectorial .TTF fonts into bitmapped .FNT fonts on the fly
        //Just instantiate TTFGenerator with a reference to a .TTF file...
        //...change the settings in TTFStyle...

        Gdx.input.setInputProcessor(stage);
        //Set the stage up to access user inputs

        BitmapFont testFont = new BitmapFont(Gdx.files.internal("font/testfont.fnt"));
        //Set up font for easy access later on

        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Fill the screen with the table
        //This is bound to change in the future for obvious reasons

        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = testFont;
        menuButtonStyle.pressedOffsetX = 1;
        menuButtonStyle.pressedOffsetY = -1;
        //Set up the format for the buttons on the menu
        //FONTS NEED TO BE STORED AS .FNT FILES
        //STILL NEED TO SORT OUT BUTTON ANIMATIONS

        testButton = new TextButton("TEST BUTTON", menuButtonStyle);
        testButton.pad(20);
        //Initialise test button using defined style

        //ROW 1
        Label title = new Label("Duck-Related Game", new Label.LabelStyle(testFont, Color.BLACK));
        title.setFontScale(2);
        table.add(title);

        //ROW 2
        table.row();
        table.add(testButton);

        //FINALISE TABLE
        table.debug();
        stage.addActor(table);
        //"Debug" instruction renders temporary borders around elements
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //OpenGL nonsense
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
