import com.badlogic.gdx.Game;
import com.mygdx.game.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
/**
 * @author Duck Related Team Name in Big Massive Letters
 * @since Assessment 2
 * @version Assessment 2
 *
 * An executable version of the game can be found at: https://jm179796.github.io/SEPR/DRTN-Assessment2.jar
 * Our website is: https://jm179796.github.io/SEPR/
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
    private College TestCollege = new College (1, "I am a test.");

    @Test
    public void testAssignCollege(){
        TestPlayer.assignCollege(TestCollege);
        assertEquals(TestPlayer.getCollege(),TestCollege);
    }

    @Test
    public void testAssignTile() {
        List<Tile> TileList = new ArrayList<Tile>();
        assertEquals(TestPlayer.getTileList(),TileList);
        TestPlayer.assignTile(TestTile);
        assertNotEquals(TestPlayer.getTileList(), TileList);
    }

    @Test
    public void testVaryResource() {
        Integer ore = TestPlayer.getOreCount();
        Integer food = TestPlayer.getFoodCount();
        Integer energy = TestPlayer.getEnergyCount();
        Integer money = TestPlayer.getMoney();
        for (Integer i = 1; i < 200; i++){
            ore += i;
            food += i;
            energy += i;
            money += i;
            TestPlayer.varyResource("Ore", i);
            TestPlayer.varyResource("Food", i);
            TestPlayer.varyResource("Energy", i);
            TestPlayer.varyResource("Money", i);
            assertEquals(ore,TestPlayer.getOreCount());
            assertEquals(food,TestPlayer.getFoodCount());
            assertEquals(energy,TestPlayer.getEnergyCount());
            assertEquals(money,TestPlayer.getMoney());
        }


    }

    @Test
    public void testcalculateScore(){
        Integer ore = TestPlayer.getOreCount();
        Integer food = TestPlayer.getFoodCount();
        Integer energy = TestPlayer.getEnergyCount();
        Integer score = ore + food + energy;
        assertEquals(score, TestPlayer.calculateScore());
        //assertEquals((TestPlayer.getOreCount() + TestPlayer.getFoodCount() + TestPlayer.getEnergyCount()), TestPlayer.calculateScore());
    }

    @Test
    public void testIncreaseRoboticonInventory(){
        Integer count = TestPlayer.getInventoryRoboticons();
        count += 1;
        TestPlayer.increaseRoboticonInventory();
        assertEquals(count,TestPlayer.getInventoryRoboticons());
    }

    @Test
    public void testDecreaseRoboticonInventory(){
        Integer count = TestPlayer.getInventoryRoboticons();
        count -= 1;
        TestPlayer.decreaseRoboticonInventory();
        assertEquals(count,TestPlayer.getInventoryRoboticons());
    }




















}




