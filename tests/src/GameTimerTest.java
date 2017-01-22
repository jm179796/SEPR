import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kieran Hall KJH532
 * @version 1.0
 * @since 1.0
 */
public class GameTimerTest extends TesterFile {
    private boolean complete = false;
    private GameTimer TestTimer = new GameTimer(3, new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 120), Color.WHITE, new Runnable() {
        @Override
        public void run() {
            TimerComplete();
        }
    });


    private void TimerComplete(){
        this.complete = true;
    }

    /**
     * Tests that the GameTimer class executes it's runnable on complettion
     * @throws Exception incase sleep is interrupted
     */
    @Test
    public void testTimerCompletion() throws Exception{
        assertFalse(this.complete);
        TestTimer.start();
        Thread.sleep(3100);
        assertTrue(this.complete);
    }
}

