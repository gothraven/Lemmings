package main.util.observebale;

import main.util.event.Event;
import main.util.observer.Observer;

public interface Observable {
	void registerObserver(Observer o);
	void unregisterObserver(Observer o);
	void notifyObeservers(Event e);
}
