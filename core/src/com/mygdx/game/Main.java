package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	private Game game;
	//Allows screens to refer to this core class, enabling independent switching

	SpriteBatch batch;
	//Stores the batch of visual elements to be rendered

	public Main() {
		game = this;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();

		setScreen(new SplashScreen(game));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
