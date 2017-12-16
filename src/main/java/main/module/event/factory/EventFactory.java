package main.module.event.factory;

import main.module.event.factory.game.GameEvent;
import main.module.event.factory.level.LevelEvent;
import main.module.game.halloffame.HallOfFame;
import main.module.game.level.Level;
import main.module.game.level.LevelInfo;
import main.module.game.level.factory.lemming.Lemming;
import main.module.game.level.factory.map.Map;

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
