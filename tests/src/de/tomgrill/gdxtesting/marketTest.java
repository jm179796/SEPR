package de.tomgrill.gdxtesting;

import org.junit.Test;
import com.mygdx.game.*;

import java.util.Arrays;

import static org.hamcrest.Matcher.*;
import static org.junit.Assert.*;


/**
 * @author Martynas Minskis mm1544
 * @version 1.0
 * @since 1.0
 */
public class marketTest {
    private Player TestPlayer = new Player(0);
    private Market TestMarket = new Market();



    /**
     * Tests sell method.
     * <p>
     * Initial values of OreCount, Money, OreSellPrice, OreBuyPrice are set to 10.
     * </p>
     */
    @Test
    public void sellTest() {
        TestPlayer.setOreCount(10);
        TestPlayer.setMoney(10);
        TestMarket.setOreSellPrice(10);
        TestMarket.setOreBuyPrice(10);
        TestMarket.setOreStock(10);
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
     * Tests buy method.
     * <p>
     * Initial values of OreCount, OreSellPrice, OreBuyPrice are set to 10 and Money are set to 100.
     * </p>
     */
    @Test
    public void buyTest() {
        TestPlayer.setOreCount(10);
        TestPlayer.setMoney(100);
        TestMarket.setOreSellPrice(10);
        TestMarket.setOreBuyPrice(10);
        TestMarket.setOreStock(10);
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

    @Test
    public void gambleTest() {
        //TestPlayer.setMoney(100);
        //System.out.println(TestPlayer.getMoney());
       // TestMarket.gamble(50, TestPlayer);
        //System.out.println(TestPlayer.getMoney());

        TestPlayer.setMoney(50);
        System.out.println(TestPlayer.getMoney());

        TestMarket.gamble(50, TestPlayer);
        Integer TestMoney = 0;
        assertEquals(TestMoney, TestPlayer.getMoney());

        //System.out.println(TestPlayer.getMoney());
    }

}


