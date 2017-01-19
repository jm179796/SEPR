package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.Random;

/**
 * @author Martynas MM1544
 * @version 1.0
 * @since 1.0
 */
public class Market extends Table {
    private Game game;

    private Drawer drawer;

    private TTFont tableFont;

    /**
     * Variable holding current Ore resource amount as an Integer, initialises at 0 as stated in the brief.
     */
    private Integer OreStock = 0;
    
    /**
     * Variable holding current Food resource amount as an Integer, initialises at 16 as stated in the brief.
     */
    private Integer FoodStock = 16;

    /**
     * Variable holding current Energy resource amount as an Integer, initialises at 16 as stated in the brief.
     */
    private Integer EnergyStock = 16;

    /**
     * Variable holding current amount of Roboticons as an Integer, initialises at 12 as stated in the brief.
     */
    private Integer RoboticonStock = 12;

    /**
     * Variable holding ore resource selling price.
     */
    private Integer OreSellPrice = 14;

    /**
     * Variable holding food resource selling price.
     */
    private Integer FoodSellPrice = 14;

    /**
     * Variable holding energy resource selling price.
     */
    private Integer EnergySellPrice = 14;

    /**
     * Variable holding ore resource buying price.
     */
    private Integer OreBuyPrice = 15;

    /**
     * Variable holding food resource buying price.
     */
    private Integer FoodBuyPrice = 15;

    /**
     * Variable holding energy resource buying price.
     */
    private Integer EnergyBuyPrice = 15;

    /**
     * Variable holding roboticon buying price.
     */
    private Integer RoboticonBuyPrice = 20;

    public TextButton buyOre;
    public TextButton buyFood;
    public TextButton buyEnergy;
    public TextButton buyRoboticon;

    public TextButton sellOre;
    public TextButton sellFood;
    public TextButton sellEnergy;

    private Label oreStockLabel;
    private Label foodStockLabel;
    private Label energyStockLabel;
    private Label roboticonStockLabel;

    public Market(Game game) {
        super();

        this.game = game;

        drawer = new Drawer(this.game);

        tableFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 36);
        try {
            OreBuyPrice = calculateNewCost(OreStock, "buy");
            FoodBuyPrice = calculateNewCost(FoodStock, "buy");
            EnergyBuyPrice = calculateNewCost(EnergyStock, "buy");
            OreSellPrice = calculateNewCost(OreStock, "sell");
            FoodSellPrice = calculateNewCost(FoodStock, "sell");
            EnergySellPrice = calculateNewCost(EnergyStock, "sell");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        constructInterface();
    }

