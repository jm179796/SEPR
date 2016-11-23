package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
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

    private FreeTypeFontGenerator TTFGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter TTFStyle = new FreeTypeFontGenerator.FreeTypeFontParameter();
    //Set up a font-generator and a style configuration for it to work from
    //The font-generator will serve to convert vector-type .TTF fonts into bitmapped .FNT fonts on the fly
    //Just call an instance of the setFont() function to use it

    public MainMenu(Game game) {
        this.game = game;
    }
    //Import current game-state

    @Override
    public void show() {
        stage = new Stage();
        table = new Table();
        //Initialise stage and button-table

        Gdx.input.setInputProcessor(stage);
        //Set the stage up to access user inputs

        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Fill the screen with the table
        //This is bound to change in the future for obvious reasons

        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = setFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 36, Color.BLACK);
        menuButtonStyle.pressedOffsetX = 1;
        menuButtonStyle.pressedOffsetY = -1;
        //Set up the format for the buttons on the menu
        //FONTS NEED TO BE STORED AS .FNT FILES
        //STILL NEED TO SORT OUT BUTTON ANIMATIONS

        testButton = new TextButton("TEST BUTTON", menuButtonStyle);
        testButton.pad(20);
        //Initialise test button using defined style

        //ROW 1
        Label title = new Label("Duck-Related Game", new Label.LabelStyle(TTFGenerator.generateFont(TTFStyle), Color.BLACK));
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

    public BitmapFont setFont(FileHandle fontFile, int size, Color color) {
        TTFGenerator = new FreeTypeFontGenerator(fontFile);
        TTFStyle.size = size;
        TTFStyle.color = color;
        TTFStyle.borderWidth = 0;
        TTFStyle.borderColor = Color.BLACK;
        TTFStyle.borderStraight = false;
        TTFStyle.shadowOffsetX = 0;
        TTFStyle.shadowOffsetY = 0;
        TTFStyle.shadowColor = new Color(0, 0, 0, 0.75f);

        return (TTFGenerator.generateFont(TTFStyle));
    }
    //Sets font without border or shadow

    public BitmapFont setFont(FileHandle fontFile, int size, Color color, float borderWidth, Color borderColor, boolean borderStraight) {
        TTFGenerator = new FreeTypeFontGenerator(fontFile);
        TTFStyle.size = size;
        TTFStyle.color = color;
        TTFStyle.borderWidth = borderWidth;
        TTFStyle.borderColor = borderColor;
        TTFStyle.borderStraight = borderStraight;
        TTFStyle.shadowOffsetX = 0;
        TTFStyle.shadowOffsetY = 0;
        TTFStyle.shadowColor = new Color(0, 0, 0, 0.75f);

        return (TTFGenerator.generateFont(TTFStyle));
    }
    //Sets font without shadow but with border

    public BitmapFont setFont(FileHandle fontFile, int size, Color color, int shadowOffsetX, int shadowOffsetY, Color shadowColor) {
        TTFGenerator = new FreeTypeFontGenerator(fontFile);
        TTFStyle.size = size;
        TTFStyle.color = color;
        TTFStyle.borderWidth = 0;
        TTFStyle.borderColor = Color.BLACK;
        TTFStyle.borderStraight = false;
        TTFStyle.shadowOffsetX = shadowOffsetX;
        TTFStyle.shadowOffsetY = shadowOffsetY;
        TTFStyle.shadowColor = shadowColor;

        return (TTFGenerator.generateFont(TTFStyle));
    }
    //Sets font without border but with shadow

    public BitmapFont setFont(FileHandle fontFile, int size, Color color, float borderWidth, Color borderColor, boolean borderStraight, int shadowOffsetX, int shadowOffsetY, Color shadowColor) {
        TTFGenerator = new FreeTypeFontGenerator(fontFile);
        TTFStyle.size = size;
        TTFStyle.color = color;
        TTFStyle.borderWidth = borderWidth;
        TTFStyle.borderColor = borderColor;
        TTFStyle.borderStraight = borderStraight;
        TTFStyle.shadowOffsetX = shadowOffsetX;
        TTFStyle.shadowOffsetY = shadowOffsetY;
        TTFStyle.shadowColor = shadowColor;

        return (TTFGenerator.generateFont(TTFStyle));
    }
    //Sets font with border and shadow


}
