package com.mygdx.game;

import com.badlogic.gdx.utils.Timer;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

/**
 * Created by Joseph on 29/11/2016.
 */
public class GameTimer extends com.badlogic.gdx.scenes.scene2d.ui.Label {

    private int minutes;
    private int seconds;
    private Timer timer;
    //Declare a timer and some appropriate variables to track time

    public GameTimer(int minutes, int seconds, TTFont font) {
        super("", new LabelStyle(font.font(), font.color()));
        //Set up timer label with the TTFont provided

        setTime(minutes, seconds);
        //Render the time given

        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                decrement();
            }
        }, 1, 1);
        //Set up local timer to decrement internal time variables after every second
    }

    public void setTime(int minutes, int seconds) {
        if (seconds >= 60) {
            throw new ValueException("Invalid Number of Seconds");
            //Check to see if the given time is valid
        } else {
            this.minutes = minutes;
            this.seconds = seconds;
            this.setText(String.format("%02d", this.minutes) + ":" + String.format("%02d", this.seconds));
            //Instantiate private time variables with new values and render it out
        }
    }

    private void decrement() {
        if (seconds == 0) {
            if (minutes == 0) {
                stop();
                //Stop the timer if it ever reaches 0
            } else {
                setTime(minutes - 1, 59);
                //Roll over to the next minute if the seconds counter ever reaches 0 and there are still minutes left on the clock
            }
        } else {
            setTime(minutes, seconds - 1);
            //Decrement the seconds counter if there are still seconds on the clock
        }
    }

    public int minutes() {
        return minutes;
    }

    public int seconds() {
        return seconds;
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

}
