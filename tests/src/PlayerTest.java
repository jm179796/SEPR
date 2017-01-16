import com.badlogic.gdx.Game;
import com.mygdx.game.Main;
import com.mygdx.game.Player;
import com.mygdx.game.Roboticon;
import com.mygdx.game.Tile;
import org.junit.Test;

/**
 * @author Nico Pinedo NWP503
 * @version 1.0
 * @since 1.0
 */
public class PlayerTest extends TesterFile {

    private Game game = new Main();
    private Player TestPlayer = new Player(0);
    private Tile TestTile = new Tile(game, 0,0,0,0, true, new Runnable() {
        @Override
        public void run() {

        }
    });
    private Roboticon TestRoboticon = new Roboticon(0, TestPlayer, TestTile);

    @Test
    public void ValidVaryResource() {

    }





















}




