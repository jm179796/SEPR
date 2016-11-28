package com.mygdx.game;

import java.util.*;


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
		List<List<Tile>> TileList = createTiles();
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

	/**
	 * This method creates a 4x4 array containing tile objects.
	 * <p>
	 * The ore and energy count are set to 500 initially, the ore and energy modifiers are set to a random integer
	 * between 1 and 3. No tiles contain a landmark initially. This will be changed in another method so we can
	 * specify which tiles have landmarks in accordance to the map.
	 * </p>
	 * @return List A 2d array containing the tiles for the map. It is a 4x4 object.
	 */

	public static List<List<Tile>> createTiles() {
		List<List<Tile>> TileList = new ArrayList<List<Tile>>();
		for(int i = 0; i < TileList.size(); i++) {
			TileList.add(new ArrayList<Tile>());
		}
		int IDCount = 0;
		Random rand = new Random();
		for(int x = 0; x < 4 ; x++){
			for(int y = 0; y < 4 ; y++){
				IDCount += 1;
				Integer[] Coordinates = {x,y};
				TileList.get(x).add( new Tile(IDCount, rand.nextInt(3) + 1,  rand.nextInt(3) + 1, false, Coordinates));
			}
		}
		return TileList;
	}

	public static void Phase1(Player Player){
		//detect selected tile of Player
		//for(Tile adjacentTile:Player.getTileList()) {
			//if (adjacentTile.getCoordinates()[0] - Tile.getCoordinates()[0] <= 1 && adjacentTile.getCoordinates()[0] - Tile.getCoordinates()[0] >= -1) {
				//if (adjacentTile.getCoordinates()[1] - Tile.getCoordinates()[1] <= 1 && adjacentTile.getCoordinates()[1] - Tile.getCoordinates()[1] >= -1) {
					//Player.assignTile(adjacentTile);
					//adjacentTile.setOwner(Player);
				//}
			//}
		//}
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
