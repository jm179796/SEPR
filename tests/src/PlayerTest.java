import com.badlogic.gdx.Game;
import com.mygdx.game.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * @author Nico Pinedo NWP503
 * @version 1.0
 * @since 1.0
 */
public class PlayerTest extends TesterFile {

    private Game game = new Main();
    private Player TestPlayer = new Player(1);
    private Tile TestTile = new Tile(game, 0,0,0,0, true, new Runnable() {
        @Override
        public void run() {

        }
    });
    private Roboticon TestRoboticon = new Roboticon(0, TestPlayer, TestTile);
    private College TestCollege = new College (1, "I am a test college.");

    @Test
    public void testAssignCollege(){
        TestPlayer.assignCollege(TestCollege);
        assertEquals(TestPlayer.getCollege(),TestCollege);
    }

    @Test
    public void testAddRoboticon() {
        Integer count = TestPlayer.getRoboticonCount();
        count += 1;
        TestPlayer.addRoboticon(TestRoboticon);
        assertEquals(count, TestPlayer.getRoboticonCount());
    }

    @Test
    public void testAssignTile() {
        List<Tile> TileList = new ArrayList<Tile>();
        assertEquals(TestPlayer.getTileList(),TileList);
        TestPlayer.assignTile(TestTile);
        assertNotEquals(TestPlayer.getTileList(), TileList);
    }

    @Test
    public void ValidVaryResource() {

    }





















}




