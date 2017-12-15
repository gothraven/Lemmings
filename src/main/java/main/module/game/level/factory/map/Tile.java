package main.module.game.level.factory.map;

import main.module.game.level.factory.lemming.Lemming;

public class Tile {

	private TileType type;
	private TileAction action;

	public Tile(TileType type) {
		this.type = type;
		this.action = type.getAction();
	}

	public void action(Lemming lemming, Map map) {
		this.action.action(lemming, map);
	}

	public TileType getType () {
		return type;
	}
}
