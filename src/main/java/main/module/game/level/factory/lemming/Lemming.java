package main.module.game.level.factory.lemming;

import main.module.game.level.factory.map.Map;
import main.util.geometry.Direction;
import main.util.geometry.Position;
import main.module.game.level.factory.lemming.power.PowerRules;
import main.module.game.level.factory.lemming.state.State;

public class Lemming {

	private Position pos;
	private Direction dir;
	private State power;
	private boolean alive;
	private boolean saved;

	public Lemming (Position pos) {
		this(pos, Direction.RIGHT);
	}

	public Lemming (Position pos, Direction dir) {
		this(pos, dir, State.WALKER);
	}

	public Lemming (Position pos, Direction dir, State power) {
		this.pos = new Position(pos);
		this.dir = dir;
		this.power = power;
		this.alive = true;
		this.saved = false;
	}

	public void move (Map map) {
		this.power.action(this, map);
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

	public PowerRules getPower() {
		return power;
	}

}
