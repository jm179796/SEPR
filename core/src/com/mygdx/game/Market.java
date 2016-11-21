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

  public void buy(String Stock_Type, int Quantity ) {
    switch(Stock_Type){
      case "ore":
          if(Quantity < OreStock) {
            int OrePrice;
            OrePrice = OreBuyPrice * Quantity;
            if(Player.Money >= OrePrice){
                OreStock -= Quantity;
                Player.Money -= OrePrice;
                Player.OreCount += Quantity;
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
              if(Player.Money >= FoodPrice){
                  FoodStock -= Quantity;
                  Player.Money -= FoodPrice;
                  Player.FoodCount += Quantity;
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
                if(Player.Money >= EnergyPrice){
                   EnergyStock -= Quantity;
                   Player.Money -= EnergyPrice;
                   Player.EnergyCount += Quantity;
                } else {
                    System.out.println("Insufficient money");
                }
          } else {
        System.out.println("Inscufficient resources");
      }

  }

  public void sell(String Stock_Type, int Quantity) {
        switch(Stock_Type){
            case "ore":
                if(Player.OreCount >= Quantity){
                    OreStock = OreStock + Quantity;
                }

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
