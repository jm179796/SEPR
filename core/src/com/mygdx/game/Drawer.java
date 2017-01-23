package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
/**
 * @author Duck Related Team Name in Big Massive Letters
 * @since Assessment 2
 * @version Assessment 2
 *
 * An executable version of the game can be found at: https://jm179796.github.io/SEPR/DRTN-Assessment2.jar
 * Our website is: https://jm179796.github.io/SEPR/
 */
public class Drawer {
    /**
     * Holds game-state
     */
    private Game game;

    /**
     * Class constructor
     * Stores the game's state inside the drawer class
     *
     * @param game
     */
    public Drawer (Game game) {
        this.game = game;
        //Import current game-state
    }

    /**
     * Draws a rectangle on the next frame to be rendered
     * Works by kickstarting a rendering pipeline and drawing a rectangle in that pipeline before disposing of it again
     *
     * @param type Defines the type of rectangle to be drawn: it can be filled or line-only
     * @param color Defines the colour of the rectangle to be drawn
     * @param x X-coordinate of the new rectangle's top-left corner
     * @param y Y-coordinate of the new rectangle's top-left corner
     * @param width Width of the new rectangle
     * @param height Height of the new rectangle
     */
    public void rectangle(ShapeRenderer.ShapeType type, Color color, int x, int y, int width, int height, int thickness) {
        ShapeRenderer renderer = new ShapeRenderer();
        //Establish shape-renderer

        renderer.begin(type);
        //Activate the renderer

        renderer.setColor(color);
        //Set the colour of the rectangle to be rendered

        for (int i = 0; i < thickness; i++) {
            renderer.rect(x + i, Gdx.graphics.getHeight() - y - height + i, width - (i * 2), height - (i * 2));
        }
        //Render a rectangle with the specified parameters

        renderer.end();
        //Shut the renderer down after the shape has been drawn

        renderer.dispose();
        //Get rid of the renderer now that it isn't useful anymore
    }

    /**
     * Draws a solid-coloured rectangle on the next frame to be rendered
     * Overloaded variant of the [rectangle()] method which automatically determines the resultant rectangle's
     * rendering method
     *
     * @param color Defines the colour of the rectangle to be drawn
     * @param x X-coordinate of the new rectangle's top-left corner
     * @param y Y-coordinate of the new rectangle's top-left corner
     * @param width Width of the new rectangle
     * @param height Height of the new rectangle
     */
    public void filledRectangle(Color color, int x, int y, int width, int height) {
        rectangle(ShapeRenderer.ShapeType.Filled, color, x, y, width, height, 1);
    }

    /**
     * Draws a line-only rectangle on the next frame to be rendered
     * Overloaded variant of the [rectangle()] method which automatically determines the resultant rectangle's
     * rendering method
     *
     * @param color Defines the colour of the rectangle to be drawn
     * @param x X-coordinate of the new rectangle's top-left corner
     * @param y Y-coordinate of the new rectangle's top-left corner
     * @param width Width of the new rectangle
     * @param height Height of the new rectangle
     */
    public void lineRectangle(Color color, int x, int y, int width, int height, int thickness) {
        rectangle(ShapeRenderer.ShapeType.Line, color, x, y, width, height, thickness);
    }

    /**
     * Draws a bordered, solid-coloured rectangle on the next frame to be rendered
     * This basically draws a filled rectangle and a line-only rectangle of equal dimensions in the same place
     *
     * @param fillColor Defines the fill-colour of the rectangle to be drawn
     * @param lineColor Defines the line-colour of the rectangle to be drawn
     * @param x X-coordinate of the new rectangle's top-left corner
     * @param y Y-coordinate of the new rectangle's top-left corner
     * @param width Width of the new rectangle
     * @param height Height of the new rectangle
     */
    public void borderedRectangle(Color fillColor, Color lineColor, int x, int y, int width, int height, int thickness) {
        rectangle(ShapeRenderer.ShapeType.Filled, fillColor, x, y, width, height, 1);
        rectangle(ShapeRenderer.ShapeType.Line, lineColor, x, y, width, height, thickness);
    }

