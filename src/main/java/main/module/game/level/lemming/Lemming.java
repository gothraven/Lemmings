package main.module.game.level.lemming;

import main.geometry.Direction;
import main.geometry.Position;
import main.module.game.level.lemming.state.Power;

public class Lemming {

	private Position pos;
	private Direction dir;
	private Power power;

	public Lemming(Position pos, Direction dir, Power power){
		this.pos = new Position(pos);
		this.dir = dir;
	}

}
