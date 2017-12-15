package main.module.game.level.factory.map;

import main.util.geometry.Position;

import java.io.InputStreamReader;

public class Map {

	public Map(InputStreamReader isr) {

	}

	public Tile getTile(Position pos) {
		return new Tile();
	}
}
