package com.mygdx.game;

import java.util.*;

/**
 * @author Martynas MM1544
 * @version 1.0
 * @since 1.0
 */
public class Market {
    /**
     * Variable holding ore resource value.
     */
    private Integer OreStock = 0;

    /**
     * Variable holding food resource value.
     */
    private Integer FoodStock = 16;

    /**
     * Variable holding energy resource value.
     */
    private Integer EnergyStock = 16;

    /**
     * Variable holding number of roboticons that are awailable on market.
     */
    private Integer RoboticonStock = 12;

    /**
     * Variable holding ore resource selling price.
     */
    private Integer OreSellPrice;

    /**
     * Variable holding food resource selling price.
     */
    private Integer FoodSellPrice;

    /**
     * Variable holding energy resource selling price.
     */
    private Integer EnergySellPrice;

    /**
     * Variable holding ore resource buying price.
     */
    private Integer OreBuyPrice;

    /**
     * Variable holding food resource buying price.
     */
    private Integer FoodBuyPrice;

    /**
     * Variable holding energy resource buying price.
     */
    private Integer EnergyBuyPrice;

    /**
     * Variable holding roboticon buying price.
     */
    private Integer RoboticonBuyPrice;

    /**
     * A method that allows buying resources from the market.
     * <p>
     *Depending on what type of resources is passed ("ore", "food" or "energy") method checks whether it is sufficient
     * amount of that resource in market stock. Then it is checked whether does Player has enough money to buy required
     * amount. Market stock value (e.g. OreStock) is reduced by the quantity that has been bought(Quantity). Players money
     * (playersMoney) are reduced by the amount that was spent on the purchase. The value of Players resource stock is
     * updated. New selling and buying prices, for that chosen resource, are calculated (calculateNewCost()).
     * </p>
     *
     * @param Stock_Type    Type of resources (ore, energy or food) that is stored in the market.
     * @param Quantity      The amount of resources that Player wants to buy.
     * @param Player        A Player object.
     */
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
                    OreBuyPrice = calculateNewCost(OreStock, OreBuyPrice, "buy");
                    OreSellPrice = calculateNewCost(OreStock, OreSellPrice, "sell");
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
                    FoodBuyPrice = calculateNewCost(FoodStock, FoodBuyPrice, "buy");
                    FoodSellPrice = calculateNewCost(FoodStock, FoodSellPrice, "sell");
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
                    EnergyBuyPrice = calculateNewCost(EnergyStock, EnergyBuyPrice, "buy");
                    EnergySellPrice = calculateNewCost(EnergyStock, EnergySellPrice, "sell");
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


    /**
     * A method that allows selling resources to the market.
     * <p>
     * Depending on what type of resources it is passed ("ore", "food" or "energy") method checks whether the Player has
     * sufficient amount (Quantity) of resource that he is willing to sell. Market stock value (e.g. OreStock) is
     * increased by the quantity that has been sold by Player. Players money(playersMoney) are increased by the amount
     * that was gotten by selling resources. The value of Player's resource is decreased by the amount that has been sold.
     * New selling and buying prices, for that chosen resource, are calculated (calculateNewCost()).
     * </p>
     * @param Stock_Type    Type of resources (ore, energy or food) that is stored in the market.
     * @param Quantity      The amount of resources that Player wants to buy.
     * @param Player        A Player object.
     */
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
                OreBuyPrice = calculateNewCost(OreStock, OreBuyPrice, "buy");
                OreSellPrice = calculateNewCost(OreStock, OreSellPrice, "sell");

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
                FoodBuyPrice = calculateNewCost(FoodStock, FoodBuyPrice, "Buy");
                FoodSellPrice = calculateNewCost(FoodStock, FoodSellPrice, "sell");

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
                EnergyBuyPrice = calculateNewCost(EnergyStock, EnergyBuyPrice, "buy");
                EnergySellPrice = calculateNewCost(EnergyStock, EnergySellPrice, "sell");

            } else {
                System.out.println("Inscufficient resources");
            }

        }
    }


    /**
     * A method that allowes to gamble.
     * <p>
     * First it is checked whether a chosen amount of money is not higher than the total amount of Player's money.
     * Number generator generates whether 0 or 1. If 0 is generated - Player loses and his money is reduced by the
     * "amountToGamble". If 1 is generated - Player wins and his money is increased by the "amountToGamble".
     * </p>
     *
     * @param amountToGamble    The amount of money that is meant to be spent for gambling.
     * @param Player            A Player object.
     * @return                  Returns True if Player has won, False if he lost and null if Player has less money than
     * chosen amount of money to gamble with.
     */
    public Boolean gamble(int amountToGamble, Player Player) {
        int playersMoney = Player.getMoney();
        if (amountToGamble <= playersMoney) {
            Random rand = new Random();
            int result = rand.nextInt(1);
            if (result == 0) {
                playersMoney -= amountToGamble;
                Player.setMoney(playersMoney);
                return false;
            } else {
                playersMoney += amountToGamble;
                Player.setMoney(playersMoney);
                return true;
            }

        }else {
            return null;
        }
    }


    /**
     * A method that calculates cost of market selling and buying prices.
     *
     * <p>
     *  When Stock value equals 0 and wanted operation is "buy", costOfResource value is set to 0. When Stock value is
     *  equal to 0 and wanted operation is "sell", costOfResource value is set to 200.
     *  If wanted operation is "buy", new buying price is calculated. If wanted operation is "sell", then new selling
     *  price is calculated.
     * </p>
     * @param Stock              Integer values of market resources.
     * @param costOfResources    Integer cost value of market resources.
     * @param oper               String value representing operations "buy" and "sell".
     */
    private int calculateNewCost(int Stock, int costOfResources, String oper) {
        double cost;
        if (Stock == 0 && oper.equals("buy")) {
            costOfResources = 0;
        } else if (Stock == 0 && oper.equals("sell")) {
            costOfResources = 200;


        } else if (oper.equals("buy")) {
            cost = 160 / Stock + 2;
            costOfResources = (int) Math.round(cost);

        } else if (oper.equals("sell")) {
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
        return costOfResources;

    }
    //public Integer getPrice(String Stock_Type){
    //return Stock_Type.Price;
    //}
}




