package com.duck.game.unimplemented;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Game_Engine {
	
	public static void main(){
		List<Phase> PhaseList = createPhases();
		Player Player1 = new Player(1);
		Player Player2 = new Player(2);
		//assignCollege(Player1)
		//assignCollege(Player2)
		Market Market = new Market();
		boolean gameRunning = true;
		while(gameRunning == true){
			//game code
		}
		
	}
	//creation of phases and returns a list of them//
	public static List<Phase> createPhases(){
		Phase Phase1 = new Phase(1, Integer.MAX_VALUE);
		Phase Phase2 = new Phase(2, 90);
		Phase Phase3 = new Phase(3, 90);
		Phase Phase4 = new Phase(1, Integer.MAX_VALUE);
		Phase Phase5 = new Phase(1, Integer.MAX_VALUE);
		List<Phase> PhaseList = new ArrayList<Phase>();
		PhaseList.add(Phase1);
		PhaseList.add(Phase2);
		PhaseList.add(Phase3);
		PhaseList.add(Phase4);
		PhaseList.add(Phase5);
		return PhaseList;
	}
	
	public static void Phase1(Player Player){
		//detect selected tile of Player
		//check if tile is adjacent to a player tile
		//add tile to TileList of Player
		//set Owner of Tile to Player
	}
	
	public static void Phase2(Player Player){
		//detect the roboticon bought by the player
		//Player.addRoboticon(Roboticon);
		//Roboticon.setOwner(Player);
		//customise Roboticon
	}
	
	public static void Phase3(Player Player, Phase Phase) throws InterruptedException {
        while (Phase.GetTime() != 0) {
            //check for roboticon selection
            //check for tile selection
            //Tile.assignRoboticon(Roboticon)
            TimeUnit.SECONDS.sleep(1);

        }
    }

	public static void Phase4(Player Player, Phase Phase) throws InterruptedException {
        while(Phase.GetTime() != 0){
            //code for production of  resources
            TimeUnit.SECONDS.sleep(1);

         }

	}

	public static void Phase5(Player Player){
        //code for user selection of stock type
        //basic structure as follows
        //int price = Market.getPrice(Stock_Type);
        //Player.changeMoney(-price);
        //Market.Stock_type -= 1;
        //Player.varyResource(Stock_Type, 1);
    }
	
	//public static void assignCollege(Player){
		//code for detecting selected college
	//}
	
	 
	
	

}
