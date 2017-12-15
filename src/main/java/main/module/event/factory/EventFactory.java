package main.module.event.factory;

import main.module.event.factory.game.GameEvent;
import main.module.event.factory.level.LevelEvent;
import main.util.event.Event;

public class EventFactory {

	//TODO fix params
	public static Event createEvent(boolean c) {
		return new GameEvent();
	}

	//TODO fix params
	public static Event createEvent(int a) {
		return new LevelEvent();
	}
}
