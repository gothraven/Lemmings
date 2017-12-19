package main.module.game;


import main.module.event.game.GameEvent;
import main.module.game.halloffame.HallOfFame;
import main.module.game.level.Level;
import main.util.factory.EventFactory;
import main.util.factory.LevelFactory;
import main.module.game.player.Player;
import main.util.event.Event;
import main.util.observebale.Observable;
import main.util.observer.Observer;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Game implements Observable {

	private ArrayList<Observer> gameObservers;
	private Player player;
	private HallOfFame hallOfFame;
	private Level level;
	private LinkedList<File> levels;
	private boolean on;

	public Game (String playerName) {
		this.hallOfFame = new HallOfFame();
		this.player = new Player(playerName);
		this.levels = new LinkedList<>();
		this.loadLevelNames();
		this.gameObservers = new ArrayList<>();
		this.on = true;
	}

	private void loadLevelNames () {
		File dir = null;
		try {
			String LEVELS_DIR = "levels/";
			dir = new File(ClassLoader.getSystemResource(LEVELS_DIR).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		File[] levelFiles = dir.listFiles();
		if (levelFiles != null)
			levels.addAll(Arrays.asList(levelFiles));
	}

	public void start () {
		if (!levels.isEmpty()) {
			this.level = LevelFactory.createLevel(this, levels.poll());
			GameEvent event = EventFactory.createEvent(level, EventFactory.LEVELSTART);
			notifyObeservers(event);
		} else {
			this.end();
		}
	}

	public void registerObserver (Observer gameObserver) {
		this.gameObservers.add(gameObserver);
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
			GameEvent event = EventFactory.createEvent(level, EventFactory.LEVELSTART);
			notifyObeservers(event);
		}
		else
			this.gameOver();
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

	public void gameOver() {
		//TODO this.hallOfFame.addPlayer(this.player);
		System.out.println(player);
		this.end();
	}

	public void end () {
		this.on = false;
		GameEvent event = EventFactory.createEvent(this, EventFactory.GAMEEND);
		notifyObeservers(event);
	}
}