    public void constructInterface() {
        drawer.addTableRow(this, new Label("Market", new Label.LabelStyle(tableFont.font(), Color.WHITE)), 0, 0, 5, 0, 3);

        tableFont.setSize(24);
        TextButton.TextButtonStyle tableButtonStyle = new TextButton.TextButtonStyle();
        tableButtonStyle.font = tableFont.font();
        tableButtonStyle.fontColor = Color.WHITE;
        tableButtonStyle.pressedOffsetX = 1;
        tableButtonStyle.pressedOffsetY = -1;

        buyOre = new TextButton(getOreBuyPrice().toString(), tableButtonStyle);
        buyFood = new TextButton(getFoodBuyPrice().toString(), tableButtonStyle);
        buyEnergy = new TextButton(getEnergyBuyPrice().toString(), tableButtonStyle);
        buyRoboticon = new TextButton(getRoboticonBuyPrice().toString(), tableButtonStyle);

        sellOre = new TextButton(getOreSellPrice().toString(), tableButtonStyle);
        sellFood = new TextButton(getFoodSellPrice().toString(), tableButtonStyle);
        sellEnergy = new TextButton(getEnergySellPrice().toString(), tableButtonStyle);

        this.row();
        this.add(new Label("Item", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left().padRight(90);
        this.add(new Label("Buy", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left().padRight(30);
        this.add(new Label("Sell", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left().padRight(30);

        this.row();
        this.add(new Label("Ore", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(buyOre).left();
        this.add(sellOre).left();

        this.row();
        this.add(new Label("Food", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(buyFood).left();
        this.add(sellFood).left();

        this.row();
        this.add(new Label("Energy", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(buyEnergy).left();
        this.add(sellEnergy).left();

        this.row();
        this.add(new Label("Roboticons", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left().padBottom(15);
        this.add(buyRoboticon).left().padBottom(15);

        oreStockLabel = new Label(getOreStock().toString(), new Label.LabelStyle(tableFont.font(), Color.WHITE));
        foodStockLabel = new Label(getFoodStock().toString(), new Label.LabelStyle(tableFont.font(), Color.WHITE));
        energyStockLabel = new Label(getEnergyStock().toString(), new Label.LabelStyle(tableFont.font(), Color.WHITE));
        roboticonStockLabel = new Label(getRoboticonStock().toString(), new Label.LabelStyle(tableFont.font(), Color.WHITE));

        this.row();
        this.add(new Label("Item", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left().padRight(90);
        this.add(new Label("Stock", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();

        this.row();
        this.add(new Label("Ore", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(oreStockLabel).left();

        this.row();
        this.add(new Label("Food", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(foodStockLabel).left();

        this.row();
        this.add(new Label("Energy", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(energyStockLabel).left();

        this.row();
        this.add(new Label("Roboticons", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(roboticonStockLabel).left();
    }

    public Integer getRoboticonStock() {
        return this.RoboticonStock;
    }

    public void setRoboticonStock (Integer NewRoboticonStock) {
        this.RoboticonStock = NewRoboticonStock;
        roboticonStockLabel.setText(getRoboticonStock().toString());
    }

    /**
     * Getter for RoboticonBuyPrice
     *
     * @return this.RoboticonBuyPrice is integer roboticon buy price value
     */
    public Integer getRoboticonBuyPrice(){
        return this.RoboticonBuyPrice;
    }

    /**
     * Setter for RoboticonBuyPrice
     *
     * @param NewRoboticonBuyPrice integer value that RoboticonBuyPrice is assigned to.
     */
    public void setRoboticonBuyPrice(Integer NewRoboticonBuyPrice){
        this.RoboticonBuyPrice = NewRoboticonBuyPrice;
        buyRoboticon.setText(getRoboticonBuyPrice().toString());
    }

    /**
     * Getter  for OreStock
     *
     * @return this.OresStock is  integer ore stock value of a Market.
     */
    public Integer getOreStock() {
        return this.OreStock;
    }

    /**
     * Setter for OreStock.
     *
     * @param NewOreStock integer value that OreStock is assigned to.
     */
    public void setOreStock(Integer NewOreStock) {
        this.OreStock = NewOreStock;
    }

    /**
     * Getter for OreSellPrice
     *
     * @return this.OreSellPrice returns ore selling price value as an integer.
     */
    public Integer getOreSellPrice() {
        return this.OreSellPrice;
    }

    /**
     * Setter for OreSellPrice.
     *
     * @param NewOreSellPrice integer value that OreSellPrice is set to.
     */
    public void setOreSellPrice(Integer NewOreSellPrice) {
        this.OreSellPrice = NewOreSellPrice;
        sellOre.setText(getOreSellPrice().toString());
    }

    /**
     * Getter for OreBuyPrice.
     *
     * @return this.OreBuyPrice returns ore buying price as an integer.
     */
    public Integer getOreBuyPrice() {
        return this.OreBuyPrice;
    }

    /**
     * Setter for OreBuyPrice.
     *
     * @param NewOreBuyPrice integer value that OreBuyPrice is set to.
     */
    public void setOreBuyPrice(Integer NewOreBuyPrice) {
        this.OreBuyPrice = NewOreBuyPrice;
        buyOre.setText(getOreBuyPrice().toString());
    }

    /**
     * Getter  for FoodStock
     *
     * @return this.FoodStock is  integer food stock value of a Market.
     */
    public Integer getFoodStock() {
        return this.FoodStock;
    }

    /**
     * Setter for FoodStock.
     *
     * @param NewFoodStock integer value that FoodStock is assigned to.
     */
    public void setFoodStock(Integer NewFoodStock) {
        this.FoodStock = NewFoodStock;
    }

    /**
     * Getter for FoodSellPrice
     *
     * @return this.FoodSellPrice returns food selling price value as an integer.
     */
    public Integer getFoodSellPrice() {
        return this.FoodSellPrice;
    }

    /**
     * Setter for FoodSellPrice.
     *
     * @param NewFoodSellPrice integer value that FoodSellPrice is set to.
     */
    public void setFoodSellPrice(Integer NewFoodSellPrice) {
        this.FoodSellPrice = NewFoodSellPrice;
        sellFood.setText(getFoodSellPrice().toString());
    }

    /**
     * Getter for FoodBuyPrice.
     *
     * @return this.FoodBuyPrice returns food buying price as an integer.
     */
    public Integer getFoodBuyPrice() {
        return this.FoodBuyPrice;
    }

    /**
     * Setter for FoodBuyPrice.
     *
     * @param NewFoodBuyPrice integer value that FoodBuyPrice is set to.
     */
    public void setFoodBuyPrice(Integer NewFoodBuyPrice) {
        this.FoodBuyPrice = NewFoodBuyPrice;
        buyFood.setText(getFoodBuyPrice().toString());
    }

    /**
     * Getter  for EnergyStock
     *
     * @return this.EnergyStock is  integer energy stock value of a Market.
     */
    public Integer getEnergyStock() {
        return this.EnergyStock;
    }

    /**
     * Setter for EnergyStock.
     *
     * @param NewEnergyStock integer value that EnergyStock is assigned to.
     */
    public void setEnergyStock(Integer NewEnergyStock) {
        this.EnergyStock = NewEnergyStock;
    }

    /**
     * Getter for EnergySellPrice
     *
     * @return this.EnergySellPrice returns energy selling price value as an integer.
     */
    public Integer getEnergySellPrice() {
        return this.EnergySellPrice;
    }

    /**
     * Setter for EnergySellPrice.
     *
     * @param NewEnergySellPrice integer value that EnergySellPrice is set to.
     */
    public void setEnergySellPrice(Integer NewEnergySellPrice) {
        this.EnergySellPrice = NewEnergySellPrice;
        sellEnergy.setText(getEnergySellPrice().toString());
    }

    /**
     * Getter for EnergyBuyPrice.
     *
     * @return this.EnergyBuyPrice returns energy buying price as an integer.
     */
    public Integer getEnergyBuyPrice() {
        return this.EnergyBuyPrice;
    }

    /**
     * Setter for EnergyBuyPrice.
     *
     * @param NewEnergyBuyPrice integer value that EnergyBuyPrice is set to.
     */
    public void setEnergyBuyPrice(Integer NewEnergyBuyPrice) {
        this.EnergyBuyPrice = NewEnergyBuyPrice;
        buyEnergy.setText(getEnergyBuyPrice().toString());
    }


    /**
     * A method that allows buying resources from the market.
     * <p>
     * Depending on what type of resources is passed ("ore", "food" or "energy") method checks whether it is sufficient
     * amount of that resource in market stock. Then it is checked whether does Player has enough money to buy required
     * amount. Market stock value (e.g. OreStock) is reduced by the quantity that has been bought(Quantity). Players money
     * (playersMoney) are reduced by the amount that was spent on the purchase. The value of Players resource stock is
     * updated. New selling and buying prices, for that chosen resource, are calculated (calculateNewCost()).
     * </p>
     *
     * @param Stock_Type Type of resources (ore, energy or food) that is stored in the market.
     * @param Quantity   The amount of resources that Player wants to buy.
     * @param Player     A Player object.
     */
    public Player buy(String Stock_Type, Integer Quantity, Player Player) throws Exception {
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
                    OreBuyPrice = calculateNewCost(OreStock, "buy");
                    OreSellPrice = calculateNewCost(OreStock, "sell");
                    oreStockLabel.setText(getOreStock().toString());
                    buyOre.setText(getOreBuyPrice().toString());
                } else {
                    throw new Exception("Insufficient money");
                }
            } else {
                throw new Exception("Insufficient resources");
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
                    FoodBuyPrice = calculateNewCost(FoodStock, "buy");
                    FoodSellPrice = calculateNewCost(FoodStock, "sell");
                    foodStockLabel.setText(getFoodStock().toString());
                    buyFood.setText(getFoodBuyPrice().toString());
                } else {
                    throw new Exception("Insufficient money");
                }

            } else {
                throw new Exception("Insufficient resources");
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
                    EnergyBuyPrice = calculateNewCost(EnergyStock, "buy");
                    EnergySellPrice = calculateNewCost(EnergyStock, "sell");
                    energyStockLabel.setText(getEnergyStock().toString());
                    buyEnergy.setText(getEnergyBuyPrice().toString());
                } else {
                    throw new Exception("Insufficient money");
                }
            } else {
                throw new Exception("Insufficient resources");
            }
        } else {

            throw new Exception("Wrong Stock_Type passed");
        }
        return Player;


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
     *
     * @param Stock_Type Type of resources (ore, energy or food) that is stored in the market.
     * @param Quantity   The amount of resources that Player wants to buy.
     * @param Player     A Player object.
     */
    public Player sell(String Stock_Type, int Quantity, Player Player) throws Exception {
        int playersMoney = Player.getMoney();
        if ("ore".equals(Stock_Type)) {
            int playersOre = Player.getOreCount();
            if (playersOre >= Quantity) {
                OreStock += Quantity;
                playersMoney += Quantity * OreSellPrice;
                Player.setMoney(playersMoney);
                playersOre -= Quantity;
                Player.setOreCount(playersOre);

                OreBuyPrice = calculateNewCost(OreStock, "buy");
                OreSellPrice = calculateNewCost(OreStock, "sell");
                oreStockLabel.setText(getOreStock().toString());
                sellOre.setText(getOreSellPrice().toString());
            } else {
                throw new Exception("Insufficient resources");

            }
        } else if ("food".equals(Stock_Type)) {
            int playersFood = Player.getFoodCount();
            if (playersFood >= Quantity) {

                FoodStock += Quantity;
                playersMoney += Quantity * FoodSellPrice;
                Player.setMoney(playersMoney);
                playersFood -= Quantity;
                Player.setFoodCount(playersFood);
                FoodBuyPrice = calculateNewCost(FoodStock, "buy");
                FoodSellPrice = calculateNewCost(FoodStock, "sell");
                foodStockLabel.setText(getFoodStock().toString());
                sellFood.setText(getFoodSellPrice().toString());
            } else {
                throw new Exception("Insufficient resources");
            }
        } else if ("energy".equals(Stock_Type)) {
            int playersEnergy = Player.getEnergyCount();
            if (playersEnergy >= Quantity) {
                EnergyStock += Quantity;
                playersMoney += Quantity * EnergySellPrice;
                Player.setMoney(playersMoney);
                playersEnergy -= Quantity;
                Player.setEnergyCount(playersEnergy);
                EnergyBuyPrice = calculateNewCost(EnergyStock, "buy");
                EnergySellPrice = calculateNewCost(EnergyStock, "sell");
                energyStockLabel.setText(getEnergyStock().toString());
                sellEnergy.setText(getEnergySellPrice().toString());
            } else {
                throw new Exception("Insufficient resources");
            }

        }
        return Player;

    }


    /**
     * A method that allows gambling as specified in the brief.
     * <p>
     * First it is checked whether a chosen amount of money is not higher than the total amount of Player's money.
     * Number generator generates whether 0 or 1. If 0 is generated - Player loses and his money is reduced by the
     * "amountToGamble". If 1 is generated - Player wins and his money is increased by the "amountToGamble".
     * </p>
     *
     * @param amountToGamble The amount of money that is meant to be spent for gambling.
     * @param Player         A Player object.
     * @return Returns True if Player has won, False if he lost and null if Player has less money than
     * chosen amount of money to gamble with.
     */
    public Boolean gamble(Integer amountToGamble, Player Player) {
        int playersMoney = Player.getMoney();
        if (amountToGamble <= playersMoney) {
            Random rand = new Random();
            int result = rand.nextInt(2);
            if (result == 0) {
                playersMoney -= amountToGamble;
                Player.setMoney(playersMoney);
                return false;
            } else {
                playersMoney += amountToGamble;
                Player.setMoney(playersMoney);
                return true;
            }

        } else {
            return null; //throw an error or prevent them clicking it in the first place
        }

    }


    /**
     * A method that calculates cost of market selling and buying prices.
     * <p>
     * <p>
     * When Stock value equals 0 and wanted operation is "buy", costOfResource value is set to 0. When Stock value is
     * equal to 0 and wanted operation is "sell", costOfResource value is set to 200.
     * If wanted operation is "buy", new buying price is calculated. If wanted operation is "sell", then new selling
     * price is calculated.
     * </p>
     *
     * @param Stock Integer values of market resources.
     * @param oper  String value representing operations "buy" and "sell".
     */
    private int calculateNewCost(int Stock, String oper) throws Exception {
        double cost;
        Integer costOfResources;
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
            throw new Exception("Wrong operator");
        }
        return costOfResources;

    }

    /**
     * A method that allows to buy a Roboticon.
     * <p>
     * Ferst method checks whether there are any available roboticons left. Then it is checked whether player has enough
     * money to buy Roboticon. Roboticon object is created and assigned to Tile and to Player objects. Robotikon stock
     * number is decreased by one. Player's money are decreased by amount that was spent on purchase.
     * </p>
     *
     * @param Player      A Player object that owns the Roboticon.
     */
    public Player buyRoboticon(Player Player) throws Exception {
        if (RoboticonStock > 0) {
            if (Player.getMoney() >= RoboticonBuyPrice) {
                if (OreStock > 0) {
                    RoboticonStock -= 1;
                    Player.setMoney(Player.getMoney() - RoboticonBuyPrice);
                    RoboticonBuyPrice += 5;
                    Player.increaseRoboticonInventory();

                    roboticonStockLabel.setText(this.getRoboticonStock().toString());
                    buyRoboticon.setText(getRoboticonBuyPrice().toString());
                } else {
                    throw new Exception("Insufficient ore");
                }
            } else {
                throw new Exception("Insufficient money");
            }
        } else {
            throw new Exception("No available Roboticons");
        }
    return Player;
    }

}




