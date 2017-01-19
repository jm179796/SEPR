package com.mygdx.game;

import com.badlogic.gdx.Game;

public class Main extends Game {
	private Game game;
	//Allows screens to refer to this core class, enabling independent switching

	public Main() {
		game = this;
	}

	@Override
	public void create () {
		setScreen(new SplashScreen(game));
		//Load the splash screen as soon as the game opens
	}

	//It's a bit quiet in here, so I might as well leave you with a few tips
	//Use CTRL-I to generate any essential subroutines required to implement the current class
	//Use CTRL-Q after clicking on a keyword to read up on what it does
	//If you ever reference a class that hasn't been imported, use ALT-ENTER to generate the import call
	//Each screen will run create(), resize() and then render() in that order
}
