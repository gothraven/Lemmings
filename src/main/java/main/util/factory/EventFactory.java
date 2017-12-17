package main.util.factory;

import main.module.event.game.GameEvent;
import main.module.event.level.LevelEvent;
import main.module.game.halloffame.HallOfFame;
import main.module.game.level.Level;
import main.module.game.level.LevelInfo;
import main.module.game.level.lemming.Lemming;
import main.module.game.level.map.Map;

import java.util.ArrayList;

public class EventFactory {

	//TODO fix params
	public static GameEvent createEvent(HallOfFame hallOfFame, Level level) {
		return new GameEvent();
	}

	public static LevelEvent createEvent(LevelInfo levelInfo, ArrayList<Lemming> lemmings, Map map) {
		return new LevelEvent(levelInfo, lemmings, map);
	}
}
