package com.mygdx.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * Created by Joseph on 24/11/2016.
 */
public class TTFont {

    private BitmapFont BMFont;
    //This class builds off of the BitmapFont class
    //I would simply extend the class, but that doesn't seem to work

    private FreeTypeFontGenerator TTFGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter TTFStyle;
    //Set up a font-generator and a style configuration for it to work from
    //The font-generator will serve to convert vector-type .TTF fonts into bitmapped .FNT fonts on the fly
    //Just call an instance of the setFont() function to use it


    public TTFont(FileHandle fontFile, int size, Color color) {
        TTFGenerator = new FreeTypeFontGenerator(fontFile);
        TTFStyle = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //Initialise font-generator and font-style objects

        TTFStyle.size = size;
        TTFStyle.color = color;
        TTFStyle.borderWidth = 0;
        TTFStyle.borderColor = Color.BLACK;
        TTFStyle.borderStraight = false;
        TTFStyle.shadowOffsetX = 0;
        TTFStyle.shadowOffsetY = 0;
        TTFStyle.shadowColor = new Color(0, 0, 0, 0.75f);
        //Initialise default font settings

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    @Setter
    public void setFont(FileHandle fontFile) {
        TTFGenerator = new FreeTypeFontGenerator(fontFile);

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    @Getter
    public BitmapFont font() {
        return BMFont;
    }

    @Setter
    public void setSize(int size) {
        TTFStyle.size = size;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    @Getter
    public int size() {
        return TTFStyle.size;
    }

    @Setter
    public void setColor(Color color) {
        TTFStyle.color = color;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    @Getter
    public Color color() {
        return TTFStyle.color;
    }

    @Setter
    public void setBorder(float borderWidth, Color borderColor) {
        TTFStyle.borderWidth = borderWidth;
        TTFStyle.borderColor = borderColor;
        TTFStyle.borderStraight = false;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    @Setter
    public void setBorder(float borderWidth, Color borderColor, boolean borderStraight) {
        TTFStyle.borderWidth = borderWidth;
        TTFStyle.borderColor = borderColor;
        TTFStyle.borderStraight = borderStraight;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    @Getter
    public float borderWidth() {
        return TTFStyle.borderWidth;
    }

    @Getter
    public Color borderColor() {
        return TTFStyle.borderColor;
    }

    public void removeBorder() {
        TTFStyle.borderWidth = 0;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    @Setter
    public void setShadow(int shadowOffsetX, int shadowOffsetY, Color shadowColor) {
        TTFStyle.shadowOffsetX = shadowOffsetX;
        TTFStyle.shadowOffsetY = shadowOffsetY;
        TTFStyle.shadowColor = shadowColor;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    @Getter
    public Color shadowColor() {
        return TTFStyle.shadowColor;
    }

    public void removeShadow() {
        TTFStyle.shadowOffsetX = 0;
        TTFStyle.shadowOffsetY = 0;
        TTFStyle.shadowColor = new Color(0, 0, 0, 0.75f);

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }
}
