package de.tomgrill.gdxtesting;

import org.junit.Test;
import org.junit.Before;
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
    //private Tile TestTile = new Tile(2, 100, 100, false, (1,2));



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


   // @Test
   // public void buyRoboticonTest(){
   //     TestMarket.buyRoboticon(1, TestPlayer, );
   // }

    /**
     * Tests sell method for ore resource.
     * <p>
     * Initial values of OreCount, Money, OreSellPrice, OreBuyPrice are set to 10.
     * </p>
     */
    @Test
    public void sellOreTest() {
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
    public void sellFoodTest() {
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
        TestMarket.sell("energy", 10, TestPlayer);
        Integer TestEnergyCount = 0;
        assertEquals(TestEnergyCount, TestPlayer.getEnergyCount());
        Integer TestMoney = 110;
        assertEquals(TestMoney, TestPlayer.getMoney());
        Integer TestSellPrice = 8;
        assertEquals(TestSellPrice, TestMarket.getEnergySellPrice());
        Integer TestBuyPrice = 10;
        assertEquals(TestBuyPrice, TestMarket.getEnergyBuyPrice());
        Integer TestFoodStock = 20;
        assertEquals(TestFoodStock, TestMarket.getEnergyStock());
    }

    /**
     * Tests buy method for ore resource.
     * <p>
     * Initial values of OreCount, OreSellPrice, OreBuyPrice are set to 10 and Money are set to 100.
     * </p>
     */
    @Test
    public void buyOreTest() {
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
    public void buyFoodTest() {
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
    public void buyEnergyTest() {
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

//    @Test
//    public void gambleTest() {
//        //TestPlayer.setMoney(100);
//        //System.out.println(TestPlayer.getMoney());
//       // TestMarket.gamble(50, TestPlayer);
//        //System.out.println(TestPlayer.getMoney());
//
//        TestPlayer.setMoney(50);
//        System.out.println(TestPlayer.getMoney());
//
//        TestMarket.gamble(50, TestPlayer);
//        Integer TestMoney = 0;
//        assertEquals(TestMoney, TestPlayer.getMoney());
//
//        //System.out.println(TestPlayer.getMoney());
}




