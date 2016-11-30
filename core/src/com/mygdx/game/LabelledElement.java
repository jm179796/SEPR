package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.awt.*;

/**
 * Created by Joseph on 30/11/2016.
 */
public class LabelledElement extends Table {

    public LabelledElement(String labelText, TTFont labelFont, boolean labelRight, Actor actor) {
        super();

        if (labelRight == false) {
            this.add(new Label(labelText, new Label.LabelStyle(labelFont.font(), Color.BLACK)));
            this.add(actor);
        } else {
            this.add(actor);
            this.add(new Label(labelText, new Label.LabelStyle(labelFont.font(), Color.BLACK)));
        }
    }

    public LabelledElement(String labelText, TTFont labelFont, Actor actor) {
        this(labelText, labelFont, false, actor);
    }
}