    /**
     * Prints text directly on to the next frame, foregoing the need to generate any labels or scenes
     * Works by orthographically projecting the bitmaps in the provided TTFont object's internal BitmapFont during
     * a rendering pipeline which only exists while the method itself exists
     *
     * @param text The text to be printed
     * @param font The font of the text to be printed
     * @param x The X-coordinate of the text's location
     * @param y The Y-coordinate of the text's location
     */
    public void text(String text, TTFont font, int x, int y) {
        SpriteBatch batch = new SpriteBatch();
        //Establish rendering batch

        Camera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.setProjectionMatrix(camera.combined);
        //Set up a direct orthographic projection of the provided text

        batch.begin();
        //Start the rendering batch

        font.font().draw(batch, text, x - (Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - y);
        //Print the provided text to the screen...
        //...specifically by simulating the printing process through the orthographic projection of a 3D textual object

        batch.end();
        //End the rendering batch

        batch.dispose();
        //Dispose of the rendering batch now that it's run its course
    }

    /**
     * Draws debug lines around all actors on the specified stage
     *
     * @param stage The stage holding the actors around which to draw debug lines
     */
    public void debug(Stage stage) {
        for (Actor a : stage.getActors()) {
            a.debug();
        }
    }

    /**
     * Automatically adds an actor on to a new row within a specified table
     * Exists primarily to cut down on statement quantities
     *
     * @param table The table to be expanded
     * @param actor The actor to add to the provided table
     * @param width The width of the cell in which the provided actor is to be placed
     * @param height The height of the cell in which the provided actor is to be placed
     * @param padTop The top-most padding of the cell in which the provided actor is to be placed
     * @param padLeft The left-most padding of the cell in which the provided actor is to be placed
     * @param padBottom The bottom-most padding of the cell in which the provided actor is to be placed
     * @param padRight The right-most padding of the cell in which the provided actor is to be placed
     * @param colSpan The number of columns over which the new cell will span
     */
    public void addTableRow(Table table, Actor actor, float width, float height, float padTop, float padLeft, float padBottom, float padRight, int colSpan) {
        if (width == 0 && height == 0) {
            table.row().colspan(colSpan);
        } else {
            table.row().colspan(colSpan).size(width, height);
        }

        table.add(actor).pad(padTop, padLeft, padBottom, padRight);
    }

    /**
     * Automatically adds an actor on to a new row within a specified table
     * Exists primarily to cut down on statement quantities
     * Assumes that the new cell to be created has a width of 1 column
     *
     * @param table The table to be expanded
     * @param actor The actor to add to the provided table
     * @param width The width of the cell in which the provided actor is to be placed
     * @param height The height of the cell in which the provided actor is to be placed
     * @param padTop The top-most padding of the cell in which the provided actor is to be placed
     * @param padLeft The left-most padding of the cell in which the provided actor is to be placed
     * @param padBottom The bottom-most padding of the cell in which the provided actor is to be placed
     * @param padRight The right-most padding of the cell in which the provided actor is to be placed
     */
    public void addTableRow(Table table, Actor actor, float width, float height, float padTop, float padLeft, float padBottom, float padRight) {
        addTableRow(table, actor, width, height, padTop, padLeft, padBottom, padRight, 1);
    }

    /**
     * Automatically adds an actor on to a new row within a specified table
     * Exists primarily to cut down on statement quantities
     * Fits the dimensions of the new cell around the contents of the surrounding cells and the provided actor's size
     *
     * @param table The table to be expanded
     * @param actor The actor to add to the provided table
     * @param padTop The top-most padding of the cell in which the provided actor is to be placed
     * @param padLeft The left-most padding of the cell in which the provided actor is to be placed
     * @param padBottom The bottom-most padding of the cell in which the provided actor is to be placed
     * @param padRight The right-most padding of the cell in which the provided actor is to be placed
     * @param colSpan The number of columns over which the new cell will span
     */
    public void addTableRow(Table table, Actor actor, float padTop, float padLeft, float padBottom, float padRight, int colSpan) {
        addTableRow(table, actor, 0, 0, padTop, padLeft, padBottom, padRight, colSpan);
    }

    /**
     * Automatically adds an actor on to a new row within a specified table
     * Exists primarily to cut down on statement quantities
     * Assumes that the new cell to be created has a width of 1 column
     * Fits the dimensions of the new cell around the contents of the surrounding cells and the provided actor's size
     *
     * @param table The table to be expanded
     * @param actor The actor to add to the provided table
     * @param padTop The top-most padding of the cell in which the provided actor is to be placed
     * @param padLeft The left-most padding of the cell in which the provided actor is to be placed
     * @param padBottom The bottom-most padding of the cell in which the provided actor is to be placed
     * @param padRight The right-most padding of the cell in which the provided actor is to be placed
     */
    public void addTableRow(Table table, Actor actor, float padTop, float padLeft, float padBottom, float padRight) {
        addTableRow(table, actor, 0, 0, padTop, padLeft, padBottom, padRight, 1);
    }

    /**
     * Automatically adds an actor on to a new row within a specified table
     * Exists primarily to cut down on statement quantities
     * Does not pad the new cell
     *
     * @param table The table to be expanded
     * @param actor The actor to add to the provided table
     * @param width The width of the cell in which the provided actor is to be placed
     * @param height The height of the cell in which the provided actor is to be placed
     * @param colSpan The number of columns over which the new cell will span
     */
    public void addTableRow(Table table, Actor actor, float width, float height, int colSpan) {
        addTableRow(table, actor, width, height, 0, 0, 0, 0, colSpan);
    }

    /**
     * Automatically adds an actor on to a new row within a specified table
     * Exists primarily to cut down on statement quantities
     * Assumes that the new cell to be created has a width of 1 column
     * Does not pad the new cell
     *
     * @param table The table to be expanded
     * @param actor The actor to add to the provided table
     * @param width The width of the cell in which the provided actor is to be placed
     * @param height The height of the cell in which the provided actor is to be placed
     */
    public void addTableRow(Table table, Actor actor, float width, float height) {
        addTableRow(table, actor, width, height, 0, 0, 0, 0, 1);
    }

    /**
     * Automatically adds an actor on to a new row within a specified table
     * Exists primarily to cut down on statement quantities
     * Fits the dimensions of the new cell around the contents of the surrounding cells and the provided actor's size
     * Does not pad the new cell
     *
     * @param table The table to be expanded
     * @param actor The actor to add to the provided table
     * @param colSpan The number of columns over which the new cell will span
     */
    public void addTableRow(Table table, Actor actor, int colSpan) {
        addTableRow(table, actor, 0, 0, 0, 0, 0, 0, colSpan);
    }

    /**
     * Automatically adds an actor on to a new row within a specified table
     * Exists primarily to cut down on statement quantities
     * Assumes that the new cell to be created has a width of 1 column
     * Fits the dimensions of the new cell around the contents of the surrounding cells and the provided actor's size
     * Does not pad the new cell
     *
     * @param table The table to be expanded
     * @param actor The actor to add to the provided table
     */
    public void addTableRow(Table table, Actor actor) {
        addTableRow(table, actor, 0, 0, 0, 0, 0, 0, 1);
    }

    /**
     * Stretches the last row in the provided table to span across the maximum number of columns in that table
     *
     * @param table The table containing the cell to be stretched
     */
    public void stretchCurrentCell(Table table) {
        table.getCells().items[table.getRows()].fillX();
    }

    /**
     * Simplifies simultaneously changing TextButtons' colours and enabling/disabling them on the fly
     *
     * @param button The button to be enabled/disabled
     * @param enabled The button's new status
     * @param buttonColor The new colour that the button should assume
     */
    public void switchTextButton(TextButton button, boolean enabled, Color buttonColor) {
        button.getLabel().setColor(buttonColor);
        //Assign a new colour to the specified label

        if (enabled == true) {
            button.setTouchable(Touchable.enabled);
        } else {
            button.setTouchable(Touchable.disabled);
        }
        //Enable or disable the button as specified
    }
}
