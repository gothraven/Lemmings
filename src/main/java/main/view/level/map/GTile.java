package main.view.level.map;

import main.module.game.level.map.Tile;
import main.view.level.GamePanel;

import java.awt.*;

public class GTile {

	public static void draw(Tile tile, Graphics graphics) {
		graphics.setColor(getColor(tile));
		graphics.fillRect(tile.getPosition().getX() * GamePanel.SCALE,
				tile.getPosition().getY() * GamePanel.SCALE,
				GamePanel.SCALE,
				GamePanel.SCALE);
	}

	private static Color getColor (Tile tile) {
		return GTileType.valueOf(tile.getType().name()).getColor();
	}

}
