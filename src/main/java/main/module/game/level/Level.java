package main.module.game.level;

import main.module.event.factory.EventFactory;
import main.module.event.factory.level.LevelEvent;
import main.module.game.Game;
import main.module.game.level.factory.LemmingFactory;
import main.module.game.level.factory.MapFactory;
import main.module.game.level.factory.lemming.Lemming;
import main.module.game.level.factory.map.Map;
import main.util.event.Event;
import main.util.exceptions.InvalideFileException;
import main.util.geometry.Position;
import main.util.observebale.Observable;
import main.util.observer.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Level implements Observable {
	private ArrayList<Observer> levelObservers;
	private Game game;
	private LevelInfo info;
	private ArrayList<Lemming> lemmings;
	private Map map;
	private Timer time;
	private int lemShowSpeedCt, gameSpeedCt, lemShowCt;

	public Level (InputStreamReader levelFile) throws InvalideFileException
	{
		this(null, levelFile);
	}

	public Level (Game game, InputStreamReader levelFIle) throws InvalideFileException {
		this.game = game;
		this.map = MapFactory.createMap(levelFIle);
		this.levelObservers = new ArrayList<Observer>();
		this.info = new LevelInfo(levelFIle);
		this.lemmings = new ArrayList<Lemming>();
		this.time = new Timer(LevelInfo.SPEED_SCALE, new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				LevelInfo info = Level.this.info;
				if (info.getGameTime() > 0) {
					info.setGameTime(info.getGameTime() - 1);
					Timer time = (Timer) e.getSource();
					time.restart();
				} else {
					Level.this.info.setWon(false);
					//TODO Level.this.game.end();
				}
			}
		});
		this.lemShowSpeedCt = 0;
		this.gameSpeedCt = 0;
		this.lemShowCt = 0;
	}

	public void update () {
		if (this.info.isEnPause()) {

			if (this.time.isRunning()) {
				this.time.stop();
				LevelEvent event = EventFactory.createEvent(info, lemmings, map);
				notifyObeservers(event);
			}
		} else {
			if (!this.time.isRunning()) {
				this.time.start();
			}
			if (this.gameSpeedCt < (LevelInfo.SPEED_SCALE / this.info.getGameSpeed())) {
				this.gameSpeedCt++;
			} else {
				if (this.lemShowCt < this.info.getNbLemTotal()) {
					if (this.lemShowSpeedCt < (LevelInfo.SPEED_SCALE / this.info.getLimShowSpeed())) {
						this.lemShowSpeedCt++;
					} else {
						Position p = this.map.getEnterPos();
						Lemming walker = LemmingFactory.createLemming(new Position(p));
						this.lemmings.add(walker);

						this.info.setNbLemInGame(this.info.getNbLemInGame() + 1);
						this.lemShowCt++;
						this.lemShowSpeedCt = 0;
					}
				}
				for (Iterator<Lemming> it = this.lemmings.iterator(); it.hasNext();) {
					Lemming lemming = (Lemming) it.next();
					if (!lemming.isAlive()) {
						this.info.setNbLemDead(this.info.getNbLemDead() + 1);
						this.info.setNbLemInGame(this.info.getNbLemInGame() - 1 );
						it.remove();
					} else if (lemming.isSaved()) {
						this.info.setNbLemSaved(this.info.getNbLemSaved() + 1);
						this.info.setNbLemInGame(this.info.getNbLemInGame() - 1);
						it.remove();
					} else if (lemming.isAlive() && !lemming.isSaved()) { //TODO in lemming.ingame
						lemming.move(map);
					}
				}

				if (this.lemShowCt == this.info.getNbLemTotal() & this.info.getNbLemInGame() == 0) {
					if (info.getNbLemSaved() >= info.getNbLemToSave())
						this.info.setWon(true);
					else
						this.info.setWon(false);
					//TODO this.game.end();
				}

				this.gameSpeedCt = 0;
				LevelEvent event = EventFactory.createEvent(info, lemmings, map);
				notifyObeservers(event);
			}
		}
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