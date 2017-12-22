package main.module.game.level.lemming;

import main.module.game.level.lemming.power.Power;
import main.module.game.level.map.Map;
import main.module.game.level.map.Tile;
import main.module.game.level.map.TileType;
import main.util.exceptions.TileAlreadyExistsException;
import main.util.geometry.Direction;
import main.util.geometry.Position;

import java.util.ArrayList;

public class Lemming {
	private Position pos;
	private Direction dir;
	private Power power;
	private boolean alive;
	private boolean saved;
	private int fallingCounter;
	private int powerTimeCounter = 0;


	public Lemming (Position pos) {
		this(pos, Direction.RIGHT);
	}

	public Lemming (Position pos, Direction dir) {
		this(pos, dir, Power.WALKER);
	}

	public Lemming (Position pos, Direction dir, Power power) {
		this.pos = new Position(pos);
		this.dir = dir;
		this.power = power;
		this.alive = true;
		this.saved = false;
	}

	public void update (Map map, ArrayList<Lemming> lems) {
		this.power.action(this, map, lems);
	}

	public boolean changePower (Power power) {
		if (this.power == power)
			return false;
		if (this.power == Power.BLOCKER & power == Power.BOMBER) {
			this.power = power;
			powerTimeCounter = 0;
			return true;
		}
		if (this.power != Power.BLOCKER && this.power != Power.BOMBER) {
			this.power = power;
			powerTimeCounter = 0;
			return true;
		}
		return false;
	}

	public boolean fall (Map map) {
		Tile t = map.getTile(new Position(Direction.DOWN.WhatIsNextPosition(this.pos)));
		if (t == null || t.getType().canBeIn()) {
			pos = Direction.DOWN.WhatIsNextPosition(pos);
			fallingCounter++;
			int MAX_HEIGHT = 5;
			if (fallingCounter > MAX_HEIGHT)
				kill();
			return true;
		}
		return false;
	}
	public boolean walk (Map map, ArrayList<Lemming> lems) {
		Tile tDirection = map.getTileInThisDirection(pos, dir);
		if ((tDirection == null || tDirection.getType().canBeIn()) && blockerInTheWay(lems)) {
			pos = dir.WhatIsNextPosition(pos);
			fallingCounter = 0;
			return true;
		}
		return false;
	}

	public boolean build (Map map) throws TileAlreadyExistsException {
		Tile tDirection = map.getTileInThisDirection(pos, dir);
		if (tDirection == null) {
			map.addTile(new Position(dir.WhatIsNextPosition(pos)), TileType.BOX);
			return true;
		}
		return false;
	}

	private boolean blockerInTheWay (ArrayList<Lemming> lems) {
		Position p = dir.WhatIsNextPosition(pos);
		for (Lemming l : lems)
			if (l.pos.equals(p) && l.power == Power.BLOCKER)
				return false;
		return true;
	}

	public boolean jump (Map map, ArrayList<Lemming> lems) {
		Tile upperNextTile = map.getTile(dir.upper().WhatIsNextPosition(pos));
		Tile upperTile = map.getTile(Direction.UP.WhatIsNextPosition(pos));
		if (((upperNextTile == null || upperNextTile.getType().canBeIn()) && upperTile == null) && blockerInTheWay(lems)) {
			pos = dir.upper().WhatIsNextPosition(pos);
			return true;
		}
		return false;
	}

	public boolean climb (Map map) {
		Tile upperTile = map.getTile(Direction.UP.WhatIsNextPosition(pos));
		if (upperTile == null || upperTile.getType() == TileType.EXIT) {
			pos = Direction.UP.WhatIsNextPosition(pos);
			return true;
		}
		return false;
	}

	public void fly (Map map) {
		Tile t = map.getTile(new Position(Direction.DOWN.WhatIsNextPosition(this.pos)));
		if (t == null || t.getType().canBeIn()) {
			pos = Direction.DOWN.WhatIsNextPosition(pos);
		}
	}

	public void oppositDirection () {
		dir = dir.oppositDirection(dir);
	}

	public int getPowerTimeCounter () {
		return powerTimeCounter;
	}

	public void setCountStepAfterChangePower () {
		this.powerTimeCounter++;
	}

	public void resetCountStepAfterChangePower () {
		this.powerTimeCounter = 0;
	}

	public void setPos (Position pos) {
		this.pos = pos;
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

	public boolean inGame () {
		return (isAlive() && ! isSaved());
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