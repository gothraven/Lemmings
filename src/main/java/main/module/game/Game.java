package main.module.game;


import main.module.game.halloffame.HallOfFame;
import main.module.game.level.Level;
import main.module.game.player.Player;
import main.util.event.Event;
import main.util.observebale.Observable;
import main.util.observer.Observer;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;

public class Game implements Observable {

	private ArrayList<Observer> gameObservers;
	private Player player;
	private HallOfFame hallOfFame;
	private Level level;
	private Queue<InputStreamReader> levels;
	private boolean on;

	public Game (String playerName) {
		this.hallOfFame = new HallOfFame();
		this.player = new Player(playerName);
		this.loadLevelNames();
		this.gameObservers = new ArrayList<Observer>();
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
}


