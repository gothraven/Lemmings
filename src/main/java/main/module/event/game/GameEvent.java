package main.module.event.game;

import main.util.event.Event;
import main.util.observebale.Observable;

public enum GameEvent implements Event {
	LEVELSTART(1), GAMEEND(2);

	private Observable observable;
	private int iD;

	GameEvent(int iD) {
		this.iD = iD;
	}

	public void setObservable (Observable observable) {
		this.observable = observable;
	}

	public Observable getObservable () {
		return observable;
	}

	public int getID () {
		return iD;
	}
}
