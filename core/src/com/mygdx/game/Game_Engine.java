<<<<<<< HEAD
<<<<<<< HEAD
package com.mygdx.game;
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
=======
package com.mygdx.game;

/**
 * @author Jack Mountain jm1797
 * @version 1.1
 * @since 1.0
 */
public class Game_Engine {
	
	public static void main() {
		Player Player1 = new Player(1);
		Player Player2 = new Player(2);
		//assignCollege(Player1)
		//assignCollege(Player2)
		Market Market = new Market();
		int currentPhase = 1;
		boolean gameRunning = true;
		while (gameRunning) {
			PhaseSelector(currentPhase, Player1);
			PhaseSelector(currentPhase, Player2);
			currentPhase += 1;
			if (currentPhase > 5) {
				currentPhase = 1;
			}
		}
	}


	//public static List createTiles()[
			//need to decide how the tiles will be stored before this is implemented

			//]

	public static void Phase1(Player Player){
		//detect selected tile of Player
		//check if tile is adjacent to a player tile
		//Player.assignTile(Tile);
		//Tile.setOwner(Player);
	}
	
	public static void Phase2(Player Player) throws InterruptedException{
		//timeLeft = 120;
		//while(timeLeft > 0){
			//detect the roboticon bought by the player
				//Player.addRoboticon(Roboticon);
				//Roboticon.setOwner(Player);
				//customise Roboticon
			//TimeUnit.SECONDS.sleep(1);
			//timeLeft -= 1;
	}
	
	public static void Phase3(Player Player) throws InterruptedException {
       //timeLeft = 120;
		// while (timeLeft > 0) {
            //check for roboticon selection
            //check for tile selection
            //Tile.assignRoboticon(Roboticon);
            //TimeUnit.SECONDS.sleep(1);
			//timeLeft -= 1;
		// }
    }

	public static void Phase4(Player Player){
		//code for production of resources

	}

	public static void Phase5(Player Player){
        //code for user selection of stock type
        //basic structure as follows
        //int price = Market.getPrice(Stock_Type);
        //Player.changeMoney(-price);
        //Market.Stock_type -= 1;
        //Player.varyResource(Stock_Type, 1);
    }

	/**
	 * Method to initialise the correct method corresponding to the current phase
	 @param Phase An integer holding the number of the current phase
	 @param Player The object of the player playing the round
	 */

	public static void PhaseSelector(int Phase, Player Player) {
		Player.toggleActive();
		if(Phase == 1){
			Phase1(Player);
		}
		else if(Phase == 2){
			try {
				Phase2(Player);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(Phase == 3){
			try {
				Phase3(Player);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(Phase == 4){
			Phase4(Player);
		}
		else{
			Phase5(Player);
		}
		Player.toggleActive();
	}



	//public static void assignCollege(Player){
		//code for detecting selected college
	//}
	
	 
	
	

}
>>>>>>> refs/remotes/origin/master
=======
package com.mygdx.game;

import java.util.List;

/**
 * @author Jack Mountain jm1797
 * @version 1.2
 * @since 1.0
 */
public class Game_Engine {
	
	public static void main() {
		Player Player1 = new Player(1);
		Player Player2 = new Player(2);
		//assignCollege(Player1)
		//assignCollege(Player2)
		Market Market = new Market();
		int currentPhase = 1;
		boolean gameRunning = true;
		while (gameRunning) {
			PhaseSelector(currentPhase, Player1);
			PhaseSelector(currentPhase, Player2);
			currentPhase += 1;
			if (currentPhase > 5) {
				currentPhase = 1;
			}
		}
	}


	//public static List createTiles()[
			//need to decide how the tiles will be stored before this is implemented

			//]

	public static void Phase1(Player Player){
		//detect selected tile of Player
		//check if tile is adjacent to a player tile
		//Player.assignTile(Tile);
		//Tile.setOwner(Player);
	}
	
	public static void Phase2(Player Player) throws InterruptedException{
		//timeLeft = 120;
		//while(timeLeft > 0){
			//detect the roboticon bought by the player
				//Player.addRoboticon(Roboticon);
				//Roboticon.setOwner(Player);
				//customise Roboticon
			//TimeUnit.SECONDS.sleep(1);
			//timeLeft -= 1;
	}
	
	public static void Phase3(Player Player) throws InterruptedException {
       //timeLeft = 120;
		// while (timeLeft > 0) {
            //check for roboticon selection
            //check for tile selection
            //Tile.assignRoboticon(Roboticon);
            //TimeUnit.SECONDS.sleep(1);
			//timeLeft -= 1;
		// }
    }

	public static void Phase4(Player Player){
		List<Tile> tileList = Player.getTileList();
		for (Tile Tile : tileList){
			Player = Tile.Produce(Player);
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

	/**
	 * Method to initialise the correct method corresponding to the current phase
	 @param Phase An integer holding the number of the current phase
	 @param Player The object of the player playing the round
	 */

	public static void PhaseSelector(int Phase, Player Player) {
		Player.toggleActive();
		if(Phase == 1){
			Phase1(Player);
		}
		else if(Phase == 2){
			try {
				Phase2(Player);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(Phase == 3){
			try {
				Phase3(Player);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(Phase == 4){
			Phase4(Player);
		}
		else{
			Phase5(Player);
		}
		Player.toggleActive();
	}



	//public static void assignCollege(Player){
		//code for detecting selected college
	//}
	
	 
	
	

}
>>>>>>> refs/remotes/origin/master
