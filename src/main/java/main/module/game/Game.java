package main.module.game;


import main.module.game.halloffame.HallOfFame;
import main.module.game.level.Level;
import main.module.game.levelfactory.LevelFactory;
import main.module.game.player.Player;
import main.util.event.Event;
import main.util.observebale.Observable;
import main.util.observer.Observer;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game implements Observable {

	public static String LEVELS_DIR = "levels/";

	private ArrayList<Observer> gameObservers;
	private Player player;
	private HallOfFame hallOfFame;
	private Level level;
	private LinkedList<InputStreamReader> levels;
	private boolean on;

	public Game (String playerName) {
		this.hallOfFame = new HallOfFame();
		this.player = new Player(playerName);
		this.levels = new LinkedList<InputStreamReader>();
		this.loadLevelNames();
		this.gameObservers = new ArrayList<Observer>();
		this.level = LevelFactory.createLevel(this, levels.poll());
		this.on = true;
	}

	public void loadLevelNames () {
		File dir = null;
		try {
			dir = new File(ClassLoader.getSystemResource(LEVELS_DIR).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		File[] levelFiles = dir.listFiles();
		for(File file: levelFiles)
			levels.add(new InputStreamReader(ClassLoader.getSystemResourceAsStream(LEVELS_DIR+file.getName())));
	}

	public void registerObserver (Observer gameObserver) {
		this.gameObservers.add(gameObserver);
		this.level.registerObserver(gameObserver);
	}

	public void unregisterObserver (Observer gameObserver) {
		int index = this.gameObservers.indexOf(gameObserver);
		if (index >= 0 && index < this.gameObservers.size())
			this.gameObservers.remove(index);
	}

	public void notifyObeservers (Event gameEvent) {
		for(Observer o: this.gameObservers)
			o.update(gameEvent);
	}

	public void nextLevel() {
		if (! levels.isEmpty()) {
			this.level = LevelFactory.createLevel(this, levels.poll());
		}
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


