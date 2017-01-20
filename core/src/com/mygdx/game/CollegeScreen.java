package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * @author Duck-Related Team Name in BIG MASSIVE LETTERS
 * @version READ ASSESSMENT 2
 */
public class CollegeScreen implements Screen {


    private Game game; //Stores current game-state, enabling transitions between screens
    private Stage stage;
    private Table tableCentre;
    private Table tableP1; //Player 1 Table
    private Table tableP2; //Player 2 Table
    private TTFont CollegeFont; //Establish college menu font
    private TextButton[] buttons = new TextButton[5]; //Establish menu environment and structure

    public CollegeScreen(Game game) {
        this.game = game;
        //Import current game-state
    }

    @Override
    public void show() {

        stage = new Stage();
        //Initialise stage
        Gdx.input.setInputProcessor(stage);
        //Set the stage up to access user inputs

        CollegeFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 36);
        //Initialise menu font

        //Construct Tables
        constructCentreTable();
        constructP1Table();
        constructP2Table();

        tableP1.debug();
        tableP2.debug();
        tableCentre.debug();
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

    public void constructP1Table() {
        tableP1 = new Table();
        //Construct player 1 table

        tableP1.setBounds(32, 0, 256, Gdx.graphics.getHeight());
        //Set boundaries of player 1 table

        CollegeFont.setSize(42);
        tableP1.add(new Label("Player 1\n", new Label.LabelStyle(CollegeFont.font(), Color.BLACK)));

        stage.addActor(tableP1);
        //Add player 1 table to the stage


    }

    public void constructP2Table() {
        tableP2 = new Table();
        //Construct player 2 table

        tableP2.setBounds(Gdx.graphics.getWidth() - 32 - 256, 0, 256, Gdx.graphics.getHeight());
        //Set boundaries of player 2 table



        CollegeFont.setSize(42);
        tableP2.add(new Label("Player 2", new Label.LabelStyle(CollegeFont.font(), Color.BLACK)));

        CollegeFont.setSize(28);
        tableP2.row();
        tableP2.add(new Label("\n\n..........Emblem..........\n\n\n", new Label.LabelStyle(CollegeFont.font(), Color.BLACK)));

        tableP2.row();
        tableP2.add(new Label("College Name", new Label.LabelStyle(CollegeFont.font(), Color.BLACK)));

        CollegeFont.setSize(16);
        tableP2.row();
        tableP2.add(new Label("Description\n\n\n\n", new Label.LabelStyle(CollegeFont.font(), Color.BLACK)));

        CollegeFont.setSize(28);
        tableP2.row();
        tableP2.add(new Label("\nReady Button", new Label.LabelStyle(CollegeFont.font(), Color.BLACK)));



        stage.addActor(tableP2);
        //Add player 2 table to the stage


    }

    public void constructCentreTable() {
        tableCentre = new Table();
        //construct centre table

        tableCentre.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Fill the screen with the table

        TextButton.TextButtonStyle collegeButtonStyle = new TextButton.TextButtonStyle();
        collegeButtonStyle.font = CollegeFont.font();
        collegeButtonStyle.fontColor = Color.BLACK;
        collegeButtonStyle.pressedOffsetX = 1;
        collegeButtonStyle.pressedOffsetY = -1;
        //Set up the format for the buttons in centre table

        //ADD TITLE BAR
        CollegeFont.setSize(46);
        Label title = new Label("College Selection Screen\n" , new Label.LabelStyle(CollegeFont.font(), Color.BLACK));
        tableCentre.add(title);

        buttons[0] = new TextButton("Alcuin", collegeButtonStyle); //Initialise college buttons using defined style
        buttons[1] = new TextButton("Derwent", collegeButtonStyle);
        buttons[2] = new TextButton("James", collegeButtonStyle);
        buttons[3] = new TextButton("Vanbrugh", collegeButtonStyle);
        buttons[4] = new TextButton("Wentworth", collegeButtonStyle);

        buttons[0].addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        //ADD BUTTONS
        for (int i = 0; i < buttons.length; i++) {
            tableCentre.row();
            tableCentre.add(buttons[i]);
        }

        tableCentre.row();
        tableCentre.add(new Label("\n\n", new Label.LabelStyle(CollegeFont.font(), Color.BLACK)));

        stage.addActor(tableCentre);
    }

}