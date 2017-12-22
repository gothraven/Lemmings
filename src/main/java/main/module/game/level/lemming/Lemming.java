package main.module.game.level.lemming;

import main.module.game.level.lemming.state.State;
import main.module.game.level.map.Map;
import main.module.game.level.map.Tile;
import main.module.game.level.map.TileType;
import main.util.exceptions.TileAlreadyExistsException;
import main.util.geometry.Direction;
import main.util.geometry.Position;

import java.util.ArrayList;

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
		this(pos, dir, State.BOMBER);
	}

	public Lemming (Position pos, Direction dir, State power) {
		this.pos = new Position(pos);
		this.dir = dir;
		this.power = power;
		this.alive = true;
		this.saved = false;
	}

	public void update (Map map, ArrayList<Lemming> lems) {
		this.power.action(this, map, lems);
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

	public State getPower () {
		return power;
	}

	public boolean fall (Map map) {
		Tile t = map.getTile(new Position(Direction.DOWN.WhatIsNextPosition(this.pos)));
		if (t == null || t.getType().canBeIn()) {
			pos = Direction.DOWN.WhatIsNextPosition(pos);
			fallingCounter++;
			if (fallingCounter > MAX_HEIGHT)
				kill();
			return true;
		}
		return false;
	}

	public boolean walk (Map map, ArrayList<Lemming> lems) {
		Tile tDirection = map.getTileInThisDirection(pos, dir);
		if ((tDirection == null || tDirection.getType().canBeIn()) && !blockerInTheWay(lems)) {
			pos = dir.WhatIsNextPosition(pos);
			fallingCounter = 0;
			return true;
		}
		return false;
	}

	private boolean blockerInTheWay(ArrayList<Lemming> lems) {
		Position p = dir.WhatIsNextPosition(pos);
		for(Lemming l : lems)
			if (l.pos.equals(p) && l.power == State.BLOCKER)
				return true;
		return false;
	}

	public boolean jump (Map map) {
		Tile upperNextTile = map.getTile(dir.upper().WhatIsNextPosition(pos));
		Tile upperTile = map.getTile(Direction.UP.WhatIsNextPosition(pos));
		if ((upperNextTile == null || upperNextTile.getType().canBeIn()) && upperTile == null) {
			pos = dir.upper().WhatIsNextPosition(pos);
			return true;
		}
		return false;
	}
	public boolean climb (Map map) {

		Tile upperTile = map.getTile(Direction.UP.WhatIsNextPosition(pos));
		if ( upperTile == null) {
			pos = Direction.UP.WhatIsNextPosition(pos);
			return true;
		}
		return false;
	}
	public boolean fly (Map map) {
			Tile t = map.getTile(new Position(Direction.DOWN.WhatIsNextPosition(this.pos)));
			if (t == null || t.getType().canBeIn()) {
				pos = Direction.DOWN.WhatIsNextPosition(pos);
				return true;
			}
			return false;
		}


	public void oppositDirection(){
		dir = dir.oppositDirection(dir);
	}

	public void resetFallingCounter () {
		this.fallingCounter = 0;
	}
	public void fallingCounter(){ this.fallingCounter++; }
	public int getFallingCounter(){ return  this.fallingCounter; }

	public void setPos (Position pos) {
		this.pos = pos;
	}

	public void setDir (Direction dir) {
		this.dir = dir;
	}
	public boolean build(Map map) throws TileAlreadyExistsException {
		Tile tDirection = map.getTileInThisDirection(pos, dir);
		if(tDirection == null){
			map.addTile(new Position(dir.WhatIsNextPosition(pos)), TileType.BOX);
			return true;
		}
	return false;
	}
}