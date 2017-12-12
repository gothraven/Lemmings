package main.module.game;


import main.module.game.halloffame.HallOfFame;
import main.module.game.level.Level;
import main.module.game.player.Player;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Stack;

public class Game {
	private Player player;
	private HallOfFame hallOfFame;
	private Level level;
	private Queue<InputStreamReader> levels;
	private boolean on;

	public Game (String playerName) {
		this.player = new Player(playerName);
		this.hallOfFame = new HallOfFame();
		this.loadLevelNames();
		this.level = new Level(levels.poll());
		this.on = true;
	}

	public void loadLevelNames () {
		//TODO load level names from the directory level/
	}

	public void nextLevel(){
		if (! levels.isEmpty())
			this.level = new Level(levels.poll());
		else
			this.on = false;
	}

	public void update () {
		level.update();
	}

	public boolean isOn () {
		return on;
	}

	public Level getLevel () {
		return level;
	}

	public Player getPlayer () {
		return player;
	}
}


