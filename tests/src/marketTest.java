import com.badlogic.gdx.Game;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Market;
import com.mygdx.game.Player;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @author Martynas Minskis mm1544
 * @version 1.0
 * @since 1.0
 */
public class marketTest extends TesterFile{
    private Player TestPlayer = new Player(0);
    private Game testGame;
    private GameEngine testGameEngine;
    private Market TestMarket = new Market(testGame,testGameEngine);




    private void setUpOre() {
        TestPlayer.setOreCount(10);
        TestPlayer.setMoney(10);
        TestMarket.setOreSellPrice(10);
        TestMarket.setOreBuyPrice(10);
        TestMarket.setOreStock(10);
    }

    private void setUpOre2(){
        TestPlayer.setOreCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setOreSellPrice(10);
        TestMarket.setOreBuyPrice(10);
        TestMarket.setOreStock(10);
    }

    private void setUpFood() {
        TestPlayer.setFoodCount(10);
        TestPlayer.setMoney(10);
        TestMarket.setFoodSellPrice(10);
        TestMarket.setFoodBuyPrice(10);
        TestMarket.setFoodStock(10);
    }

    private void setUpFood2() {
        TestPlayer.setFoodCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setFoodSellPrice(10);
        TestMarket.setFoodBuyPrice(10);
        TestMarket.setFoodStock(10);
    }

    private void setUpEnergy() {
        TestPlayer.setEnergyCount(10);
        TestPlayer.setMoney(10);
        TestMarket.setEnergySellPrice(10);
        TestMarket.setEnergyBuyPrice(10);
        TestMarket.setEnergyStock(10);
    }

    private void setUpEnergy2() {
        TestPlayer.setEnergyCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setEnergySellPrice(10);
        TestMarket.setEnergyBuyPrice(10);
        TestMarket.setEnergyStock(10);
    }

    private void setUpEnergy3() {
        TestPlayer.setEnergyCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setEnergySellPrice(10);
        TestMarket.setEnergyBuyPrice(10);
        TestMarket.setEnergyStock(100);
    }


    /**
     * Tests sell method for ore resource.
     * <p>
     * Initial values of OreCount, Money, OreSellPrice, OreBuyPrice are set to 10.
     * </p>
     */
    @Test
    public void sellOreTest() throws Exception {
        setUpOre();
        TestMarket.sell("ore", 10, TestPlayer);
        Integer TestOreCount = 0;
        assertEquals(TestOreCount, TestPlayer.getOreCount());
        Integer TestMoney = 110;
        assertEquals(TestMoney, TestPlayer.getMoney());
        Integer TestSellPrice = 8;
        assertEquals(TestSellPrice, TestMarket.getOreSellPrice());
        Integer TestBuyPrice = 10;
        assertEquals(TestBuyPrice, TestMarket.getOreBuyPrice());
        Integer TestOreStock = 20;
        assertEquals(TestOreStock, TestMarket.getOreStock());
    }

    /**
     * Tests sell method for food resource.
     * <p>
     * Initial values of OreCount, Money, OreSellPrice, OreBuyPrice are set to 10.
     * </p>
     */
    @Test
    public void sellFoodTest() throws Exception {
        setUpFood();
        TestMarket.sell("food", 10, TestPlayer);
        Integer TestFoodCount = 0;
        assertEquals(TestFoodCount, TestPlayer.getFoodCount());
        Integer TestMoney = 110;
        assertEquals(TestMoney, TestPlayer.getMoney());
        Integer TestSellPrice = 8;
        assertEquals(TestSellPrice, TestMarket.getFoodSellPrice());
        Integer TestBuyPrice = 10;
        assertEquals(TestBuyPrice, TestMarket.getFoodBuyPrice());
        Integer TestFoodStock = 20;
        assertEquals(TestFoodStock, TestMarket.getFoodStock());
    }

    /**
     * Tests sell method for energy resource.
     * <p>
     * Initial values of EnergyCount, Money, EnergySellPrice, EnergyBuyPrice are set to 10.
     * </p>
     */
    @Test
    public void sellEnergyTest() {
        setUpEnergy();
        try {
            TestMarket.sell("energy", 10, TestPlayer);
        } catch (Exception e) {
            fail("Expected to pass");
        }
    }

    /**
     * Tests sell method for energy resource, when energyStock is < then required energy.
     * <p>
     *     Exception("Insufficient resources") is thrown.
     *     Initial values of EnergyCount, Money, EnergySellPrice, EnergyBuyPrice are set to 10.
     * </p>
     */
    @Test
    public void sellEnergyException() {
        setUpEnergy();
        try {
            TestMarket.sell("energy", 100, TestPlayer);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Insufficient resources");
        }
    }

    /**
     * Tests buy method for energy resource, when players money is < than required required amount to buy.
     * <p>
     *     Exception("Insufficient money") is thrown.
     *     Initial values of EnergyCount, Money, EnergySellPrice, EnergyBuyPrice are set to 10.
     * </p>
     */
    @Test
    public void buyEnergyException() throws Exception {
        setUpEnergy3();
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
    }

    /**
     * Tests buy method for ore resource.
     * <p>
     * Initial values of OreCount, OreSellPrice, OreBuyPrice are set to 10 and Money are set to 100.
     * </p>
     */
    @Test
    public void buyOreTest() throws Exception {
        setUpOre2();
        TestMarket.buy("ore", 10, TestPlayer);
        Integer TestOreCount = 20;
        assertEquals(TestOreCount, TestPlayer.getOreCount());
        Integer TestMoney = 0;
        assertEquals(TestMoney, TestPlayer.getMoney());
        Integer TestSellPrice = 200;
        assertEquals(TestSellPrice, TestMarket.getOreSellPrice());
        Integer TestBuyPrice = 0;
        assertEquals(TestBuyPrice, TestMarket.getOreBuyPrice());
        Integer TestOreStock = 0;
        assertEquals(TestOreStock, TestMarket.getOreStock());

    }

    /**
     * Tests buy method for food resource.
     * <p>
     * Initial values of FoodCount, FoodSellPrice, FoodBuyPrice are set to 10 and Money are set to 100.
     * </p>
     */
    @Test
    public void buyFoodTest() throws Exception {
        setUpFood2();
        TestMarket.buy("food", 10, TestPlayer);
        Integer TestFoodCount = 20;
        assertEquals(TestFoodCount, TestPlayer.getFoodCount());
        Integer TestMoney = 0;
        assertEquals(TestMoney, TestPlayer.getMoney());
        Integer TestSellPrice = 200;
        assertEquals(TestSellPrice, TestMarket.getFoodSellPrice());
        Integer TestBuyPrice = 0;
        assertEquals(TestBuyPrice, TestMarket.getFoodBuyPrice());
        Integer TestFoodStock = 0;
        assertEquals(TestFoodStock, TestMarket.getFoodStock());

    }

    /**
     * Tests buy method for energy resource.
     * <p>
     * Initial values of EnergyCount, EnergySellPrice, EnergyBuyPrice are set to 10 and Money are set to 100.
     * </p>
     */
    @Test
    public void buyEnergyTest() throws Exception {
        setUpEnergy2();
        TestMarket.buy("energy", 10, TestPlayer);
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

    }

    @Test
    public void gambleTest() {
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
}