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
		//assignCollege(Player1) NEEDS USER INPUT
		//assignCollege(Player2) NEEDS USER INPUT
		Market Market = new Market();
		List<List<Tile>> TileList = createTiles();
		int currentPhase = 1;
		boolean gameRunning = true;
		while (gameRunning) {
			Player1 = PhaseSelector(currentPhase, Player1);
			Player2 = PhaseSelector(currentPhase, Player2);
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

	/**
	 * Initiates the first phase of the round.
	 * <p>
	 * First of all the tile that the user has selected is detected by the method(THIS NEEDS EXPANDING ONCE IMPLEMENTED).
	 * Then the tile is checked against all the other tiles in the player's tile list to see if it is adjacent to the selected tile
	 * by using it's coordinates.  If at least one of the players tiles are adjacent then the tile is added to the players collection and the phase ends. If not,
	 * the user will be asked to select another tile.
	 * </p>
	 * @param Player The player that is active during the phase.
	 */
	public static Player Phase1(Player Player){
		//detect selected tile of Player
		//for(Tile adjacentTile:Player.getTileList()) {
			// boolean adjacent = adjacentTile.isAdjacent(selectedTile);
			// if(adjacent == true){
					//Player.assignTile(adjacentTile);
					//adjacentTile.setOwner(Player);
					//break
				//}
		//}
		return Player;
	}

	/**
	 * Initiates the second phase of the round.
	 * <p>
	 * The whole phase is timed based on the time stored in the variable 'timeLeft' as seconds. The method will then
	 * detect when the player has bought a roboticon(THIS NEEDS EXPANDING ONCE IMPLEMENTED) and then add it to the
	 * collection of the player.
	 * </p>
	 * @param Player The player that is active during the phase.
	 * @throws InterruptedException Thrown if the time slept is interrupted as the clock decreases.
	 */
	public static Player Phase2(Player Player) throws InterruptedException{
		//timeLeft = 120;
		//while(timeLeft > 0){
			//detect the roboticon bought by the player
				//Player.addRoboticon(Roboticon);
				//Roboticon.setOwner(Player);
				//customise Roboticon
			//TimeUnit.SECONDS.sleep(1);
			//timeLeft -= 1;
		return Player;
	}

	/**
	 * Initiates the third phase of the round.
	 * <p>
	 * The whole phase is timed based on the time stored in the variable 'timeLeft' as seconds. The method then waits for
	 * the user to select of roboticon and a tile to place it on then adds it to the tile.
	 * </p>
	 * @param Player The player that is active during the phase.
	 * @throws InterruptedException Thrown if the time slept is interrupted as the clock decreases.
	 */
	public static Player Phase3(Player Player) throws InterruptedException {
       //timeLeft = 120;
		// while (timeLeft > 0) {
            //check for roboticon selection
            //check for tile selection
            //Tile.assignRoboticon(Roboticon);
            //TimeUnit.SECONDS.sleep(1);
			//timeLeft -= 1;
		// }
		return Player;
    }

	/**
	 * Initiates the fourth phase of the round.
	 * <p>
	 * For every tile in the player's tile list, the resources produced by each tile are added to the player's inventory.
	 * </p>
	 * @param Player The player that is active during the phase.
	 */
	public static Player Phase4(Player Player){
		List<Tile> tileList = Player.getTileList();
		for (Tile Tile : tileList){
			Player = Tile.Produce(Player);
		}
		return Player;

	}

	/**
	 * Initiates the fifth phase of the round.
	 * <p>
	 * Lets the player access the market and buy/sell goods(NEEDS EXPANDING ONCE IMPLEMENTED).
	 * </p>
	 * @param Player
	 */
	public static Player Phase5(Player Player){
        //code for user selection of stock type
        //basic structure as follows
        //int price = Market.getPrice(Stock_Type);
        //Player.changeMoney(-price);
        //Market.Stock_type -= 1;
        //Player.varyResource(Stock_Type, 1);
		return Player;
    }

	/**
	 * Method to initialise the correct method corresponding to the current phase
	 @param Phase An integer holding the number of the current phase
	 @param Player The object of the player playing the round
	 */

	public static Player PhaseSelector(int Phase, Player Player) {
		Player.toggleActive();
		if(Phase == 1){
			Player = Phase1(Player);
		}
		else if(Phase == 2){
			try {
				Player = Phase2(Player);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(Phase == 3){
			try {
				Player = Phase3(Player);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(Phase == 4){
			Player = Phase4(Player);
		}
		else{
			Player = Phase5(Player);
		}
		Player.toggleActive();
		return Player;
	}



	//public static void assignCollege(Player){
		//code for detecting selected college
	//}
	
	 
	
	

}
