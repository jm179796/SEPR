import com.badlogic.gdx.Game;
import com.mygdx.game.Main;
import com.mygdx.game.Player;
import com.mygdx.game.Roboticon;
import com.mygdx.game.Tile;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Kieran Hall KJH532
 * @version 1.0
 * @since 1.0
 */
public class TileTest extends TesterFile {
    private Game game = new Main();
    private Player TestPlayer = new Player(0);
    private Tile TestTile = new Tile(game, 0, 0, 0, 0, true, new Runnable() {
        @Override
        public void run() {

        }
    });
    private Roboticon TestRoboticon = new Roboticon(0, TestPlayer, TestTile);

    /**
     * Test confirming that the Player's resources are updated with roboticon production modifiers after tile.produce has completed
     */
    @Test
    public void ValidProduce() {
        Integer TestValues[] = {TestPlayer.getEnergyCount(), TestPlayer.getFoodCount(), TestPlayer.getOreCount()};
        TestPlayer = TestTile.Produce(TestPlayer);

        assertTrue(TestPlayer.getEnergyCount() > TestValues[0]);
        assertTrue(TestPlayer.getFoodCount() > TestValues[1]);
        assertTrue(TestPlayer.getOreCount() > TestValues[2]);

    }

    @Test
    public void ValidToggleAcquire() {

    }

    @Test
    public void ValidConfirmAcquire() {

    }

}
