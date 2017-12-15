package main.module.game.level;

import main.util.event.Event;
import main.module.game.Game;
import main.util.observebale.Observable;
import main.util.observer.Observer;
import main.module.game.level.factory.MapFactory;
import main.module.game.level.factory.map.Map;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class Level implements Observable {
	private ArrayList<Observer> levelObservers;
	private Game game;
	//TODO LevelInfo
	//TODO Lemmings
	private Map map;

	public Level (InputStreamReader levelFile) {
		this(null, levelFile);
	}

	public Level (Game game, InputStreamReader levelFIle) {
		this.game = game;
		this.map = MapFactory.createMap(levelFIle);
		this.levelObservers = new ArrayList<Observer>();
	}

	public void update () {
		//TODO update the level map and lemmings and keep in mind the clock ticks
	}

	public void registerObserver (Observer levelObserver) {
		this.levelObservers.add(levelObserver);
	}

	public void unregisterObserver (Observer levelObserver) {
		int index = this.levelObservers.indexOf(levelObserver);
		if (index >= 0 && index < this.levelObservers.size())
			this.levelObservers.remove(index);
	}

	public void notifyObeservers (Event levelEvent) {
		for (Observer o: this.levelObservers)
			o.update(levelEvent);
	}

}
