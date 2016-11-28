package de.tomgrill.gdxtesting;

import org.junit.Test;
import com.mygdx.game.*;

import java.util.Arrays;

import static org.hamcrest.Matcher.*;
import static org.junit.Assert.*;


/**
 * @author Kieran Hall KJH532
 * @version 1.0
 * @since 1.0
 */
public class RobotTest {

    private Player TestPlayer = new Player(0);
    private Integer[] TestCoordinates = {1, 1};
    private Tile TestTile = new Tile(0, 5, 5, false, TestCoordinates);
    private Roboticon TestRobot = new Roboticon(0, TestPlayer, TestTile);

    /**
     * Tests confirming that a valid Roboticon can be upgraded.
     */
    @Test
    public void ValidUpgrade() {
        Integer NewLevel[] = {2, 1, 1};
        TestRobot.upgrade("Ore");
        assertArrayEquals(NewLevel, TestRobot.getLevel());
        NewLevel[1] = 2;
        TestRobot.upgrade("Energy");
        assertArrayEquals(NewLevel, TestRobot.getLevel());
        NewLevel[2] = 2;
        TestRobot.upgrade("Food");
        assertArrayEquals(NewLevel, TestRobot.getLevel());
    }

    /**
     * Test confirming the possibleUpgrades method returns the possible upgrades available
     */
    @Test
    public void ValidUpgradeReturn() {
        Integer TestUpgrades[] = {2, 2, 2};
        assertArrayEquals(TestRobot.possibleUpgrades(), TestUpgrades);
    }

    /**
     * Test confirming productionModifier method works and provides values within a reasonable range
     */
    @Test
    public void ValidProductionModifier() {

        Integer Modifiers[] = TestRobot.productionModifier();

        for (int i = 0; i < 3; i++) {
            Integer current = Modifiers[i];
            System.out.print(current);
            assertTrue(Modifiers[i] < 6);
        }
    }
}
