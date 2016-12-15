import com.badlogic.gdx.Game;
import com.mygdx.game.Main;
import com.mygdx.game.Tile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * @author Kieran Hall KJH532
 * @version 1.0
 * @since 1.0
 */
public class TileTest extends TesterFile {
    private Game game = new Main();
    private Tile TestTile = new Tile(game, 0, 0, 0, 0, 0, 0, true, new Runnable() {
        @Override
        public void run() {

        }
    });

    @Test
    public void oneEqualsOne() {
        assertEquals(1, 1);
    }

}
