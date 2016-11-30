package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

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


    public void rectangle(ShapeRenderer renderer, Color color, ShapeRenderer.ShapeType type, int x, int y, int width, int height, boolean endAfterDraw) {
        if (renderer.isDrawing() && renderer.getCurrentType() != type) {
            renderer.set(type);
        } else if (!renderer.isDrawing()) {
            renderer.begin(type);
        }
        //Set renderer to render given shape-type if it isn't set to render that type already...
        //...or turn it on if it's currently inactive

        renderer.setColor(color);
        //Set the colour of the rectangle to be rendered

        renderer.rect(x, Gdx.graphics.getHeight() - y, width, height);
        //Render a rectangle with the specified parameters

        if (endAfterDraw == true) {
            renderer.end();
        }
        //Shut the renderer down if the user wants to
    }

    public void rectangle(ShapeRenderer renderer, Color color, ShapeRenderer.ShapeType type, int x, int y, int width, int height) {
        rectangle(renderer, color, type, x, y, width, height, false);
    }

    public void filledRectangle(ShapeRenderer renderer, Color color, int x, int y, int width, int height, boolean endAfterDraw) {
        rectangle(renderer, color, ShapeRenderer.ShapeType.Filled, x, y, width, height, endAfterDraw);
    }

    public void filledRectangle(ShapeRenderer renderer, Color color, int x, int y, int width, int height) {
        rectangle(renderer, color, ShapeRenderer.ShapeType.Filled, x, y, width, height, false);
    }

    public void lineRectangle(ShapeRenderer renderer, Color color, int x, int y, int width, int height, boolean endAfterDraw) {
        rectangle(renderer, color, ShapeRenderer.ShapeType.Line, x, y, width, height, endAfterDraw);
    }

    public void lineRectangle(ShapeRenderer renderer, Color color, int x, int y, int width, int height) {
        rectangle(renderer, color, ShapeRenderer.ShapeType.Line, x, y, width, height, false);
    }

    public void debug(Stage stage) {
        for (Actor a : stage.getActors()) {
            a.debug();
        }
    }
    //Draws temporary debug lines around all of the actors on the stage
}
