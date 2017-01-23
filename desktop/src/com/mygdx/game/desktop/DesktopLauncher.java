package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

/**
 * @author Duck Related Team Name in Big Massive Letters
 * @version Assessment 2
 *          <p>
 *          An executable version of the game can be found at: https://jm179796.github.io/SEPR/DRTN-Assessment2.jar
 *          Our website is: https://jm179796.github.io/SEPR/
 * @since Assessment 2
 */
public class DesktopLauncher {

	/**
	 * Creates a new window with the specified title, size and behaviour parameters before assigning a fresh
	 * game-state to it, which is managed by the Main() class and all classes recursively spawned by it
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//GAME CONFIG
		config.title = "Duck-Related Game";
		config.width = 1024;
		config.height = 512;
		config.vSyncEnabled = true;
		config.resizable = false;

		new LwjglApplication(new Main(), config);
	}
}
