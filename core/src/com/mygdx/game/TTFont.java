package com.mygdx.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by Joseph on 24/11/2016.
 */
public class TTFont {

    /**
     *
     */
    private BitmapFont BMFont;
    //This class builds off of the BitmapFont class
    //I would simply extend the class, but that doesn't seem to work

    /**
     *
     */
    private FreeTypeFontGenerator TTFGenerator;

    /**
     *
     */
    private FreeTypeFontGenerator.FreeTypeFontParameter TTFStyle;
    //Set up a font-generator and a style configuration for it to work from
    //The font-generator will serve to convert vector-type .TTF fonts into bitmapped .FNT fonts on the fly
    //Just call an instance of the setFont() function to use it


    /**
     * Constructor
     * @param fontFile
     * @param size
     * @param borderWidth
     * @param borderColor
     * @param borderStraight
     * @param shadowOffsetX
     * @param shadowOffsetY
     * @param shadowColor
     */
    public TTFont(FileHandle fontFile, int size, float borderWidth, Color borderColor, boolean borderStraight, int shadowOffsetX, int shadowOffsetY, Color shadowColor) {
        TTFGenerator = new FreeTypeFontGenerator(fontFile);
        TTFStyle = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //Initialise font-generator and font-style objects

        TTFStyle.size = size;
        TTFStyle.borderWidth = borderWidth;
        TTFStyle.borderColor = borderColor;
        TTFStyle.borderStraight = borderStraight;
        TTFStyle.shadowOffsetX = shadowOffsetX;
        TTFStyle.shadowOffsetY = shadowOffsetY;
        TTFStyle.shadowColor = shadowColor;
        //Set font attributes

        BMFont = TTFGenerator.generateFont(TTFStyle);
        //Generate font
    }

    /**
     * Constructor
     * @param fontFile
     * @param size
     * @param shadowOffsetX
     * @param shadowOffsetY
     * @param shadowColor
     */
    public TTFont(FileHandle fontFile, int size, int shadowOffsetX, int shadowOffsetY, Color shadowColor) {
        this(fontFile, size, 0, Color.BLACK, false, shadowOffsetX, shadowOffsetY, shadowColor);
    }

    /**
     * Constructor
     * @param fontFile
     * @param size
     * @param borderWidth
     * @param borderColor
     * @param borderStraight
     */
    public TTFont(FileHandle fontFile, int size, float borderWidth, Color borderColor, boolean borderStraight) {
        this(fontFile, size, borderWidth, borderColor, borderStraight, 0, 0, new Color(0, 0, 0, 0.75f));
    }

    /**
     * Constructor
     * @param fontFile
     * @param size
     */
    public TTFont(FileHandle fontFile, int size) {
        this(fontFile, size, 0, Color.BLACK, false, 0, 0, new Color(0, 0, 0, 0.75f));
    }

    /**
     * Constructor
     * @param fontFile
     */
    public TTFont(FileHandle fontFile) {
        this(fontFile, 16, 0, Color.BLACK, false, 0, 0, new Color(0, 0, 0, 0.75f));
    }

    /**
     * @Setter
     * @param fontFile
     */
    public void setFont(FileHandle fontFile) {
        TTFGenerator = new FreeTypeFontGenerator(fontFile);

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    /**
     * @Getter
     * @return
     */

    public BitmapFont font() {
        return BMFont;
    }

    /**
     * @Setter
     * @param size
     */
    public void setSize(int size) {
        TTFStyle.size = size;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    /**
     *  @Getter
     * @return
     */
    public int size() {
        return TTFStyle.size;
    }

    /**
     * @Setter
     * @param borderWidth
     * @param borderColor
     */
    public void setBorder(float borderWidth, Color borderColor) {
        TTFStyle.borderWidth = borderWidth;
        TTFStyle.borderColor = borderColor;
        TTFStyle.borderStraight = false;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    /**
     *
     * @param borderWidth
     * @param borderColor
     * @param borderStraight
     */
    public void setBorder(float borderWidth, Color borderColor, boolean borderStraight) {
        TTFStyle.borderWidth = borderWidth;
        TTFStyle.borderColor = borderColor;
        TTFStyle.borderStraight = borderStraight;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    /**
     *
     * @return
     */
    public float borderWidth() {
        return TTFStyle.borderWidth;
    }

    /**
     *
     * @return
     */
    public Color borderColor() {
        return TTFStyle.borderColor;
    }

    /**
     *
     */
    public void removeBorder() {
        TTFStyle.borderWidth = 0;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    /**
     *
     * @param shadowOffsetX
     * @param shadowOffsetY
     * @param shadowColor
     */
    public void setShadow(int shadowOffsetX, int shadowOffsetY, Color shadowColor) {
        TTFStyle.shadowOffsetX = shadowOffsetX;
        TTFStyle.shadowOffsetY = shadowOffsetY;
        TTFStyle.shadowColor = shadowColor;

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }

    /**
     *
     * @return
     */
    public Color shadowColor() {
        return TTFStyle.shadowColor;
    }

    /**
     *
     */
    public void removeShadow() {
        TTFStyle.shadowOffsetX = 0;
        TTFStyle.shadowOffsetY = 0;
        TTFStyle.shadowColor = new Color(0, 0, 0, 0.75f);

        BMFont = TTFGenerator.generateFont(TTFStyle);
    }
}
