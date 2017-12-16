package main.module.game.level.factory.lemming;

import main.module.game.level.factory.map.Map;
import main.util.geometry.Direction;
import main.util.geometry.Position;
import main.util.power.PowerRules;
import main.module.game.level.factory.lemming.state.State;

public class Lemming {
	public static int MAX_HEIGHT = 5;
	private Position pos;
	private Direction dir;
	private State power;
	private boolean alive;
	private boolean saved;
	private int fallingCounter;

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

	public boolean inGame() {
		return (isAlive() && !isSaved());
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

	public void fall(){
		pos.setX(pos.getX()+Direction.DOWN.getXdir());
		pos.setY(pos.getY()+Direction.DOWN.getYdir());

	}
	public void walke() {
		pos.setX(pos.getX() + dir.getXdir());
		pos.setY(pos.getY() + dir.getYdir());
	}
	public void moveUp() {
		pos.setX(pos.getX() + Direction.UP.getXdir());
		pos.setY(pos.getY() + Direction.UP.getYdir());
	}
	public void jump(){
		Direction next = getDir().upper();
		pos.setX(pos.getX() + next.getXdir());
		pos.setY(pos.getY() + next.getYdir());
	}
	public void oppositDirection(){
		dir = dir.oppositDirection(dir);
	}
	public void restFallingCounter(){ this.fallingCounter = 0; }
	public void fallingCounter(){ this.fallingCounter++; }
	public int getFallingCounter(){ return  this.fallingCounter; }

}