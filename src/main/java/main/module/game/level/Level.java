package main.module.game.level;

import main.module.event.level.LevelEvent;
import main.module.game.Game;
import main.module.game.level.lemming.Lemming;
import main.module.game.level.map.Map;
import main.util.event.Event;
import main.util.exceptions.InvalideFileException;
import main.util.factory.EventFactory;
import main.util.factory.LemmingFactory;
import main.util.factory.MapFactory;
import main.util.geometry.Position;
import main.util.observebale.Observable;
import main.util.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Level implements Observable {
	private ArrayList<Observer> levelObservers;
	private Game game;
	private LevelInfo info;
	private ArrayList<Lemming> lemmings;
	private Map map;
	private Dimension mapDimensions;
	private Timer timer;
	private int lemShowSpeedCt, gameSpeedCt, lemShowCt;

	public Level (File levelFile) throws InvalideFileException
	{
		this(null, levelFile);
	}

	public Level (Game game, File levelFIle) throws InvalideFileException {
		this.game = game;
		this.map = MapFactory.createMap(levelFIle);
		this.mapDimensions = new Dimension(map.getMaxAxes());
		this.levelObservers = new ArrayList<>();
		this.info = new LevelInfo(levelFIle);
		this.lemmings = new ArrayList<>();
		this.timer = new Timer(LevelInfo.SPEED_SCALE, e -> {
			LevelInfo info = Level.this.info;
			if (info.getGameTime() > 0) {
				info.setGameTime(info.getGameTime() - 1);
				Timer time = (Timer) e.getSource();
				time.restart();
			} else {
				Level.this.info.setWon(false);
				this.game.getPlayer().scoreUP(this.info.getNbLemSaved(), this.info.getGameTime());
				this.game.gameOver();
			}
		});
		this.lemShowSpeedCt = 0;
		this.gameSpeedCt = 0;
		this.lemShowCt = 0;
	}

	public void update () {
		if (this.info.isEnPause()) {

			if (this.timer.isRunning()) {
				this.timer.stop();
				LevelEvent event = EventFactory.createEvent(info, lemmings, map);
				notifyObeservers(event);
			}
		} else {
			if (!this.timer.isRunning()) {
				this.timer.start();
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
					Lemming lemming = it.next();
					if (!lemming.isAlive()) {
						this.info.setNbLemDead(this.info.getNbLemDead() + 1);
						this.info.setNbLemInGame(this.info.getNbLemInGame() - 1 );
						it.remove();
					} else if (lemming.isSaved()) {
						this.info.setNbLemSaved(this.info.getNbLemSaved() + 1);
						this.info.setNbLemInGame(this.info.getNbLemInGame() - 1);
						it.remove();
					} else if (lemming.inGame()) {
						lemming.update(map, lemmings);
					}
				}

				if (this.lemShowCt == this.info.getNbLemTotal() & this.info.getNbLemInGame() == 0) {
					this.game.getPlayer().scoreUP(this.info.getNbLemSaved(), this.info.getGameTime());
					if (info.getNbLemSaved() >= info.getNbLemToSave()) {
						this.info.setWon(true);
						this.game.nextLevel();
					} else {
						this.info.setWon(false);
						this.game.gameOver();
					}
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

	public ArrayList<Lemming> getLemmings () {
		return lemmings;
	}

	public int getGameSpeedCt () {
		return gameSpeedCt;
	}

	public int getLemShowCt () {
		return lemShowCt;
	}

	public int getLemShowSpeedCt () {
		return lemShowSpeedCt;
	}

	public LevelInfo getInfo () {
		return info;
	}

	public Map getMap () {
		return map;
	}

	public Timer getTimer () {
		return timer;
	}

	public Dimension getMapDimensions () {
		return mapDimensions;
	}
}