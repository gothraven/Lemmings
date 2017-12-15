package main.module.event.factory;

import main.module.event.factory.game.GameEvent;
import main.module.event.factory.level.LevelEvent;
import main.module.game.level.LevelInfo;
import main.module.game.level.factory.lemming.Lemming;
import main.module.game.level.factory.map.Map;
import main.util.event.Event;

import java.util.ArrayList;

public class EventFactory {

	//TODO fix params
	public static Event createEvent() {
		return new GameEvent();
	}

	public static Event createEvent(LevelInfo levelInfo, ArrayList<Lemming> lemmings, Map map) {
		return new LevelEvent(levelInfo, lemmings, map);
	}
}
