package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * @author Duck-Related Team Name in BIG MASSIVE LETTERS
 * @version READ ASSESSMENT 2
 */

public class Overlay extends Stage {

    /**
     * Stores current game-state, granting direct render access to the class
     */
    private Game game;

    /**
     * Table providing spatial framework for the overlay
     */
    private Table table;

    /**
     * Object defining QOL drawing functions for rectangles and on-screen tables
     * Used in this class to draw overlay area
     */
    private Drawer drawer;

    /**
     * Width of the overlay region
     */
    private int width;

    /**
     * Height of the overlay region
     */
    private int height;

    /**
     * Thickness of the overlay region's border
     */
    private int lineThickness;

    /**
     * The colour of the overlay's core
     */
    private Color fillColor;

    /**
     * The colour of the overlay's border
     */
    private Color lineColor;

    public Overlay(Game game, Color fillColor, Color lineColour, int width, int height, int lineThickness) {
        super();
        //Construct the core stage

        this.game = game;
        //Import current game-state to access the game's renderer

        drawer = new Drawer(game);
        //Import QOL drawing functions

        this.fillColor = fillColor;
        this.lineColor = lineColour;
        this.width = width;
        this.height = height;
        this.lineThickness = lineThickness;
        //Import overlay size/colour variables

        table = new Table();
        //Instantiate the table which will provide the overlay's spatial framework

        table.setBounds((Gdx.graphics.getWidth() - this.width / 2), (Gdx.graphics.getHeight() - this.height / 2), this.width, this.height);
        //Set the overlay up to inhabit the centre of the window and cover the provided size

        this.addActor(table);
        //Bind the overlay's spatial framework to the core stage
    }

    @Override
    public void draw() {
        super.draw();

        drawer.borderedRectangle(fillColor, lineColor, (Gdx.graphics.getWidth() - this.width / 2), (Gdx.graphics.getHeight() - this.height / 2), width, height, lineThickness);
    }

    public Table table(){
        return table;
    }
}
