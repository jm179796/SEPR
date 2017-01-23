import com.badlogic.gdx.Game;
import com.mygdx.game.Main;
import com.mygdx.game.Player;
import com.mygdx.game.Roboticon;
import com.mygdx.game.Tile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Duck Related Team Name in Big Massive Letters
 * @since Assessment 2
 * @version Assessment 2
 *
 * An executable version of the game can be found at: https://jm179796.github.io/SEPR/DRTN-Assessment2.jar
 * Our website is: https://jm179796.github.io/SEPR/
 */

public class TileTest extends TesterFile {
    private Game game = new Main();
    private Player TestPlayer = new Player(1);
    private Tile TestTile = new Tile(game, 0, 5, 5, 5, true, new Runnable() {
        @Override
        public void run() {

        }
    });
    private Roboticon TestRoboticon = new Roboticon(0, TestPlayer, TestTile);


    /**
     * Test confirming that the Player's resources are updated with roboticon production modifiers after tile.produce has completed
     */
    @Test
    public void testProduce() {
        Integer TestValues[] = {TestPlayer.getEnergyCount(), TestPlayer.getFoodCount(), TestPlayer.getOreCount()};

        TestPlayer = TestTile.Produce(TestPlayer);

        assertTrue(TestPlayer.getEnergyCount() > TestValues[0]);
        assertTrue(TestPlayer.getFoodCount() > TestValues[1]);
        assertTrue(TestPlayer.getOreCount() > TestValues[2]);

    }

    @Test
    public void testAssignRoboticon(){
        TestTile.assignRoboticon(TestRoboticon);
        assertTrue(TestTile.hasRoboticon());
    }
    @Test
    public void testUnassignRoboticon(){
        TestTile.assignRoboticon(TestRoboticon);
        TestTile.unassignRoboticon(TestRoboticon);
        assertFalse(TestTile.hasRoboticon());
    }

    @Test
    public void testisOwned(){
        assertFalse(TestTile.isOwned());
        TestTile.setOwner(TestPlayer);
        assertTrue(TestTile.isOwned());

    }
    @Test
    public void testhasRoboticon(){
        TestTile.unassignRoboticon(TestRoboticon);
        assertFalse(TestTile.hasRoboticon());
        TestTile.assignRoboticon(TestRoboticon);
        assertTrue(TestTile.hasRoboticon());

    }

}
