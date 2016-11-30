package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by Joseph on 30/11/2016.
 */
public class Tile extends Button {

    private Runnable runnable;
    //Establish object to hold button function

    public Tile(final Runnable runnable) {
        super(new ButtonStyle());

        this.runnable = runnable;
        //Retrieve and store an independent method that the tile can be bound to

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                runnable.run();
            }
        });
    }

    public Runnable getFunction() {
        return runnable;
    }

    public void runFunction() {
        runnable.run();
    }
}
