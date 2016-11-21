package com.mygdx.game;
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

  public void buy(String Stock_Type, int Quantity ) {
    switch(Stock_Type){
      case "ore": if(Quantity < OreStock) {
        OreStock = OreStock - Quantity;
        // need to change players stock value and money

                } else {
                  System.out.println("Inscufficient resources");
                }
      case "food": if (Quantity < FoodStock) {
        FoodStock = FoodStock - Quantity;
        // need to change players stock value and money
                } else {
                  System.out.println("Inscufficient resources");
                }
      case "energy": if (Quantity < EnergyStock) {
        EnergyStock = EnergyStock - Quantity;
        // need to change players stock value and money
      } else {
        System.out.println("Inscufficient resources");
      }

  }

  public void sell(String Stock_Type, int Quantity) {
        switch(Stock_Type){
            case "ore":
                OreStock = OreStock + Quantity;
                // need to change players stock value and money

            case "food": FoodStock = FoodStock + Quantity;
                // need to change players stock value and money

            case "energy":
                EnergyStock = EnergyStock + Quantity;
                // need to change players stock value and money

    }
  }

  public void gamble() {
  }

  public void calculateNewCost() {

  }

  //public Integer getPrice(String Stock_Type){
    //return Stock_Type.Price;
  //}

}
