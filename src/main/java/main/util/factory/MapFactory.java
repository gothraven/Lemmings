package main.util.factory;

import main.module.game.level.map.Map;
import main.module.game.level.map.Tile;
import main.module.game.level.map.TileType;
import main.util.exceptions.InvalideFileException;
import main.util.geometry.Position;

import java.io.File;

public class MapFactory {

	public static Map createMap (File file) {
		if (file != null) {
			try {
				return new Map(file);
			} catch (InvalideFileException e) {
				e.printStackTrace();
			}
		}
		Map map = new Map();
		map.setEnterPos(new Position(0, 0));
		map.setExitPos(new Position(1, 0));
		map.getMap().put(new Position(0, 0), new Tile(TileType.ENTER));
		map.getMap().put(new Position(1, 0), new Tile(TileType.EXIT));
		return map;
	}
}
