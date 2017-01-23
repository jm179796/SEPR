package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.Random;

/**
 * @author Duck Related Team Name in Big Massive Letters
 * @since Assessment 2
 * @version Assessment 2
 *
 * An executable version of the game can be found at: https://jm179796.github.io/SEPR/DRTN-Assessment2.jar
 * Our website is: https://jm179796.github.io/SEPR/
 */
public class Market extends Table {
    /**
     * Holds game-state
     */
    private Game game;

    /**
     * Engine class that handles all of the game's logical processing
     */
    private GameEngine engine;

    /**
     * Object defining QOL drawing functions for rectangles and on-screen tables
     * Used in this class to simplify row creation in tables
     */
    private Drawer drawer;

    /**
     * Defines the font of the text comprising the market's interface
     */
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

    /**
     * Button in the market's interface that buys ore
     */
    private TextButton buyOre;

    /**
     * Button in the market's interface that buys food
     */
    private TextButton buyFood;

    /**
     * Button in the market's interface that buys energy
     */
    private TextButton buyEnergy;

    /**
     * Button in the market's interface that buys Roboticons
     */
    private TextButton buyRoboticon;

    /**
     * Button in the market's interface that sells the current player's ore stocks to the market
     */
    private TextButton sellOre;

    /**
     * Button in the market's interface that sells the current player's food stocks to the market
     */
    private TextButton sellFood;

    /**
     * Button in the market's interface that sells the current player's energy stocks to the market
     */
    private TextButton sellEnergy;

    /**
     * Visualises the amount of ore stocks currently held by the market
     */
    private Label oreStockLabel;

    /**
     * Visualises the amount of food stocks currently held by the market
     */
    private Label foodStockLabel;

    /**
     * Visualises the amount of energy stocks currently held by the market
     */
    private Label energyStockLabel;

    /**
     * Visualises the amount of Roboticon stocks currently held by the market
     */
    private Label roboticonStockLabel;

