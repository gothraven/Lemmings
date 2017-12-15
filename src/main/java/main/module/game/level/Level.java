package main.module.game.level;

import main.module.game.Game;

import java.io.InputStreamReader;

public class Level {

	private Game game;
	//TODO LevelInfo
	//TODO Lemmings
	//TODO Map

	public Level (InputStreamReader levelFile) {
		this(null, levelFile);
	}

	public Level (Game game, InputStreamReader levelFIle) {
		this.game = game;
	}

	public void update () {
		//update the level map and lemmings and keep in mind the clock ticks
	}

}
