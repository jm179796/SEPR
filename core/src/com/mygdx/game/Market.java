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
            if(this.Player.Money >= OrePrice){
                OreStock -= Quantity;
                this.Player.Money -= OrePrice;
                this.Player.OreCount += Quantity;
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
              if(this.Player.Money >= FoodPrice){
                  FoodStock -= Quantity;
                  this.Player.Money -= FoodPrice;
                  this.Player.FoodCount += Quantity;
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
                if(this.Player.Money >= EnergyPrice){
                   EnergyStock -= Quantity;
                   this.Player.Money -= EnergyPrice;
                   this.Player.EnergyCount += Quantity;
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
                if(this.Player.OreCount >= Quantity){
                    OreStock += Quantity;
                    this.Player.Money += Quantity * OreSellPrice;
                    this.Player.OreCount -= Quantity;
                } else {
                    System.out.println("Inscufficient resources");
                }
            case "food":
                if(this.Player.FoodCount >= Quantity){
                    FoodStock += Quantity;
                    this.Player.Money += Quantity * FoodSellPrice;
                    this.Player.FoodCount -= Quantity;
                } else {
                    System.out.println("Inscufficient resources");
                }
            case "energy":
                if(this.Player.EnergyCount >= Quantity){
                    EnergyStock += Quantity;
                    this.Player.Money += Quantity * EnergySellPrice;
                    this.Player.EnergyCount -= Quantity;
                } else {
                    System.out.println("Inscufficient resources");
                }

    }
  }

  public void gamble() {
  }

  public void calculateNewCost() {
      int[][][] PriceArray = {
              {100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 0 }
              {1, 1, 2, 3, 4, 5, 6, 8, 11, 15, 30}
              {2, 2, 3, 4, 5, 6, 7, 9, 13, 20, 0}
            }
    private void SellPriceSetter(ResourcePriceStorage){
        // ResourcePriceStorage = OreSellPrice, FoodSellPrice...


    }


    private void BuyPriceSetter(ResourcePriceStorage){

    }



  }

  //public Integer getPrice(String Stock_Type){
    //return Stock_Type.Price;
  //}

}
