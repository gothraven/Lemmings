package main.module.event.factory.level;

import main.module.game.level.LevelInfo;
import main.module.game.level.factory.lemming.Lemming;
import main.module.game.level.factory.map.Map;
import main.util.event.Event;

import java.util.ArrayList;

public class LevelEvent implements Event {

	private Map map;
	private LevelInfo levelInfo;
	private ArrayList<Lemming> lemmings;
	
	public LevelEvent(LevelInfo levelInfo, ArrayList<Lemming> lemmings, Map map) {
		this.levelInfo = levelInfo;
		this.lemmings = lemmings;
		this.map = map;
	}

	public ArrayList<Lemming> getLemmings () {
		return lemmings;
	}

	public LevelInfo getLevelInfo () {
		return levelInfo;
	}

	public Map getMap () {
		return map;
	}

}
