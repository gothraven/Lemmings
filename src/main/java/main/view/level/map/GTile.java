package main.view.level.map;

import main.module.game.level.map.Tile;
import main.view.level.GamePanel;

import java.awt.*;

public class GTile {

	public static void draw(Tile tile, Graphics graphics) {
		graphics.setColor(Color.CYAN);
		graphics.fillRect(tile.getPosition().getX() * GamePanel.SCALE,
				tile.getPosition().getY() * GamePanel.SCALE,
				GamePanel.SCALE,
				GamePanel.SCALE);
	}

}
