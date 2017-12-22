package main.view.level.map;

import main.module.game.level.map.Map;
import main.module.game.level.map.Tile;
import main.util.geometry.Position;

import java.awt.*;
import java.util.Set;

public class GMap {

	public static void draw(Map map, Graphics g) {
		Set<Position> positions = map.getMap().keySet();
		for (Position position : positions) {
			Tile t = map.getMap().get(position);
			if (t != null)
				GTile.draw(t, g);
		}
	}

}
