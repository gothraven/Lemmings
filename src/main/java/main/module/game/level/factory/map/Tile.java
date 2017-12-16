package main.module.game.level.factory.map;

import main.module.game.level.factory.lemming.Lemming;

import java.util.ArrayList;

public class Tile {

	private TileType type;
	private TileAction action;

	public Tile(TileType type) {
		this.type = type;
		this.action = type.getAction();
	}

	@Override
	public boolean equals (Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		return ((Tile)obj).type.equals(type);
	}

	public void action (Lemming lemming, Map map, ArrayList<Lemming> lems) {
		this.action.action(lemming, map, lems);
	}

	public TileType getType () {
		return type;
	}

	@Override
	public String toString () {
		return "type :" + type;
	}
}
