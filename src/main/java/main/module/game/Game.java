package main.module.game;


import com.sun.tools.internal.ws.processor.util.DirectoryUtil;
import main.module.game.halloffame.HallOfFame;
import main.module.game.level.Level;
import main.module.game.player.Player;
import sun.rmi.rmic.iiop.DirectoryLoader;

import java.io.InputStreamReader;
import java.util.Queue;

public class Game {
	private Player player;
	private HallOfFame hallOfFame;
	private Level level;
	private Queue<InputStreamReader> levels;
	private boolean on;

	public Game (String playerName) {
		this.hallOfFame = new HallOfFame();
		this.player = new Player(playerName);
		this.loadLevelNames();
		this.level = new Level(levels.poll());
		this.on = true;
	}

	public void loadLevelNames () {

		//TODO load level names from the directory level/
	}

	public void nextLevel() {
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


