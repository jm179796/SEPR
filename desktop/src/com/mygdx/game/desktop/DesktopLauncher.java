package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.duck.game.engine.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//GAME CONFIG
		config.title = "Duck-Related Game";
		config.width = 800;
		config.height = 600;


		new LwjglApplication(new Main(), config);
	}
}
