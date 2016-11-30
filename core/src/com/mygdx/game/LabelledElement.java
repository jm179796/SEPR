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

    public LabelledElement(String labelText, TTFont labelFont, Color labelColor, boolean labelRight, Actor actor, float spacing) {
        super();
        //Set up table format

        if (labelRight == false) {
            this.add(new Label(labelText, new Label.LabelStyle(labelFont.font(), labelColor))).left().width(spacing);
            this.add(actor);
        } else {
            this.add(actor);
            this.add(new Label(labelText, new Label.LabelStyle(labelFont.font(), labelColor))).right().width(spacing);
        }
        //Add label and actor to the table in whatever order the user specifies
    }

    public LabelledElement(String labelText, TTFont labelFont, Color labelColor, Actor actor, float spacing) {
        this(labelText, labelFont, labelColor, false, actor, spacing);
    }
}
