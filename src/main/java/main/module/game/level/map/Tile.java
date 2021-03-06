package main.module.game.level.map;

import main.module.game.level.lemming.Lemming;
import main.util.geometry.Position;

import java.util.ArrayList;

public class Tile {

	private TileType type;
	private TileAction action;
	private Position position;

	public Tile(TileType type) {
		this(type, null);
	}

	Tile (TileType type, Position position) {
		this.position = new Position(position);
		this.type = type;
		this.action = type.getAction();
	}

	@Override
	public boolean equals (Object obj) {
		return obj != null && obj.getClass() == this.getClass() && ((Tile) obj).type.equals(type);
	}

	public void action (Lemming lemming, Map map, ArrayList<Lemming> lems) {
		this.action.action(lemming, map, lems);
	}

	public TileType getType () {
		return type;
	}

	public Position getPosition () {
		return position;
	}

	public void setPosition (Position position) {
		this.position = position;
	}

	@Override
	public String toString () {
		return "type :" + type;
	}
}
