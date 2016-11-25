package com.mygdx.game;

import java.util.*;

/**
 * @author Martynas MM1544
 * @version 1.0
 * @since 1.0
 */
public class Market {

    private Integer OreStock = 0;

    private Integer FoodStock = 16;

    private Integer EnergyStock = 16;

    private Integer RoboticonStock = 12;

    private Integer OreSellPrice;

    private Integer FoodSellPrice;

    private Integer EnergySellPrice;

    private Integer OreBuyPrice;

    private Integer FoodBuyPrice;

    private Integer EnergyBuyPrice;

    private Integer RoboticonBuyPrice;

    public void buy(String Stock_Type, int Quantity, Player Player) {
        int playersMoney = Player.getMoney();
        if ("ore".equals(Stock_Type)) {
            if (Quantity <= OreStock) {
                int OrePrice = OreBuyPrice * Quantity;
                if (playersMoney >= OrePrice) {
                    OreStock -= Quantity;
                    playersMoney -= OrePrice;
                    Player.setMoney(playersMoney);
                    int playersOre = Player.getOreCount();
                    playersOre += Quantity;
                    Player.setOreCount(playersOre);
                    calculateNewCost(OreStock, OreBuyPrice, "buy");
                    calculateNewCost(OreStock, OreSellPrice, "sell");
                } else {
                    System.out.println("Insufficient money");
                }
            } else {
                System.out.println("Inscufficient resources");
            }
        } else if ("food".equals(Stock_Type)) {
            if (Quantity <= FoodStock) {
                int FoodPrice = FoodBuyPrice * Quantity;
                if (playersMoney >= FoodPrice) {
                    FoodStock -= Quantity;
                    playersMoney -= FoodPrice;
                    Player.setMoney(playersMoney);
                    int playersFood = Player.getFoodCount();
                    playersFood += Quantity;
                    Player.setFoodCount(playersFood);
                    calculateNewCost(FoodStock, FoodBuyPrice, "buy");
                    calculateNewCost(FoodStock, FoodSellPrice, "sell");
                } else {
                    System.out.println("Insufficient money");
                }

            } else {
                System.out.println("Inscufficient resources");
            }
        } else if ("energy".equals(Stock_Type)) {
            if (Quantity <= EnergyStock) {
                int EnergyPrice = EnergyBuyPrice * Quantity;
                if (playersMoney >= EnergyPrice) {
                    EnergyStock -= Quantity;
                    playersMoney -= EnergyPrice;
                    Player.setMoney(playersMoney);
                    int playersEnergy = Player.getEnergyCount();
                    playersEnergy += Quantity;
                    Player.setEnergyCount(playersEnergy);
                    calculateNewCost(EnergyStock, EnergyBuyPrice, "buy");
                    calculateNewCost(EnergyStock, EnergySellPrice, "sell");
                } else {
                    System.out.println("Insufficient money");
                }
            } else {
                System.out.println("Inscufficient resources");
            }
        } else {
            System.out.println("Wrong Stock_Type passed");
        }

    }


    public void sell(String Stock_Type, int Quantity, Player Player) {
        int playersMoney = Player.getMoney();
        if ("ore".equals(Stock_Type)) {
            int playersOre = Player.getOreCount();
            if (playersOre >= Quantity) {
                OreStock += Quantity;
                playersMoney += Quantity * OreSellPrice;
                Player.setMoney(playersMoney);
                playersOre -= Quantity;
                Player.setOreCount(playersOre);
                calculateNewCost(OreStock, OreBuyPrice, "buy");
                calculateNewCost(OreStock, OreSellPrice, "sell");

            } else {
                System.out.println("Inscufficient resources");
            }
        } else if ("food".equals(Stock_Type)) {
            int playersFood = Player.getFoodCount();
            if (playersFood >= Quantity) {
                FoodStock += Quantity;
                playersMoney += Quantity * FoodSellPrice;
                Player.setMoney(playersMoney);
                playersFood -= Quantity;
                Player.setFoodCount(playersFood);
                calculateNewCost(FoodStock, FoodBuyPrice, "Buy");
                calculateNewCost(FoodStock, FoodSellPrice, "sell");

            } else {
                System.out.println("Inscufficient resources");
            }
        } else if ("energy".equals(Stock_Type)) {
            int playersEnergy = Player.getEnergyCount();
            if (playersEnergy >= Quantity) {
                EnergyStock += Quantity;
                playersMoney += Quantity * EnergySellPrice;
                Player.setMoney(playersMoney);
                playersEnergy -= Quantity;
                Player.setEnergyCount(playersEnergy);
                calculateNewCost(EnergyStock, EnergyBuyPrice, "buy");
                calculateNewCost(EnergyStock, EnergySellPrice, "sell");

            } else {
                System.out.println("Inscufficient resources");
            }

        }
    }

    public Boolean gamble(int amountToGamble, Player Player) {
        Random rand = new Random();
        int result = rand.nextInt(1);
        int playersMoney = Player.getMoney();
        if (result == 0) {
            playersMoney -= amountToGamble;
            Player.setMoney(playersMoney);
            return false;
        }
        else {
            playersMoney += amountToGamble;
            Player.setMoney(playersMoney);
            return true;
        }

    }

    public void calculateNewCost(int Stock, int costOfResources, String oper) {
        double cost = 0;
        if (Stock == 0 && oper == "buy") {
            costOfResources = 0;
        } else if (Stock == 0 && oper == "sell") {
            costOfResources = 200;


        } else if (oper == "buy") {
            cost = 160 / Stock + 2;
            int costInt = (int) Math.round(cost);
            costOfResources = costInt;

        } else if (oper == "sell") {
            cost = 160 / Stock;
            int costInt = (int) Math.round(cost);
            if (costInt < 1) {
                costOfResources = 1;
            } else {
                costOfResources = costInt;
            }
        } else {
            System.out.println("Wrong operator");
        }

    }
    //public Integer getPrice(String Stock_Type){
    //return Stock_Type.Price;
    //}
}