    /**
     * Constructs the market by calculating buying/selling costs and arranging the associated visual interface
     * Imports the game's state (for direct renderer access) and the engine which controls it, before setting up
     * the functions and visual features of its internal purchase/sale buttons and populating a drawable visual
     * framework with them and some other stock/identification labels
     *
     * @param game The game's state, which is used in this context to operate the game's renderer via the Drawer class
     * @param engine The game's engine, which directly controls the availability and prices of market resources
     */
    public Market(Game game, GameEngine engine) {
        super();
        //Run the constructor for the Table object that forms this market's visual interface

        this.game = game;
        //Import the game's current state

        this.engine = engine;
        //Link the market to the game's engine
        //This is required to access phase information, as certain market sectors open and close based on it

        drawer = new Drawer(this.game);
        //QOL class that uses the game's state to directly access and control the game's renderer
        //Used by this class to construct the market's visual interface

        tableFont = new TTFont(Gdx.files.internal("font/testfontbignoodle.ttf"), 24);
        //Establish the font in which the market interface's text is to be pronted

        try {
            OreBuyPrice = calculateNewCost(OreStock, "buy");
            FoodBuyPrice = calculateNewCost(FoodStock, "buy");
            EnergyBuyPrice = calculateNewCost(EnergyStock, "buy");
            OreSellPrice = calculateNewCost(OreStock, "sell");
            FoodSellPrice = calculateNewCost(FoodStock, "sell");
            EnergySellPrice = calculateNewCost(EnergyStock, "sell");
            //Calculate the market's buying/selling prices
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        constructButtons();
        //Build the purchase/sale buttons to populate the market's interface with

        constructInterface();
        //Build the market's visual interface using the buttons declared and prepared earlier
    }

    /**
     * Instantiates the purchase/sale buttons to be placed in the market and sets their on-click functions
     * Obviously, these buttons enable players to buy and sell resources during certain game-phases
     */
    private void constructButtons() {
        TextButton.TextButtonStyle tableButtonStyle = new TextButton.TextButtonStyle();
        tableButtonStyle.font = tableFont.font();
        tableButtonStyle.fontColor = Color.WHITE;
        tableButtonStyle.pressedOffsetX = 1;
        tableButtonStyle.pressedOffsetY = -1;
        //Set the visual parameters for the rest of the market's labels and buttons

        /**
         * Button that attempts to buy a Roboticon for the current player when clicked on
         */
        buyRoboticon = new TextButton("-" + getRoboticonBuyPrice().toString(), tableButtonStyle);
        buyRoboticon.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    try {
                        engine.updateCurrentPlayer(buy("roboticon", 1, engine.currentPlayer()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                refreshButtonAvailability();

            }
        });
        //Set the button for purchasing Roboticons to do just that (but only when the game is in phase 2)

        /**
         * Button that attempts to buy a unit of ore for the current player when clicked on
         */
        buyOre = new TextButton("-" + getOreBuyPrice().toString(), tableButtonStyle);
        buyOre.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    try {
                        engine.updateCurrentPlayer(buy("ore", 1, engine.currentPlayer()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                refreshButtonAvailability();
            }
        });
        //Set the button for purchasing ore to do just that (but only when the game is in phase 5)

        /**
         * Button that attempts to buy a unit of food for the current player when clicked on
         */
        buyFood = new TextButton("-" + getFoodBuyPrice().toString(), tableButtonStyle);
        buyFood.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    try {
                        engine.updateCurrentPlayer(buy("food", 1, engine.currentPlayer()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                refreshButtonAvailability();
            }
        });
        //Set the button for purchasing food to do just that (but only when the game is in phase 5)

        /**
         * Button that attempts to buy a unit of energy for the current player when clicked on
         */
        buyEnergy = new TextButton("-" + getEnergyBuyPrice().toString(), tableButtonStyle);
        buyEnergy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    try {
                        engine.updateCurrentPlayer(buy("energy", 1, engine.currentPlayer()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                refreshButtonAvailability();
            }
        });
        //Set the button for purchasing energy to do just that (but only when the game is in phase 5)

        /**
         * Button that attempts to take a unit of energy from the player's inventory and sell it back to the market
         * when clicked on
         */
        sellEnergy = new TextButton("+" + getEnergySellPrice().toString(), tableButtonStyle);
        sellEnergy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {

                    try {
                        engine.updateCurrentPlayer(sell("energy", 1, engine.currentPlayer()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                refreshButtonAvailability();
            }
        });
        //Set the button for selling energy to do just that (but only when the game is in phase 5)

        /**
         * Button that attempts to take a unit of ore from the player's inventory and sell it back to the market
         * when clicked on
         */
        sellOre = new TextButton("+" + getOreSellPrice().toString(), tableButtonStyle);
        sellOre.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    try {
                        engine.updateCurrentPlayer(sell("ore", 1, engine.currentPlayer()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                refreshButtonAvailability();
            }
        });
        //Set the button for selling ore to do just that (but only when the game is in phase 5)

        /**
         * Button that attempts to take a unit of food from the player's inventory and sell it back to the market
         * when clicked on
         */
        sellFood = new TextButton("+" + getFoodSellPrice().toString(), tableButtonStyle);
        sellFood.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {

                    try {
                        engine.updateCurrentPlayer(sell("food", 1, engine.currentPlayer()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                refreshButtonAvailability();
            }
        });
        //Set the button for selling food to do just that (but only when the game is in phase 5)

        refreshButtonAvailability();
        //Ensure that these buttons are disabled at the beginning of the game
    }

    /**
     * Builds the market's visual interface by populating it with labels and buttons
     * Once this method has finished executing, the market can be drawn to a stage like any other actor
     */
    private void constructInterface() {
        tableFont.setSize(36);
        drawer.addTableRow(this, new Label("Market", new Label.LabelStyle(tableFont.font(), Color.WHITE)), 0, 0, 5, 0, 3);
        //Add a heading to the market interface

        tableFont.setSize(24);

        this.row();
        this.add(new Label("Item", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left().padRight(90);
        this.add(new Label("Buy", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left().padRight(40);
        this.add(new Label("Sell", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left().padRight(20);
        //Visual guff

        this.row();
        this.add(new Label("Ore", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(buyOre).left();
        this.add(sellOre).left();
        //Add buttons for buying and selling ore to the market's visual framework
        //Note that the strings encoded by these TextButtons represent the market's current buying/selling prices for ore

        this.row();
        this.add(new Label("Food", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(buyFood).left();
        this.add(sellFood).left();
        //Add buttons for buying and selling food to the market's visual framework
        //Note that the strings encoded by these TextButtons represent the market's current buying/selling prices for food

        this.row();
        this.add(new Label("Energy", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(buyEnergy).left();
        this.add(sellEnergy).left();
        //Add buttons for buying and selling energy to the market's visual framework
        //Note that the strings encoded by these TextButtons represent the market's current buying/selling prices for energy

        this.row();
        this.add(new Label("Roboticons", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left().padBottom(15);
        this.add(buyRoboticon).left().padBottom(15);
        //Add button for buying Roboticons to the market's visual framework
        //Note that the string encoded by this TextButton represents the market's current buying price for Roboticons

        oreStockLabel = new Label(getOreStock().toString(), new Label.LabelStyle(tableFont.font(), Color.WHITE));
        foodStockLabel = new Label(getFoodStock().toString(), new Label.LabelStyle(tableFont.font(), Color.WHITE));
        energyStockLabel = new Label(getEnergyStock().toString(), new Label.LabelStyle(tableFont.font(), Color.WHITE));
        roboticonStockLabel = new Label(getRoboticonStock().toString(), new Label.LabelStyle(tableFont.font(), Color.WHITE));
        //Prepare new labels to encode resources' stock levels within the market
        //These will NOT be interactive, unlike the buttons declared earlier on

        this.row();
        this.add(new Label("Item", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left().padRight(90);
        this.add(new Label("Stock", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        //More visual guff!

        this.row();
        this.add(new Label("Ore", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(oreStockLabel).left();
        //Add label to encode current ore stocks to the market's visual framework

        this.row();
        this.add(new Label("Food", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(foodStockLabel).left();
        //Add label to encode current food stocks to the market's visual framework

        this.row();
        this.add(new Label("Energy", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(energyStockLabel).left();
        //Add label to encode current energy stocks to the market's visual framework

        this.row();
        this.add(new Label("Roboticons", new Label.LabelStyle(tableFont.font(), Color.WHITE))).left();
        this.add(roboticonStockLabel).left();
        //Add label to encode current Roboticon stocks to the market's visual framework
    }

    /**
     * Returns the number of Roboticons currently held in the market
     *
     *
     * @return Integer The number of Roboticons currently held in the market
     */
    public Integer getRoboticonStock() {
        return this.RoboticonStock;
    }

    /**
     * Sets the market's Roboticon stock level
     * Also updates the appropriate stock label to reflect the new quantity given
     *
     * @param NewRoboticonStock The new number of Roboticons to be held in the market
     */
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
     * Getter for OreStock
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
        if ("ore".equals(Stock_Type)) {
            if (Quantity <= OreStock) {
                int OrePrice = OreBuyPrice * Quantity;
                if (Player.getMoney() >= OrePrice) {
                    OreStock -= Quantity;
                    Player.setMoney(Player.getMoney() - OrePrice);
                    int playersOre = Player.getOreCount();
                    playersOre += Quantity;
                    Player.setOreCount(playersOre);
                    OreBuyPrice = calculateNewCost(OreStock, "buy");
                    OreSellPrice = calculateNewCost(OreStock, "sell");
                    oreStockLabel.setText(getOreStock().toString());
                    buyOre.setText("-" + getOreBuyPrice().toString());
                } else {
                    throw new Exception("Insufficient money");
                }
            } else {
                throw new Exception("Insufficient resources");
            }
        } else if ("food".equals(Stock_Type)) {
            if (Quantity <= FoodStock) {
                int FoodPrice = FoodBuyPrice * Quantity;
                if (Player.getMoney() >= FoodPrice) {
                    FoodStock -= Quantity;
                    Player.setMoney(Player.getMoney() - FoodPrice);
                    int playersFood = Player.getFoodCount();
                    playersFood += Quantity;
                    Player.setFoodCount(playersFood);
                    FoodBuyPrice = calculateNewCost(FoodStock, "buy");
                    FoodSellPrice = calculateNewCost(FoodStock, "sell");
                    foodStockLabel.setText(getFoodStock().toString());
                    buyFood.setText("-" + getFoodBuyPrice().toString());
                } else {
                    throw new Exception("Insufficient money");
                }

            } else {
                throw new Exception("Insufficient resources");
            }
        } else if ("energy".equals(Stock_Type)) {
            if (Quantity <= EnergyStock) {
                int EnergyPrice = EnergyBuyPrice * Quantity;
                if (Player.getMoney() >= EnergyPrice) {
                    EnergyStock -= Quantity;
                    Player.setMoney(Player.getMoney() - EnergyPrice);
                    int playersEnergy = Player.getEnergyCount();
                    playersEnergy += Quantity;
                    Player.setEnergyCount(playersEnergy);
                    EnergyBuyPrice = calculateNewCost(EnergyStock, "buy");
                    EnergySellPrice = calculateNewCost(EnergyStock, "sell");
                    energyStockLabel.setText(getEnergyStock().toString());
                    buyEnergy.setText("-" + getEnergyBuyPrice().toString());
                } else {
                    throw new Exception("Insufficient money");
                }
            } else {
                throw new Exception("Insufficient resources");
            }
        } else if ("roboticon".equals(Stock_Type)) {
            if (RoboticonStock > 0) {
                if (Player.getMoney() >= RoboticonBuyPrice) {
                    RoboticonStock -= 1;
                    Player.setMoney(Player.getMoney() - RoboticonBuyPrice);
                    RoboticonBuyPrice += 5;
                    Player.increaseRoboticonInventory();

                    roboticonStockLabel.setText(this.getRoboticonStock().toString());
                    buyRoboticon.setText("-" + getRoboticonBuyPrice().toString());
                } else {
                    throw new Exception("Insufficient money");
                }
            } else {
                throw new Exception("No available Roboticons");
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
                sellOre.setText("+" + getOreSellPrice().toString());
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
                sellFood.setText("+" + getFoodSellPrice().toString());
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
                sellEnergy.setText("+" + getEnergySellPrice().toString());
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
     * When Stock value equals 0 and wanted operation is "buy", costOfResource value is set to 0. When Stock value is
     * equal to 0 and wanted operation is "sell", costOfResource value is set to 200.
     * If wanted operation is "buy", new buying price is calculated. If wanted operation is "sell", then new selling
     * price is calculated.
     * </p>
     *
     * @param Stock Integer values of market resources.
     * @param oper  String value representing operations "buy" and "sell".
     *
     * @return costofresources Integer value of the resource's new cost
     * @throws Exception Thrown if there's a wrong operator used with the function
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
     * Enables/disables the market's purchase/sale buttons and updates their colours to reflect the player's current
     * amount of money, the game's current phase and the player's inventory
     *
     * GRAY: Cannot buy/sell resource on the current phase
     * GREEN: Can buy/sell resource
     * RED: Cannot buy/sell resource due to a lack of money or stock
     */
    public void refreshButtonAvailability() {
        if (engine.phase() == 2) {
            if (engine.currentPlayer().getMoney() >= RoboticonBuyPrice && RoboticonStock > 0) {
                drawer.switchTextButton(buyRoboticon, true, Color.GREEN);
            } else {
                drawer.switchTextButton(buyRoboticon, false, Color.RED);
            }
            //If the game is in phase 2, enable the roboticon purchase button ONLY (and only if the current player can
            //afford one while one is in stock)

            drawer.switchTextButton(buyOre, false, Color.GRAY);
            drawer.switchTextButton(buyFood, false, Color.GRAY);
            drawer.switchTextButton(buyEnergy, false, Color.GRAY);
            //Disable all of the market's other functions in phase 2
        } else if (engine.phase() == 5) {
            if (engine.currentPlayer().getMoney() >= OreBuyPrice && OreStock > 0) {
                drawer.switchTextButton(buyOre, true, Color.GREEN);
            } else {
                drawer.switchTextButton(buyOre, false, Color.RED);
            }
            //Conditionally enable the ore purchase button

            if (engine.currentPlayer().getMoney() >= EnergyBuyPrice && EnergyStock > 0) {
                drawer.switchTextButton(buyEnergy, true, Color.GREEN);
            } else {
                drawer.switchTextButton(buyEnergy, false, Color.RED);
            }
            //Conditionally enable the energy purchase button

            if (engine.currentPlayer().getMoney() >= FoodBuyPrice && FoodStock > 0) {
                drawer.switchTextButton(buyFood, true, Color.GREEN);
            } else {
                drawer.switchTextButton(buyFood, false, Color.RED);
            }
            //Conditionally enable the food purchase button

            drawer.switchTextButton(buyRoboticon, false, Color.GRAY);
            //Disable the roboticon purchase button in round 5

            if (engine.currentPlayer().getOreCount() > 0) {
                drawer.switchTextButton(sellOre, true, Color.GREEN);
            } else {
                drawer.switchTextButton(sellOre, false, Color.RED);
            }
            //Conditionally enable the ore sale button

            if (engine.currentPlayer().getEnergyCount() > 0) {
                drawer.switchTextButton(sellEnergy, true, Color.GREEN);
            } else {
                drawer.switchTextButton(sellEnergy, false, Color.RED);
            }
            //Conditionally enable the energy sale button

            if (engine.currentPlayer().getFoodCount() > 0) {
                drawer.switchTextButton(sellFood, true, Color.GREEN);
            } else {
                drawer.switchTextButton(sellFood, false, Color.RED);
            }
            //Conditionally enable the food sale button
        } else {
            drawer.switchTextButton(buyOre, false, Color.GRAY);
            drawer.switchTextButton(buyFood, false, Color.GRAY);
            drawer.switchTextButton(buyEnergy, false, Color.GRAY);

            drawer.switchTextButton(buyRoboticon, false, Color.GRAY);

            drawer.switchTextButton(sellOre, false, Color.GRAY);
            drawer.switchTextButton(sellFood, false, Color.GRAY);
            drawer.switchTextButton(sellEnergy, false, Color.GRAY);
            //Disable the entire market if the game is in one of phases 1, 3 and 4
        }
    }
}




