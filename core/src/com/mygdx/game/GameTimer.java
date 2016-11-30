package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Joseph on 29/11/2016.
 */
public class GameTimer extends com.badlogic.gdx.scenes.scene2d.ui.Label {

    private int minutes;
    private int seconds;
    private Timer timer;
    //Declare a timer and some appropriate variables to track time

    private Runnable terminalMethod;
    //Holds the method that will be executed when the local timer runs out

    public GameTimer(int minutes, int seconds, TTFont font, Color color, Runnable end) {
        super("", new LabelStyle(font.font(), color));
        //Set up timer label with the provided TTFont

        if ((minutes == 0 && seconds == 0) || minutes < 0 || seconds < 0) {
            throw new RuntimeException("Invalid Internal Time");
        } else {
            setTime(minutes, seconds);
        }
        //Set and render the provided amount of time

        this.terminalMethod = end;
        //Set up the given terminal method for execution when the private timer runs out

        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                decrement();
            }
        }, 1, 1);
        timer.stop();
        //Set up the local timer to decrement internal time variables after every second
        //The "timer.stop()" instruction prevents the timer from starting as soon as it's instantiated
    }

    public GameTimer(int minutes, int seconds, TTFont font, Color color) {
        this(minutes, seconds, font, color, new Runnable() {
            @Override
            public void run() {
                return;
            }
        });
    }
    //Alternative constructor that establishes a timer without a useful terminal method

    public GameTimer(int seconds, TTFont font, Color color, Runnable end) {
        this(0, seconds, font, color, end);
    }
    //Alternative constructor that sets a timer to count down from a number of seconds

    public GameTimer(int seconds, TTFont font, Color color) {
        this(0, seconds, font, color, new Runnable() {
            @Override
            public void run() {
                return;
            }
        });
    }
    //Alternative constructor that sets a timer without a useful terminal method to count down from a number of seconds

    public void setTime(int minutes, int seconds) {
        this.minutes = minutes + (seconds / 60);
        this.seconds = seconds % 60;
        this.setText(String.format("%02d", this.minutes) + ":" + String.format("%02d", this.seconds));
        //Instantiate private time variables with new values and render it out
    }

    private void decrement() {
        if (seconds == 0 && minutes > 0) {
            setTime(minutes - 1, 59);
            //Roll over on to the next minute if the seconds' counter ever reaches 0 and there are still minutes left on the clock
        } else {
            setTime(minutes, seconds - 1);
            //Decrement the seconds counter if there are still seconds on the clock
        }

        if (minutes == 0 && seconds == 0) {
            timer.stop();
            terminalMethod.run();
            //Stop the timer if it ever reaches 0...
            //...and execute the object's terminal method
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

    public void setTerminalMethod(Runnable terminalMethod) {
        this.terminalMethod = terminalMethod;
    }
    //Allows for the timer's terminal method to be reset

    public Runnable getTerminalMethod() {
        return this.terminalMethod;
    }

    public void runTerminalMethod() {
        this.terminalMethod.run();
    }
    //Allows for the timer's terminal method to be run at any time
}
