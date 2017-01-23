import com.badlogic.gdx.Game;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Market;
import com.mygdx.game.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Duck Related Team Name in Big Massive Letters
 * @since Assessment 2
 * @version Assessment 2
 *
 * An executable version of the game can be found at: https://jm179796.github.io/SEPR/DRTN-Assessment2.jar
 * Our website is: https://jm179796.github.io/SEPR/
 */
public class MarketTest extends TesterFile{
    private Player TestPlayer = new Player(0);
    private Game testGame;
    private GameScreen testScreen;
    private GameEngine testGameEngine = new GameEngine(testGame,testScreen);
    private Market TestMarket = new Market(testGame,testGameEngine);

    /**
     * Tests Valid buy conditions for all resources.
     * <p>
     *     Market resources are set to 10, and the prices are set to be valid for this test.
     *     Initial values of OreCount, OreSellPrice, OreBuyPrice are set to 10 and Money is set to 100.
     *     Initial values of FoodCount, FoodSellPrice, FoodBuyPrice are set to 10 and Money is set to 100.
     *     Initial values of FoodCount, FoodSellPrice, FoodBuyPrice are set to 10 and Money is set to 100.
     * </p>
     * @throws Exception Thrown when an invalid transaction is attempted.
     */
    @Test
    public void testBuy() throws Exception{

        //Energy
        TestPlayer.setEnergyCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setEnergySellPrice(10);
        TestMarket.setEnergyBuyPrice(10);
        TestMarket.setEnergyStock(10);

        try {
            TestMarket.buy("energy", 10, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }

        Integer TestEnergyCount = 20;
        assertEquals(TestEnergyCount, TestPlayer.getEnergyCount());
        Integer TestMoney = 0;
        assertEquals(TestMoney, TestPlayer.getMoney());
        Integer TestSellPrice = 200;
        assertEquals(TestSellPrice, TestMarket.getEnergySellPrice());
        Integer TestBuyPrice = 0;
        assertEquals(TestBuyPrice, TestMarket.getEnergyBuyPrice());
        Integer TestFoodStock = 0;
        assertEquals(TestFoodStock, TestMarket.getEnergyStock());

        //Ore
        TestPlayer.setOreCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setOreSellPrice(10);
        TestMarket.setOreBuyPrice(10);
        TestMarket.setOreStock(10);

        try {
            TestMarket.buy("ore", 10, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }

        Integer TestOreCount = 20;
        assertEquals(TestOreCount, TestPlayer.getOreCount());
        TestMoney = 0;
        assertEquals(TestMoney, TestPlayer.getMoney());
        TestSellPrice = 200;
        assertEquals(TestSellPrice, TestMarket.getOreSellPrice());
        TestBuyPrice = 0;
        assertEquals(TestBuyPrice, TestMarket.getOreBuyPrice());
        Integer TestOreStock = 0;
        assertEquals(TestOreStock, TestMarket.getOreStock());

        //Food
        TestPlayer.setFoodCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setFoodSellPrice(10);
        TestMarket.setFoodBuyPrice(10);
        TestMarket.setFoodStock(10);

        try {
            TestMarket.buy("food", 10, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }

        Integer TestFoodCount = 20;
        assertEquals(TestFoodCount, TestPlayer.getFoodCount());
        TestMoney = 0;
        assertEquals(TestMoney, TestPlayer.getMoney());
        TestSellPrice = 200;
        assertEquals(TestSellPrice, TestMarket.getFoodSellPrice());
        TestBuyPrice = 0;
        assertEquals(TestBuyPrice, TestMarket.getFoodBuyPrice());
        TestFoodStock = 0;
        assertEquals(TestFoodStock, TestMarket.getFoodStock());

        //Roboticon
        TestPlayer.setMoney(1000);
        TestMarket.setRoboticonBuyPrice(10);
        TestMarket.setRoboticonStock(10);

        try {
            TestMarket.buy("roboticon", 1, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }

    }

    /**
     * Tests Invalid buy conditions for all resources.
     * <p>
     *     Market resources are set to 10, and the prices are set to be valid for this test.
     *     Initial values of OreCount, OreSellPrice, OreBuyPrice are set to 10 and Money is set to 100.
     *     Initial values of FoodCount, FoodSellPrice, FoodBuyPrice are set to 10 and Money is set to 100.
     *     Initial values of FoodCount, FoodSellPrice, FoodBuyPrice are set to 10 and Money is set to 100.
     *
     *     However the player attempts to buy 100 of the resource, triggering the exception
     * </p>
     * @throws Exception Thrown when an invalid transaction is attempted.
     */
    @Test
    public void testBuyExceptions() throws Exception {

        //Energy
        TestPlayer.setEnergyCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setEnergySellPrice(10);
        TestMarket.setEnergyBuyPrice(10);
        TestMarket.setEnergyStock(100);

        try {
            TestMarket.buy("energy", 100, TestPlayer);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Insufficient money");
        }

        Integer TestEnergyCount = 10;
        assertEquals(TestEnergyCount, TestPlayer.getEnergyCount());
        Integer TestMoney = 100;
        assertEquals(TestMoney, TestPlayer.getMoney());
        Integer TestSellPrice = 10;
        assertEquals(TestSellPrice, TestMarket.getEnergySellPrice());
        Integer TestBuyPrice = 10;
        assertEquals(TestBuyPrice, TestMarket.getEnergyBuyPrice());
        Integer TestEnergyStock = 100;
        assertEquals(TestEnergyStock, TestMarket.getEnergyStock());

        //Ore
        TestPlayer.setOreCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setOreSellPrice(10);
        TestMarket.setOreBuyPrice(10);
        TestMarket.setOreStock(100);

        try {
            TestMarket.buy("ore", 100, TestPlayer);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Insufficient money");
        }

        Integer TestOreCount = 10;
        assertEquals(TestOreCount, TestPlayer.getOreCount());
        TestMoney = 100;
        assertEquals(TestMoney, TestPlayer.getMoney());
        TestSellPrice = 10;
        assertEquals(TestSellPrice, TestMarket.getOreSellPrice());
        TestBuyPrice = 10;
        assertEquals(TestBuyPrice, TestMarket.getOreBuyPrice());
        Integer TestOreStock = 100;
        assertEquals(TestOreStock, TestMarket.getOreStock());

        //Food
        TestPlayer.setFoodCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setFoodSellPrice(10);
        TestMarket.setFoodBuyPrice(10);
        TestMarket.setFoodStock(100);

        try {
            TestMarket.buy("food", 100, TestPlayer);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Insufficient money");
        }

        Integer TestFoodCount = 10;
        assertEquals(TestFoodCount, TestPlayer.getFoodCount());
        TestMoney = 100;
        assertEquals(TestMoney, TestPlayer.getMoney());
        TestSellPrice = 10;
        assertEquals(TestSellPrice, TestMarket.getFoodSellPrice());
        TestBuyPrice = 10;
        assertEquals(TestBuyPrice, TestMarket.getFoodBuyPrice());
        Integer TestFoodStock = 100;
        assertEquals(TestFoodStock, TestMarket.getFoodStock());

        //Roboticon
        TestPlayer.setMoney(1);
        TestMarket.setRoboticonBuyPrice(10);
        TestMarket.setRoboticonStock(10);

        try {
            TestMarket.buy("roboticon", 10, TestPlayer);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Insufficient money");
        }

        TestPlayer.setMoney(100);
        TestMarket.setRoboticonBuyPrice(10);
        TestMarket.setRoboticonStock(0);

        try {
            TestMarket.buy("roboticon", 10, TestPlayer);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "No available Roboticons");
        }
    }

    /**
     * Tests Valid sell conditions for all resources.
     * <p>
     *     Market resources are set to 10, and the prices are set to be valid for this test.
     *     Initial values of OreCount, OreSellPrice, OreBuyPrice are set to 10 and Money is set to 10.
     *     Initial values of FoodCount, FoodSellPrice, FoodBuyPrice are set to 10 and Money is set to 10.
     *     Initial values of FoodCount, FoodSellPrice, FoodBuyPrice are set to 10 and Money is set to 10.
     * </p>
     * @throws Exception Thrown when an invalid transaction is attempted.
     */
    @Test
    public void testSell() throws Exception{
        //ore
        TestPlayer.setOreCount(10);
        TestPlayer.setMoney(10);
        TestMarket.setOreSellPrice(10);
        TestMarket.setOreBuyPrice(10);
        TestMarket.setOreStock(10);

        try {
            TestMarket.sell("ore", 10, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }

        //food
        TestPlayer.setFoodCount(10);
        TestPlayer.setMoney(10);
        TestMarket.setFoodSellPrice(10);
        TestMarket.setFoodBuyPrice(10);
        TestMarket.setFoodStock(10);

        try {
            TestMarket.sell("food", 10, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }

        //energy
        TestPlayer.setEnergyCount(10);
        TestPlayer.setMoney(10);
        TestMarket.setEnergySellPrice(10);
        TestMarket.setEnergyBuyPrice(10);
        TestMarket.setEnergyStock(10);
        try {
            TestMarket.sell("energy", 10, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }
    }

    /**
     * Tests Invalid sell conditions for all resources.
     * <p>
     *     Market resources are set to 10, and the prices are set to be valid for this test.
     *     Initial values of OreCount, OreSellPrice, OreBuyPrice are set to 10 and Money is set to 10.
     *     Initial values of FoodCount, FoodSellPrice, FoodBuyPrice are set to 10 and Money is set to 10.
     *     Initial values of FoodCount, FoodSellPrice, FoodBuyPrice are set to 10 and Money is set to 10.
     *
     *     However the player attempts to sell 100 of the resource, triggering the exception
     * </p>
     * @throws Exception Thrown when an invalid transaction is attempted.
     */
    @Test
    public void testSellExceptions() throws Exception{
        //ore
        TestPlayer.setOreCount(10);
        TestPlayer.setMoney(10);
        TestMarket.setOreSellPrice(10);
        TestMarket.setOreBuyPrice(10);
        TestMarket.setOreStock(10);

        try {
            TestMarket.sell("ore", 100, TestPlayer);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Insufficient resources");
        }

        //food
        TestPlayer.setFoodCount(10);
        TestPlayer.setMoney(10);
        TestMarket.setFoodSellPrice(10);
        TestMarket.setFoodBuyPrice(10);
        TestMarket.setFoodStock(10);

        try {
            TestMarket.sell("food", 100, TestPlayer);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Insufficient resources");
        }

        //energy
        TestPlayer.setEnergyCount(10);
        TestPlayer.setMoney(10);
        TestMarket.setEnergySellPrice(10);
        TestMarket.setEnergyBuyPrice(10);
        TestMarket.setEnergyStock(10);
        try {
            TestMarket.sell("energy", 100, TestPlayer);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Insufficient resources");
        }

    }

    @Test
    public void testGamble(){
        TestPlayer.setMoney(49);

        for (int j = 0; j < 100; j++) {
            if (TestPlayer.getMoney() < 50) {
                assertNull(TestMarket.gamble(100, TestPlayer));
            } else if (TestPlayer.getMoney() >= 50) {
                Boolean current = TestMarket.gamble(50, TestPlayer);
                assertTrue(((current == Boolean.TRUE) || (current == Boolean.FALSE)));
            }
        }
    }

    @Test
    public void testcalculatenewcost(){


        TestPlayer.setEnergyCount(10);
        TestPlayer.setMoney(1000 );
        TestMarket.setEnergySellPrice(2);
        TestMarket.setEnergyBuyPrice(2);
        TestMarket.setEnergyStock(100);
        Integer initialbuyprice = TestMarket.getEnergyBuyPrice();
        Integer initialsellprice = TestMarket.getEnergySellPrice();

        try {
            TestMarket.buy("energy", 10, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }

        assertTrue(initialbuyprice < TestMarket.getEnergyBuyPrice());
        assertTrue(initialsellprice > TestMarket.getEnergySellPrice());

        TestPlayer.setOreCount(10);
        TestPlayer.setMoney(1000 );
        TestMarket.setOreSellPrice(2);
        TestMarket.setOreBuyPrice(2);
        TestMarket.setOreStock(100);
        initialbuyprice = TestMarket.getOreBuyPrice();
        initialsellprice = TestMarket.getOreSellPrice();

        try {
            TestMarket.buy("ore", 10, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }

        assertTrue(initialbuyprice < TestMarket.getOreBuyPrice());
        assertTrue(initialsellprice > TestMarket.getOreSellPrice());

        TestPlayer.setFoodCount(10);
        TestPlayer.setMoney(1000 );
        TestMarket.setFoodSellPrice(2);
        TestMarket.setFoodBuyPrice(2);
        TestMarket.setFoodStock(100);
        initialbuyprice = TestMarket.getFoodBuyPrice();
        initialsellprice = TestMarket.getFoodSellPrice();

        try {
            TestMarket.buy("food", 10, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }

        assertTrue(initialbuyprice < TestMarket.getFoodBuyPrice());
        assertTrue(initialsellprice > TestMarket.getFoodSellPrice());
    }


}
