package com.mygdx.game;

import java.util.*;

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
     // Player player = new Player(1);  // ??? what parameter needs to be passed to Player
      switch (Stock_Type) {
          case "ore":
              if (Quantity < OreStock) {
                  int OrePrice;
                  OrePrice = OreBuyPrice * Quantity;
                  if (this.Player.Money >= OrePrice) {
                      OreStock -= Quantity;
                      this.Player.Money -= OrePrice;
                      this.Player.OreCount += Quantity;
                      calculateNewCost(OreStock, OreBuyPrice, "buy");
                  } else {
                      System.out.println("Insufficient money");
                  }
              } else {
                  System.out.println("Inscufficient resources");
              }
          case "food":
              if (Quantity < FoodStock) {
                  int FoodPrice;
                  FoodPrice = FoodBuyPrice * Quantity;
                  if (this.Player.Money >= FoodPrice) {
                      FoodStock -= Quantity;
                      this.Player.Money -= FoodPrice;
                      this.Player.FoodCount += Quantity;
                      calculateNewCost(FoodStock, FoodBuyPrice, "buy");
                  } else {
                      System.out.println("Insufficient money");
                  }

              } else {
                  System.out.println("Inscufficient resources");
              }
          case "energy":
              if (Quantity < EnergyStock) {
                  int EnergyPrice;
                  EnergyPrice = EnergyBuyPrice * Quantity;
                  if (this.Player.Money >= EnergyPrice) {
                      EnergyStock -= Quantity;
                      this.Player.Money -= EnergyPrice;
                      this.Player.EnergyCount += Quantity;
                      calculateNewCost(EnergyStock, EnergyBuyPrice, "buy");
                  } else {
                      System.out.println("Insufficient money");
                  }
              } else {
                  System.out.println("Inscufficient resources");
              }

      }
  }


  public void sell(String Stock_Type, int Quantity, Player Player) {
        switch(Stock_Type){
            case "ore":
                if(this.Player.OreCount >= Quantity){
                    OreStock += Quantity;
                    this.Player.Money += Quantity * OreSellPrice;
                    this.Player.OreCount -= Quantity;
                    calculateNewCost(OreStock, OreSellPrice, "sell");
                } else {
                    System.out.println("Inscufficient resources");
                }
            case "food":
                if(this.Player.FoodCount >= Quantity){
                    FoodStock += Quantity;
                    this.Player.Money += Quantity * FoodSellPrice;
                    this.Player.FoodCount -= Quantity;
                    calculateNewCost(FoodStock, FoodSellPrice, "sell");
                } else {
                    System.out.println("Inscufficient resources");
                }
            case "energy":
                if(this.Player.EnergyCount >= Quantity){
                    EnergyStock += Quantity;
                    this.Player.Money += Quantity * EnergySellPrice;
                    this.Player.EnergyCount -= Quantity;
                    calculateNewCost(EnergyStock, EnergySellPrice, "sell");
                } else {
                    System.out.println("Inscufficient resources");
                }

    }
  }

  public void gamble() {
  }

  public void calculateNewCost(int Stock, int costOfResources, String oper) {
      double cost = 0;
      if (Stock == 0 && oper == "buy") {
          costOfResources = 0;
      } else if (Stock == 0 && oper == "sell") {
          costOfResources = 50;
      } else if (oper == "buy") {
          cost = 16 / Stock + 2;
          int costInt = (int) Math.round(cost);
          if (cost < 1) {
              costOfResources = 1;
          } else if (cost > 100) {
              costOfResources = 100;
          } else {
              costOfResources = costInt;
          }

      } else if (oper == "sell") {
          cost = 16 / Stock;
          int costInt = (int) Math.round(cost);
          if (cost < 1) {
              costOfResources = 1;
          } else if (cost > 100) {
              costOfResources = 100;
          } else {
              costOfResources = costInt;
          }
      } else {
          System.out.println("Wrong operator");
      }
  }

}

  //public Integer getPrice(String Stock_Type){
    //return Stock_Type.Price;
  //}


