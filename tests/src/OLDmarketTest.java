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
public class OLDmarketTest extends TesterFile{
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
}

