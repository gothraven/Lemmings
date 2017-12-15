package main.module.game.level.lemming;

import main.geometry.Direction;
import main.geometry.Position;
import main.module.game.level.lemming.power.PowerRules;
import main.module.game.level.lemming.state.State;

public class Lemming {

	private Position pos;
	private Direction dir;
	private PowerRules powerRules;
	private boolean alive;
	private boolean saved;

	public Lemming (Position pos) {
		this(pos, Direction.RIGHT);
	}

	public Lemming (Position pos, Direction dir) {
		this(pos, dir, State.WALKER);
	}

	public Lemming (Position pos, Direction dir, PowerRules powerRules) {
		this.pos = new Position(pos);
		this.dir = dir;
		this.powerRules = powerRules;
		this.alive = true;
		this.saved = false;
	}

	public void move () {
		this.powerRules.action(this);
	}

	public void changePower (State state) {
		//TODO implement the change
	}

	public boolean isAlive () {
		return alive;
	}

	public boolean isSaved () {
		return saved;
	}

	public void kill () {
		this.alive = false;
	}

	public void save () {
		this.saved = true;
	}

	public Direction getDir () {
		return dir;
	}

	public Position getPos () {
		return pos;
	}

	public PowerRules getPowerRules () {
		return powerRules;
	}

}
