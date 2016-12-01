package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Joseph on 30/11/2016.
 */
public class Drawer {
    private Game game;
    //Stores current game-state

    public Drawer (Game game) {
        this.game = game;
        //Import current game-state
    }

    public void rectangle(ShapeRenderer.ShapeType type, Color color, int x, int y, int width, int height) {
        ShapeRenderer renderer = new ShapeRenderer();
        //Establish shape-renderer

        renderer.begin(type);
        //Activate the renderer

        renderer.setColor(color);
        //Set the colour of the rectangle to be rendered

        renderer.rect(x, Gdx.graphics.getHeight() - y - height, width, height);
        //Render a rectangle with the specified parameters

        renderer.end();
        //Shut the renderer down after the shape has been drawn
    }

    public void filledRectangle(Color color, int x, int y, int width, int height) {
        rectangle(ShapeRenderer.ShapeType.Filled, color, x, y, width, height);
    }

    public void lineRectangle(Color color, int x, int y, int width, int height) {
        rectangle(ShapeRenderer.ShapeType.Line, color, x, y, width, height);
    }

    public void debug(Stage stage) {
        for (Actor a : stage.getActors()) {
            a.debug();
        }
    }
    //Draws temporary debug lines around all of the actors on the stage

    public void addTableRow(Table table, Actor actor, float padTop, float padLeft, float padBottom, float padRight) {
        table.row();
        table.add(actor).pad(padTop, padLeft, padBottom, padRight);
    }

    public void addTableRow(Table table, Actor actor) {
        addTableRow(table, actor, 0, 0, 0, 0);
    }
}
