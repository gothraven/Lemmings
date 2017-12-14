package main.module.game.level.lemming;

import main.geometry.Direction;
import main.geometry.Position;
import main.module.game.level.lemming.state.Power;
import main.module.game.level.lemming.state.Walker;

public class Lemming {

	private Position pos;
	private Direction dir;
	private Power power;
	private boolean alive;
	private boolean saved;

	public Lemming (Position pos) {
		this(pos, Direction.RIGHT);
	}

	public Lemming (Position pos, Direction dir) {
		this(pos, dir, new Walker());
	}

	public Lemming (Position pos, Direction dir, Power power) {
		this.pos = new Position(pos);
		this.dir = dir;
		this.power = power;
		this.alive = true;
		this.saved = false;
	}

	public void move () {
		this.power.action(this);
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

	public Power getPower () {
		return power;
	}


}
