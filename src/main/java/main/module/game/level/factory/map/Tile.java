package main.module.game.level.factory.map;

public class Tile {

	private TileType type;

	public Tile(TileType type) {
		this.type = type;
	}

	public TileType getType () {
		return type;
	}
}
