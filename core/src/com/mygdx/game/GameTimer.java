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

    public GameTimer(int minutes, int seconds, TTFont font) {
        super("", new LabelStyle(font.font(), font.color()));

        setTime(minutes, seconds);

        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                decrement();
            }
        }, 1000);
    }

    public void setTime(int minutes, int seconds) {
        if (seconds >= 60) {
            throw new ValueException("Invalid Number of Seconds");
        } else {
            this.minutes = minutes;
            this.seconds = seconds;
            updateTime();
        }
    }

    private void decrement() {
        if (seconds == 0) {
            if (minutes == 0) {
                stop();
            } else {
                minutes -= 1;
                seconds = 59;
                updateTime();
            }
        } else {
            seconds -= 1;
            updateTime();
        }
    }

    private void updateTime() {
        this.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void start() {
        if (minutes <= 0 && seconds <= 0) {
            throw new RuntimeException("Timer Must Be Reset");
        } else {
            timer.start();
        }
    }

    public void stop() {
        timer.stop();
    }

}
