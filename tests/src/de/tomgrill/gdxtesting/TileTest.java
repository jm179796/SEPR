package de.tomgrill.gdxtesting;

import com.badlogic.gdx.Game;
import org.junit.Test;
import com.mygdx.game.*;
import org.junit.runner.RunWith;
import java.util.Arrays;

import static org.hamcrest.Matcher.*;
import static org.junit.Assert.*;
/**
 * @author Kieran Hall KJH532
 * @version 1.0
 * @since 1.0
 */
@RunWith(GdxTestRunner.class)
public class TileTest {
    private Game game = new Main();
    private Tile TestTile = new Tile(game, 0, 0, 0, 0, true, new Runnable() {
        @Override
        public void run() {

        }
    });

    @Test
    public void oneEqualsOne() {
        assertEquals(1, 1);
    }

}
