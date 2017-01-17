package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Joseph on 30/11/2016.
 */
public class LabelledElement extends Table {

    public LabelledElement(String labelText, TTFont labelFont, Color labelColor, boolean labelRight, Actor actor, float labelSpacing, float actorSpacing) {
        super();
        //Set up table format

        if (labelRight == false) {
            if (labelSpacing == 0) {
                this.add(new Label(labelText, new Label.LabelStyle(labelFont.font(), labelColor)));
            } else {
                this.add(new Label(labelText, new Label.LabelStyle(labelFont.font(), labelColor))).left().width(labelSpacing);
            }

            if (actorSpacing == 0) {
                this.add(actor);
            } else
            {
                this.add(actor).width(actorSpacing);
            }
        } else {
            if (actorSpacing == 0) {
                this.add(actor);
            } else {
                this.add(actor).left().width(actorSpacing);
            }

            if (labelSpacing == 0) {
                this.add(new Label(labelText, new Label.LabelStyle(labelFont.font(), labelColor)));
            } else {
                this.add(new Label(labelText, new Label.LabelStyle(labelFont.font(), labelColor))).width(labelSpacing);
            }
        }
        //Add label and actor to the table in whatever order the user specifies
    }

    public LabelledElement(String labelText, TTFont labelFont, Color labelColor, Actor actor, float labelSpacing, float actorSpacing) {
        this(labelText, labelFont, labelColor, false, actor, labelSpacing, actorSpacing);
    }
}
