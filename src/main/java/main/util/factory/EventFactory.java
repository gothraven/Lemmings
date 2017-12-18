package main.util.factory;

import main.module.event.game.GameEvent;
import main.module.event.level.LevelEvent;
import main.module.game.level.LevelInfo;
import main.module.game.level.lemming.Lemming;
import main.module.game.level.map.Map;
import main.util.observebale.Observable;

import java.util.ArrayList;

public class EventFactory {
	public static int LEVELSTART = 1;
	public static int GAMEEND = 2;

	public static GameEvent createEvent(Observable observable, int eventID) {
		GameEvent event = null;

		if (eventID == LEVELSTART)
			event = GameEvent.LEVELSTART;
		else if (eventID == GAMEEND)
			event = GameEvent.GAMEEND;

		if (event != null)
			event.setObservable(observable);

		return event;
	}

	public static LevelEvent createEvent(LevelInfo levelInfo, ArrayList<Lemming> lemmings, Map map) {
		return new LevelEvent(levelInfo, lemmings, map);
	}
}
